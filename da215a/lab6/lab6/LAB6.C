/*
 * lab6.c
 *
 * Created: 2025-01-08 13:36:08
 * Author : aq3915
 */ 

#include <avr/io.h>
#include <stdio.h>
#include "numkey/numkey.h"
#include "hmi/hmi.h"
#include "regulator/regulator.h"
#include "motor/motor.h"


//initerar enum för olika states
enum state{
	MOTOR_OFF,
	MOTOR_ON,
	MOTOR_RUNNING,
};	

typedef enum state state_t;


//main method
int main(void)
{
	//sätter upp variabler för current state och next state
	state_t curr_state = MOTOR_OFF;
	state_t next_state = MOTOR_OFF;
	
	//initerar variabler
	char key;
	uint8_t reg;
	hmi_init();
	regulator_init();
	motor_init();
	
	char temp_str[17];
	
    while (1) 
    {
		//läser av tangentbord och regulator
		key = numkey_read();
		reg = read_regulator();

		//tillståndsmaskin
		switch(curr_state){
			
			case MOTOR_OFF:
				sprintf(temp_str, "OFF");
				if(key == '2' && reg == 0){
					next_state = MOTOR_ON;
				}
				motor_set_speed(0);
				break;
			case MOTOR_ON:
				sprintf(temp_str, "ON");
				if(key == '1'){
					next_state = MOTOR_OFF;
				}else if(reg > 0){
					next_state = MOTOR_RUNNING;
				}
				break;
			case MOTOR_RUNNING:
				sprintf(temp_str, "RUNNING AT %u%c", read_regulator(), 37);
				if(key == '1'){
					next_state = MOTOR_OFF;
				}
				motor_set_speed(reg);
				
				break;
		}
		//skriver ut moterns aktuella läge och uppdaterar tillstånd till nästa tillstånd
		output_msg("MOTOR",temp_str,0);
		curr_state = next_state;
		
    }
}

