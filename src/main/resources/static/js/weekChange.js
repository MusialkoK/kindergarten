const texts = document.getElementsByClassName("dayText");
const edits = document.getElementsByClassName("dayEdit");

function changeClick() {
    document.getElementById("changeButton").style.display = "none";
    document.getElementById("saveButton").style.display = "block";
    console.log(texts.length);
    console.log(edits.length);
    for(let i=0; i<texts.length; i++){
        texts.item(i).style.display = "none";
    }
    for(let i=0; i<edits.length; i++){
        edits.item(i).style.display = "block";
    }

}

function saveClick() {
    document.getElementById("changeButton").style.display = "block";
    document.getElementById("saveButton").style.display = "none";
    for(let i=0; i<texts.length; i++){
        texts.item(i).style.display = "block";
    }
    for(let i=0; i<edits.length; i++){
        edits.item(i).style.display = "none";
    }
}
