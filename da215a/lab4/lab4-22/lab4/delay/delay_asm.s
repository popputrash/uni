/*
 * delay_asm.s
 *
 * Author:	????
 *
 * Date:	?????
 */ 

 /*
 * delay.inc
 *
 * Author:	?????
 *
 * Date:	2014-??-??
 */ 

 .global delay_1_micros
 .global delay_micros
 .global delay_ms
 .global delay_1_s
 .global delay_s


;==============================================================================
; Delay of 1 µs (including RCALL)
;==============================================================================
delay_1_micros:   /* UPPGIFT: komplettera med ett antal NOP-instruktioner!!! */
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	RET

;==============================================================================
; Delay of X µs
;	LDI + RCALL = 4 cycles
;==============================================================================
delay_micros:/* UPPGIFT: komplettera med ett antal NOP-instruktioner!!! */
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	DEC R24				; 1 cycle
	CPI R24, 0			; more loops to do? 1 cycle
	BRNE delay_micros	;	continue!
	RET

;==============================================================================
; Delay of X ms
;	LDI + RCALL = 4 cycles
;==============================================================================
delay_ms:
	MOV R18, R24
loop_dms:
	LDI R24, 250
	RCALL delay_micros
	LDI R24, 250
	RCALL delay_micros
	LDI R24, 250
	RCALL delay_micros
	LDI R24, 250
	RCALL delay_micros
	DEC R18
	CPI R18, 0			; more loops to do?
	BRNE loop_dms		;	continue!
	RET

delay_s:
	MOV R18, R24
loop_ds:
	PUSH R18
	LDI R24, 250
	RCALL delay_ms
	LDI R24, 250
	RCALL delay_ms
	LDI R24, 250
	RCALL delay_ms
	LDI R24, 250
	RCALL delay_ms
	POP R18
	DEC R18
	CPI R18, 0			; more loops to do?
	BRNE loop_dms		;	continue!
	RET

delay_1_s:
	LDI R24, 250
	RCALL delay_ms
	LDI R24, 250
	RCALL delay_ms
	LDI R24, 250
	RCALL delay_ms
	LDI R24, 250
	RCALL delay_ms
	RET