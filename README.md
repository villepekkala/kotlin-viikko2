# Viikko3

# MVVM

Lyhenne Model-View-ViewModel. Eli kyseessä on ohjelmointiarkkitehtuuri, missä ohjelmisto jaetaan kolmeen osaan. Model, missä on sovelluksen data. View missä on käyttöliittymä, esim compose funktiot, jotka piirtävät datan ruudulle ja reagoivat syötteisiin. Viewmodel joka yhdistää sitten modelin ja viewin nimensä mukaan ja toimii niin sanotusti siltana näiden välillä. Se tarjoaa Modelin datan, Viewin UI:lle ja vastaanottaa käyttäjän syötteet. MVVM on hyödyllinen, kun on selkeää kellä / missä on "vastuu" tietystä asiasta ja MVVM malli toimii hyvin Composen kanssa, koska UI voi vain kuunella ViewModelin tiloja ja päivittyä automaattisesti.

# Miten StateFlow toimii
Viewmodelissa luodaan MutableStateFlow, joka sisältää tehtävälistan tässä sovelluksessa. UI lukee dataa collectAsState() funktiolla ja kun StateFlow muuttuu niin UI muuttuu automaattisesti Compose funktion avulla.

# Linkki videoon
https://youtu.be/6g9E5AX77gw

