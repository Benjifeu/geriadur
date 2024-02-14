let currentQuestionIndex = 0;
let score = 0;
let apiGame = "/sessionGameData";
let totalQuestionNumber = 0;
let wordTheme = 0;
let level;
const currentNameElement = document.getElementById("currentName");
const etymoNameElement = document.getElementById("etymoName");
const answerButtonElement = document.getElementById("answer-btn");
const nextButtonElement = document.getElementById("next-btn");
const etymoDescrElement = document.getElementById("etymoDescription");
const levelElement = document.getElementById("level-btn");

nextButtonElement.addEventListener("click", () => {
  if (currentQuestionIndex < totalQuestionNumber) {
    handleNextButton();
  } else {
    startQuiz();
  }
});

chooseWordTheme();

async function getData() {
  try {
    const response = await fetch(host + apiGame + "?wordTheme=" + wordTheme);

    // Check if request successfull
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const data = await response.json();
    localStorage.setItem("quizzData", JSON.stringify(data));

    let obj = JSON.parse(localStorage.getItem("quizzData"));
    totalQuestionNumber = Object.keys(obj).length;
  } catch (error) {
    console.error(console.log(error));
  }
  showQuestion();
}

function showQuestion() {
  resetState();
  var currentQuestion = JSON.parse(localStorage.getItem("quizzData"))[
    currentQuestionIndex
  ];
  console.log(currentQuestion);
  let questionNo = currentQuestionIndex + 1;
  currentNameElement.innerHTML =
    questionNo + ". " + currentQuestion.properName.currentName;
  etymoDescrElement.innerHTML = currentQuestion.properName.descr;
  currentQuestion.pCelticRadicals.map((radical) => {
    const etymonButton = document.createElement("button");
    etymonButton.textContent = radical.name.toString();
    etymonButton.setAttribute("title", radical.translation);
    etymonButton.classList.add("etymoBtn2");
    etymoNameElement.appendChild(etymonButton);
  });
  currentQuestion.proposedLiteralTranslationList.map((answer) => {
    const button = document.createElement("button");
    console.log(answer.responseChoice.toString());
    button.textContent = answer.responseChoice.toString();
    button.classList.add("btn");
    answerButtonElement.appendChild(button);
    if (answer.correctness) {
      button.dataset.correctness = answer.correctness;
    }
    button.onclick = selectAnswer;
    //button.addEventListener("click", selectAnswer)
  });
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
  Array.from(answerButtonElement.children).forEach((button) => {
    if (button.dataset.correctness === "true") {
      button.classList.add("correct");
    }
    button.disabled = true;
  });
  nextButtonElement.style.display = "block";
}

function resetState() {
  nextButtonElement.style.display = "none";
  while (answerButtonElement.firstChild) {
    answerButtonElement.removeChild(answerButtonElement.firstChild);
  }
  etymoNameElement.innerText = "";
}

function handleNextButton() {
  currentQuestionIndex++;
  console.log(currentQuestionIndex);
  console.log(totalQuestionNumber);
  console.log(score);
  if (currentQuestionIndex < totalQuestionNumber) {
    showQuestion();
  } else {
    showScore();
  }
}

function showScore() {
  resetState();
  fetch(host + apiGame + "/saveResult", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ sessionScore: score }),
  })
    .then()
    .then((response) => console.log(response));

  answerButtonElement.innerHTML = `You scored ${score} out of ${totalQuestionNumber}`;
  nextButtonElement.innerHTML = "Play Again";
  nextButtonElement.style.display = "block";
}

function chooseWordTheme() {
  const button1 = document.createElement("button");
  button1.textContent = "Lieux et Pays";
  button1.classList.add("btn");
  button1.dataset.wt = "1";
  button1.onclick = setWordTheme;
  answerButtonElement.appendChild(button1);
  const button2 = document.createElement("button");
  button2.textContent = "Figures Historiques";
  button2.classList.add("btn");
  button2.dataset.wt = "2";
  button2.onclick = setWordTheme;
  answerButtonElement.appendChild(button2);
  const button3 = document.createElement("button");
  button3.textContent = "Figures Mythiques";
  button3.classList.add("btn");
  button3.dataset.wt = "3";
  button3.onclick = setWordTheme;
  answerButtonElement.appendChild(button3);
  const button4 = document.createElement("button");
  button4.textContent = "Peuples et Tribus";
  button4.classList.add("btn");
  button4.dataset.wt = "4";
  button4.onclick = setWordTheme;
  answerButtonElement.appendChild(button4);
  const button5 = document.createElement("button");
  button5.textContent = "Objets divers";
  button5.classList.add("btn");
  button5.dataset.wt = "5";
  button5.onclick = setWordTheme;
  answerButtonElement.appendChild(button5);
}

/* The level option has been desactivated for the moment
function chooseLevel() {
    const button1 = document.createElement("button");
    button1.textContent = "Entrainement";
    button1.classList.add("btn");
    button1.dataset.lvl = "1";
    button1.onclick = setLevel;
    answerButtonElement.appendChild(button1);
    const button2 = document.createElement("button");
    button1.textContent = "Checkpoint";
    button1.classList.add("btn");
    button1.dataset.lvl = "2";
    button1.onclick = setLevel;
    answerButtonElement.appendChild(button2);
}*/

function setWordTheme(wt) {
  wordTheme = wt.target.dataset.wt;
  resetState();
  //chooseLevel()
}
function setLevel(lvl) {
  level = lvl.target.dataset.lvl;
  startQuiz();
}
function startQuiz() {
  console.log(wordTheme);
  if (wordTheme > 0 && wordTheme < 6) {
    currentQuestionIndex = 0;
    score = 0;
    getData();
  }
}
