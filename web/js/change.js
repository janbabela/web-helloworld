function change(rNo, cNo) {

    if (!checkGameOver()) {
        var charToWrite = document.getElementById("somediv").innerHTML.substring(8, 9);

        var currentButton = document.getElementById("idr" + rNo + "c" + cNo);
        if (currentButton.innerHTML == "&nbsp;") {
            if (getGameMode() != "3") {
                currentButton.innerHTML = charToWrite;
            }
            $.post("position", {
                    position: JSON.stringify(getPosition())
                }, function (response, status) {
                    handleResponse(response);
                }
            );
        }
    }
}
function handleResponse(response) {
    if (!checkGameOver()) {
        var gameMode = findGameMode(response);
        if (gameMode == "1") {
            changePlayerChar(response);
        }
        else if (gameMode == "2") {
            displayMove(response, "O");
        }
        else {
            changePlayerChar(response);
            var playerChar = getPlayerChar();
            displayMove(response, playerChar);
        }
        $("#testdiv").text(response);
        }
}
function displayMove(response, playerChar) {
    var responseSubstring = response.substring(4);
    var row = responseSubstring.substring(17,19);
    var next = responseSubstring.substring(19);
    if (responseSubstring.substring(18,19) == ",") {
        row = responseSubstring.substring(17,18);
        next = responseSubstring.substring(18);
    }
    var column = next.substring(9,11);
    var evaluation = next.substring(12,17);
    if (next.substring(10,11) == ")") {
        column = next.substring(9,10);
        evaluation = next.substring(11,16);
    }
    if (row > -1 && column > -1 ) {
        document.getElementById("idr" + row + "c" + column).innerHTML = playerChar;
    }
    document.getElementById("evaluation").innerHTML = "Evaluation: " + evaluation;
    if (evaluation == -1.0) {
        document.getElementById("somediv").innerHTML = "GAME OVER";
    }
}
function findGameMode(response) {
    document.getElementById("gamemode").innerHTML = response.substring(0,1);
    return response.substring(0,1);
}
function getGameMode() {
    return document.getElementById("gamemode").innerHTML;
}
function changePlayerChar(response) {
    if (document.getElementById("somediv").innerHTML.substring(0,9) == "PLAYER: X") {
        document.getElementById("somediv").innerHTML = "PLAYER: O";
    }
    else {
        document.getElementById("somediv").innerHTML = "PLAYER: X";
    }
    if (response.substring(36,41) == "-1.0") {
        document.getElementById("somediv").innerHTML = "GAME OVER";
    }
}
function getPlayerChar() {
    return document.getElementById("somediv").innerHTML.substring(8,9);
}
function checkGameOver() {
  return document.getElementById("somediv").innerHTML == "GAME OVER";
}
function getPosition() {
    var position = new Array(25);
    for (var i = 0; i < position.length; i++) {
        position[i] = new Array(25);
    }
    for (var r = 1; r < 26; r++) {
        for (var c = 1; c < 26; c++) {
            position[r - 1][c - 1] = document.getElementById("idr" + r + "c" + c).innerHTML;
        }
    }
    return position;
}