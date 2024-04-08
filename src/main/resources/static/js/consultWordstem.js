let apiWordstem = "/wordstems";
let data;

const countElement = document.getElementById("wordstems-count");
const wordStemNameElement = document.getElementById("wordstems-name");
const currentNameElement = document.getElementById("currentName");

async function showWordstems(currentPage, countByPage) {
  let tbl = document.getElementById("wordstemtable");
  tbl.innerText = "";
  await getShowWordstem(currentPage, countByPage).then((pageData) => {
    console.log(pageData);
    for (i = 0; i < pageData.pageWordstems.length; i++) {
      const tr = tbl.insertRow();
      tr.classList.add("wstr");

      let wsName = tr.insertCell();
      let lang = document.createElement("span");
      lang.classList.add("langws");
      lang.innerText = pageData.pageWordstems[i].wordStemLanguage;
      lang.title = pageData.pageWordstems[i].wordStemLanguage;
      wsName.appendChild(lang);
      wsName.innerHTML +=
        ": <b>" +
        data.pageWordstems[i].wordStemName +
        "</b> [" +
        data.pageWordstems[i].phonetic +
        "]";

      let wsGender = tr.insertCell();
      let gender = document.createElement("span");
      gender.classList.add("langws");
      gender.innerText = pageData.pageWordstems[i].gender;
      gender.title = pageData.pageWordstems[i].gender;
      wsGender.appendChild(gender);

      let wsClass = tr.insertCell();
      let wordclass = document.createElement("span");
      wordclass.classList.add("langws");
      wordclass.innerText = pageData.pageWordstems[i].wordClass;
      wordclass.title = pageData.pageWordstems[i].wordClass;
      wsClass.appendChild(wordclass);

      let wsRef = tr.insertCell();
      wsRef.innerHTML =
        "Fr.: " +
        data.pageWordstems[i].frTranslation +
        "<br> Eng.: " +
        data.pageWordstems[i].engTranslation;
      let wsParent = tr.insertCell();
      wsParent.innerText = data.pageWordstems[i].parentsWordStemStr;
      let wsSemField = tr.insertCell();
      wsSemField.innerText = data.pageWordstems[i].semanticField;
      //let wsDel = tr.insertCell();
      //wsDel.appendChild(document.createElement("button").onclick(deleteData(pageWordstems[i].wordStemId)));
    }

    let wsCount = document.getElementById("words-count");
    wsCount.innerText = "Nombre total de mots: " + data.wordstemsCount;
    let pagesBtn = document.getElementById("pagesbutton");
    pagesBtn.innerText = "";

    if (currentPage > 1) {
      let prevBtn = document.createElement("button");
      prevBtn.textContent = "Précédent";
      prevBtn.addEventListener("click", () => {
        showWordstems(currentPage - 1, countByPage);
      });
      pagesBtn.appendChild(prevBtn);
    }
    if (currentPage < data.pageCount - 1) {
      let nextBtn = document.createElement("button");
      nextBtn.textContent = "Suivant";
      nextBtn.addEventListener("click", () => {
        showWordstems(currentPage + 1, countByPage);
      });
      pagesBtn.appendChild(nextBtn);
    }
    if (currentPage < data.pageCount) {
      let lastBtn = document.createElement("button");
      lastBtn.textContent = "... " + data.pageCount;
      lastBtn.addEventListener("click", () => {
        showWordstems(data.pageCount, countByPage);
      });
      pagesBtn.appendChild(lastBtn);
    }
  });
  setLanguages();
}

async function getShowWordstem(currentPage, countByPage) {
  try {
    const response = await fetch(
      host + apiWordstem + "/" + currentPage + "/" + countByPage,
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
    data = await response.json();
    return data;
    //data = JSON.stringify(dataJSON);
  } catch (error) {
    console.error(console.log(error));
  }
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
