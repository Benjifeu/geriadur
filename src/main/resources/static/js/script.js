const bretonLanguage = ["breton", "breton","brezhoneg"]
const cornishLanguage = ["cornish", "cornique","kerneveureg"]
const englishLanguage = ["english", "anglais","saozneg"]
const frenchLanguage = ["french", "français","galleg"]
const gaulishLanguage = ["gaulish", "gaulois","galianeg"]
const IELanguage = ["Indo-Eur.", "Indo-Eur","Indez-Eur"]
const irishLanguage = ["irish", "irlandais","iwerzhoneg"]
const scotLanguage = ["scottish", "écossais","skoseg"]
const protoCeltLanguage = ["pr.celtic", "pr.celtic","kt.keltiek"]
const welshLanguage = ["welsh", "gallois","kembraeg"]

const brLanguage = ["br.", "br.","br."]
const corLanguage = ["cor.", "cor.","ker."]
const engLanguage = ["eng.", "ang.","sa."]
const frLanguage = ["fr.", "fr.","gal."]
const gauLanguage = ["gal.", "gau.","gau."]
const indLanguage = ["pr.I.E.", "pr.I.E.","k.I.E."]
const irLanguage = ["ir.", "irl.","iw."]
const scLanguage = ["sc.", "eco.","sko."]
const pCeltLanguage = ["pr.c.", "pr.c.","kt.k."]
const welLanguage = ["wel.", "gal.","kem."]

const nameWClass = ["name", "nom","anv"]
const verbWClass = ["verb", "verbe","verb"]
const adjWClass = ["adjective", "adjectif","anv-gwan"]
const advWClass = ["adverb", "adverbe","ragverb"]
const artWClass = ["article", "article","doareenn"]
const prnWClass = ["pronoun", "pronom.","raganv"]
const prepWClass = ["preposition", "préposition.","araogenn."]
const conjWClass = ["conjunction", "conjonction","stagell"]

const nameWClassAbr = ["name", "nom","anv"]
const verbWClassAbr = ["vrb.", "vrb.","vrb."]
const adjWClassAbr = ["adj.", "adj.","a.-gw."]
const advWClassAbr = ["adv.", "adv.","rgv."]
const artWClassAbr = ["art.", "art.","doa."]
const prnWClassAbr = ["prn.", "prn.","rag."]
const prepWClassAbr = ["prp.", "prp.","ara."]
const conjWClassAbr = ["cnj.", "cnj.","stg."]

const mGenderAbr = ["m.", "m.","g."]
const fGenderAbr = ["f.", "f.","b."]
const nGenderAbr = ["n.", "n.","n."]

const mGender = ["male", "masculin","gourel"]
const fGender = ["feminine", "féminin","benel"]
const nGender = ["neutral", "neutre","neptu"]


const siteLanguage =1
const lang =
    document.querySelectorAll("span.langEtymon");
const genre =    document.querySelectorAll("span.genEtymon");

for (var i = 0; i < lang.length; i++) {
    lang[i].title=lang[i].title.replace("LB", bretonLanguage[siteLanguage])
    lang[i].title=lang[i].title.replace("LC", cornishLanguage[siteLanguage])
    lang[i].title=lang[i].title.replace("LE", englishLanguage[siteLanguage])
    lang[i].title=lang[i].title.replace("LF", frenchLanguage[siteLanguage])
    lang[i].title=lang[i].title.replace("LG", gaulishLanguage[siteLanguage])
    lang[i].title=lang[i].title.replace("LI", irishLanguage[siteLanguage])
    lang[i].title=lang[i].title.replace("LIE", IELanguage[siteLanguage])
    lang[i].title=lang[i].title.replace("LP", protoCeltLanguage[siteLanguage])
    lang[i].title=lang[i].title.replace("LS", scotLanguage[siteLanguage])
    lang[i].title=lang[i].title.replace("LW", welshLanguage[siteLanguage])
    lang[i].title=lang[i].title.replace("WN", nameWClass[siteLanguage])
    lang[i].title=lang[i].title.replace("WV", verbWClass[siteLanguage])
    lang[i].title=lang[i].title.replace("GM", mGender[siteLanguage])
    lang[i].title=lang[i].title.replace("GF", fGender[siteLanguage])
    lang[i].title=lang[i].title.replace("GN", nGender[siteLanguage])


    lang[i].innerHTML=lang[i].innerHTML.replace("LB", brLanguage[siteLanguage])
    lang[i].innerHTML=lang[i].innerHTML.replace("LC", corLanguage[siteLanguage])
    lang[i].innerHTML=lang[i].innerHTML.replace("LE", engLanguage[siteLanguage])
    lang[i].innerHTML=lang[i].innerHTML.replace("LF", frLanguage[siteLanguage])
    lang[i].innerHTML=lang[i].innerHTML.replace("LG", gauLanguage[siteLanguage])
    lang[i].innerHTML=lang[i].innerHTML.replace("LI", irLanguage[siteLanguage])
    lang[i].innerHTML=lang[i].innerHTML.replace("LIE", indLanguage[siteLanguage])
    lang[i].innerHTML=lang[i].innerHTML.replace("LP", pCeltLanguage[siteLanguage])
    lang[i].innerHTML=lang[i].innerHTML.replace("LS", scLanguage[siteLanguage])
    lang[i].innerHTML=lang[i].innerHTML.replace("LW", welLanguage[siteLanguage])
    lang[i].innerHTML=lang[i].innerHTML.replace("WN", nameWClassAbr[siteLanguage])
    lang[i].innerHTML=lang[i].innerHTML.replace("WV", verbWClassAbr[siteLanguage])
    lang[i].innerHTML=lang[i].innerHTML.replace("WN", verbWClassAbr[siteLanguage])
    lang[i].innerHTML=lang[i].innerHTML.replace("GM", mGenderAbr[siteLanguage])
    lang[i].innerHTML=lang[i].innerHTML.replace("GF", fGenderAbr[siteLanguage])
    lang[i].innerHTML=lang[i].innerHTML.replace("GN", nGenderAbr[siteLanguage])

}