//const swup = new Swup();
let currentQuestionIndex = 0;
let score = 0;
let apiGame = "/sessionGameData";
let totalQuestionNumber = 0;
let wordTheme = 0;
let level;
var allWordstemsPc = []
let wordStemsOfNewProperNoun = [];

const formElement = document.getElementById("pres");
const currentNameElement = document.getElementById("currentName");
const imgElement = document.getElementById("etymoImg");
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
    const response = await fetch(host + apiGame + "?wordTheme=" + wordTheme, {
      method: "GET"
    }
    );

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
  formElement.innerText = "Radicaux composant le nom:"
  showQuestion();
}

function showQuestion() {
  resetState();
  var currentQuestion = JSON.parse(localStorage.getItem("quizzData"))[
    currentQuestionIndex
  ];
  let questionNo = currentQuestionIndex + 1;
  currentNameElement.innerHTML =
    questionNo + ". " + currentQuestion.properName.currentName;
  if (currentQuestion.properName.image != (null && "null" && '')) {
    var image = new Image();
    image.src = 'data:image/png;base64,' + currentQuestion.properName.image;
    imgElement.appendChild(image);
  }

  etymoDescrElement.innerHTML = currentQuestion.properName.descr;
  currentQuestion.pcelticRadicals.map((radical) => {
    const etymonButton = document.createElement("button");
    etymonButton.textContent = radical.name.toString();
    etymonButton.setAttribute("alt", radical.translation);
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
    fillProgressGauge();
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
  while (imgElement.firstChild) {
    imgElement.removeChild(imgElement.firstChild);
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

  answerButtonElement.innerHTML = `Votre score et de ${score} sur ${totalQuestionNumber}`;
  nextButtonElement.innerHTML = "Rejouer";
  nextButtonElement.style.display = "block";
}
function chooseWordTheme() {
  formElement.innerText = "Choisissez un thème lexical:"
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
  button5.textContent = "Armes et Créatures";
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
    button1.onclick = lvl.target.dataset.lvl;
    answerButtonElement.appendChild(button1);
    const button2 = document.createElement("button");
    button1.textContent = "Checkpoint";
    button1.classList.add("btn");
    button1.dataset.lvl = "2";
    button1.onclick = lvl.target.dataset.lvl;
    answerButtonElement.appendChild(button2);
}*/

function fillProgressGauge() {
  let progressBar = document.getElementById("progressBar");
  progressBar.style.width = (score * 6.6) + "%";
}

function setWordTheme(wt) {
  wordTheme = wt.target.dataset.wt;
  resetState();
  //chooseLevel()
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

var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("modalAddPropreNounBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];


localStorage.setItem("wordstemPC", wordStemsOfNewProperNoun);

// When the user clicks the button, open the modal 
btn.onclick = function () {
  modal.style.display = "block";
  getRadicalsPC();
  console.log(localStorage.getItem("radicalsPC"))
  autocomplete(document.getElementById("radicalPCInput"));
}

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}


async function getRadicalsPC() {
  try {
    const response = await fetch(host + "/wordStems/Str", {
      method: "GET"
    }
    );

    // Check if request successfull
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const data = await response.json();
    localStorage.setItem("radicalsPC", JSON.stringify(data));
  } catch (error) {
    console.error(error);
  }
}


class ProperNounDTO {
  constructor(currentName, etymoName, wordStemsPC, descrFr, descrEng, wordTheme, litTransFr, litTransEng, litTransType) {
    this.currentName = currentName;
    this.etymoName = etymoName;
    this.wordStemsPC = wordStemsPC; 
    this.descrFr = descrFr;
    this.descrEng = descrEng;
    this.wordTheme = wordTheme;
    this.litTransFr = litTransFr;
    this.litTransEng = litTransEng;
    this.litTransType = litTransType;
  }
}


function autocomplete(inp) {
  var radicalsPC = JSON.parse(localStorage.getItem("radicalsPC"));

  /*the autocomplete function takes two arguments,
  the text field element and an array of possible autocompleted values:*/
  var currentFocus;
  inp.addEventListener("input", function (e) {
    var autocompleteList, autocompleteWordDiv, val = this.value;
    closeAllLists();
    if (!val) { return false; }
    currentFocus = -1;
    autocompleteList = document.createElement("DIV");
    autocompleteList.setAttribute("id", this.id + "autocomplete-list");
    autocompleteList.setAttribute("class", "autocomplete-items");
    this.parentNode.appendChild(autocompleteList);
    for (let i = 0; i < radicalsPC.length; i++) {
      if (radicalsPC[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
        autocompleteWordDiv = document.createElement("DIV");
        /*make the matching letters bold:*/
        autocompleteWordDiv.innerHTML = "<strong>" + radicalsPC[i].substr(0, val.length) + "</strong>";
        autocompleteWordDiv.innerHTML += radicalsPC[i].substr(val.length);
        /*insert a input field that will hold the current array item's value:*/
        autocompleteWordDiv.innerHTML += "<input type='hidden' value='" + radicalsPC[i] + "'>";
        /*execute a function when someone clicks on the item value (DIV element):*/
        autocompleteWordDiv.addEventListener("click", function (e) {
          /*insert the value for the autocomplete text field:*/
          inp.value = this.getElementsByTagName("input")[0].value;
          wordStemsOfNewProperNoun.push(inp.value)
          console.log(wordStemsOfNewProperNoun);
          document.getElementById("wordstemList").innerHTML = wordStemsOfNewProperNoun;
          this.getElementsByTagName("input")[0].value = "";
          closeAllLists();
        });
        autocompleteList.appendChild(autocompleteWordDiv);
      }
    }
  });
  /*execute a function presses a key on the keyboard:*/
  inp.addEventListener("keydown", function (e) {
    var x = document.getElementById(this.id + "autocomplete-list");
    if (x) x = x.getElementsByTagName("div");
    if (e.keyCode == 40) {
      /*If the arrow DOWN key is pressed,
      increase the currentFocus variable:*/
      currentFocus++;
      /*and and make the current item more visible:*/
      addActive(x);
    } else if (e.keyCode == 38) { //up
      /*If the arrow UP key is pressed,
      decrease the currentFocus variable:*/
      currentFocus--;
      /*and and make the current item more visible:*/
      addActive(x);
    } else if (e.keyCode == 13) {
      /*If the ENTER key is pressed, prevent the form from being submitted,*/
      e.preventDefault();
      if (currentFocus > -1) {
        /*and simulate a click on the "active" item:*/
        if (x) x[currentFocus].click();
      }
    }
  });

  function addActive(x) {
    /*a function to classify an item as "active":*/
    if (!x) return false;
    /*start by removing the "active" class on all items:*/
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
    /*add class "autocomplete-active":*/
    x[currentFocus].classList.add("autocomplete-active");
  }

  function removeActive(x) {
    /*a function to remove the "active" class from all autocomplete items:*/
    for (var i = 0; i < x.length; i++) {
      x[i].classList.remove("autocomplete-active");
    }
  }

  function closeAllLists(elmnt) {
    /*close all autocomplete lists in the document,
    except the one passed as an argument:*/
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      if (elmnt != x[i] && elmnt != inp) {
        x[i].parentNode.removeChild(x[i]);
      }
    }
  }

  /*execute a function when someone clicks in the document:*/
  document.addEventListener("click", function (e) {
    closeAllLists(e.target);
  });
}


document.getElementById('properNounForm').addEventListener('submit', function(event) {
  event.preventDefault(); // Prevent default form submission
console.log(document.getElementById('pNCurrentName').value);
console.log(document.getElementById('pNEtymoName').value);
console.log(document.getElementById('descrFr').value);

  const properNounDTO= new ProperNounDTO(
    document.getElementById('pNCurrentName').value,
    document.getElementById('pNEtymoName').value,
    wordStemsOfNewProperNoun, 
    document.getElementById('descrFr').value,
    document.getElementById('descrEng').value,
    parseInt(document.getElementById('wordTheme').value),
    document.getElementById('litTransFr').value,
    document.getElementById('litTransEng').value,
    parseInt(document.getElementById('litTransType').value)
  );
  
  console.log(properNounDTO);

  fetch(host + "/properNouns", {
      method: 'POST',
      body: JSON.stringify(properNounDTO),
      headers: {
        "Content-Type": "application/json",
      }
  })
  .then(response => {
      if (response.ok) {
          console.log(response.status); 
      }
      throw new Error('Network response was not ok.');
  })
});