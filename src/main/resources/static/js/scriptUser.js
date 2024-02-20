let apiUser = "/user";

function getUserData() {}
//Posibilité de s'authentifier via un token avec spring security.
//Possibilité de générer un autre port sur le même serveur plutôt que d'utiliser plusieurs serveurs.

async function get4LastPropperNounsForm() {
  try {
    const response = await fetch(
      host + apiUser + "?lastProperNouns=" + username
    );

    // Check if request successfull
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const data = await response.json();
    localStorage.setItem("quizzData", JSON.stringify(data));

    let obj = JSON.parse(localStorage.getItem("quizzData"));
    totalQuestionNumber = Object.keys(obj).length;
  } catch (error) {
    console.error(console.log(error));
  }
  showQuestion();
}
