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
        const response = await fetch(host + apiWordstem + "?pageSize=" + countByPage, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        });

        // Check if request successfull
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const dataJSON = await response.json();
        data = JSON.stringify(dataJSON);

        totalQuestionNumber = Object.keys(obj).length;
    } catch (error) {
        console.error(console.log(error));
    }

    async function deleteData(id) {
        try {
            const response = await fetch(host + apiWordstem + "?id=" + id, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                }
            });

            // Check if request successfull
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
        } catch (error) {
            console.error(console.log(error));
        }



        function showWordstems() {
            getShowWordstem()
            for (i = 0; i < data.pageWordstems.length; i++) {
                doculent.createElement("tr");
                let wsName = document.createElement("td");
                let wsPhonetic = document.createElement("td");
                let wsLang = document.createElement("td");
                let wsGender = document.createElement("td");
                let wsRefFr = document.createElement("td");
                let wsParent = document.createElement("td");
                let wsDel = document.createElement("td");
                wsName.innerText(data.pageWordstems[i].wordStemName);
                wsPhonetic.innerText(data.pageWordstems[i].wsPhonetic);
                wsLang.innerText(data.pageWordstems[i].phonetic);
                wsGender.innerText(data.pageWordstems[i].gender);
                wsRefFr.innerText(data.pageWordstems[i].referenceWordsEng);
                wsParent.innerText(data.pageWordstems[i].wordStemName);
                document.createElement("button").onclick(deleteData(pageWordstems[i].id));
                wsDel.innerText();
            }
        };



    }

}