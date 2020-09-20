Rendszerterv a jegyzetelő alkalmazáshoz
---------------------------------------

### 1. A rendszer célja:

   A rendszer célja hogy diákoknak egy olyan felületet biztosítson hordozható elektronikus eszközeiken (okostelefon ill. tablet)
amelyet használva könnyen tudnak óráikon valamint előadásaikon jegyzetelni. A rendszer a világhírű Cornell metódust ültetné át
elektronikus környezetbe, ami már egyfajta útmutatást ad a diákoknak egy már bizonyítottan helyes és működő jegyzetelési stratégiához.
Továbbá a diákoknak minden jegyzete egy helyen lenne, a rendszer lehetőség biztosít a jegyzetek hatékony csoportosításához, azok 
átlátható rendszerezéséhez, valamint könnyű előkereséséhez. Ez utóbbit kulcsszavak szerinti, időpontbéli vagy esetleg tantárgy szerint
kereséssel valósítaná meg az alkalmazás, így jelentős számú és akár rendezetlen jegyzetek esetén is, a diák könnyedén, gyorsan és hatékonyan
rátalálhat a keresett jegyzetre, ha mondjuk emlékszik 1-2 szóra a jegyzetből vagy hogy körülbelül mikor készült a jegyzet. 
Ugyan a rendszernek nem kifejezett célja, hogy jegyzetelési tanácsadóként szolgáljon diákok számára, a környezet valamelyest rákényszeríti a
diákokat hogy egy helyes módszert használjanak és mivel az meglehetősen intuitív, ezért külön magyarázatot nem is igényel, a diákoknak nem
kell hosszas magyarázatokat végighallgatniuk hogy ráérezzenek a módszer nyújtotta előnyökre. 


### 2. Projekt terv:

  A rendszer létrehozásához rendelkezésre álló emberi erőforrások alapján a projekt a rendszerterv elfogadását követő két héten belül
megvalósítható. A projektben az alábbi szerepkörök betöltése szükséges:

* Projektvezető: Aktívan résztvesz a fejlesztésben, a többi fejlesztő munkáját átlátja és koordinálja
* Designtervező: Megrendelői igények alapján előzetes képernyőterv készítése, egyeztetés a megrendelővel a
		végső designtervet illetően, a tényleges projekt design-jának megvalósítása
* Adatbázistervező: A projekthez tartozó teljes adatbázis létrehozásáért és megfelelő működéséért,
		   valamint a programozó által igényelt lekérdezések megvalósításáért felelős. 
* Programozó: A projektvezető által kijelölt programozási feladatok megoldásáért felelős  

##### Ütemterv
   | Fő feladatok     |Részfeladat| Prioritás     |Becslés|Eltelt idő|Hátralévő idő  |
   | -----------------|----------|:-------------:| -----|---------|-----------|
   | Adatbázis |Adatbázis létrehozása| Magas | 4-6 óra |          |  6 óra          |
   | Adatbázis |CRUD funkcionalitás implementálása| Magas | 6-8 óra |          |  8 óra          |
   | Adatbázis          |Specifikus lekérdező funkciók           |Magas       |   4-6 óra |          |    6 óra        |
   | Programozói feladatok |Modell osztályok létrehozása| Közepes | 4-6 óra |          |  6 óra          |
   | UI     |  UI létrehozása         |  Közepes     | 6-8 óra     |          |   8 óra         |
   | UI     |  Controller osztályok       |  Magas     | 3-4 óra     |          |   4 óra         |
   | UI     |  UI elemek funkcionalitása        |  Magas     | 4-6 óra     |          |   6 óra         |
   | UI     |  Reszponzív design       |  Alacsony     | 3-4 óra     |          |   4 óra         |
   | Tesztelés     |  Tesztelés       |  Magas     | 3-4 óra     |          |   4 óra         |
   | Kényelmi funkciók     |  Létrehozás és tesztelés       |  Alacsony     | 6-8 óra     |          |   8 óra         |
   
   
	

### 3. Üzleti Folyamatok

