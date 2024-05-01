let apiNouns = "/properNouns";
var allWordstemsPc = []
let wordStemsOfNewProperNoun = [];

async function showWordstems() {
  await getWordstemList().then((data) => {
    console.log(data);
    localStorage.setItem("nounlist", JSON.stringify(data));
  });
  sortTable(0);
}

function insertdata(data, pageNum, pageSize) {
  console.log(data);
  let tbl = document.getElementById("nountable");
  tbl.innerText = "";
  data.filter((row, index) => {
    if (index >= (pageNum - 1) * pageSize && index < (pageNum) * pageSize) return true;
  }).forEach(nounRow => {

    console.log(nounRow);

    const tr = tbl.insertRow();
    tr.classList.add("wstr");

    let wsName = tr.insertCell();
    wsName.innerHTML =
      "<b><a href=" +
      host + "/lexique/entree?nom=" + nounRow.currentName +
      "> " + nounRow.currentName + "</b></a>";

    let wsWordTheme = tr.insertCell();
    wsWordTheme.innerText = getWordTheme(nounRow.wordTheme);
    wsWordTheme.classList.add("cell");

    let period = tr.insertCell();
    period.innerText = nounRow.period;
    period.classList.add("cell");

    let place = tr.insertCell();
    place.innerText = nounRow.place;
    place.classList.add("cell");

    let country = tr.insertCell();
    country.innerText = nounRow.country;
    country.classList.add("cell");

    let shortDescr = tr.insertCell();
    shortDescr.innerText = nounRow.shortDescrFr;
    shortDescr.classList.add("cell");
  });

  let wsCount = document.getElementById("words-count");
  wsCount.innerText = "Nombre total de mots: " + data.length;

  setLanguages();
  setPagesButton(data, pageNum, pageSize);
}

async function getWordstemList() {
  try {
    const response = await fetch(
      host + apiNouns,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    // Check if request successfull
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const data = await response.json();
    return data;

  } catch (error) {
    console.error(console.log(error));
  }
}

function getWordTheme(wordTheme) {
  switch (wordTheme) {
    case 1:
      return "Ville et Pays";
    case 2:
      return "Fig. historique";
    case 3:
      return "Fig. mythiques";
    case 4:
      return "Peuples et Tribus";
    case 5:
      return "Armes et Créatures";
    default:
      console.log(`No corresponding wortheme for the number.`);
  }
}


function sortTable(columnIndex) {
  let datalist = JSON.parse(localStorage.getItem("nounlist"))
  switch (columnIndex) {
    case 0:
      datalist.sort((a, b) => (a.currentName > b.currentName) ? 1 : -1);
      break;
    case 1:
      datalist.sort((a, b) => (a.wordTheme > b.wordTheme) ? 1 : -1);
      break;
    case 2:
      datalist.sort((a, b) => (a.year > b.year) ? 1 : -1);
      break;
    case 3:
      datalist.sort((a, b) => (a.place > b.place) ? 1 : -1);
      break;
    case 4:
      datalist.sort((a, b) => (a.country > b.country) ? 1 : -1);
      break;
    case 5:
      datalist.sort((a, b) => (a.shortDescrFr > b.shortDescrFr) ? 1 : -1);
      break;
    default:
      console.log(`No corresponding column to sort.`);
  }
  insertdata(datalist, 1, 10);
}

showWordstems(1, 10);




var modalForm = document.getElementById("modal-bg");

// Get the button that opens the modal
var addProperNounbtn = document.getElementById("modalAddPropreNounBtn");

// Get the <span> element that closes the modal
var closeBtn = document.getElementsByClassName("close")[0];


localStorage.setItem("wordstemPC", wordStemsOfNewProperNoun);

// When the user clicks the button, open the modal 
addProperNounbtn.onclick = function () {
  modalForm.style.display = "block";
  getRadicalsPC();
  console.log(localStorage.getItem("radicalsPC"))
  autocomplete(document.getElementById("radicalPCInput"));
}

// When the user clicks on <span> (x), close the modal
closeBtn.onclick = function () {
  modalForm.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
  if (event.target == modalForm) {
    modalForm.style.display = "none";
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
  constructor(currentName, etymoName, wordStemsPC, descrFr, descrEng, shortDescrFr, shortDescrEng, wordTheme, litTransFr, litTransEng,litTransType,
     place, country, period, year) {
    this.currentName = currentName;
    this.etymoName = etymoName;
    this.wordStemsPC = wordStemsPC;
    this.descrFr = descrFr;
    this.descrEng = descrEng;
    this.shortDescrFr = shortDescrFr;
    this.shortDescrEng = shortDescrEng;
    this.wordTheme = wordTheme;
    this.litTransFr = litTransFr;
    this.litTransEng = litTransEng;
    this.litTransType = litTransType;
    this.place = place;
    this.country = country;
    this.period = period;
    this.year = year;
  }
}

class SourceBasicDTO {
  constructor(sourceOriginalName, sourceAbbreviation) {
    this.sourceOriginalName = sourceOriginalName;
    this.sourceAbbreviation = sourceAbbreviation;
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


document.getElementById('properNounForm').addEventListener('submit', function (event) {
  event.preventDefault();
  console.log(document.getElementById('pNCurrentName').value);
  console.log(document.getElementById('pNEtymoName').value);
  console.log(document.getElementById('descrFr').value);

  const properNounDTO = new ProperNounDTO(
    document.getElementById('pNCurrentName').value,
    document.getElementById('pNEtymoName').value,
    wordStemsOfNewProperNoun,
    document.getElementById('descrFr').value,
    document.getElementById('descrEng').value,
    null,null,
    parseInt(document.getElementById('wordTheme').value),
    document.getElementById('litTransFr').value,
    document.getElementById('litTransEng').value,
    parseInt(document.getElementById('litTransType').value),
    null, null,null,null
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
  modalForm.style.display = "none";
});




let lottieimg = document.getElementById("lottieSubmitted");
let animation;

animation = lottie.loadAnimation({
  container: lottieimg,
  renderer: 'svg',
  loop: false,
  autoplay: false,
  path: "../images/lottie_submit.json"
});

let addlottie = document.getElementById("submitProperNoun");


addlottie.addEventListener("click", () => {

  lottieimg.style.display = "block";
  animation.goToAndPlay(0, true);
  animationCompleted = false;
  setTimeout(endLotti, 3000);
});

function endLotti() {
  lottieimg.style.display = "none"
}