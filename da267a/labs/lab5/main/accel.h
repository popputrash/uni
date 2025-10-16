#ifndef ACCEL_H
#define ACCEL_H

#include <stdint.h>
void initMPU();
int16_t get_accel_x();
int16_t get_accel_y();
int16_t get_accel_z();
uint16_t calc_accel_vector();

#endif
