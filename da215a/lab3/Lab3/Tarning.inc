/*
 * Tarning.inc
 *
 *  Created: 2024-12-04 15:49:59
 *   Author: Maximilian Andersen and Anton Ranklev
 */ 



roll_dice:
	LDI R16, 6				;dice have 6 values
test: 
	NOP
	NOP
	RCALL read_keyboard		;key-value in RVAL
	CPI RVAL, ROLL_KEY
	BREQ roll				;yes, key 1 is still pressed
	RET						;no, key is released
roll: 
	DEC R16					;start cycle count here
	BREQ roll_dice			;R16 is zero?, start agn at 6
	RJMP test				;no, keep rolling
	

