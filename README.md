# Viikko2

# Compose-tilanhallinta

Composable funktio piirtää UI:n tilan perusteella. Eli kun tilaa seurataan mutableStateOf, niin UI refreshaantuu kun tila muuttuu. Tässä tehtävässä seurattiin listaa ja kun lista muuttui kun sinne lisättin / poistettiin niin UI refreshaantui.

# Miksi ViewModel eikä remember

ViewModel säilyttää arvon koko activityn ajan kun taas rememberissä säilyttää vain Composable funktion ajan. Esim kun tehtävässä kun clickaat, että tehtävä on valmis ja painat "vain valmiit". Tällöin näkyy kaikki missä tehtävät on valmiita eli "true" arvolla. Kun clickaat takaisin alkuperäiseen, painamalla "Palauta" nappia niin se ei muista enään noita vaan nollaa kaiken alkuperäiseen mocklistaan. Sama homma olisi kuin lisäisi tehtävän, tekee sen ja painaa "vain valmiit" ja "palauta" niin ohjelma ei muistaisi tuota.

# Linkki videoon
https://youtu.be/B3UJLkgNm1Q

# APK download
[https://github.com/villepekkala/Mobiiliohjelmointi-natiiviteknologioilla/raw/refs/tags/week2/app-debug.apk](https://github.com/villepekkala/Mobiiliohjelmointi-natiiviteknologioilla/raw/refs/heads/main/app-debug.apk)
