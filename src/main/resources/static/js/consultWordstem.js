let apiWordstem = "/wordstems";


async function showWordstem() {

  const urlParams = new URLSearchParams(window.location.search);

  const wordname = urlParams.get('nom')

  await getWordstem(wordname).then((data) => {
    localStorage.setItem("wordstem", JSON.stringify(data));
  });
  insertdata(JSON.parse(localStorage.getItem("wordstem")))
  console.log(host + apiWordstem + "/" + wordname)
}



function insertdata(data) {
  console.log(data);
  let wordname = document.getElementById("wordstemname");
  wordname.innerText = data.wordStemName;
  let etymo = document.getElementById("etymology")
  etymo.innerText = data.descrFr;
  let details = document.getElementById("details")
  details.innerHTML =
    "<br> Forme phonétique: " + data.phonetic +
    "<br> Genre: " + data.gender +
    "<br> Traduction française: " + data.frTranslation +
    "<br> Source: " + data.sources;
  setLanguages();
}

async function getWordstem(wordname) {
  try {
    const response = await fetch(
      `${host}${apiWordstem}/${wordname}`,
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



showWordstem();
