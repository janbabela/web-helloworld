function change(rNo, cNo) {
    var charToWrite = "X";

    var currentButton = document.getElementById("idr"+rNo+"c"+cNo);
    if (currentButton.innerHTML == "&nbsp;") {
        currentButton.innerHTML = charToWrite;
    }

    var position = new Array(25);
    for (var i = 0; i < position.length; i++) {
        position[i] = new Array(25);
    }
    for(var r=1; r<26; r++) {
        for(var c=1; c<26; c++) {
            position[r-1][c-1] = document.getElementById("idr" + r + "c" + c).innerHTML;
        }
    }
    $.post("position", {
            position : JSON.stringify(position)
        }, function(response, status) {
            handleResponse(response);
        }
    );
}
function handleResponse(response) {
    var row = response.substring(17,19);
    var next = response.substring(19);
    if (response.substring(18,19) == ",") {
        row = response.substring(17,18);
        next = response.substring(18);
    }
    var column = next.substring(9,11);
    if (next.substring(10,11) == ")") {
        column = next.substring(9,10);
    }
    $("#testdiv").text(response + " | " +  row + " " + column);
    document.getElementById("idr" + row + "c" + column).innerHTML = "O";
}