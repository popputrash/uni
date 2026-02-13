---
date: 2026-02-13
course: da268a
topic: Untitled
tags:
  - lecture
---
# Data, Signaler och Transmission
- **Data** 
	- Det som bär mening (information), Kan vara analog (kontinuerlig, t.ex röst, video) eller digital (diskret, t.ex. text, datorkod)
	- **Analog data**: Kontinuerliga värden, oändligt många nivåer (temperatur, ljud, ljusstyrka)
	- **Digital data**: Diskreta värden, begränsat antal nivåer (text, binär kod, digitala bilder)
- **Signaler**
	- Den elektromagnetiska representationen av data. Även dessa kan vara analoga (kontinuerlig våg) eller digitala (spänningspulser)
	- **Analog signal**: Kontinuerligt varierande i amplitud och tid
	- **Digital signal**: Diskreta nivåer (oftast två: hög/låg, 1/0)
- **Kombinationer**
	- **Analog data på Analog signal**
		- Vanlig radio (AM/FM)
		- Äldre telefoni
	- **Digital data på Analog Signal**
		- Moderna modem (Wifi , 4G, 5G).
		- Man använder en analog bärsignal för att transportera digitala ettor och nollor
	- **Analog data på Digital Signal**
		- PCM (Pulse Code Modulation) 
		- Samplar analogt ljud till digitala bitar (Används i modern telefoni och musikstreaming)
		- Processen: Sampling → Kvantisering → Kodning
	- **Digital data på Digital Signal**
		- Direkt överföring av digitala bitar som spänningsnivåer
		- Används i kablar (Ethernet, USB, HDMI)
		- Enklaste formen av transmission

# Tidsdomän vs. Frekvensdomän
För att förstå signaler måste man kunna se dem på två sätt
- **Tidsdomän**
	- Visar signalens styrka (Amplitud) över tid 
	- **Amplitud (A)**: Signalens styrka eller höjd (mäts i volt, watt, etc.)
	- **Period (T)**: Tiden för en cykel (*T*= 1 / *f*)
	- **Frekvens (f)**: Antal cykler per sekund (*f* = 1 / *T*), mäts i Hertz (Hz)
	- **Fas (ϕ)**: Var i cykeln vågen startar (mäts i grader eller radianer)
	- **Våglängd (λ)**: Distansen en våg hinner färdas under en period (λ = *c/f*), där *c* är ljusets hastighet, c = 3 × 10⁸ m/s
- **Frekvensdomän**
	- Visar vilka frekvenser signalen består av. Enligt Fourieranalys kan alla signaler (även fyrkantsvåg) byggas upp av en summa av sinus vågor med olika frekvenser.
	- **Fourieranalys**: Matematiskt verktyg för att bryta ner komplexa signaler till sina frekvenskomponenter
	- **Grundton (Fundamental)**: Den lägsta frekvensen
	- **Övertoner (Harmonics)**: Multipler av grundtonen som ger signalen dess form (2f, 3f, 4f, etc.)
	- **Bandbredd (B)**: Skillnaden mellan den högsta och lägsta frekvensen i signalen (*B* = *f*_max - *f*_min)
	- **Spektrum**: Grafisk representation av signalens frekvensinnehåll

## Sambandet mellan tidsdomän och frekvensdomän
- **Fouriertransform**: Omvandlar signal från tidsdomän till frekvensdomän
- **Invers Fouriertransform**: Omvandlar från frekvensdomän tillbaka till tidsdomän
- Viktigt för att:
	- Analysera vilken bandbredd en signal kräver
	- Designa filter
	- Förstå frekvensmultiplexing (FDMA, OFDM)

# Decibel (dB) - Enhet för signalstyrka
Inom telekom räknar man ofta logaritmiskt med Decibel eftersom signalstyrkor kan variera enormt. 
- **Förstärkning / Dämpning**
	- 3 dB = Dubblering av effekten
	- -3 dB = Halvering av effekten
	- 10 dB = 10 gånger effekten
	- -10 dB = 1/10 av effekten
$$
 10\log_{10}\left( {\frac{P_{ut}}{P_{in}}} \right)
$$
- **Absoluta nivåer**
	- **dBW**: Referens 1 Watt
		- Exempel: 30 dBW = 1000 W
	- **dBm**: Referens 1 milliwatt (vanligt i mobilnät, t.ex. mottagen signalstyrka)
		- 0 dBm = 1 mW
		- 30 dBm = 1 W
		- Typiska mobiltelefon: +23 dBm (200 mW sändeffekt)
		- Bra mottagning: -70 dBm
		- Dålig mottagning: -110 dBm
	- **dBi**: För antennförstärkning (isotrop referens)

# Kanalens Kapacitet
## Nyquists bandbredd (Brusfri kanal)
Anger maxhastigheten begränsat av bandbredden
$$
C = 2B\log_{2}M
$$
- **C** = Datatakt (bps - bits per sekund)
- **B** = Bandbredd (Hz)
- **M** = Antal diskreta signalnivåer (t.ex spänningsnivåer)
- **Insikt**  
	Utan brus kan vi öka hastigheten genom att använda fler nivåer (M), men i verkligheten sätter bruset stop.
- **Exempel**:
	- B = 3000 Hz, M = 2 (binärt): C = 2 × 3000 × log₂(2) = 6000 bps
	- B = 3000 Hz, M = 4: C = 2 × 3000 × log₂(4) = 12000 bps
	- B = 3000 Hz, M = 8: C = 2 × 3000 × log₂(8) = 18000 bps
## Shannons kapacitetsteorem (Kanal med brus)
Anger den absoluta övre gränsen för dataöverföring givet brus.
$$
C = B\log_{2}(1 + SNR)
$$
- **SNR** - Signal-to-Noise Ratio (Effekten av signalen / Effekten av bruset). 
	- **OBS** i formlen används ratio och inte dB
	- Om man får SNR i dB måste man omvandla först

$$
SNR_{ratio} = 10^{SNR_{db}/10}
$$
- **Insikt** 
	Om bruset ökar (lägre SNR) måste vi antingen sänka hastigheten eller öka bandbredden för att få fram datan felfritt.
- **Exempel**:
	- B = 3000 Hz, SNR = 30 dB (1000:1 ratio): C = 3000 × log₂(1001) ≈ 30 kbps
	- B = 1 MHz, SNR = 20 dB (100:1 ratio): C ≈ 6.66 Mbps
- **Viktigt**: Shannons gräns är teoretisk - i verkligheten når man aldrig riktigt upp till denna kapacitet

## Störningar och Brus
Signaler försämras alltid på vägen
- **Dämpning (Attenuation)**
	Signalen tappar styrka med avståndet. Sker både i kabel och luft.
- **Förvrängning** 
	Olika frekvenser färdas olika fort eller dämpas olika mycket, vilket ändrar signalens form.
- **Brus (Noise)** 
	- **Termiskt burs** - Elektroner som rör sig pga värma (finns alltid, "vitt brus")
	- **Intermodulation** - När frekvenser blandas och skapar "spöksignaler"
	- **Crosstalk (Överhörning)** - Signal läcker från en kabel till en annan
	- **Impulsbrus** - Plötsliga spikar från t.ex. åska eller elmotorer
	