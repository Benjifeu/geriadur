let apiWordstem = "/wordstems";

async function showWordstems() {
  await getShowWordstem().then((data) => {
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

    console.log(wordstemRow)
    const tr = tbl.insertRow();
    tr.classList.add("wstr");

    let wsName = tr.insertCell();
    let lang = document.createElement("span");
    lang.classList.add("langws");
    lang.innerText = wordstemRow.wordStemLanguage;
    lang.title = wordstemRow.wordStemLanguage;
    wsName.appendChild(lang);
    wsName.innerHTML +=
      ": <b>" +
      wordstemRow.wordStemName +
      "</b> [" +
      wordstemRow.phonetic +
      "]";

    let wsGender = tr.insertCell();
    let gender = document.createElement("span");
    gender.classList.add("langws");
    gender.innerText = wordstemRow.gender;
    gender.title = wordstemRow.gender;
    wsGender.appendChild(gender);

    let wsClass = tr.insertCell();
    let wordclass = document.createElement("span");
    wordclass.classList.add("langws");
    wordclass.innerText = wordstemRow.wordClass;
    wordclass.title = wordstemRow.wordClass;
    wsClass.appendChild(wordclass);

    let wsRef = tr.insertCell();
    wsRef.innerHTML =
      "<span class=\"langws\" title=\"LF\">LF</span>: <i>" +
      wordstemRow.frTranslation +
      "</i><br> <span class=\"langws\" title=\"LE\">LE</span>: <i>" +
      wordstemRow.engTranslation + "</i>";
    let wsParent = tr.insertCell();
    wsParent.innerText = wordstemRow.parentsWordStemStr;
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

async function getShowWordstem() {
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
  currentpage.innerHTML="Page: " + pageNum;
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
      datalist.sort((a, b) => (a.wordStemName > b.wordStemName) ? 1 : -1);
      break;
    case 1:
      datalist.sort((a, b) => (a.gender > b.gender) ? 1 : -1);
      break;
    case 2:
      datalist.sort((a, b) => (a.wordClass > b.wordClass) ? 1 : -1);
      break;
    case 3:
      datalist.sort((a, b) => (a.frTranslation > b.frTranslation) ? 1 : -1);
      break;
    case 4:
      datalist.sort((a, b) => (a.parentsWordStemStr > b.parentsWordStemStr) ? 1 : -1);
      break;
    case 5:
      datalist.sort((a, b) => (a.semanticField > b.semanticField) ? 1 : -1);
      break;
    default:
      console.log(`No corresponding column to sort.`);
  }
  insertdata(datalist, 1, 10);
}

async function deleteData(id) {
  try {
    const response = await fetch(host + apiWordstem + "?id=" + id, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    });

    // Check if request successfull
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
  } catch (error) {
    console.error(console.log(error));
  }
}

showWordstems(1, 10);
