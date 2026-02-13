---
date: 2026-02-13
course: da268a
topic: Untitled 1
tags:
  - lecture
---
# Grundprincipen för [[Spread Spectrum]]
Traditionellt vill man packa data så tätt som möjligt (smal bandbredd) för att spara utrymme. I [[Spread Spectrum]] gör man tvärtom: man sprider medvetet ut signalen över en mycket bredare bandbredd än vad som krävs för själva datatakten.
## Hur det går till
Sändaren använder en spridningskod (spreading code/sequence) för att sprida ut signalen. Mottagaren måste känna till exakt samma kod för att kunna "samla ihop" (despread) signalen igen och återfå datan.

## Varför "slösa" bandbredden?
Det finns fyra huvudmotiv
- **Motverka störningar & Jamming:** Om en fiende (eller en mikrovågsugn) sänder brus på en specifik frekvens, påverkas vara en liten del av den utspridda signalen
- **Säkerhet (LPD - Low Probability of Detection):** För en utomstående ser den utspridda signalen bara ut som svagt bakgrundsbrus. Utan koden kan man inte avlyssna den.
- **Hantering av Multipath**: Tekniken kan skilja på signaler som studsat och kommer fram vid olika tidspunkter (särskilt DSSS).
- **Multiple Access (CDMA):** Flera användare kan sända samtidigt på samma frekvens om de använder olika koder.

# FHSS (Frequency Hopping Spread Spectrum)
Här byter sändaren frekvens snabbt och ofta enligt ett bestämt mönster.
- **Funktion:** Signalen hoppar mellan olika bärvågsfrekvenser. Mottagaren hoppar i synk.
- **Slow vs Fast Hopping**: 
	- *Slow:* Flera bitar sänds på samma frekvens innan man hoppar
	- *Fast:* Man hoppar flera gånger under tidn en enda bit sänds (mer robust mot jamming)
- **Använding**
	Bluetooth använder FFHS för att undvika krockar med t.ex. Wi.Fi som ligger på samma band. Om en frkvens är blockerad, förloras bara lite data, och nästa hopp sker till en (förhoppningsvis) ren frekvens.
# DSSS (Direct Sequence Spread Spectrum)
Istället för att hoppa i frekvens multiplicerar man varje databit med en snabb kodsekvens.
- **Chipping Code:** Varje bit (t.ex. en "etta") ersätts av en lång serie snabba pulser kallade **chips** (t.ex 10110100..)
- **Effekt:** Detta smetar ut signalens energi över ett brett frekvensband. Signalen blir mycket svagare per Hz och kan döljas under brusgolvet
- **Använding:** GPS, Wi-FI (802.11b) och 3G (CDMA)
- **Fördel mot Multipath:** DSSS är mycket bra på att hantera signaler som studsar. Eftersom koderna är tidskänsliga, kommer en fördröjd signal (eko) inte att matcha koden och filtreras bort med brus.
# RAKE Receiver (Unikt för DSSS/CDMA)
I vanliga radiosystem är multipath (ekon) ett problem som skapar fädning. I DSSS kan man vända detta till en fördel med en **RAKE-mottagare**
- **Princip:** Mottagaren har flera fingrar (korrelatorer) som var och en låser på varsitt eko (en direkt signal , en som studsat på ett his, en på ett berg).
- **Resultat:** Mottagaren lägger ihop energin från alla ekon istället för att se dem som störningar. Detta ger en starkare total signal och bättre SNR (Signal-to-Noise Ratio).

# CDMA (Code Division Multiple Access)
Detta är en accessmetod som bygger på Spread Spectrum (oftast DSSS)
- **Princip:** Alla användare sänder på samma frekvens vid samma tidspunkt
- **Seperation:** Varje användare har en unik, matematisk kod. Koderna är ortogonala eller nästintill, vilket innebär att när mottagaren letar efter "Användare A:s" kod blir "Användare B.s" signal bara till brus som filtreras bort.
- **Near-Far Problem:** En utmaning i CDMA. Om en sändare är nära basstationen kan den "skrika" så högt att den dränker de svagare signalerna från användare längre bort. Detta kräver mycket noggrann effektreglering (Power Control).

# Koder
Valet av kod är kritisk.
- **PN-Sekvenser (Pseudo-Noise):** Ser ut som slumpmässigt brus men är deterministiska (skapas av en algoritm). Används för att signalen ska likna brus.
- **Ortogonala koder:** Används i CDMA för att garantera att användarna inte stör varandra. Exempel: Walsh-koder.
