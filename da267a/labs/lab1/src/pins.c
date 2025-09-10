#include "pins.h"
#include "driver/gpio.h"

/* initialises the 4 pins */
void initPins()
{
    // init the 2 LEDs pins as output and the 2 buttons' pins as input
    // you will need to use gpio_config()
    gpio_config_t led_config;
    led_config.mode = GPIO_MODE_OUTPUT;
    led_config.pin_bit_mask = (uint64_t)1 << 33;
    led_config.pin_bit_mask |= (uint64_t)1 << 25;
    led_config.pull_down_en = 0;
    led_config.pull_up_en = 0;
    // led_config.pin_bit_mask |= 1 << GPIO_NUM_25;

    esp_err_t result = gpio_config(&led_config);

    if (result != ESP_OK)
    {
        printf("led init failure");
    }

    gpio_config_t button_config;
    button_config.mode = GPIO_MODE_INPUT;
    button_config.pin_bit_mask = (uint64_t)1 << 27;
    button_config.pin_bit_mask |= (uint64_t)1 << 26;
    button_config.pull_down_en = 0;
    button_config.pull_up_en = 1;

    result = gpio_config(&button_config);

    if (result != ESP_OK)
    {
        printf("button init failure");
    }
}

/* switches LED A on if level!=0 or off if level==0*/
void setLEDA(uint8_t level)
{

    if (level)
    {
        // set the pin of LED A to ON
        // you probably need to use gpio_set_level()
        gpio_set_level(GPIO_NUM_33, 1);
    }
    else
    {
        // set the pin of LED A to OFF
        gpio_set_level(GPIO_NUM_33, 0);
    }
}

/* switches LED B on if level!=0 or off if level==0*/
void setLEDB(uint8_t level)
{
    if (level)
    {
        // set the pin of LED A to ON
        // you probably need to use gpio_set_level()
        gpio_set_level(GPIO_NUM_25, 1);
    }
    else
    {
        // set the pin of LED A to OFF
        gpio_set_level(GPIO_NUM_25, 0);
    }
}

/* tells if button A is currently being pressed */
uint8_t isButtonAPressed()
{
    return !gpio_get_level(GPIO_NUM_27);
}

/* tells if button B is currently being pressed */
uint8_t isButtonBPressed()
{
    return !gpio_get_level(GPIO_NUM_26); // return 1 if A is pressed, 0 otherwise
}
