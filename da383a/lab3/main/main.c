#include "driver/adc.h"
#include "driver/dac.h"
#include "driver/gpio.h"
#include "esp32/rom/ets_sys.h"
#include "esp_task_wdt.h"
#include "esp_timer.h"
#include "soc/dac_channel.h"

#define M 2

// The sampling freq to be used
int freq = 10000;
static float b[M + 1] = {0.3333, 0.3333, 0.3333};

static float xbuf[M + 1];
// Callback for how often you sample.
static void periodic_timer_callback(void *arg) {
  gpio_set_level(GPIO_NUM_14, 1);

  float val = adc1_get_raw(ADC1_CHANNEL_4);
  val = val / 16;

  for (int i = M; i >= 0; i--) {
    xbuf[i + 1] = xbuf[i];
  }
  xbuf[0] = (float)val;
  float sum = 0;
  for (int i = 0; i < M; i++) {
    sum += b[i] * xbuf[i];
    ;
  }

  uint32_t outval = (uint32_t)sum;
  dac_output_voltage(DAC_CHANNEL_1, outval);
  gpio_set_level(GPIO_NUM_14, 0);
}

void app_main() {
  // init adc and dac
  adc1_config_width(ADC_WIDTH_BIT_12);
  adc1_config_channel_atten(ADC1_CHANNEL_4, ADC_ATTEN_DB_11);
  gpio_pullup_en(GPIO_NUM_32);
  dac_output_enable(DAC_CHANNEL_1);

  gpio_config_t config;
  config.pin_bit_mask = 1 << GPIO_NUM_14;
  config.mode = GPIO_MODE_DEF_OUTPUT;
  config.pull_down_en = GPIO_PULLDOWN_DISABLE;
  config.pull_up_en = GPIO_PULLUP_ENABLE;
  ESP_ERROR_CHECK(gpio_config(&config));

  const esp_timer_create_args_t periodic_timer_args = {
      .callback = &periodic_timer_callback,
      // name is optional, but may help identify the timer when debugging
      .name = "periodic"};

  esp_timer_handle_t periodic_timer;

  for (int i = 0; i < M; i++) {
    xbuf[i] = 0;
    b[i] = 0;
  }

  ESP_ERROR_CHECK(esp_timer_create(&periodic_timer_args, &periodic_timer));
  ESP_ERROR_CHECK(esp_timer_start_periodic(periodic_timer, 1000000 / freq));
}