[BPMN folyamatábra](https://github.com/vajkone/NoteTakingApp/blob/master/K%C3%A9pek/BPMN.png)

Ezzel az alkalmazással a papír alapú jegyzetelést szeretnénk kiváltani.  
A papír helyett,a mobil eszközünkön tudunk kényelmesen jegyzeteket léterehozni,és ezeket kezelni.  
Az egyetlen erőforrás amire szükség lesz, az a mobil készülék.  
A felhasználónak egy felhasználó barát környezetben lehetősége lesz jegyzeteket létrehozni,szerkeszteni,törölni.  
Illetve ezek között a létrehozott jegyzetek közt keresni is.  
A felhasználó egy gomb megnyomásával tud létrehozni egy jegyzetet,a gomb megnyomása után a szerkesztő felületre kerül.  
Itt billentyűzet segítségével tud jegyzetelni. Majd ezt a jegyzetet el is tudja menteni.  
A kereséshez használhat search boxot,vagy görgetést.A törléshez is egy külön gomb található minden adott jegyzetnél.  

### 4. Követelmények

A megrendelői követelményspecifikációban leírtakat tudomásul véve az applikációt úgy tervezzük meg, hogy egyszerű, könnyen kezelhető legyen a felhasználó számára. Új jegyzetet egyszerűen, egy "hozzáad" gomb megnyomásával lehet felvenni. A gomb megnyomása után a jegyzetelő felületre kerülünk, ahol a "jegyzet mentése" gombbal a jegyzetet elmentjük. A jegyzetek egyszerűen kerülnek kilistázásra az applikáció kezdőképernyőjén. A korábban elmentett jegyzetre való kattintással lehet azokat szerkeszteni, illetve a jobb felső sarokban lévő X-re kattintva lehet törölni. Kulcsszavak, címkék avagy dátum szerint lista nézetben tudja a felhasználó jegyzeteit csoportosítani és rendezni. A jegyzetek között való gyors és hatékony keresést kulcsszavakkal vagy dátum alapján teheti meg a felhasználó.  
Megrendelőnk kérését, miszerint az applikáció rendszerigénye legyen alacsony, teljes mértékben teljesítjük. Az applikáció egy egyszerű alsó középkategóriás mobiltelefonon, illetve tableten is probléma nélkül használható. Az applikáció grafikus felhasználói felületét úgy tervezzük meg, hogy letisztult, igényes legyen.  
A megrendelőnk igényeit szem előtt tartva az applikáció nem fér hozzá az eszközön lévő többi adathoz, valamint nem függ más alkalmazásoktól. Az applikáció elérhető offline módban, azaz a felhasználónak nincs szüksége internetkapcsolatra az applikáció használatához.  
Az alkalmazás letöltése és használata díjmentes. Az alkalmazáson belül (inapp) sem találhatóak fizetéses elemek. Az applikáció és az alapjául szolgáló szoftvertermék a szerzői jogról szóló 1999. évi LXXVI. törvény és egyéb, a szellemi alkotásokra vonatkozó jogszabályi rendelkezések védelme alatt áll. A személyes adatok kezelése az információs önrendelkezési jogról és az információszabadságról szóló 2011. évi CXII. törvény rendelkezéseinek megfelelően történik. A felhasználónak be kell tartania minden hazai előírást és szabályozást, amely az applikációra vonatkozik. A Felhasználó az applikációt vagy annak bármely részét nem adhatja kölcsön, nem ruházhatja át, nem adhatja bérbe, sem más módon nem terjesztheti.

### 6. Fizikai környezet

Az általunk elgondolt applikáció az Android Studio fejlesztői környezetben készül. Android operációs rendszert futtató mobiltelefonokon, illetve tableteken érhető el.
Minimum 5.0.0 verziójú vagy annál magasabb Android operációs rendszeren használható, jelenleg más mobil operációs rendszeren nem érhető el.  
Ahogy a megrendelői [követelményspecifikációban](https://github.com/vajkone/NoteTakingApp/blob/master/k%C3%B6vspec.md#5-a-rendszerre-vonatkoz%C3%B3-szab%C3%A1lyok) is olvasható, a megrendelőnk kérése az volt, hogy az applikáció Java vagy Kotlin programozási nyelven íródjon. 
Csapatunk a Kotlin mellett döntött, ugyanis a Kotlin az Android hivatalos nyelve, óriási fejlesztői közösség épül köré és sokkal több frissítés érhető el az Androidra Kotlinon keresztül.  
A megrendelőnk igényeit szem előtt tartva az applikációt úgy tervezzük meg, hogy a rendszerigénye alacsony legyen, egy egyszerű alsó középkategóriás mobiltelefonon,
illetve tableten is probléma nélkül használható legyen.  
A felvitt adatok tárolását lokálisan, SQL alapú adatbázisban valósítjuk meg.

#### 7. Architekturális terv

Az alkalmazás az MVC architektúrális tervezési mintát követi.  
Eszerint a programunkat 3 részre osztjuk,a modellre,nézetre, és kontrollerre.  
A modell része foglalkozik a programunk során keletkező adatok,és a köztük lévő kapcsolatok kezelésével.  
Ide értjük a táblázatot,amiben a jegyzeteket kezeljük.  
A nézet vagy angolul view,része a programunknak,a megjelenítéssel foglalkozik.  
Ez fogja megjeleníteni a modellben kezelt adatokat.  
A kontroller része a programnak a felhasználótól származó műveleteket fogja kezelni.  
A felhasználó által adott parancsokat fogja a modellben létrehozni. Például ha a felhasználó kitöröl egy jegyzetet,
akkor a kontroller fogja kitörölni az adott rekordot a táblából.  
Tehát a felhasználó használja a kontrollert,a kontroller módosítja a modellt,a modell frissíti a nézetet amit majd a felhasználó látni fog.  
A modell réteget kell változtatnunk,ha bármilyen változást szeretnénk majd a későbbiekben végrehajtani az adatbázisunk struktúráján.  
A programunk úgy került kialakításra,hogy ez az esetleges változás a többi réteget ne érintse.  
Az MVC tervezési minta sok előnnyel rendelkezik,ezekért is került a választásunk erre a mintára.  
Egyidejűleg lehet fejleszteni,tehát többen tudunk egyszerre dolgozni a komponenseken.  
Az elemek alapvetően nagy részben függetlenek egymástól,ezért könnyen változtathatóak.  
Könnyen bővíthető is,mivel a felelősségek szét vannak osztva a komponensek között.  
Az alkalmazásunk offline lesz,tehát az összes adatot az adott készüléken tárolja.  
Ebből adódóan az alkalmazás védett lesz az online támadások alól.  
Offline pedig a felhasználó készülékétől fogg fügni.  

### 8. Tesztterv  
A tesztelés feladata az, hogy a szoftver használata során fellépő hibák előfordulását csökkentse, a szoftver megbízhatóságát növelje és a szabványoknak, előírásoknak való megfelelőségét biztosítsa.  A teszt végrehajtásáért a projekt menedzser felel és a tesztcsapat hajtja végre  

Elvárások a teszteléssel kapcsolatban:
* A tesztprogram az ebben a dokumentumban meghatározott tesztterv alapján fut.  
* Az this.BestTeam=true projektcsapat felelős a tesztadatok előállításáért.   
* Az olvasó ismeri az alapdokumentumokat, amelyek meghatározzák a rendszert.  
 
Szükséges erőforrások:  
- 5.0,vagy annál nagyobb verziószámú android operációs rendszerű mobilkészülékek.  
- Számítógépek android emulátorral ellátva  
- klimatizált helyiség  
- stabil áramellátás  
- áramelosztó  
- internetelérés  

Feladatkörök és felelősségek:  
| Feladatkör       |    Felelősség/tevékenység    |   Személy   |
|------------------|------------------------------|-------------|
| Tesztelő         |    A teszt végrehajtása    |             |
| Tesztelő         |    Az észrevételek dokumentálása    |             |
| Tesztelő         |    Teszt dokumentáció archiválása    |             |
| Szakértő |    A szakértő az észrevételek elemzi és megoldást javasol.    |      |
| Teszt-koordinátor |    Teszt terv készítése    |      |
| Teszt-koordinátor |    A tesztterv jóváhagyatása a     |      |
| Teszt-koordinátor |    A tesztterv jóváhagyatása a projektmenedzserrel    |      |
| Teszt-koordinátor |    Teszt forgatókönyvek létrehozása    |      |
| Teszt-koordinátor |    Inkonzisztenciák kezelése    |      |
| Teszt-koordinátor |    Helyes és időbeni hibakezelés    |      |
| Teszt-koordinátor |    Szükség esetén problémák eszkalálása a projekt menedzsernek    |      |
| Teszt-koordinátor |    Végső riport készítése    |      |
| Teszt-koordinátor |    Teszt dokumentum archiválása    |      |
| Teszt-koordinátor |    Az észrevételek státuszának követése, ill. dokumentálása    |      |
| Projektvezető |    Teszt terv jóváhagyása    |             |
| Projektvezető |    Teszt forgatókönyv (testscript)    |             |  

Tesztkörnyezet:  
A fejlesztők teljes hozzáféréssel végezzék a tesztelést.  

Tesztelési eljárás leírása:  
Lépések: A felhasználó által lehetséges esetek tesztelése  
Várt érték és a teszt álltal adott értékek összehasonlítása   
Teszt Szkriptek elkészítése (számítógépek által adott utasítások)
Unit tesztek elkészítése   

### 9. Karbantartási terv  

A szoftver rendszernek támogatnia kell az alábbi tevékenységeket:  

* rendszeres biztonsági mentés  
* meghibásodás esetén az utolsó konzisztens állapot visszaállítása  
* rendszer frissítése  
* általános átvizsgálás során észlelt hibák javítása  
* bejelentések során érkezett hibák javítása  
* felhasználók igényeinek folyamatos figyelése és felmérése  
* biztonsági beállítások folyamatos felülvizsgálata és szükség esetén korrigálásuk.  

### 10. Implementációs terv  

A Feladatokat a Kotlin programozási nyelven implementáljuk  
Cél: 
- Felkutatni, hogy a specifikáció nem megvalósítható, vagy ellentmondásos-e  
- Törekedni kell arra, hogy az elkészülő osztályok, modulok újrafelhasználhatóak legyenek. A már meglévő modulokat érdemes újra felhasználni.  
- Keretrendszer kialakítása

Tartalmazza kell:   
- A forrásfájl komponensek megvalósításának sorrendjét  
- A fordítások sorrendjét  

Az implementációs modell alapján készült.  

### 12. Telepítési terv

Az alkalmazást egy .apk kiterjesztésű telepítő fájllal bármilyen 5.0,vagy annál nagyobb verziószámú android operációs rendszerű eszközre telepíthető lesz.
Ehez engedélyezni kell az eszközön az ismeretlen forrásból származó alkalmazások telepítését.


