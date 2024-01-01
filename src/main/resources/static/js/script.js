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


let currentQuestionIndex = 0;
let score = 0;
let url = 'http://localhost:8080/sessionGame/get'
let totalQuestionNumber = 0;
let wordTheme = 0;
const currentNameElement = document.getElementById("currentName")
const etymoNameElement = document.getElementById("etymoName")
const translationNameElement = document.getElementById("translationName")
const answerButton = document.getElementById("answer-buttons")
const nextButton = document.getElementById("next-btn")
const returnButton = document.getElementById("return-btn")

chooseWordTheme()


function getData() {
    fetch(url + "?wordTheme=" + wordTheme)
        .then(res => {
            return res.json();
        })
        .then(data => {
            localStorage.setItem('quizzData', JSON.stringify(data));
            obj = JSON.parse(localStorage.getItem('quizzData'))
            totalQuestionNumber = Object.keys(obj).length
        })
        .catch(error => console.log(error));
}

function showQuestion() {
    resetState()
    var currentQuestion = JSON.parse(localStorage.getItem('quizzData'))[currentQuestionIndex];
    console.log(currentQuestion)
    let questionNo = currentQuestionIndex + 1;
    currentNameElement.innerHTML = questionNo + ". " + currentQuestion.properName.currentName;
    etymoNameElement.innerHTML = "Forme celtique: ";
    translationNameElement.innerHTML = "Traduction: ";
    currentQuestion.pCelticRadicals.map((radical) =>{
    etymoNameElement.innerHTML += radical.name + " - ";
    translationNameElement.innerHTML += radical.translation + " - "})
    currentQuestion.proposedLiteralTranslationList.map(answer => {
        const button = document.createElement("button");
        console.log(answer.responseChoice.toString())
        button.textContent = answer.responseChoice.toString();
        button.classList.add("btn");
        answerButton.appendChild(button);
        if (answer.correctness) {
            button.dataset.correctness = answer.correctness;
        }
        button.onclick = selectAnswer;
        //button.addEventListener("click", selectAnswer)
    })
}

function selectAnswer(e) {
    const selectedBtn = e.target;
    const isCorrect = selectedBtn.dataset.correctness === "true";
    if (isCorrect) {
        console.log(selectedBtn.dataset.correctness);
        //selectedBtn.style.background = "#9aeabc";
        selectedBtn.classList.add("correct");
        score++;
    } else {
        selectedBtn.classList.add("incorrect");
    }
    Array.from(answerButton.children).forEach(button => {
        if (button.dataset.correctness === "true") {
            button.classList.add("correct");
        }
        button.disabled = true;
    });
    nextButton.style.display = "block";
}

function resetState() {
    nextButton.style.display = "none";
    while (answerButton.firstChild) {
        answerButton.removeChild(answerButton.firstChild)
    }
}

nextButton.addEventListener("click", () => {
    if (currentQuestionIndex < totalQuestionNumber) {
        handleNextButton();
    } else {
        startQuiz()

    }
})

function handleNextButton() {
    currentQuestionIndex++;
    console.log(currentQuestionIndex)
    console.log(totalQuestionNumber)
    console.log(score)
    if (currentQuestionIndex < totalQuestionNumber) {
        showQuestion();
    } else {
        showScore();
    }

}

function showScore() {
    resetState();
    fetch('http://localhost:8080/sessionGame/saveResult', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ "sessionScore": score })
    })
        .then(response => console.log(response))

    answerButton.innerHTML = `You scored ${score} out of ${totalQuestionNumber}`;
    nextButton.innerHTML = "Play Again";
    nextButton.style.display = "block";
}

function chooseWordTheme() {
    const button1 = document.createElement("button");
    button1.textContent = "Lieux et Pays";
    button1.classList.add("btn");
    button1.dataset.wt = "1";
    button1.onclick = setWordTheme;
    answerButton.appendChild(button1);
    const button2 = document.createElement("button");
    button2.textContent = "Figures Historiques";
    button2.classList.add("btn");
    button2.dataset.wt = "2";
    button2.onclick = setWordTheme;
    answerButton.appendChild(button2);
    const button3 = document.createElement("button");
    button3.textContent = "Figures Mythiques";
    button3.classList.add("btn");
    button3.dataset.wt = "3";
    button3.onclick = setWordTheme;
    answerButton.appendChild(button3);
    const button4 = document.createElement("button");
    button4.textContent = "Peuples et Tribus";
    button4.classList.add("btn");
    button4.dataset.wt = "4";
    button4.onclick = setWordTheme;
    answerButton.appendChild(button4);
    const button5 = document.createElement("button");
    button5.textContent = "Armes et Objets";
    button5.classList.add("btn");
    button5.dataset.wt = "5";
    button5.onclick = setWordTheme;
    answerButton.appendChild(button5);
}

function setWordTheme(wt) {
    wordTheme = wt.target.dataset.wt;
    startQuiz()
}

function startQuiz() {
    console.log(wordTheme)
    if (wordTheme > 0 && wordTheme < 6) {
        currentQuestionIndex = 0;
        score = 0;
        getData();
        showQuestion();
    }

}

