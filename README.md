# Viikko4

# Navigointi JetPack Composessa

Navigointi toimii reittien avulla, tehtävässä tein "Routes" nimellä johon tallensin arvot Home ja Calendar. NavHostissa Composable funktiota hyväksi käyttäen annettiin näille arvoille osoite vastaavaan näyttöön. Clickatessa bottombarissa olevaa painiketta, navcontroller saa komennon siirtyä tiettyyn reittiin. NavHost sitten tunnistaa reitin ja piirtää ruudulle oikean näkymän.

# MVVM ja jaettu tila
Sovellus luo yhden TaskViewModelin MainActivityn alussa. Tämä sama olio sitten välitetään Home ja calendarscreenille. Tällöin molemmat näkymät tarkkailevat samaa tehtävän tilaa (StateFlow), jonka ansiosta sitten tehtävän poisto / editointi päivittyy molemmilla sivuilla.

# CalendarScreen 
Tehtävät haetaan ViewModelista ja ryhmitellään dueDaten perusteella käyttämällä groupBy. Ryhmät järjestetään aikajärjestykseen, pienin päivämäärä ylimmäksi ja isoin alimmaksi.

# AlertDialog
Tehtävien hallinta on toteutettu DetailScreen-komponentilla, joka toimii modaalisena AlertDialog ikkunana, nykyisen näkymän päällä. Tehtävää lisätessä dialogi avautuu tyhjänä ja tallennus kutsuu addTask-funktiota ja tallentaa arvot syöttökentistä.  Tehtävää clickatessa listasta, dialogi saa parametrina valitun Task-olion tiedot, ja tallennus kutsuu updateTask-funktiota.


# Linkki videoon
https://youtu.be/mf6Wmt9waVg

# APK lataus 
https://github.com/villepekkala/Mobiiliohjelmointi-natiiviteknologioilla/raw/refs/heads/main/app-debug.apk
