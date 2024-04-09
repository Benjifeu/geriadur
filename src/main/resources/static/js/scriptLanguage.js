function setLanguages() {
    const lang = document.getElementsByClassName("langws");
    for (var i = 0; i < lang.length; i++) {
        lang[i].title = lang[i].title.replace("LB", bretonLanguage[siteLanguage])
        lang[i].title = lang[i].title.replace("LC", cornishLanguage[siteLanguage])
        lang[i].title = lang[i].title.replace("LE", englishLanguage[siteLanguage])
        lang[i].title = lang[i].title.replace("LF", frenchLanguage[siteLanguage])
        lang[i].title = lang[i].title.replace("LG", gaulishLanguage[siteLanguage])
        lang[i].title = lang[i].title.replace("LI", irishLanguage[siteLanguage])
        lang[i].title = lang[i].title.replace("LIE", IELanguage[siteLanguage])
        lang[i].title = lang[i].title.replace("LOI", oldIrishLanguage[siteLanguage])
        lang[i].title = lang[i].title.replace("LPC", protoCeltLanguage[siteLanguage])
        lang[i].title = lang[i].title.replace("LS", scotLanguage[siteLanguage])
        lang[i].title = lang[i].title.replace("LW", welshLanguage[siteLanguage])
        lang[i].title = lang[i].title.replace("WN", nameWClass[siteLanguage])
        lang[i].title = lang[i].title.replace("WPREP", prepWClass[siteLanguage])
        lang[i].title = lang[i].title.replace("WV", verbWClass[siteLanguage])
        lang[i].title = lang[i].title.replace("GM", mGender[siteLanguage])
        lang[i].title = lang[i].title.replace("GF", fGender[siteLanguage])
        lang[i].title = lang[i].title.replace("GN", nGender[siteLanguage])
        lang[i].title = lang[i].title.replace("GU", uGender[siteLanguage])

        lang[i].innerHTML = lang[i].innerHTML.replace("LB", brLanguage[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("LC", corLanguage[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("LE", engLanguage[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("LF", frLanguage[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("LG", gauLanguage[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("LI", irLanguage[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("LOI", oldILanguage[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("LIE", indLanguage[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("LPC", pCeltLanguage[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("LS", scLanguage[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("LW", welLanguage[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("WN", nameWClassAbr[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("WV", verbWClassAbr[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("WN", verbWClassAbr[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("WPREP", prepWClassAbr[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("GM", mGenderAbr[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("GF", fGenderAbr[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("GN", nGenderAbr[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("NO", noGenderAbr[siteLanguage])
        lang[i].innerHTML = lang[i].innerHTML.replace("GU", uGenderAbr[siteLanguage])
    }
}

