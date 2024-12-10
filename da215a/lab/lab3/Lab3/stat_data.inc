/*
 * stat_data.inc
 *
 *  Created: 2024-12-04 16:44:57
 *   Author: aq3915
 */ 
 /* ----------------------------------------------------------------
DESCRIPTION
Space in the RAM to save the results from dice throws.
The following functions are provided below:
store_stat (R24 contains the dice value)
The function will increment the
Total number of throws and the
number of throws with results equals R24.
get_stat (R24 gives the value for which the
result is retrieved. If R24 = 0, the
total number of throws shall be returned.
The result is returned in R24.
clear_stat (no parameters in nor out)
Clears all data.
------------------------------------------------------------------*/
.DSEG ; The following applies to the RAM:
.ORG 0x100 ; Set starting
; address of data
; segment to 0x100
rolls:	.BYTE 7

.CSEG

store_stat:
	LDI XH, high(rolls)
	LDI XL, low(rolls)
	LD R15, X
	INC R15
	ST X, R15

	ADD XL, R16
	LD R24, X
	INC R24
	ST X, R24

	RET

get_stat: 
	LDI XH, high(rolls)
	LDI XL, low(rolls)

	ADD XL, R24
	LDI R24, 0x0
	ADC XH, R24

	LD R24, X       
	      
	RET
clear_stat:
	LDI R16, 0
	STS rolls, R16
	STS rolls+1, R16
	STS rolls+2, R16
	STS rolls+3, R16
	STS rolls+4, R16
	STS rolls+5, R16
	STS rolls+6, R16
	RET