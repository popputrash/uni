---
date: 2026-02-13
course: da268a
topic: Untitled
tags:
  - lecture
---

# Vad är modulering och varför behövs det?
Modulering är processen att låta en informationssignal (t.ex. din röst eller data) styra en egenskap hos en bärvåg (carrier wave). Bärvågen är oftast en sinusvåg med högfrekvens som passar för sändas genom luften

## Varför modulera?
- För att kunna signalen effektivt genom en kanal (luft, kabel, fiber)
- Basbandsignaler (låg frekvens) kräver orimligt stora antenner. Genom att flytta upp signalen till en hög frekvens kan vi använda små antenner
	- Antenner är mest effektiva när de är $\lambda/4$ eller $\lambda/2$ långa
	- Exempel: 1 kHz signal → $\lambda = 300$ km → antenn behöver vara 75 km lång!
	- Vid 100 MHz → $\lambda = 3$ m → antenn kan vara 75 cm
- Möjliggör FDM (Frequency Division Multiplexing) där flera kanaler sänds samtidigt på olika frekvenser utan att krocka
- Skyddar mot störningar och brus genom rätt val av moduleringsteknik
- Anpassar signalen till kanalens egenskaper (bandbredd, dämpning) 
## Utvärderings kriterier
- **Bandbreddseffektivitet** - Hur mycket data kan vi packa in i ett visst frekvensutrymme?
- **Energieffektivitet** - Hur mycket batteri drar sändningen? (Kritiskt för mobiler)
- **Störningskänslighet** - Hur väl klarar signalen brus?
- **Systemkomplexitet** - Hur dyrt/svårt är det att bygga sändare/mottagare?
- **Informationskapacitet** - Vilken hastighet (bps) kan uppnås?

# Analog Modulering
Här är informationssignalen kontinuerlig (analog). 
- **Amplitudmodulering (AM)** 
	- Informationssignalen styr bärvågens amplitud
	- Matematisk form: $s(t) = [A_c + m(t)] \times \cos(2\pi f_c t)$
		- $A_c$ = bärvågens amplitud
		- $m(t)$ = informationssignalen
		- $f_c$ = bärfrekvensen
	- **Modulationsindex (m)**: Förhållandet mellan informationssignalens amplitud och bärvågens amplitud
		- $m < 1$: Undermodulering (säker men ineffektiv)
		- $m = 1$: 100% modulering (optimal)
		- $m > 1$: Övermodulering (förvrängning)
	- **Bandbredd**: $B_{AM} = 2 \times f_{max}$ (dubbel sidband)
	- Fördel - Mycket enkel teknik och billig mottagare
	- Nackdel - Väldigt känslig för brus (eftersom brus ofta påverkar amplituden) och energikrävande
	- Varianter:
		- **DSB-FC (Double Sideband - Full Carrier)**: Standard AM, slösar energi på bärvåg
		- **DSB-SC (Double Sideband - Suppressed Carrier)**: Tar bort bärvågen, sparar 2/3 av effekten
		- **SSB (Single Sideband)**: Tar bort ena sidbandet, sparar bandbredd ($B = f_{max}$)
		- **VSB (Vestigial Sideband)**: Kompromiss, används i analog TV
- **Frekvensmodulering (FM)**
	- Informationssignalen styr bärvågens frekvens
	- Matematisk form: $s(t) = A_c \times \cos(2\pi f_c t + 2\pi k_f \int m(t)dt)$
		- $k_f$ = frekvenssensitivitet
	- **Frekvensdeviation ($\Delta f$)**: Maximal avvikelse från bärfrekvensen
	- **Modulationsindex ($\beta$)**: $\beta = \Delta f / f_m$
		- Narrowband FM: $\beta < 1$
		- Wideband FM: $\beta > 1$ (bättre kvalitet, kräver mer bandbredd)
	- **Bandbredd (Carsons regel)**: $B_{FM} \approx 2(\Delta f + f_{max})$
	- Fördel - Betydligt bättre på att stå emot brus än AM, konstant amplitud (energieffektivt)
	- Nackdel - Kräver större bandbredd än AM
	- Används i: FM-radio (88-108 MHz), analog TV-ljud
- **Fasmodulering (PM)**
	- Informationssignalen styr bärvågens fas
	- Matematisk form: $s(t) = A_c \times \cos(2\pi f_c t + k_p \times m(t))$
	- Mycket lik FM men derivatan av signalen moduleras
	- Används mindre i praktiken

# Digital Modulering
Här överförs digitala ettor och nollor. 
- **ASK (Amplitude Shift Keying)**
	- Använder olika amplituder för att representera 1 och 0. Enkelt men bruskänsligt
	- **Binary ASK (BASK/OOK)**: 
		- 0 = ingen signal ($A = 0$)
		- 1 = signal ($A = A_c$)
	- **M-ary ASK**: Fler amplitudnivåer ($M = 4, 8, 16...$)
	- **Bandbredd**: $B = 2R_b$ (där $R_b$ = bitrate)
	- **Spectral efficiency**: 0.5 bits/s/Hz (för BASK)
	- Används i: RFID, optisk fiber, äldre modem
	- Problem: Brus påverkar amplitud direkt → hög felfrekvens
