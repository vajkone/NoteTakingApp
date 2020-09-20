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

### 4. Követelmények

A megrendelői követelményspecifikációban leírtakat tudomásul véve az applikációt úgy tervezzük meg, hogy egyszerű, könnyen kezelhető legyen a felhasználó számára. Új jegyzetet egyszerűen, egy "hozzáad" gomb megnyomásával lehet felvenni. A gomb megnyomása után a jegyzetelő felületre kerülünk, ahol a "jegyzet mentése" gombbal a jegyzetet elmentjük. A jegyzetek egyszerűen kerülnek kilistázásra az applikáció kezdőképernyőjén. A korábban elmentett jegyzetre való kattintással lehet azokat szerkeszteni, illetve a jobb felső sarokban lévő X-re kattintva lehet törölni. Kulcsszavak, címkék avagy dátum szerint lista nézetben tudja a felhasználó jegyzeteit csoportosítani és rendezni. A jegyzetek között való gyors és hatékony keresést kulcsszavakkal vagy dátum alapján teheti meg a felhasználó.  
Megrendelőnk kérését, miszerint az applikáció rendszerigénye legyen alacsony, teljes mértékben teljesítjük. Az applikáció egy egyszerű alsó középkategóriás mobiltelefonon, illetve tableten is probléma nélkül használható. Az applikáció grafikus felhasználói felületét úgy tervezzük meg, hogy letisztult, igényes legyen.  
A megrendelőnk igényeit szem előtt tartva az applikáció nem fér hozzá az eszközön lévő többi adathoz, valamint nem függ más alkalmazásoktól. Az applikáció elérhető offline módban, azaz a felhasználónak nincs szüksége internetkapcsolatra az applikáció használatához.

### 6. Fizikai környezet

Az általunk elgondolt applikáció az Android Studio fejlesztői környezetben készül. Android operációs rendszert futtató mobiltelefonokon, illetve tableteken érhető el.
Minimum 5.0.0 verziójú vagy annál magasabb Android operációs rendszeren használható, jelenleg más mobil operációs rendszeren nem érhető el.  
Ahogy a megrendelői [követelményspecifikációban](https://github.com/vajkone/NoteTakingApp/blob/master/k%C3%B6vspec.md#5-a-rendszerre-vonatkoz%C3%B3-szab%C3%A1lyok) is olvasható, a megrendelőnk kérése az volt, hogy az applikáció Java vagy Kotlin programozási nyelven íródjon. 
Csapatunk a Kotlin mellett döntött, ugyanis a Kotlin az Android hivatalos nyelve, óriási fejlesztői közösség épül köré és sokkal több frissítés érhető el az Androidra Kotlinon keresztül.  
A megrendelőnk igényeit szem előtt tartva az applikációt úgy tervezzük meg, hogy a rendszerigénye alacsony legyen, egy egyszerű alsó középkategóriás mobiltelefonon,
illetve tableten is probléma nélkül használható legyen.  
A felvitt adatok tárolását lokálisan, SQL alapú adatbázisban valósítjuk meg.
