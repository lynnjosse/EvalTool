document.getElementById("evalphotodiv").onclick = function() {lightBox()};

var bigPhoto = document.getElementById("overlay");

function lightBox() {
    bigPhoto.style.display = "block";
}

document.getElementById("overlay").onclick =function() {unLightBox()};

function unLightBox() {
    bigPhoto.style.display = "none";
    }