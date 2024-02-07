let apiWordstem = '/wordstems'
let countByPage = 10;
let wordstemCount = 0;
let pageCount = 0;
let wordstemList = 0;
let data;


const countElement = document.getElementById("wordstems-count")
const wordStemNameElement = document.getElementById("wordstems-name")
const currentNameElement = document.getElementById("currentName")




async function getShowWordstem() {
    try {
        const response = await fetch(host +apiWordstem+"?pageSize="+countByPage,{
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }});

        // Check if request successfull
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const dataJSON = await response.json();
        data = JSON.stringify(dataJSON);

        totalQuestionNumber = Object.keys(obj).length;  } catch (error) {
        console.error(console.log(error));
    }

    function showWordstems(){
        getShowWordstem()
        for(i=0;i<data.pageWordstems.length;i++){
        let wsName = document.createElement("name");
        let wsPhonetic = document.createElement("phonetic");
        let wsLang = document.createElement("lang");
        let wsGender = document.createElement("gender");
        let wsRefFr = document.createElement("fr");
        let wsParent = document.createElement("parent");
        let wsDel = document.createElement("parent");
        wsName.innerText(data.pageWordstems[i].wordStemName);
        }
    };



}

