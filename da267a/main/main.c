#include "driver/gpio.h"
#include "esp_rom_sys.h"
#include "freertos/idf_additions.h"
#include "freertos/projdefs.h"
#include "hal/gpio_types.h"
#include "portmacro.h"
#include <accel.h>
#include <circular_buffer.h>
#include <esp_pm.h>
#include <esp_task_wdt.h>
#include <esp_timer.h>
#include <i2c.h>
#include <math.h>
#include <stdint.h>
#include <stdio.h>
#include <sys/types.h>
// the reason why I have chosen this sampling frequency is because .....
#define SAMPLING_PERIOD 100

// I have chosen to run the algorithm every YYY because ....
#define ALGO_PERIOD 1000

// number of samples to he held inside the buffer: this is because ...
#define BUFFER_SIZE 10

#define SAMPLING_PRIO 5

#define ALGO_PRIO 1

// minimum SD to avoid converging to 0
#define MIN_SD 300

#define STEPGOAL 6


// constant applied to SD to detect steps
#define K 1.5f

// minimum time between steps, this value is chosen because...
#define MIN_INTRA_STEP_TIME 150

int step_count = 0;

volatile uint64_t lastpress;

uint64_t last_step = 0;

struct circularBuffer buffer;

SemaphoreHandle_t xSemaphore = NULL; 

static void algo_task(void *args) {
  TickType_t xLastWakeTime = xTaskGetTickCount();
  while (1) {
    int size = length(&buffer);
    if (size > 0) {
      int tot = 0;
      for (int i = 0; i < size; i++) {

        tot += get(&buffer, i);
      }
      int mean = tot / size;
      tot = 0;
      for (int i = 0; i < size; i++) {
        tot += pow(get(&buffer, i) - mean, 2);
      }
      int sd = sqrt(tot / size);

      if (sd < MIN_SD)
        sd = MIN_SD;

      for (int i = 0; i < size; i++) {
        int s = removeHead(&buffer);
        if (s > mean + K * sd &&
            (esp_timer_get_time() - last_step)/1000 > MIN_INTRA_STEP_TIME) {
					printf("i:%d\n", i);
          step_count++;
          last_step = esp_timer_get_time();
        }
      }
    }
    vTaskDelayUntil(&xLastWakeTime, pdMS_TO_TICKS(ALGO_PERIOD));
  }
}

static void sampling_task(void *args) {
  TickType_t xLastWakeTime = xTaskGetTickCount();
  while (true) {

    uint16_t r;
    int x = get_accel_x();
    int y = get_accel_y();
    int z = get_accel_z();
    r = calc_accel_vector();
    addElement(&buffer, r);
    printf("vector: %6d x: %6d y: %6d z: %6d stepcount: %3d\n", r, x, y, z,
           step_count);
    vTaskDelayUntil(&xLastWakeTime, pdMS_TO_TICKS(SAMPLING_PERIOD));
  }
}

static void button_handler(void *args){
	gpio_intr_disable(27);
	uint64_t time = esp_timer_get_time();
	if((time-lastpress)>200000){
		lastpress = time;
		xSemaphoreGiveFromISR(xSemaphore, NULL);
		
	}
	gpio_intr_enable(27);
}

void button_task(void *args){
	while(1){
		if(xSemaphoreTake(xSemaphore, portMAX_DELAY) == pdTRUE){
			if(step_count < STEPGOAL){
				for(int i = 0; i < 10; i++){
					gpio_set_level(26, 1);
					vTaskDelay(pdMS_TO_TICKS(100));
					gpio_set_level(26, 0);
					vTaskDelay(pdMS_TO_TICKS(200));
				}
			}else{
				for(int i = 0; i < 10; i++){
					gpio_set_level(26, 1);
					vTaskDelay(pdMS_TO_TICKS(500));
					gpio_set_level(26, 0);
					vTaskDelay(pdMS_TO_TICKS(100));
				}
			}
		}
	}
}

void app_main(void) {
  esp_pm_config_t pm;
  pm.light_sleep_enable = 1;
  pm.max_freq_mhz = 240;
  pm.min_freq_mhz = 80;

  esp_pm_configure(&pm);

	gpio_config_t config;
	config.pin_bit_mask = (u_int64_t)1 << 26;
	config.mode = GPIO_MODE_OUTPUT;
	config.pull_down_en = 0;
	config.pull_up_en = 0;
	gpio_config(&config);

	gpio_config_t button;
	button.pin_bit_mask = (uint64_t)1 << 27;
	button.mode = GPIO_MODE_INPUT;
	button.pull_up_en = 1;
	button.pull_down_en = 0;
	button.intr_type = GPIO_INTR_NEGEDGE;
	gpio_config(&button);
	
	gpio_install_isr_service(ESP_INTR_FLAG_EDGE);
	gpio_isr_handler_add(27, button_handler, NULL);

	xSemaphore = xSemaphoreCreateBinary();

  initI2C(33, 32);
  initMPU();

  int *buffer_data = (int *)malloc(BUFFER_SIZE * sizeof(int));
  initCircularBuffer(&buffer, buffer_data, BUFFER_SIZE);

  xTaskCreate(*algo_task, "algo_task", 2048, NULL, ALGO_PRIO, NULL);
  xTaskCreate(*sampling_task, "sampling_task", 2048, NULL, SAMPLING_PRIO, NULL);
	xTaskCreate(*button_task, "button_task", 2048, NULL, 10, NULL);
} 
