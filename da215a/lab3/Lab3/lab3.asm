;
; Lab3.asm
;
; Created: 2024-12-04 13:15:56
; Author : Maximilian Andersen and Anton Ranklev
;

;==============================================================================
; Definitions of registers, etc. ("constants")
;==============================================================================
	.EQU RESET		= 0x0000
	.EQU PM_START	= 0x0056
	.DEF TEMP		= R16
	.DEF RVAL		= R24
	.EQU NO_KEY		= 0x0F
	.EQU ROLL_KEY	= 0x32
	.EQU STAT_KEY	= 0x33

;==============================================================================
; Start of program
;==============================================================================
	.CSEG
	.ORG RESET
	RJMP init

	.ORG PM_START

	.INCLUDE "delay.inc"
	.INCLUDE "lcd.inc"
	.INCLUDE "keyboard.inc"
	.INCLUDE "Tarning.inc"
	.INCLUDE "stat_data.inc"
	.INCLUDE "stats.inc"
	.INCLUDE "monitor.inc"
	map_table: .DB "147*2580369#",0,0,0,NO_KEY
	Hello_str: .DB "Hello",0
	Rolling_str: .DB "Rolling...",0,0
	Value_str: .DB "Value: ",0


init:
	; Set stack pointer to point at the end of RAM.
	LDI R16, LOW(RAMEND)
	OUT SPL, R16
	LDI R16, HIGH(RAMEND)
	OUT SPH, R16
	; Initialize pins

	CALL init_pins

	CALL lcd_init

	CALL init_stat

	CALL init_monitor

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


main:
	PRINTSTRING Hello_str
loop:
	RCALL compare_keys
	RJMP loop

compare_keys:
	RCALL read_keyboard	;Avläser keyboard och kollar ifall värdet är 2,3,8,9
	CPI RVAL, ROLL_KEY	;Hoppar till knappens label ifall RVALL = värdet för tangenten.
	BREQ case2
	CPI RVAL, STAT_KEY
	BREQ case3
	CPI RVAL, 0x38
	BREQ case8
	CPI RVAL, 0x39
	BREQ case9
	RJMP loop


case2:
	
	RCALL lcd_clear_display	;Clearar LCDn, och printar ut Rolling_str.
	LDI R24, 2		;Delayer efter clear då lcdn tar 1,52ms att cleara.
	RCALL delay_ms
	PRINTSTRING Rolling_str
	RCALL roll_dice		;Callar på roll_dice för att generera pseudo random nummer
	RCALL lcd_clear_display
	LDI R24, 2
	RCALL delay_ms		
	MOV R24, R16		;Flyttar värdet från R16 till R24 då roll_dice sätter R16 till
	SUBI R24, -0x30		;de genererade nummret. Omvandlar värdet till ASCII 
	PUSH R24		;PUSH och POP på R24 då PRINTSTRING macrot använder R24
	PRINTSTRING Value_str
	POP R24
	RCALL lcd_write_chr 
	RCALL store_stat
	RCALL delay_1_s
	RJMP loop


case3:
	RCALL showstat		;Kallar på showstat och hoppar tillbaks till main.
	RJMP loop


case8:
	RCALL clear_stat	;Kallar på clearstat och hoppar tillbaka till main.
	RJMP loop
case9:
	RCALL monitor		;Kallar på monitor och hoppar tillbaka till main.
	RJMP loop
