const lang = document.querySelectorAll("span.langLexeme");
const genre = document.querySelectorAll("span.genLexeme");

for (var i = 0; i < lang.length; i++) {
    lang[i].title = lang[i].title.replace("LB", bretonLanguage[siteLanguage])
    lang[i].title = lang[i].title.replace("LC", cornishLanguage[siteLanguage])
    lang[i].title = lang[i].title.replace("LE", englishLanguage[siteLanguage])
    lang[i].title = lang[i].title.replace("LF", frenchLanguage[siteLanguage])
    lang[i].title = lang[i].title.replace("LG", gaulishLanguage[siteLanguage])
    lang[i].title = lang[i].title.replace("LI", irishLanguage[siteLanguage])
    lang[i].title = lang[i].title.replace("LIE", IELanguage[siteLanguage])
    lang[i].title = lang[i].title.replace("LP", protoCeltLanguage[siteLanguage])
    lang[i].title = lang[i].title.replace("LS", scotLanguage[siteLanguage])
    lang[i].title = lang[i].title.replace("LW", welshLanguage[siteLanguage])
    lang[i].title = lang[i].title.replace("WN", nameWClass[siteLanguage])
    lang[i].title = lang[i].title.replace("WV", verbWClass[siteLanguage])
    lang[i].title = lang[i].title.replace("GM", mGender[siteLanguage])
    lang[i].title = lang[i].title.replace("GF", fGender[siteLanguage])
    lang[i].title = lang[i].title.replace("GN", nGender[siteLanguage])


    lang[i].innerHTML = lang[i].innerHTML.replace("LB", brLanguage[siteLanguage])
    lang[i].innerHTML = lang[i].innerHTML.replace("LC", corLanguage[siteLanguage])
    lang[i].innerHTML = lang[i].innerHTML.replace("LE", engLanguage[siteLanguage])
    lang[i].innerHTML = lang[i].innerHTML.replace("LF", frLanguage[siteLanguage])
    lang[i].innerHTML = lang[i].innerHTML.replace("LG", gauLanguage[siteLanguage])
    lang[i].innerHTML = lang[i].innerHTML.replace("LI", irLanguage[siteLanguage])
    lang[i].innerHTML = lang[i].innerHTML.replace("LIE", indLanguage[siteLanguage])
    lang[i].innerHTML = lang[i].innerHTML.replace("LP", pCeltLanguage[siteLanguage])
    lang[i].innerHTML = lang[i].innerHTML.replace("LS", scLanguage[siteLanguage])
    lang[i].innerHTML = lang[i].innerHTML.replace("LW", welLanguage[siteLanguage])
    lang[i].innerHTML = lang[i].innerHTML.replace("WN", nameWClassAbr[siteLanguage])
    lang[i].innerHTML = lang[i].innerHTML.replace("WV", verbWClassAbr[siteLanguage])
    lang[i].innerHTML = lang[i].innerHTML.replace("WN", verbWClassAbr[siteLanguage])
    lang[i].innerHTML = lang[i].innerHTML.replace("GM", mGenderAbr[siteLanguage])
    lang[i].innerHTML = lang[i].innerHTML.replace("GF", fGenderAbr[siteLanguage])
    lang[i].innerHTML = lang[i].innerHTML.replace("GN", nGenderAbr[siteLanguage])
}


let result_of_parsing_json;
let currentQuestionIndex = 0;
let score = 0;
let url = 'http://localhost:8080/sessionGame/get'
var jsonData = '';
const etymonElement = document.getElementById("etymonName");
const answerButton = document.getElementById("answer-buttons");
const nextButton = document.getElementById("next-btn")
startQuiz()


function getData() {
    fetch(url)
        .then(res => {
            return res.json();
        })
        .then(data => {
            var currentQuestion = data[currentQuestionIndex];
            console.log(currentQuestion)
            let questionNo = currentQuestionIndex + 1;
            etymonElement.innerHTML = questionNo + ". " + currentQuestion.etymonName;

            currentQuestion.proposedLiteralTranslationList.map(answer => {
                const button = document.createElement("button");
                console.log(answer.toString())
                button.textContent = answer.toString();
                button.classList.add("btn");
                answerButton.appendChild(button);
            })
        })
        .then(response => console.log(JSON.stringify(response)))
        .catch(error => console.log(error));
}

    /*
    function showQuestion() {
        var currentQuestion = jsonData[currentQuestionIndex];
        console.log(currentQuestion)
        let questionNo = currentQuestionIndex + 1;
        questionElement.innerHTML = questionNo + ". " + currentQuestion.etymonName;

        currentQuestion.proposedLiteralTranslationList.forEach(answer => {
            const button = document.createElement("button");
            button.innerHTML = answer.text();
            button.classList.add("btn");
            answerButton.appendChild(button);
        })
    }*/

    function startQuiz() {
        currentQuestionIndex = 0;
        score = 0;

        getData();
    }

