let apiWordstem = "/sources";
let data;

const countElement = document.getElementById("wordstems-count");
const wordStemNameElement = document.getElementById("wordstems-name");
const currentNameElement = document.getElementById("currentName");

async function showSources(currentPage, countByPage) {
  let tbl = document.getElementById("wordstemtable");
  tbl.innerText = "";
  await getShowWordstem(currentPage, countByPage).then((pageData) => {
    console.log(pageData);
    for (i = 0; i < pageData.pageSources.length; i++) {
      const tr = tbl.insertRow();

      let srcName = tr.insertCell();
      srcName.innerHTML = data.pageSources[i].abbreviation;

      let srcFullName = tr.insertCell();
      srcFullName.innerText = pageData.pageSources[i].sourceNameInOriginalLanguage;

      let wsClass = tr.insertCell();
      wsClass.innerText = pageData.pageSources[i].typeOfSource;

      let wsRef = tr.insertCell();
      wsRef.innerHTML = data.pageSources[i].dateOfPublication;

      let wsParent = tr.insertCell();
      wsParent.innerText = data.pageSources[i].parentsWordStemStr;
    }

    let wsCount = document.getElementById("words-count");
    wsCount.innerText = "Nombre total de mots: " + data.wordstemsCount;
    let pagesBtn = document.getElementById("pagesbutton");
    pagesBtn.innerText = "";

    if (currentPage > 1) {
      let prevBtn = document.createElement("button");
      prevBtn.textContent = "Previous";
      prevBtn.addEventListener("click", () => {
        showSources(currentPage - 1, countByPage);
      });
      pagesBtn.appendChild(prevBtn);
    }
    if (currentPage < data.pageCount - 1) {
      let nextBtn = document.createElement("button");
      nextBtn.textContent = "Next";
      nextBtn.addEventListener("click", () => {
        showSources(currentPage + 1, countByPage);
      });
      pagesBtn.appendChild(nextBtn);
    }
    if (currentPage < data.pageCount) {
      let lastBtn = document.createElement("button");
      lastBtn.textContent = "... " + data.pageCount;
      lastBtn.addEventListener("click", () => {
        showSources(data.pageCount, countByPage);
      });
      pagesBtn.appendChild(lastBtn);
    }
  });
  setLanguages();
  setGender();
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

showSources(1, 10);
