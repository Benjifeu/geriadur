let host = "http://ec2-51-20-9-64.eu-north-1.compute.amazonaws.com:8081"
//let host = "http://localhost:8081"
const siteLanguage = 1
const language = 1


const bretonLanguage = ["breton", "breton", "brezhoneg"]
const cornishLanguage = ["cornish", "cornique", "kerneveureg"]
const englishLanguage = ["english", "anglais", "saozneg"]
const frenchLanguage = ["french", "français", "galleg"]
const gaulishLanguage = ["gaulish", "gaulois", "galianeg"]
const IELanguage = ["indo-european", "indo-européen", "indezeuropeg"]
const irishLanguage = ["irish", "irlandais", "iwerzhoneg"]
const oldIrishLanguage= ["old irish.", "vieil irlandais", "heniwerzhoneg."]
const scotLanguage = ["scottish", "écossais", "skoseg"]
const protoCeltLanguage = ["proto-celtic", "proto-celtique", "kent-keltiek"]
const welshLanguage = ["welsh", "gallois", "kembraeg"]

const brLanguage = ["br.", "br.", "br."]
const corLanguage = ["cor.", "cor.", "ker."]
const engLanguage = ["eng.", "ang.", "sa."]
const frLanguage = ["fr.", "fr.", "gal."]
const gauLanguage = ["gal.", "gau.", "gau."]
const indLanguage = ["pr.I.E.", "pr.I.E.", "k.I.E."]
const irLanguage = ["ir.", "irl.", "iw."]
const oldILanguage= ["old.ir.", "v.irl.", "h.iw."]
const scLanguage = ["sc.", "eco.", "sko."]
const pCeltLanguage = ["pr.c.", "pr.c.", "kt.k."]
const welLanguage = ["wel.", "gal.", "kem."]

const nameWClass = ["name", "nom", "anv"]
const verbWClass = ["verb", "verbe", "verb"]
const adjWClass = ["adjective", "adjectif", "anv-gwan"]
const advWClass = ["adverb", "adverbe", "ragverb"]
const artWClass = ["article", "article", "doareenn"]
const prnWClass = ["pronoun", "pronom.", "raganv"]
const prepWClass = ["preposition", "préposition.", "araogenn."]
const conjWClass = ["conjunction", "conjonction", "stagell"]
const affixWClass = ["affix", "affixe", "lostger"]

const nameWClassAbr = ["n.", "n.", "an."]
const verbWClassAbr = ["vrb.", "vrb.", "vrb."]
const adjWClassAbr = ["adj.", "adj.", "a.-gw."]
const advWClassAbr = ["adv.", "adv.", "rgv."]
const artWClassAbr = ["art.", "art.", "doa."]
const prnWClassAbr = ["prn.", "prn.", "rag."]
const prepWClassAbr = ["prp.", "prp.", "ara."]
const conjWClassAbr = ["cnj.", "cnj.", "stg."]
const affixWClassAbr = ["aff.", "aff.", "lst"]

const mGenderAbr = ["m.", "m.", "g."]
const fGenderAbr = ["f.", "f.", "b."]
const nGenderAbr = ["n.", "n.", "n."]
const noGenderAbr = ["", "", ""]
const uGenderAbr = ["un.", "inc.", "dia."]

const mGender = ["male", "masculin", "gourel"]
const fGender = ["feminine", "féminin", "benel"]
const nGender = ["neutral", "neutre", "neptu"]
const uGender = ["unknow", "inconnu", "dianav"]
const noGender = ["", "", ""]


function setPagesButton(data, pageNum, pageSize) {
    let pagesBtn = document.getElementById("pagesbutton");
    pagesBtn.innerText = "";
  
    if (pageNum > 1) {
      let prevBtn = document.createElement("button");
      prevBtn.textContent = "Précédent";
      prevBtn.addEventListener("click", () => {
        insertdata(data, pageNum - 1, pageSize);
      });
      pagesBtn.appendChild(prevBtn);
    }
    let currentpage = document.createElement("SPAN");
    currentpage.innerHTML = "Page: " + pageNum;
    pagesBtn.appendChild(currentpage);
    if (pageNum < Math.ceil(data.length / pageSize) - 1) {
      let nextBtn = document.createElement("button");
      nextBtn.textContent = "Suivant";
      nextBtn.addEventListener("click", () => {
        insertdata(data, pageNum + 1, pageSize);
      });
      pagesBtn.appendChild(nextBtn);
    }
    if (pageNum < Math.ceil(data.length / pageSize)) {
      let lastBtn = document.createElement("button");
      lastBtn.textContent = "... " + Math.ceil(data.length / pageSize);
      lastBtn.addEventListener("click", () => {
        insertdata(data, Math.ceil(data.length / pageSize), pageSize);
      });
      pagesBtn.appendChild(lastBtn);
    }
  
  }