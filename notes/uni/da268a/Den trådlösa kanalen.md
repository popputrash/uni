---
date: 2026-02-13
course: da268a
topic: Untitled
tags:
  - lecture
---

# Antenner: Definition och Egenskaper
En antenn fungerar som en omvandlare mellan elektriska signaler i en ledare och elektromagnetiska vågor i rymden. Samma antenn kan ofta användas för både sändning och mottagning. 

- **Strålningsmönster (Radiation Pattern)**
	En grafisk representation av hur antennen riktar energin.
	- **Huvudlob (Main lobe)** - Riktningen där signalen är starkast
	- **Sidolober (Side lobe)** - Oönskad strålning i andra riktningar.
	- **Lob-bredd (Beamwidth)** - Ett mått på hur smal eller bred strålen är (direktivitet)
- **Antennkategorier**
	- **Isotrop antenn** 
		En teoterisk punktkälla som strålar lika mycket energi åt alla håll. Det finns inte i verkligheten men kan användas som referens för att beräkna antennvinst.
	- **Rundstrålande (Omnidirectional)** 
		Strålar lika mycket i alla riktningar i ett plat (oftast horisontellt), men inte uppåt/nedåt. Exempel: Den klassiska "pinnen" (dipolantennen) på en Wi-Fi-router eller bilradio.
	- **Riktad (Directional)**
		Fokuserar energin i en specifik riktning. Detta ger lång räckvidd men kräver att sändare och mottagare är riktade mot varandra. Exempel: Parabolantenner och antenn-arrayer.
- **Antennvinst (Gain, $G$)**
	- Beskriver hur mycket starkare signalen är i huvudriktningen jämfört med en isotrop antenn.
	- En hög vinst (Gain) innebär inte att antennen skapar mer energi, utan att den fokuserar den energi som finns (som en ficklampa jämfört med en glödlamoa)
	- Det finns ett direkt samband mellan vinsten och antennens effektiva area ($A_{c}$) och frekvensen ($f$): 
$$
G = \frac{4\pi A_{c}}{\lambda²} = \frac{4\pi f²A_{c}}{c²}
$$
	Detta visar att vid högre frekvenser (kortare våglängd $\lambda$) kan man få högre vinst med samma antennstorlek

# Specifika Antenntyper
- **Dipolantennen** - Den mest grundläggande antennens
	- Halvvågsdipol (Hertz-antenn) - Består av två ledare med total längd $= \frac{\lambda}{2}$
	- Kvartsvågsdipol (Marconi-antenn) - En vertikal ledare monterad på en jordad yta (t.ex. biltak ) Längd $=\frac{\lambda}{4}$
- **Parabolantenn**
	- Använder en reflektor för att rikta radiovågorna till en smal stråle. Används för mikrovågor (t.ex. satellit-TV och radar) för att få mycket hög riktverkan och vinst.

# Utbredningsmodeller (Propagation Modes)
Hur signalen färdas från A till B beror kraftigt på frekvensen.
- **Markvåg (Ground Wave)**
	- **Frekvens:** $<2 Mhz$ (Låga frekvenser, t.ex. AM-radio).
	- **Beteende:** Vågen följer jordytans krökning.
	- **Räckvidd:** Vågen följer jordytans krökning.
- **Rymdvåg (Sky Wave)**
	- **Frekvens:** $2-30 MHz$ (HF-bandet, amatörradio, BBC World Service)
	- **Beteende:_** Signalen studsar (reflekteras) mot jonosfären högt upp i atmosfären och tillbaka till jorden.
	- **Räckvidd:** **:** Möjliggör kommunikation över kontinenter ("shortwave radio") genom att hoppa flera gånger mellan jord och jonosfär
- **Fri sikt (Line-of-Sight, LOS)**
	- **Frekvens:** $>30 Hz$ (VHF, UHF, mikrovågor) inkluderar FM-radio, TV, Wi-Fi, 4G/5G och satellit.
	- **Beteende:** Signalen går rakt fram och penetrerar jonosfären (försvinner ut i rymden om den inte tas emot).
	- **Begränsning:** Sändare och mottagare måste "se" varandra. Räckvidden begränsas av jordens krökning och hinder
- **Horisont och Refraktion (Vid LOS)**
	För Line-of-Sight-system är horisonten den bortre gränsen.
	- **Optisk vs Radio-horisont:** Radiovågor böjs (refrakteras) något av atmosfären, vilket gör att de "ser" lite längre än ögat.
	- **K-faktor:** För att kompensera för denna böjning i beräkningar används ofta faktorn $K = 4/3$. Radiohorisonten ligger alltså längre bort än den optiska horisonten. 
	- **Höjdens betydelse:** Räckvidden $(d)$ ökar med roten ur antennhöjden $(h)$ För att dubbla räckvidden måste tornet vara fyra gånger så högt $d ∝ \sqrt{h}$
