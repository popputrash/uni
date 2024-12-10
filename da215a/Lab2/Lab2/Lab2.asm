/*
 * lab1.asm
 * 
 * This is a very simple demo program made for the course DA215A at
 * Malmö University. The purpose of this program is:
 *	-	To test if a program can be transferred to the ATmega32U4
 *		microcontroller.
 *	-	To provide a base for further programming in "Laboration 1".
 *
 * After a successful transfer of the program, while the program is
 * running, the embedded LED on the Arduino board should be turned on.
 * The LED is connected to the D13 pin (PORTC, bit 7).
 *
 * Author:	Mathias Beckius, updated by Magnus Krampell
 *
 * Date:	2014-11-05, 2021-11-17
 */ 
 
;==============================================================================
; Definitions of registers, etc. ("constants")
;==============================================================================
	.EQU RESET		= 0x0000
	.EQU PM_START	= 0x0056
	.DEF TEMP		= R16
	.DEF RVAL		= R24
	.EQU NO_KEY		= 0x0F

;==============================================================================
; Start of program
;==============================================================================
	.CSEG
	.ORG RESET
	RJMP init

	.ORG PM_START
	.INCLUDE "delay.inc"
	.INCLUDE "lcd.inc"
;==============================================================================
; Basic initializations of stack pointer, I/O pins, etc.
;==============================================================================
init:
	; Set stack pointer to point at the end of RAM.
	LDI R16, LOW(RAMEND)
	OUT SPL, R16
	LDI R16, HIGH(RAMEND)
	OUT SPH, R16
	; Initialize pins

	CALL init_pins

	CALL lcd_init

	; Jump to main part of program
	RJMP main

;==============================================================================
; Initialize I/O pins
;==============================================================================
init_pins:	
	//LDI R16, 0b10000000
	//OUT DDRC, R16
	LDI TEMP, 0xFF		
	OUT DDRB, TEMP		;Sätter alla B PORTAR som output
	OUT DDRF, TEMP		;Sätter alla F PORTAR som output
	OUT PORTB, TEMP
	OUT PORTF, TEMP

	LDI TEMP, 0xFF
	OUT DDRD, TEMP

	LDI TEMP, 0x00	;Sätter PORTE som input
	OUT DDRE, TEMP

	RET
	
;==============================================================================
; Main part of program
;==============================================================================
read_keyboard:
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
	RET					;returns to main

main:
	LDI R24, 0b10000000
	RCALL lcd_write_instr

	LCD_WRITE_CHAR 'K'
	LCD_WRITE_CHAR 'E'
	LCD_WRITE_CHAR 'Y'
	LCD_WRITE_CHAR ':'
	
	LDI R24, 0b11000000
	RCALL lcd_write_instr

l1:	
	RCALL read_keyboard
	CPI RVAL, NO_KEY
	BREQ l1
	MOV TEMP, RVAL
	PUSH RVAL
	SUBI RVAL, 10
	POP RVAL
	BRSH l3
l4: 
	SUBI RVAL, -0x30
	RCALL lcd_write_chr
l2:
	RCALL read_keyboard
	CP TEMP, RVAL
	BREQ l2

	RJMP l1

l3:
	SUBI RVAL, -7
	RJMP l4


/**	CPI RVAL, NO_KEY
	BREQ l1
	MOV R24, RVAL
	RCALL lcd_write_chr
	MOV TEMP, RVAL
l2:
	RCALL delay_ms
	RCALL read_keyboard
	CP RVAL, TEMP
	BREQ l2

	RJMP l1**/

loop:
	RJMP loop

/**
read_keyboard:
	IN TEMP, 
	COM TEMP
	MOV RVAL, TEMP	
**/
	;CALL read_keyboard

	;LSL RVAL
	;LSL RVAL
	;LSL RVAL
	;LSL RVAL
	//COM RVAL
	;OUT PORTF, RVAL
	;NOP
	;NOP
	;RJMP main	