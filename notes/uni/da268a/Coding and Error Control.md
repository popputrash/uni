---
date: 2026-02-13
course: da268a
topic: Untitled 1
tags:
  - lecture
---

# Varför uppstår fel?
Digital kommunikation är aldrig perfekt. I trådlösa system är detta extra tydligt på grund av den tuffa miljön.
- **Orsaker till fel:** Termiskt brus, interferens (störningar från andra), dämpning (signalen blir svag), multipath fading (signalen studsar och släcker ut sig själv) och synkroniseringsfel
- **Konsekvens:** En enda bit som "flippar" (t.ex. 1010110 -> 1011110) kan helt ändra innebörden av ett meddelande eller göra det oläsbart
-
# Feldetektering (Error Detection)
Första steget är att mottagaren måste kunna upptäcka att något har gått fel. Detta görs genom att sändaren lägger till extra information ("redundans") som mottagaren kontrollerar. 
- **Partity Check (Paritetsbit)
	Den enklaste metoden. Man lägger till en extra bit till varje datablock.
	- **Even Parity:** Lägg till en 1:a eller 0:a så att det totala antalet 1:or blir jämnt.
	- **Odd parity:** Lägg till en bit så att antalet 1:or blir udda
	- **Svaghet:** Om två bitar blir fel samtidigt, tar felen ut varandra och paritetskontrollen missar felet. Det fungerar alltså bara bra om felen är få utspridda.
- **CRC (Cyclic Redundancy Check)**
	- En mer robust metod som används i t.ex. Ethernet och Wi-Fi
	- **Princip:** Sändaren räknar ut en "rest" (Frame Check Sequence, FCS) genom att dividera datan med ett förutbestämt tal. Mottagaren gör samma division. Om reseten är noll antas datan vara korrekt. CRC upptäcker nästan alla typer av fel, inklusive "burst errors" (många fel i rad).
# **ARQ (Automatic Repeat Request)**

- När ett fel väl upptäckts (t.ex. via CRC) vad händer?
	- **Princip:** Mottagaren begär att sändaren skickar datan igen ("retransmission").
	- **Fördel:** Enkelt och ger felfri data till slut.
	- **Nackdel:** I trådlösa nät med många fel leder detta till ständiga om-sändningar. Detta sänker hastigheter och skapar fördröjning (latency), vilket är dåligt för t.ex. röstsamtal.
# **FEC (Forward Error Correction)**
 Istället för att begära om-sändning försöker mottagaren rätta felet direkt med hjälp av smart matematik.
- **Princip:** Sändaren skickar med så mycket extra information (redundans) att mottagaren kan räkna ut vad den trasiga biten borde varit.
- **Hammingkoder** - Ett klassiskt exempel på "Block Codes".
	- Designade för att rätta enstaka bitfel
	- **Avstånd (Distance):** Man ser till att alla giltiga kodord skiljer sig åt med minst 3 bitar. Om man tar emot en sekvens som inte är ett giltigt kodord, letar man upp det giltiga order som ligger "närmast" (skiljer sig med minst antal bitar) och antar att det var det som skickades
- **Fördel:** Ingen om-sändning behövs, vilket är bra för realtidskommunikation (ingen extra fördröjning)
- **Nackdel:** Den extra datan tar upp bandbredden ("overhead") hela tiden, även när kanalen är bra.
# HARQ (Hybrid ARQ) - Den moderna lösningen
I moderna system som 4G (LTE) och 5G duger varken ren ARQ eller ren FEC man kombinerar dem.
- Hur det fungerar:
	- Använd FEC för att försöka rätta mindre fel direkt (snabbast)
	- Om felet är för grovt för att rättas av FEC, använd ARQ för att begära en ny sändning.
- Detta ger balans mellan hastighet, tillförlitlighet och effektivitet