- **FSK (Frequency Shift Keying)**
	- Använder olika frekvenser för 1 och 0. Användes i äldre modem och tidiga mobilnät
	- **Binary FSK (BFSK)**:
		- 0 → frekvens $f_0$
		- 1 → frekvens $f_1$
		- Separation: $\Delta f = |f_1 - f_0|$
	- **M-ary FSK (MFSK)**: $M$ olika frekvenser
	- **Bandbredd**: $B \approx 2\Delta f + 2R_b$
	- **Continuous Phase FSK (CPFSK)**: Fasen är kontinuerlig mellan symboler
		- **MSK (Minimum Shift Keying)**: Speciell form av CPFSK med minimal bandbredd
		- **GMSK (Gaussian MSK)**: MSK med Gaussfilter, används i GSM (2G)
	- Fördel: Mer robust mot brus än ASK, konstant amplitud
	- Nackdel: Kräver mer bandbredd än PSK
	- Används i: Bluetooth, äldre modem (Bell 103), Caller ID
- **PSK (Phase Shift Keying)**
	- Använder olika faslägen (tex 0° för 0 och 180° för 1). Detta är generellt mer energieffektivt än FSK och ASK
	- **BPSK (Binary PSK)**:
		- 2 faser: 0° och 180° (eller 0 och $\pi$)
		- 1 bit per symbol
		- Mest robust mot brus
	- **QPSK (Quadrature PSK)**: 
		- 4 faser: 0°, 90°, 180°, 270°
		- 2 bits per symbol
		- Se mer under Avancerad Modulering
	- **8-PSK**: 
		- 8 faser (45° mellan varje)
		- 3 bits per symbol
	- **Bandbredd**: $B = R_b$ (för BPSK), $B = R_b/2$ (för QPSK)
	- **Spectral efficiency**: 
		- BPSK: 1 bit/s/Hz
		- QPSK: 2 bits/s/Hz
		- 8-PSK: 3 bits/s/Hz
	- Fördel: Bäst spektral effektivitet, robust mot amplitudbrus
	- Nackdel: Kräver noggrann fassynchronisering, mer komplex mottagare
	- Används i: Satelliter, WiFi, mobilnät

- **Bitar vs Symboler**
	- En symbol är ett tillstånd som signalen kan anta under en viss tid ($T_s$ = symboltid)
	- Om vi bara har två tillstånd (t.ex. hög/låg amplitud) bär varje symbol **1 bit**
	- Om vi kan skapa flera unika tillstånd (t.ex. 4 olika faslägen) kan varje symbol bära flera bitar. Detta ökar datahastigheten utan att öka bandbredden
	- **Relation**:
		- $M$ symboler → $\log_2(M)$ bits per symbol
		- Symbol rate ($R_s$, baud) = $R_b / \log_2(M)$
		- Bitrate ($R_b$) = $R_s \times \log_2(M)$
	- **Exempel**:
		- BPSK: $M=2$ → 1 bit/symbol → vid 1000 baud = 1000 bps
		- QPSK: $M=4$ → 2 bits/symbol → vid 1000 baud = 2000 bps
		- 16-QAM: $M=16$ → 4 bits/symbol → vid 1000 baud = 4000 bps

# Avancerad Modulering & Konstellationsdiagram
För att visualisera signaler använder man konstellationsdiagram med två axlar: **I** (In-phase-cosinus) och **Q** (Quadrature, sinus). Varje punkt i diagrammet är en symbol.

## Konstellationsdiagram
- **I-axeln (In-phase)**: Cosinus-komponenten av signalen
- **Q-axeln (Quadrature)**: Sinus-komponenten av signalen (90° fasförskjuten)
- Varje punkt representerar en unik kombination av amplitud och fas
- Avstånd från origo = Amplitud
- Vinkel från I-axeln = Fas
- **Minimalt avstånd ($d_{min}$)**: Avståndet mellan närmaste punkter
	- Större $d_{min}$ → bättre motstånd mot brus
	- Högre $M$ (fler punkter) → mindre $d_{min}$ → kräver högre SNR

## PSK-familjen
- **BPSK (Binary PSK)**
	- 2 punkter på I-axeln (eller motsatta sidor av origo)
	- Enklast att detektera
	- Används när robusthet är viktigare än hastighet
- **QPSK (Quadrature PSK)**
	- Har 4 punkter i diagrammet (en i varje kvadrant)
	- Varje symbol bär 2 bitar (00, 01, 10, 11)
	- Dubbelt så snabbt som BPSK med samma bandbredden
	- Punkter ligger på en cirkel på 45°, 135°, 225°, 315°
	- Mycket populär i satelliter och 4G
	- Variant: **OQPSK (Offset QPSK)**: Q-komponenten förskjuts $T_s/2$ för att undvika fashopp på 180°
- **8PSK**
	- Har 8 punkter i en cirkel (45° mellan varje)
	- Varje symbol bär 3 bitar ($2^3 = 8$)
	- Används i satellitkommunikation och vissa 3G-lägen
