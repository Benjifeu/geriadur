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
            getShowWordstem();




            tbl = document.createElement('table');
            tbl.style.width = '1000px';
            tbl.style.border = '1px solid gray';

            for (i = 0; i < data.pageWordstems.length; i++) {
                const tr = tbl.insertRow();
                let wsName = tr.insertCell();
                wsName.innerText = data.pageWordstems[i].wordStemLanguage+" "+data.pageWordstems[i].wordStemName+" [" + data.pageWordstems[i].wsPhonetic + "]";
                let wsGender = tr.insertCell();
                wsGender.innerText=data.pageWordstems[i].gender;

                let wsRefFr = tr.insertCell();

                let wsParent = tr.insertCell();
                let wsDel = tr.insertCell();

                
                wsRefFr.innerText(data.pageWordstems[i].referenceWordsEng);
                wsParent.innerText(data.pageWordstems[i].wordStemName);
                wsDel.appendChild(document.createElement("button").onclick(deleteData(pageWordstems[i].wordStemId)));
            }
        }
        body.appendChild(tbl);
    }
}