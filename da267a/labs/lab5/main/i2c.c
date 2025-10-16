#include "i2c.h"
#include "esp_err.h"
#include "freertos/projdefs.h"
#include "hal/gpio_types.h"
#include "hal/i2c_types.h"
#include <driver/i2c.h>
#include <stdint.h>

void initI2C(int sdapin, int sclpin) {
  i2c_config_t conf;
  conf.mode = I2C_MODE_MASTER;
  conf.sda_io_num = sdapin;
  conf.sda_pullup_en = GPIO_PULLUP_ENABLE;
  conf.scl_io_num = sclpin;
  conf.scl_pullup_en = GPIO_PULLUP_ENABLE;
  conf.master.clk_speed = 100000;
	conf.clk_flags = 0;

  esp_err_t res = i2c_param_config(I2C_NUM_0, &conf);
  ESP_ERROR_CHECK(res);

  res = i2c_driver_install(I2C_NUM_0, conf.mode, 0, 0, 0);

  ESP_ERROR_CHECK(res);
}

void writeI2C(uint8_t address, uint8_t reg, uint8_t data) {
  i2c_cmd_handle_t cmd = i2c_cmd_link_create();
  esp_err_t res = i2c_master_start(cmd);
  ESP_ERROR_CHECK(res);
  res = i2c_master_write_byte(cmd, address << 1 | I2C_MASTER_WRITE,
                              1); // WRITE bit set!
  ESP_ERROR_CHECK(res);
  res = i2c_master_write_byte(cmd, reg, 1);
  ESP_ERROR_CHECK(res);
  res = i2c_master_write_byte(cmd, data, 1);
  ESP_ERROR_CHECK(res);
  res = i2c_master_stop(cmd);
  ESP_ERROR_CHECK(res);
  res = i2c_master_cmd_begin(I2C_NUM_0, cmd, 1000 / portTICK_PERIOD_MS);
  ESP_ERROR_CHECK(res);
  i2c_cmd_link_delete(cmd);
}

void readI2C(uint8_t address, uint8_t reg, uint8_t *buffer, int len) {

  i2c_cmd_handle_t cmd = i2c_cmd_link_create();
  esp_err_t res = i2c_master_start(cmd);
  ESP_ERROR_CHECK(res);
  res = i2c_master_write_byte(cmd, address << 1 | I2C_MASTER_WRITE,
                              1); // WRITE bit set!
  ESP_ERROR_CHECK(res);
  res = i2c_master_write_byte(cmd, reg, 1);
  ESP_ERROR_CHECK(res);
  res = i2c_master_stop(cmd);
  ESP_ERROR_CHECK(res);
  res = i2c_master_cmd_begin(I2C_NUM_0, cmd, 1000 / portTICK_PERIOD_MS);
  ESP_ERROR_CHECK(res);
  i2c_cmd_link_delete(cmd);

  cmd = i2c_cmd_link_create();
  res = i2c_master_start(cmd);

  ESP_ERROR_CHECK(res);


  res = i2c_master_write_byte(cmd, address << 1 | I2C_MASTER_READ,
                              1); // READ bit set!


  res = i2c_master_read(cmd, buffer, len, I2C_MASTER_NACK);
  ESP_ERROR_CHECK(res);
  res = i2c_master_stop(cmd);
  ESP_ERROR_CHECK(res);
  res = i2c_master_cmd_begin(I2C_NUM_0, cmd, 1000 / portTICK_PERIOD_MS);
  i2c_cmd_link_delete(cmd);
}
