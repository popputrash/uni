---
date: 2026-02-13
course: da268a
topic: Untitled 1
tags:
  - lecture
aliases:
  - FFT
---
# Tidsdomän vs Frekvensdomän
 - **Tidsdomän:** $(s(t))$: Visar hur signalens amplitud varierar över tid.
 - **Frekvensdomän:** $(S(f))$: Visar signalens spektrum - vilka frekvenser den består av och dess styrka.
 - **Viktig princip:** Det finns en fundamental "trade-off" mellan tid och frekvens.
	 - **Tidslokalisering ger frekvensspridning**: En mycket kort puls i tiden (snabb data) kräver en bred bandbredd i frekvens
	 - **Frekvenslokalisering ger tidsspridning**: En ren ton (smal i frekvens) måste pågå under lång tidsdomän
# Transformer (Verktyg för byta domän)
För att växla mellan dessa två synsätt används Fourier-transformer. 
- **CTFT (Continuous Time Fourier Transform)**
	- Används för analoga, kontinuerliga signaler.
	- Ger ett kontinuerligt spektrum
	- **Moduleringsteoremet:** Om man multiplicerar en signal med en cosinus-våg (bärvåg) i tidsdomänen, motsvarar det att man flyttar signalens spektrum till bärvågsfrekvensen $(f_{0})$ i frekvensdomänen
- **DTFT (Discrete Time Fourier Transform)**
	- Används för tidsdiskreta (samplade) signaler $(s[n])$
	- Resultatet är ett kontinuerligt spektrum som är periodiskt (upprepar sig)
- **DFT / FFT (Discrete Fourier Transform)**
	- Detta är den praktiskt viktigaste transformen för datorer och modern radio (5G och WiFi)
	- Både tiden och frekvensen är diskreta (punkter istället för kurvor).
	- **FFT (Fast Fourier Transform):** Är en effektiv algoritm för att beräkna DFT snabbt. Utan FFT skulle tekniker som OFDM vara för krävande för att räkna ut i realtid
# Kanalens påverkan (System response)
- **Impulssvar $(h(t))$:** Beskriver hur signalen reagerar på en oändligt kort puls. I en trådlös kanal visar detta alla ekon (multipath) som kommer efter varandra.
- **Frekvenssvar $(H(f))$:** Är Fouriertransformen av impulssvaret. Det visar hur kanalen förstärker eller dämpar olika frekvenser (t.ex. om vissa frekvenser "försvinner" p.g.a fädning)
- **Faltning vs Multiplikation**
	- I tidsdomänen är den mottagna signalen en faltning (convolution) av den sända signalen och kanalens impulssvar: $y(t)=s(t)*h(t)$. Detta är matematiskt jobbigt att räkna på.
	- I frekvensdomänen blir detta en enkel multiplikation: $Y(f) = S(f)·H(f)$ Detta är en av huvudorsakerna till att man gillar att jobba i frekvensdomänen (som i OFDM)  - svår faltning ersätts av enkel multiplikation.

# Bandbredd
- Eftersom signaler i teorin kan ha oändlig utbredning i frekvens (men väldigt svaga "svansar"), definierar man effektiv bandbredd som det frekvensomfång som innehåller merparten av signalens energi.
