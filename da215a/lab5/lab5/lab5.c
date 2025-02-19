/*
 * lab5.c
 *
 * Created: 2024-12-17 13:28:56
 * Author : aq3915
 */ 

#include <avr/io.h>
#include <stdio.h>
#include "hmi/hmi.h"
#include "temp/temp.h"
#include "numkey/numkey.h"

//enum för olika tillstånd
enum state{
	SHOW_TEMP_C,
	SHOW_TEMP_F,
	SHOW_TEMP_CF,
};

typedef enum state state_t;



int main(void)
{
	//initierar variabler som kommer användas i tillståndsmaskinen och hmi/temp
	//skapar en sträng array som kommer användas till text som ska skrivas ut till LCDn
	state_t curr_state = SHOW_TEMP_F;
	state_t next_state = SHOW_TEMP_F;
	char key;
	hmi_init();
	temp_init();
	char temp_str[17];
	
	//tillstånds maskin, läser av tangentbord sedan swithchar den över 
	//nuvarande tillstånd och sedan implementerar de tillståndets krav.
    while (1) 
    {
		key = numkey_read();
		
		switch(curr_state){
			case SHOW_TEMP_C:
				sprintf(temp_str, "%u%cC", temp_read_celsius(), 0xDF);
				if(key == '2'){
					next_state = SHOW_TEMP_F;
				}else if (key == '3'){
					next_state = SHOW_TEMP_CF;
				}
				break;
			case SHOW_TEMP_F:
				sprintf(temp_str, "%u%cF", temp_read_fahrenheit(), 0xDF);
				if(key == '1'){
					next_state = SHOW_TEMP_C;
				}else if (key == '3'){
					next_state = SHOW_TEMP_CF;
				}
				break;
			case SHOW_TEMP_CF:
				sprintf(temp_str, "%u%cC/%u%cF", temp_read_celsius(), 0xDF, temp_read_fahrenheit(), 0xDF);
				if(key == '1'){
					next_state = SHOW_TEMP_C;
				}else if (key == '2'){
					next_state = SHOW_TEMP_F;
				}
			
		}
		//skriver ut temeperaturen i valt tillstånd
		//och uppdaterar tillstånd till nästa tillstånd
		output_msg("TEMP------",temp_str,0);
		curr_state = next_state;
    }
}
