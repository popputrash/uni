/*
 * keyboard.inc
 *
 *  Created: 2024-12-04 13:19:14
 *   Author: Maximilian Andersen and Anton Ranklev
 */ 
read_keyboard_num:
	LDI R18, 0				; reset counter
scan_key:	
	MOV R19, R18			;Copy R18 to R19
	LSL R19			
	LSL R19
	LSL R19
	LSL R19					;Shift left 4 times 00001111 - 11110000
	OUT PORTB, R19
	PUSH R18			; set column and row
	LDI R24, 10
	RCALL delay_ms
	POP R18
	SBIC PINE, 6			; IF PIN6 is low skip next line
	RJMP return_key_val		; JUMP return_key_val
	INC R18					; Increase R18 - Column
	CPI R18, 12				; Compare R18 with 12
	BRNE scan_key			; If not equal jump to scan_key else (all rows and collums has been checked)
	LDI R18, NO_KEY		; no key was pressed!
return_key_val:
	MOV RVAL, R18		;sets rval to value of R18
	RET	

to_ASCII:
	LDI ZH, high(map_table <<1) ;Initialize Z pointer
	LDI ZL, low(map_table <<1)
	ADD ZL, RVAL ;Add index
	LDI RVAL, 0x00
	ADC ZH, RVAL ;Add 0 to catch Carry, if present
	LPM RVAL, Z
	RET

read_keyboard:
	RCALL read_keyboard_num
	RCALL to_ASCII
	RET
