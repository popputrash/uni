---
date: 2026-02-13
course: da268a
topic: Untitled 1
tags:
  - lecture
---
# Evolutionen - Från 1G till 6G
- **Trenden:** Vi går från "Connected Things" (5G) till "Connected Intelligence" (6G)
- **Jämförelser**
	- 4G - Fokus på mobilt bredband (video, appar). Latens ca 100ms
	- 5G - Fokus på IoT och kritiska tillämpningar. Latens ca 10ms, Datahastighet upp till 10 Gbps
	- 6G (Vision): Latens ca 1ms. Hastighet 1 Tbps. Integrerad AI och satelliter

# 5G Usage Scenarios
5G är designat för att klara tre fundamentalt olika behov, ofta illustrerade som en triangel.
- eMBB (Enhanced Mobile Broadband): "Snabbare 4G". Fokus på  extremt hög datahastighet för streaming, VR/AR och molntjänster
- mMTC (Massive Machine Type Communications): "Internet of Things". Fokus på att koppla upp miljoner billiga, batterisnåla sensorer per km². Här är täckning och batteritid viktigare än hastighet
- URLLC (Ultra-Reliable Low Latency Communications): "Kritiska System". Fokus på extrem pålitlighet och minimal fördröjning. Används för självkörande bilar, fjärrkirurgi och industriautomation.

# Spektrum och Täckning
5G använder ett bredare spektrum än tidigare nät. Man delar in det i tre band:
- Lågband $(< 1 GHz)$: Ger bra täckning och går igenom väggar (bra för mMTC/IoT), men har låg kapacitet.
- Mellanband $(1-6 GHz)$: "Det gyllene bandet". En kompromiss som ger både bra kapacitet och okej täckning. Här ligger de flesta 5G-mobiler idag.
- Högband / mmWave $(>24 GHz)$: Ectrem kapacitet och hastighet., men signalen stoppas av träd, fönster och regn. Kräver fri sikt och täta basstationen (bra för eMMB i arenor/city)

# 5G-Arkitektur och Teknik
Nätet delas upp i RAN (Radio Access Network) och Core.
- gNB (gNodeB): Namnet på 5G-basstationen
- Core-funktioner: Istället för "burkar" är kärnnätet mjukvarubaserat (Service Based Architecture), Viktiga funktioner är
	- AMF: Hanterar mobilitet (Control Plane)
	- SMF: Hanterar Sessioner (Control Plane)
	- UPF: Hanterar själva datatrafiken (User Plane)
Numerologi (Flexibel OFDM) Till skillnad från 4G (som var låst till 15kHz subcarrier spacing), är 5G flexibelt.
- $\Delta f$: Kan vara 15, 30, 60, 120 eller 240 kHz ($15\times{2^\mu}$) ^
