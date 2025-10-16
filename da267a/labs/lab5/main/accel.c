#include "accel.h"
#include "driver/i2c.h"
#include "esp_err.h"
#include "i2c.h"
#include <math.h>
#include <stdint.h>
#include "i2c.h"

#define MPU6050_ACCEL_XOUT_H 0x3B
#define MPU6050_ACCEL_XOUT_L 0x3C

#define MPU6050_ACCEL_YOUT_H 0x3D
#define MPU6050_ACCEL_YOUT_L 0x3E

#define MPU6050_ACCEL_ZOUT_H 0x3F
#define MPU6050_ACCEL_ZOUT_L 0x40

#define MPU6050_ADDR 0x68

#define MPU6050_PWR_MGMT_1 0x6B

#define MPU6050_SMPLRT_DIV 0x19

#define MPU6050_WHO_AM_I 0x75

void initMPU() {

  i2c_cmd_handle_t cmd = i2c_cmd_link_create();

  esp_err_t res = i2c_master_start(cmd);
  ESP_ERROR_CHECK(res);
  // set address + write and check for ack
  res = i2c_master_write_byte(cmd, MPU6050_ADDR << 1 | I2C_MASTER_WRITE, 1);
  ESP_ERROR_CHECK(res);
  // write the register address and check for ack
  res = i2c_master_write_byte(cmd, MPU6050_PWR_MGMT_1, 1);
  ESP_ERROR_CHECK(res);
  // write value of the regiter: 0, and check for ack
  res = i2c_master_write_byte(cmd, 0x00, 1);
  ESP_ERROR_CHECK(res);
  // end of command
  res = i2c_master_stop(cmd);
  ESP_ERROR_CHECK(res);
  // send the command, 1 second timeout
  res = i2c_master_cmd_begin(I2C_NUM_0, cmd, 1000 / portTICK_PERIOD_MS);
  ESP_ERROR_CHECK(res);
  // delete command now that it's not needed
  i2c_cmd_link_delete(cmd);

  // set the sampling frequency
  // the sampling freq is gyro sampling freq / (1 + divider)
  // setting divider to 250 leads to sampling freq. of 32 Hz
  cmd = i2c_cmd_link_create();
  res = i2c_master_start(cmd);
  ESP_ERROR_CHECK(res);
  res = i2c_master_write_byte(cmd, MPU6050_ADDR << 1 | I2C_MASTER_WRITE,
                              1); // WRITE bit set!
  ESP_ERROR_CHECK(res);
  res =
      i2c_master_write_byte(cmd, MPU6050_SMPLRT_DIV, 1); // write to SMPLRT_DIV
  ESP_ERROR_CHECK(res);
  res = i2c_master_write_byte(cmd, 100, 1); // set SMPLRT_DIV to 250
  ESP_ERROR_CHECK(res);
  res = i2c_master_stop(cmd);
  ESP_ERROR_CHECK(res);
  res = i2c_master_cmd_begin(I2C_NUM_0, cmd, 1000 / portTICK_PERIOD_MS);
  ESP_ERROR_CHECK(res);
  i2c_cmd_link_delete(cmd);
}
int16_t get_accel_x(){
	uint8_t buf;
	uint16_t accel = 0;
	readI2C(MPU6050_ADDR, MPU6050_ACCEL_XOUT_L, &buf, 1);
	accel = buf;
	readI2C(MPU6050_ADDR, MPU6050_ACCEL_XOUT_H, &buf, 1);
  accel |= ((uint16_t)buf) << 8;
	return (int16_t)accel;
}
int16_t get_accel_y(){
	uint8_t buf;
	uint16_t accel = 0;
	readI2C(MPU6050_ADDR, MPU6050_ACCEL_YOUT_L, &buf, 1);
	accel = buf;
	readI2C(MPU6050_ADDR, MPU6050_ACCEL_YOUT_H, &buf, 1);
  accel |= ((uint16_t)buf) << 8;
	return (int16_t)accel;
}
int16_t get_accel_z(){
	uint8_t buf;
	uint16_t accel = 0;
	readI2C(MPU6050_ADDR, MPU6050_ACCEL_ZOUT_L, &buf, 1);
	accel = buf;
	readI2C(MPU6050_ADDR, MPU6050_ACCEL_ZOUT_H, &buf, 1);
  accel |= ((uint16_t)buf) << 8;
	return (int16_t)accel;
}

uint16_t calc_accel_vector(){
	int16_t x, y, z;
	x = get_accel_x();
	y = get_accel_y();
	z = get_accel_z();
	return sqrt(pow(x, 2)+ pow(y, 2)+ pow(z, 2));


}

