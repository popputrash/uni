/*
 * motor.c
 *
 * Created: 2025-01-08 15:10:04
 *  Author: aq3915
 */ 

#include <avr/io.h>


//initierar motorn
void motor_init(void){
	DDRC = 0xFF;
	TCCR3A |= (1 << COM3A1);
	// Waveform Generation Mode 5, Fast PWM (8-bit)
	TCCR3A |= (1 << WGM32) | (1 << WGM30);
	TCCR3B |= (1 << WGM32) | (1 << WGM30);
	// Timer Clock, 16/64 MHz = 1/4 MHz
	TCCR3B |= (1 << CS31) | (1 << CS30);
	
}

void motor_set_speed(uint8_t speed){
	//skriver ut till PWM modulen
	OCR3AH = 0;
	//skalar om värde till från 0-100 till 0-255
	OCR3AL = (speed * 255) / 100;
	
}
