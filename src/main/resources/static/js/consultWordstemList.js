let apiWordstem = "/wordstems";

async function showWordstems() {
  await getWordstemList().then((data) => {
    localStorage.setItem("wordstemlist", JSON.stringify(data));
  });
  sortTable(0);
}

function insertdata(data, pageNum, pageSize) {
  console.log(data);
  let tbl = document.getElementById("wordstemtable");
  tbl.innerText = "";
  data.filter((row, index) => {
    if (index >= (pageNum - 1) * pageSize && index < (pageNum) * pageSize) return true;
  }).forEach(wordstemRow => {

    console.log(wordstemRow);

    const tr = tbl.insertRow();
    tr.classList.add("wstr");
    let wsLang = tr.insertCell();
    let lang = document.createElement("span");
    lang.classList.add("langws");
    lang.innerText = wordstemRow.wordStemLanguage;
    lang.title = wordstemRow.wordStemLanguage;
    wsLang.appendChild(lang);

    let wsName = tr.insertCell();
    wsName.innerHTML =
      "<b><a style=\"color: rgb(50, 50, 150)\" href=" +
      host + "/lexique/entree?nom=" + wordstemRow.wordStemName +
      "> " + wordstemRow.wordStemName + "</b></a>";

    let wsClass = tr.insertCell();
    let wordclass = document.createElement("span");
    wordclass.classList.add("langws");
    wordclass.innerText = wordstemRow.wordClass;
    wordclass.title = wordstemRow.wordClass;
    wsClass.appendChild(wordclass);
    wsClass.innerHTML += "&nbsp";
    let gender = document.createElement("span");
    gender.classList.add("langws");
    gender.innerText = wordstemRow.gender;
    gender.title = wordstemRow.gender;
    wsClass.appendChild(gender);


    let wsRef = tr.insertCell();
    wsRef.innerHTML =
      "<span class=\"langws\" title=\"LF\">LF</span>: <i>" +
      wordstemRow.frTranslation +
      "</i><br> <span class=\"langws\" title=\"LE\">LE</span>: <i>" +
      wordstemRow.engTranslation + "</i>";

    let wsParent = tr.insertCell();
    wsParent.innerText = wordstemRow.firstOccurence;

    let wsSemField = tr.insertCell();
    wsSemField.innerText = wordstemRow.semanticField;
    //let wsDel = tr.insertCell();
    //wsDel.appendChild(document.createElement("button").onclick(deleteData(pageWordstems[i].wordStemId)));
  });

  let wsCount = document.getElementById("words-count");
  wsCount.innerText = "Nombre total de mots: " + data.length;

  setLanguages();
  setPagesButton(data, pageNum, pageSize);
}

async function getWordstemList() {
  try {
    const response = await fetch(
      host + apiWordstem,
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

function sortTable(columnIndex) {
  let datalist = JSON.parse(localStorage.getItem("wordstemlist"))
  switch (columnIndex) {
    case 0:
      datalist.sort((a, b) => (a.wordStemLanguage > b.wordStemLanguage) ? 1 : -1);
      break;
    case 1:
      datalist.sort((a, b) => (a.wordStemName > b.wordStemName) ? 1 : -1);
      break;
    case 2:
      datalist.sort((a, b) => (a.wordClass > b.wordClass) ? 1 : -1);
      break;
    case 3:
      datalist.sort((a, b) => (a.frTranslation > b.frTranslation) ? 1 : -1);
      break;
    case 4:
      datalist.sort((a, b) => (a.firstOccurence > b.firstOccurence) ? 1 : -1);
      break;
    case 5:
      datalist.sort((a, b) => (a.semanticField > b.semanticField) ? 1 : -1);
      break;
    default:
      console.log(`No corresponding column to sort.`);
  }
  insertdata(datalist, 1, 10);
}

async function showWordstemInfo() {
  await getWordstem().then((data) => {
    localStorage.setItem("wordsteminfo", JSON.stringify(data));
  });
  sortTable(0);
}

async function getWordstemList() {
  try {
    const response = await fetch(
      host + apiWordstem,
      {
        method: "GET"
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

var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("modalAddWordstemBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function () {
  modal.style.display = "block";

  getSources();
  let sourceElement = document.getElementById("source");
  let sourcelist = localStorage.getItem("sourcelist");
  console.log(sourcelist)
  JSON.parse(sourcelist).forEach((source) => {
    newOption = document.createElement("option")
    console.log(source)
    newOption.value = source.sourceAbbreviation;
    newOption.innerText = source.sourceOriginalName;
    sourceElement.appendChild(newOption)
  });
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

showWordstems(1, 10);



/*post methods */

class WordstemBasicDTO {
  constructor(wordStemName, wordStemLanguage, phonetic, gender, wordClass, engTranslation, frTranslation, semanticField, firstOccurence, source) {
    this.wordStemName = wordStemName;
    this.wordStemLanguage = wordStemLanguage;
    this.phonetic = phonetic;
    this.gender = gender;
    this.wordClass = wordClass;
    this.engTranslation = engTranslation;
    this.frTranslation = frTranslation;
    this.semanticField = semanticField;
    this.firstOccurence = firstOccurence;
    this.source = source;
  }
}

class SourceBasicDTO{
  constructor(sourceOriginalName,sourceAbbreviation){
    this.sourceOriginalName = sourceOriginalName;
    this.sourceAbbreviation = sourceAbbreviation;
  }
}

document.getElementById('wordstemForm').addEventListener('submit', function (event) {
  event.preventDefault(); // Prevent default form submission

  const wordstemDTO = new WordstemBasicDTO(
    document.getElementById('wordStemName').value,
    document.getElementById('wordStemLanguage').value,
    document.getElementById('phonetic').value,
    document.getElementById('gender').value,
    document.getElementById('wordClass').value,
    document.getElementById('engTranslation').value,
    document.getElementById('frTranslation').value,
    document.getElementById('semanticField').value,
    document.getElementById('firstOccurence').value,
    document.getElementById('source').value
  );

  console.log(wordstemDTO);

  fetch(host + apiWordstem, {
    method: 'POST',
    body: JSON.stringify(wordstemDTO),
    headers: {
      "Content-Type": "application/json",
      // 'Content-Type': 'application/x-www-form-urlencoded',
    }
  })
    .then(response => {
      if (response.ok) {
        console.log(response.status);
      }
      throw new Error('Network response was not ok.');
    })
});

async function getSources() {
  try {
    const response = await fetch(host + "/sources", {
      method: "GET"
    }
    );

    // Check if request successfull
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const data = await response.json();
    localStorage.setItem("sourcelist", JSON.stringify(data));
  } catch (error) {
    console.error(error);
  }
}