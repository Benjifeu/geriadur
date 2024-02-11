let host = 'http://localhost:8080'

let apiWordstem = '/wordstems'
let data;


const countElement = document.getElementById("wordstems-count")
const wordStemNameElement = document.getElementById("wordstems-name")
const currentNameElement = document.getElementById("currentName")


function showWordstems(currentPage, countByPage) {
    let tbl = document.getElementById("wordstemtable");
    //tbl.innerText('');
    getShowWordstem(currentPage, countByPage).then(pageData => {
        console.log(pageData);
        for (i = 0; i < pageData.pageWordstems.length; i++) {
            const tr = tbl.insertRow();
            let wsName = tr.insertCell();
            wsName.innerText = pageData.pageWordstems[i].wordStemLanguage + " " + data.pageWordstems[i].wordStemName + " [" + data.pageWordstems[i].phonetic + "]";
            let wsGender = tr.insertCell();
            wsGender.innerText = pageData.pageWordstems[i].gender;
            let wsClass = tr.insertCell();
            wsGender.innerText = pageData.pageWordstems[i].wordClass;
            let wsRef = tr.insertCell();
            wsRef.innerHTML="Fr.: " + data.pageWordstems[i].engTranslation+"<br> Eng.: " + data.pageWordstems[i].frTranslation;
            let wsParent = tr.insertCell();
            //let wsDel = tr.insertCell();
            wsParent.innerText= data.pageWordstems[i].parentsWordStemStr;
            //wsDel.appendChild(document.createElement("button").onclick(deleteData(pageWordstems[i].wordStemId)));
        }
        let pagesBtn = document.getElementById("pagesbutton");
        pagesBtn.innerText = "Nombre total de mots: "+ data.wordstemsCount;
    })
    ;
}

async function getShowWordstem(currentPage, countByPage) {
    try {
        const response = await fetch(host + apiWordstem + "/" + currentPage + "/" + countByPage, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        });
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
            }
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