- **QAM (Quadrature Amplitude Modulation)**
	- Kombinerar ändring av både Fas och Amplitud
	- Punkterna bildar ett rutnät i diagrammet
	- Mest spektraleffektiv men kräver högst SNR
	- **16-QAM**: 16 punkter (4×4 rutnät) → 4 bits per symbol
		- Spectral efficiency: 4 bits/s/Hz
		- Kräver SNR ≈ 15-20 dB
		- Används i 4G LTE, WiFi 4
	- **64-QAM**: 64 punkter (8×8 rutnät) → 6 bits per symbol
		- Spectral efficiency: 6 bits/s/Hz
		- Kräver SNR ≈ 22-25 dB
		- Används i 4G LTE, WiFi 5
	- **256-QAM**: 256 punkter (16×16) → 8 bits per symbol
		- Spectral efficiency: 8 bits/s/Hz
		- Kräver SNR ≈ 28-32 dB
		- Används i 4G/5G med bra täckning, WiFi 5/6
	- **1024-QAM**: 1024 punkter → 10 bits per symbol
		- Används i WiFi 6/6E och 5G
		- Kräver mycket högt SNR (>32 dB)
	- **Trade-off**: Högre QAM = mer data men kräver mycket bättre signal
		- Vid cellkanten: QPSK eller 16-QAM
		- Nära basstation: 64-QAM eller 256-QAM

## Adaptiv modulering
- Moderna system (4G/5G, WiFi) byter modulering dynamiskt
- **MCS (Modulation and Coding Scheme)**: Index som kombinerar modulation + kodning
- När SNR är hög → använd högre QAM (mer data)
- När SNR är låg → använd QPSK eller BPSK (mer robust)
- **Link Adaptation**: Systemet mäter kanalförhållanden och väljer bästa MCS
- Exempel 4G LTE:
	- Bra signal: 256-QAM → ~300 Mbps möjligt
	- Dålig signal: QPSK → ~10 Mbps

# Gray-kodning
När man bestämmer vilka bitar (t.ex. "011") som hör till vilken punkt i diagrammet använder man **Gray-kodning** 
- **Princip**
	Två punkter som ligger bredvid varandra i diagrammet ska bara skiljas åt med en enda bit. (t.ex. 00 och 01)
	- Varje steg ändrar endast en bit
	- Sekvens för 2 bitar: 00 → 01 → 11 → 10
	- Sekvens för 3 bitar: 000 → 001 → 011 → 010 → 110 → 111 → 101 → 100
- **Syfte**
	Om mottagaren tolkar fel på grund av brus, hamnar den oftast på grann-punkten. Med Gray-kodning blir då antalet bitfel minimerat (bara 1 bit blir fel istället för kanske alla 3)
- **Felfördel**: Minskar BER (Bit Error Rate) med upp till 50% jämfört med vanlig binär kodning
- **Exempel QPSK**:
	- Med binär kodning: 00, 01, 10, 11 (grannpunkter kan skilja 2 bitar)
	- Med Gray-kodning: 00, 01, 11, 10 (grannpunkter skiljer bara 1 bit)

# Prestanda och jämförelser
## Spektral effektivitet
Hur många bits per sekund per Hertz:
- BPSK: 1 bit/s/Hz
- QPSK: 2 bits/s/Hz
- 8-PSK: 3 bits/s/Hz
- 16-QAM: 4 bits/s/Hz
- 64-QAM: 6 bits/s/Hz
- 256-QAM: 8 bits/s/Hz

## SNR-krav för $10^{-6}$ BER (ungefärliga värden)
- BPSK: ~10 dB
- QPSK: ~10 dB
- 8-PSK: ~14 dB
- 16-QAM: ~17 dB
- 64-QAM: ~23 dB
- 256-QAM: ~30 dB

## Val av modulering
**BPSK/QPSK när:**
- Låg SNR (dålig signal)
- Lång räckvidd krävs
- Robusthet viktigare än hastighet
- Satelliter, djuprymskommunikation

**16-QAM/64-QAM när:**
- Medelhög SNR
- Balans mellan hastighet och täckning
- Typiska mobilnät

**256-QAM/1024-QAM när:**
- Mycket hög SNR
- Kort avstånd
- Maximal hastighet önskad
- WiFi inomhus, nära basstationer

# Moderna tekniker
## OFDM (Orthogonal Frequency Division Multiplexing)
- Används i 4G, 5G, WiFi
- Delar upp dataströmmen i många parallella låghastighetssignaler
- Varje subcarrier moduleras separat (kan använda olika QAM-ordning)
- Robust mot flervägsutbredning

## MIMO (Multiple Input Multiple Output)
- Kombineras med modulering
- Flera antenner på både sändare och mottagare
- Kan skicka olika dataströmmar samtidigt
- Multiplicerar kapaciteten

## Spread Spectrum
- **FHSS (Frequency Hopping)**: Hoppar mellan frekvenser
- **DSSS (Direct Sequence)**: Sprider signal över bred bandbredd
- Används för att undvika störningar och öka säkerhet
