let apiWordstem = '/wordstems'
let countByPage = 10;
let wordstemCount = 0;
let pageCount = 0;
let wordstemList = 0;
let data;

const currentNameElement = document.getElementById("currentName")




async function getWordstemData() {
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

    getWordstemData();



}

