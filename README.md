# Viikko 5

# Retrofit

Sovellus käyttää Retrofit kirjastoa verkkoliikenteen hallintaan. Retrofitin avulla API on määritelty kotlin interfacena WeatherApi tiedostossa. Kirjasto siis hoitaa pyyntöjen muodostamisen, lähettämisen ja vastaanottamisen.

# Json-muunnos
OpenWeatherMap API palauttaa datan json muodossa, joten sovellus käyttää gson-muunninta, joka toimii Retrofitin taustalla. Gson muokkaa saapuvan JSON tekstin suoraan kotlinin dataluokiksi.

# Coroutines
 API kutsut suoritetaan Kotlin Coroutinesin avulla käyttämällä suspend funktiota WeatherApi interfacessa. Viewmodel käyttää viewModelScope.launchia haun käynnistämiseen. Tämä sitten varmistaa, että sovellus pyörii sujuvasti kun Ui päivittyy vasta kun data on saapunut.
 
# Ui-tila
ViewModelissa hallinnoi WeatherUiState oliota, jolla on eri tiloja jotka ovat reaktiivisia. Eli kun tila muuttuu niin Ui piirtyy uudelleen JetPack composen avulla jossa käytetään Compose funktiota.

# Api-key
Api avain on tallennettu local.properties tiedostoon, josta sitten gradle lukee sen ja luo BuildConfig-luokan. Avainta sitten käytetään kun viitataan build.config.api_key muuttujaan.

# Youtube
https://youtu.be/1HmN0Hy1L0Y

# APK
https://github.com/villepekkala/Mobiiliohjelmointi-natiiviteknologioilla/raw/refs/heads/week5-branch/app-debug.apk
