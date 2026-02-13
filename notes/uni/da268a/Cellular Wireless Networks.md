---
date: 2026-02-13
course: da268a
topic: Untitled 1
tags:
  - lecture
---
# Det cellulära konceptet
Grundtanken med cellulära nätverk är att ersätta en enda stark sändare (som täcker en hel stad) med många små sändare med låg effekt.
- **Celler:** Täckningsområdet delas in i små geografiska områden kallade celler, oftast representerade som hexagoner i teorin. Varje cell betjänas av en basstation (Base Station)
- **Frekvensåteranvänding (Frequency Reuse):** Detta är nyckeln till hög kapacitet. Eftersom sändarna har låg effekt stannar signalen inom cellen (och lite till). Detta gör att samma frekvens kan användas igen i en annan cell en bit bort utan att de stör varandra.
- **Kluster och N-faktor:** Ett mönster av celler där alla tillgängliga frekvenser används en gång kallas ett kluster. Antalet celler i ett kluster betecknas med $N$.
	- *Litet $N$:* Frekvenser återanvänds oftare -> Högre kapacitet (fler samtal per km²). men högre risk för interferens
	- *Stort N*: Minde interferens, men lägre kapacitet.

# Nätverkets Arkitektur
 - **Base Station (BS):** Innehåller antenner, radiosändare/mottagare och styrutrustning. Hanterar den trådlösa länken till din mobil.
 - **MTSO (Mobile Telecommunications Office):** "Hjärnan" i det äldre mobilnätet (Motsvarar Core Network idag). Den kopplar samtal, hanterar debitering, och håller koll på var mobilen befinner sig (Mobility Management)
 - **Kanaler:** 
	 - *Control Channels:* Används för att sätta upp samtal och skicka systeminfo (pinga mobilen)
	 - *Traffic Channels:* Bär själva samtalet eller datan

 # Mobilitet och Handoff
 En av de mest kritiska funktionerna är att kunna byta basstation under ett pågående samtal utan avbrott.
 - **Handoff (eller Handover):** Processen när nätverket flyttar anslutningen från en basstation till en annan när signalstyrkan blir för låg i den nuvarande cellen.
 - **Varför samtal bryts:** Handoff misslyckas om nätverket reagerar för långsamt (vid snabb förflyttning), om målcellen är full (trängsel) eller på gund av "ping-ping"-effekter där mobilen hoppar fram och tillbaka vid en cellgräns
 - **Hard vs Soft Handoff**
	 - *Hard Handoff (FDMA/TDMA)* "Break before make". Anslutningen bryts med den gamla basstationen precis innan den nya kopplas upp.
	 - *Soft handoff (CDMA/3G)* "Make before break". Mobilen är ansluten till två basstationer samtidigt under övergången. Detta ger en säkrare överlämning
# Öka kapaciteten (När nätet blir fullt)
När antalet användare ökar måste nätet förtätas.
- **Cell splitting:** Man delar upp en stor cell i flera mindre med egna basstationer. Detta ökar kapaciteten enormt men kräver fler master.
- **Cell Sectoring:** Istället för en rundstrålande antenn använder man riktade antenner (t.ex. 3 sektorer på 120deg vardera) Detta minskar inferensen och gör att man kan återanvända frekvenser tätare.
- **Microcells / Femtocells:** Man sätter upp mycket små celler i byggnader eller på lyktstolpar för att täcka "svarta hål" och avlasta det stora nätet.
# Mobilnätets generationer (1G - 4G)
- **1G (Analogt):** Endast tal. Dålig säkerhet (lätt att avlyssna). Använde FDMA
- **2G(Digitalt, GSM)**: Införde kryptering, felkorrigering och SMS. Använde TDMA/FDMA. Effektivare spektrumanvändning
- **3G (Data, CDMA):** Designat för data och internet. Använde CDMA som tillåter "Soft Handoff" och är motståndskraftigt mot multipath-fädning (tack vare RAKE-Mottagare)
- **4G (Bredband, OFDM/MIMI):** Rent IP-Baserat nät för höga hastigheter (upp till 1 Gbps)

# MIMO (Multiple Input Multiple Output)
- **Princip:** Användning av flera antenner på både sändare och mottagare
- **Fördelar**
	- *Diversity:* Skicka samma data via olika vägar för att motverka fädning (ökar tillförlitligheten)
	- *Spatial Multiplexing:* Skicka olika dataströmmar parallellt på samma frekvens (ökar hastigheten)
	- *Beamforming:* Rikta signalen mot en specifik användare för att nå längre och minska störningar
	- *Multi-User MIMO:* Basstationen pratar med flera användare samtidigt på samma frekvens genom att rikta olika "strålar" (beams) mot dem