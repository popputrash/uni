---
date: 2026-02-13
course: da268a
topic: Untitled 1
tags:
  - lecture
aliases:
  - OFDM
---
# Introduktion och Bakgrund
OFDM är en teknik för "Multicarrier Modulation" som revolutionerade trådlös kommunikation genom att erbjuda hög spektrumeffektivitet och god motståndskraft mot störningar.
- **Användningsområden:** Det är de nhuvudsakliga tekniken för 4G(LTE), WiMAX och moderna Wi-Fi-standarder (802.11n/ac/ax)
- **Fördelar**
	- Hög spektrumeffektivitet (bps/Hz)
	- Minskar problem med Intersymbol Interference (ISI)
	- Förbättrad felhantering
# Principen: FDM vs OFDM
För att förstå ODM måste man jämföra det med traditionell FDM (Frequency Division Multiplexing)
- **FDM (Traditionell):**
	- Delar upp spektrumet i separata frekvenskanaler
	- Kräver skyddsband (guard bands) mellan kanalerna för att de inte ska störa varandra. Detta slösar bandbredd
- **OFDM (Ortogonal):**
	- Använder hundratals eller tusentals underbärvågor (subcarriers) som överlappar varandra.
	- Inga skyddsband behövs mellan underbärvågorna, vilket sparar plats.
	- **Ortogonalitet:**  Detta är nyckeln. Underbärvågorna är matematiskt oberoende. Toppen (peak) på en våg sammanfaller exakt med nollgenomgången på de andra vågorna. Därför kan mottagaren skilja dem åt trots att de överlappar.
# Hur OFDM fungerar (Parallell dataöverföring)
Istället för att skicka en supersnabb dataström (som är känslig för multipath-störningar), delar OFDM upp datan i många långsamma parallella strömmar
- **Långsam symboltid:** Om du delar upp datan på N underbärvågor, blir symboltiden på varje våg N gånger längre.
- **Fördel:** En lång symboltid gör signalen mycket mer robust mot multipath-fördröjningar (ekon), vilket minskar risken för ISI
# Implementering med FFT/IFFT
Att bygga tusentals fysiska oscillatorer för varje underbärvåg vore orimligt dyrt och komplext. Istället sker allt digitalt med hjälp av Fourier-transformer.

- **Sändaren (IFFT):** Använder *Inverse Fast Fourier Transform* för att ta datan (i frekvensdomänen) och skapa den sammansatta vågformen (i tidsdomänen ) som ska sändas.
- **Mottagaren (FFT):** Använder *Fast Fourier Transform* för att plocka isär tids-signalen och återfå datan på de olika frekvenserna.
- **Effektivitet:** FFT är en algoritm som gör dessa beräkningar extremt snabbt, vilket gör OFDM praktiskt genomförbart i realtid
# Cyclic Prefix (CP) - Skyddsintervall
Trots långsam symboltid kan multipath (ekon) skapa viss störning. För att helt eliminera detta lägger man till ett **Cyclic Prefix**.
- **Hur det fungerar:** Man kopierar slutet av OFDM-Symbolen och klistra in det i början av symbolen. 
- **Syfte:** Deta fungerar som ett skyddsintervall (guard interval). Så länge ekot (fördröjningen) är kortare än CP, så skyddas datan från ISI och mottagaren kan tolka signalen korrekt.
- **Trade-off:** 
	- **Lång CP:** Klarar svåra miljöer med långa ekon (t.ex utomhus/lång räckvidd), men sänker datahastigheten
	- **Kort CP:** Effektivare dataöverföring, men känsligare mot ekon (används inomhus).
# Utmaningar
Tekniken har två huvudsakliga nackdelar som måste hanteras
- **PAPR (Peak-to-Average Power Ratio)**
	- Eftersom OFDM består av summan av många sinusvågor kan de ibland samverka (adderas) så att signalstyrkan plötsligt blir väldigt hög (en hög "topp").
	- Detta ställer höga krav på förstärkaren i sändaren (de måste vara linjära över ett stort område), vilket drar mycket ström och är dyrt.
- **ICI (Intercarrier interferance)**
	- Känslighet för frekvensfel. Om sändare och mottagare inte är perfekt synkade, eller om det finns Doppler-skift (p.g.a. rörelse), förloras ortogonaliteten och kanalerna börjar störa varandra.
# OFDMA (Multi-user Access)
- I OFDMA tilldelas olika användare olika grupper av underbärvågor (Resource Blocks) och olika tidsluckor. Detta gör att 4G/5G-nätet kan hantera många användare samtidigt med hög flexibilitet.