- **Utbredningsmekanismer**
	När signalen möter hinder i miljön (t.ex. i en stad) sker fem grundläggande fenomen
	- **Fri rymd (Free-space propagation)**
		Signalen färdas rakt fram utan hinder. Styrkan avtar med $\frac{1}{d²}$
	- **Transmission**
		Signalen passerar genom ett material (t.ex en vägg)
	- **Reflektion**
		Signalen studsar mot en yta som är stor jämfört med våglängden (t.ex. en husfasad eller marken?
	- Diffratktion 
		Signalen "böjer av" runt vassa kanter och hörn. Detta gör att man kan ha täckning även i "skuggan" bakom ett hus (dock svagare signal)
	- **Scattering (Spridning)** 
		Signalen träffar små objekt (t.ex. lövverk, regndroppar eller skrovliga ytor) och sprids åt många håll.

# Dämpning och Path Loss
Signalen tappar styrka när den färdas från sändare till mottagare. Mottagaren måste ha tillräckligt med signalstyrka jämfört med bruset (SNR) för att kunna tolka datan.
- **Free Space Loss (Fri rymd)**
	Även utan hinder sprider energin ut sig över en yta. Formeln för förlust i dB:
$$
L_{dB = 20\log(f)+20\log (d) - 147.56}
$$
	I fri rymd ökar förlusten med 20 dB varje gång avståndet $(d)$ eller frekvenser $(f)$
	ökar med en faktor 10
- **Path Loss Exponent (n)**
	- I verkligheten finns hinder. Förlusten beskrivs då som proportionellt mot $d^n$
	- Formeln justeras till: $10n\log(d)$
	- **Värden på $n$**
		- Fri rymd: $n=2$ (20 dB förlust per 10x avstånd)
		- Stadsmiljö: $n=3$ (30 dB förlust per 10x avstånd)
		- Inomhus med hinder: $4$ (40 dB förlust per 10x avstånd)
	- Detta innebär att signalen dör ut *mycket* snabbare i sen stad än ute på havet
# Brus (Noise)
Brus är oönskade signaler som adderas till vår nyttosignal.
- **Termiskt brus**
	- Orsakas av elektronernas värmerörelse i alla komponenter. Finns alltid och kan inte elemineras
	- Beror på temperatur $(T)$ och bandbredd $(B)$ . Brusets effekt är $N=kTB$ där k är Boltzmanns konstant.
- **Intermodulation**
	- Uppstår när signaler på olika frekvenser delar medium och blandas (ofta pga icke-linjära förstärkare), vilket skapar "spöksignaler" på på summor och differenser av frekvenserna $(f_1 +f_{2}, f_{1}-f_{2})$
- **Crosstalk (Överhörning)**
	- Oönskad koppling mellan signalvägar, t.ex. när man hör att annat samtal i bakgrunden på en fast telefonledning
- **Impulsbredd**
	- Oregelbundna spikar av brus, t.ex. från blixtnedslag eller elektriska motorer som startar. Svårt att förutsäga men kan slå ut datapaket helt.
**Viktiga mått**: $E_b/{N_{0}}$ (Energi per bit jämfört med brusdensiteten). Detta är standardmåttet för att jämföra prestanda mellan olika digitala system oavsett bandbredd.

## Multipath
Radiovågor beter sig inte som en laserstråle- De studsar på omgivningen genom tre fenomen.
- **Reflektion** - Studs mot stora ytor (husväggar, marken)
- **Diffraktion** - Vågen "nöjer av" runt vassa kanter
- **Scattering (Spridning):** Vågen träffar små objekt och sprids åt alla håll

**Effekten av multipath:** Mottagaren får samma signal flera gånger men med olika fördröjning och fas.
- Om de anländer i "motfas" släcker de ut varandra (destruktiv interferens) -> Signalen försvinner plötsligt
- **ISI (Intersymbol interference):** En fördröjd kopia av en symbol (t.ex. en "etta") krockar med nästa symbol (t.ex. en "nolla"). Detta gör datan oläsbar vid höga hastigheter
# Fädning (Fadning)
Fädningar är variationer i signalstyrka. Man delar in det på två sätt: baserat på tid (hur snabbt det förändras) och baserat på frekvens (hur bandbredden påverkas).
### Indelning 1: Tid / Mobilitet (Doppler)
- **Slow Fading (Storskalig)**
	- Orsakas av skuggning (t.ex. du går bakom en byggnad)
	- Signalen ändras långsamt jämfört med tiden det tar att sända en bit $(T_{c}\gg T_{b})$
	- Kanalen är "stabil" under en bitsänding
- **Fast Fading (Småskalig)**
	- Orsakas av multipath och rörelse (doppler)
	- Signalen fladdrar snabbt upp och ner.
	- Sker om koherenstiden är kortare än bit-tiden $(T_{c}< T_{b})$
### Inledning 2: Frekvens / Bandbredd (Multipath delay)
- **Flat fading:**
	- Hela signalens bandbredd dippar samtidigt
	- Sker när signalens bandbredd $(B_{s})$ är smalare än kanalens koherensbandbredd $(B_{c})$
	- Ingen ISI, bara sämre SNR
- **Frequency Selective Fading**
	- Vissa frekvenser i signalen dämpas medan andra är starka (signalen "klipps sönder" i frekvensled)
	- Sker när signalen är bredbandig $(B_{s}>B_{c})$
	- Orsakar svår ISI. Detta är det stora problement för snabb dataöverföring som 4G/5G
# Channel Correction
- **Equalization (Utjämning):** En digital filterkrets i mottagaren som försöker "räkna baklänges" för att ta bort distorsionen från kanalen
- **Diversity/ MIMO:** Använd flera antenner. Om en antenn har en "dipp" (fading), kanske den andra har en bra signal.
- **OFDM:** Istället för en snabb bredbandig signal (känslig för selective fading), skicka många långsamma smalbandiga signaler (som bara drabbas av flat fading).
- **Coding (Felkorrigering):** Lägg till extra bitar så att fel kan rättas i efterhand (FEC)