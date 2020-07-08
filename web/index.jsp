<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Title</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
      function change(rNo, cNo)
      {
        var charToWrite;
        if ($("#somediv").text() == "FIRST: X" || $("#somediv").text()=="") {
          charToWrite = "X";
        } else {
          charToWrite = "O";
        }

        var currentButton = document.getElementById("idr"+rNo+"c"+cNo);
        if (currentButton.innerHTML == "&nbsp;") {
          currentButton.innerHTML = charToWrite;
          $.get("plainMove", function (responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
            $("#somediv").text(responseText);// Locate HTML DOM element with ID "somediv" and set its text content with the response text.
          });
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
            $("#testdiv").text(response);
          }
        );
        $.get("testing", function (responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
          $("#testing").text(responseText);// Locate HTML DOM element with ID "somediv" and set its text content with the response text.
        });
      }
    </script>
  </head>
  <body>
  <div>
    Header
  </div>
  <div>
    <table>
      <tr>
        <td><button type="button" class="button" id="idr1c1" onclick="change(1,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c2" onclick="change(1,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c3" onclick="change(1,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c4" onclick="change(1,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c5" onclick="change(1,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c6" onclick="change(1,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c7" onclick="change(1,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c8" onclick="change(1,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c9" onclick="change(1,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c10" onclick="change(1,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c11" onclick="change(1,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c12" onclick="change(1,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c13" onclick="change(1,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c14" onclick="change(1,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c15" onclick="change(1,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c16" onclick="change(1,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c17" onclick="change(1,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c18" onclick="change(1,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c19" onclick="change(1,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c20" onclick="change(1,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c21" onclick="change(1,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c22" onclick="change(1,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c23" onclick="change(1,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c24" onclick="change(1,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr1c25" onclick="change(1,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr2c1" onclick="change(2,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c2" onclick="change(2,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c3" onclick="change(2,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c4" onclick="change(2,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c5" onclick="change(2,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c6" onclick="change(2,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c7" onclick="change(2,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c8" onclick="change(2,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c9" onclick="change(2,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c10" onclick="change(2,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c11" onclick="change(2,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c12" onclick="change(2,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c13" onclick="change(2,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c14" onclick="change(2,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c15" onclick="change(2,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c16" onclick="change(2,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c17" onclick="change(2,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c18" onclick="change(2,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c19" onclick="change(2,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c20" onclick="change(2,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c21" onclick="change(2,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c22" onclick="change(2,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c23" onclick="change(2,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c24" onclick="change(2,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr2c25" onclick="change(2,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr3c1" onclick="change(3,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c2" onclick="change(3,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c3" onclick="change(3,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c4" onclick="change(3,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c5" onclick="change(3,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c6" onclick="change(3,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c7" onclick="change(3,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c8" onclick="change(3,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c9" onclick="change(3,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c10" onclick="change(3,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c11" onclick="change(3,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c12" onclick="change(3,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c13" onclick="change(3,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c14" onclick="change(3,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c15" onclick="change(3,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c16" onclick="change(3,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c17" onclick="change(3,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c18" onclick="change(3,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c19" onclick="change(3,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c20" onclick="change(3,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c21" onclick="change(3,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c22" onclick="change(3,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c23" onclick="change(3,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c24" onclick="change(3,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr3c25" onclick="change(3,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr4c1" onclick="change(4,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c2" onclick="change(4,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c3" onclick="change(4,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c4" onclick="change(4,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c5" onclick="change(4,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c6" onclick="change(4,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c7" onclick="change(4,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c8" onclick="change(4,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c9" onclick="change(4,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c10" onclick="change(4,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c11" onclick="change(4,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c12" onclick="change(4,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c13" onclick="change(4,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c14" onclick="change(4,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c15" onclick="change(4,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c16" onclick="change(4,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c17" onclick="change(4,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c18" onclick="change(4,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c19" onclick="change(4,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c20" onclick="change(4,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c21" onclick="change(4,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c22" onclick="change(4,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c23" onclick="change(4,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c24" onclick="change(4,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr4c25" onclick="change(4,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr5c1" onclick="change(5,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c2" onclick="change(5,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c3" onclick="change(5,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c4" onclick="change(5,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c5" onclick="change(5,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c6" onclick="change(5,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c7" onclick="change(5,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c8" onclick="change(5,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c9" onclick="change(5,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c10" onclick="change(5,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c11" onclick="change(5,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c12" onclick="change(5,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c13" onclick="change(5,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c14" onclick="change(5,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c15" onclick="change(5,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c16" onclick="change(5,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c17" onclick="change(5,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c18" onclick="change(5,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c19" onclick="change(5,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c20" onclick="change(5,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c21" onclick="change(5,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c22" onclick="change(5,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c23" onclick="change(5,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c24" onclick="change(5,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr5c25" onclick="change(5,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr6c1" onclick="change(6,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c2" onclick="change(6,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c3" onclick="change(6,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c4" onclick="change(6,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c5" onclick="change(6,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c6" onclick="change(6,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c7" onclick="change(6,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c8" onclick="change(6,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c9" onclick="change(6,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c10" onclick="change(6,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c11" onclick="change(6,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c12" onclick="change(6,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c13" onclick="change(6,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c14" onclick="change(6,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c15" onclick="change(6,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c16" onclick="change(6,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c17" onclick="change(6,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c18" onclick="change(6,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c19" onclick="change(6,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c20" onclick="change(6,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c21" onclick="change(6,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c22" onclick="change(6,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c23" onclick="change(6,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c24" onclick="change(6,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr6c25" onclick="change(6,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr7c1" onclick="change(7,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c2" onclick="change(7,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c3" onclick="change(7,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c4" onclick="change(7,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c5" onclick="change(7,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c6" onclick="change(7,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c7" onclick="change(7,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c8" onclick="change(7,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c9" onclick="change(7,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c10" onclick="change(7,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c11" onclick="change(7,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c12" onclick="change(7,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c13" onclick="change(7,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c14" onclick="change(7,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c15" onclick="change(7,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c16" onclick="change(7,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c17" onclick="change(7,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c18" onclick="change(7,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c19" onclick="change(7,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c20" onclick="change(7,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c21" onclick="change(7,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c22" onclick="change(7,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c23" onclick="change(7,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c24" onclick="change(7,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr7c25" onclick="change(7,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr8c1" onclick="change(8,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c2" onclick="change(8,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c3" onclick="change(8,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c4" onclick="change(8,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c5" onclick="change(8,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c6" onclick="change(8,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c7" onclick="change(8,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c8" onclick="change(8,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c9" onclick="change(8,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c10" onclick="change(8,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c11" onclick="change(8,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c12" onclick="change(8,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c13" onclick="change(8,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c14" onclick="change(8,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c15" onclick="change(8,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c16" onclick="change(8,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c17" onclick="change(8,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c18" onclick="change(8,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c19" onclick="change(8,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c20" onclick="change(8,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c21" onclick="change(8,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c22" onclick="change(8,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c23" onclick="change(8,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c24" onclick="change(8,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr8c25" onclick="change(8,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr9c1" onclick="change(9,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c2" onclick="change(9,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c3" onclick="change(9,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c4" onclick="change(9,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c5" onclick="change(9,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c6" onclick="change(9,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c7" onclick="change(9,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c8" onclick="change(9,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c9" onclick="change(9,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c10" onclick="change(9,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c11" onclick="change(9,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c12" onclick="change(9,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c13" onclick="change(9,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c14" onclick="change(9,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c15" onclick="change(9,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c16" onclick="change(9,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c17" onclick="change(9,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c18" onclick="change(9,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c19" onclick="change(9,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c20" onclick="change(9,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c21" onclick="change(9,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c22" onclick="change(9,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c23" onclick="change(9,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c24" onclick="change(9,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr9c25" onclick="change(9,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr10c1" onclick="change(10,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c2" onclick="change(10,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c3" onclick="change(10,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c4" onclick="change(10,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c5" onclick="change(10,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c6" onclick="change(10,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c7" onclick="change(10,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c8" onclick="change(10,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c9" onclick="change(10,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c10" onclick="change(10,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c11" onclick="change(10,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c12" onclick="change(10,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c13" onclick="change(10,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c14" onclick="change(10,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c15" onclick="change(10,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c16" onclick="change(10,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c17" onclick="change(10,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c18" onclick="change(10,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c19" onclick="change(10,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c20" onclick="change(10,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c21" onclick="change(10,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c22" onclick="change(10,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c23" onclick="change(10,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c24" onclick="change(10,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr10c25" onclick="change(10,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr11c1" onclick="change(11,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c2" onclick="change(11,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c3" onclick="change(11,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c4" onclick="change(11,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c5" onclick="change(11,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c6" onclick="change(11,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c7" onclick="change(11,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c8" onclick="change(11,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c9" onclick="change(11,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c10" onclick="change(11,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c11" onclick="change(11,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c12" onclick="change(11,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c13" onclick="change(11,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c14" onclick="change(11,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c15" onclick="change(11,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c16" onclick="change(11,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c17" onclick="change(11,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c18" onclick="change(11,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c19" onclick="change(11,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c20" onclick="change(11,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c21" onclick="change(11,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c22" onclick="change(11,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c23" onclick="change(11,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c24" onclick="change(11,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr11c25" onclick="change(11,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr12c1" onclick="change(12,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c2" onclick="change(12,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c3" onclick="change(12,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c4" onclick="change(12,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c5" onclick="change(12,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c6" onclick="change(12,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c7" onclick="change(12,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c8" onclick="change(12,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c9" onclick="change(12,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c10" onclick="change(12,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c11" onclick="change(12,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c12" onclick="change(12,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c13" onclick="change(12,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c14" onclick="change(12,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c15" onclick="change(12,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c16" onclick="change(12,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c17" onclick="change(12,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c18" onclick="change(12,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c19" onclick="change(12,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c20" onclick="change(12,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c21" onclick="change(12,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c22" onclick="change(12,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c23" onclick="change(12,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c24" onclick="change(12,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr12c25" onclick="change(12,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr13c1" onclick="change(13,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c2" onclick="change(13,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c3" onclick="change(13,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c4" onclick="change(13,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c5" onclick="change(13,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c6" onclick="change(13,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c7" onclick="change(13,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c8" onclick="change(13,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c9" onclick="change(13,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c10" onclick="change(13,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c11" onclick="change(13,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c12" onclick="change(13,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c13" onclick="change(13,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c14" onclick="change(13,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c15" onclick="change(13,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c16" onclick="change(13,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c17" onclick="change(13,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c18" onclick="change(13,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c19" onclick="change(13,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c20" onclick="change(13,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c21" onclick="change(13,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c22" onclick="change(13,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c23" onclick="change(13,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c24" onclick="change(13,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr13c25" onclick="change(13,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr14c1" onclick="change(14,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c2" onclick="change(14,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c3" onclick="change(14,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c4" onclick="change(14,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c5" onclick="change(14,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c6" onclick="change(14,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c7" onclick="change(14,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c8" onclick="change(14,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c9" onclick="change(14,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c10" onclick="change(14,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c11" onclick="change(14,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c12" onclick="change(14,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c13" onclick="change(14,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c14" onclick="change(14,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c15" onclick="change(14,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c16" onclick="change(14,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c17" onclick="change(14,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c18" onclick="change(14,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c19" onclick="change(14,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c20" onclick="change(14,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c21" onclick="change(14,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c22" onclick="change(14,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c23" onclick="change(14,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c24" onclick="change(14,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr14c25" onclick="change(14,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr15c1" onclick="change(15,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c2" onclick="change(15,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c3" onclick="change(15,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c4" onclick="change(15,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c5" onclick="change(15,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c6" onclick="change(15,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c7" onclick="change(15,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c8" onclick="change(15,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c9" onclick="change(15,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c10" onclick="change(15,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c11" onclick="change(15,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c12" onclick="change(15,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c13" onclick="change(15,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c14" onclick="change(15,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c15" onclick="change(15,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c16" onclick="change(15,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c17" onclick="change(15,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c18" onclick="change(15,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c19" onclick="change(15,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c20" onclick="change(15,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c21" onclick="change(15,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c22" onclick="change(15,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c23" onclick="change(15,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c24" onclick="change(15,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr15c25" onclick="change(15,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr16c1" onclick="change(16,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c2" onclick="change(16,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c3" onclick="change(16,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c4" onclick="change(16,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c5" onclick="change(16,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c6" onclick="change(16,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c7" onclick="change(16,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c8" onclick="change(16,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c9" onclick="change(16,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c10" onclick="change(16,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c11" onclick="change(16,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c12" onclick="change(16,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c13" onclick="change(16,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c14" onclick="change(16,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c15" onclick="change(16,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c16" onclick="change(16,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c17" onclick="change(16,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c18" onclick="change(16,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c19" onclick="change(16,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c20" onclick="change(16,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c21" onclick="change(16,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c22" onclick="change(16,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c23" onclick="change(16,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c24" onclick="change(16,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr16c25" onclick="change(16,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr17c1" onclick="change(17,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c2" onclick="change(17,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c3" onclick="change(17,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c4" onclick="change(17,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c5" onclick="change(17,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c6" onclick="change(17,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c7" onclick="change(17,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c8" onclick="change(17,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c9" onclick="change(17,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c10" onclick="change(17,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c11" onclick="change(17,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c12" onclick="change(17,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c13" onclick="change(17,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c14" onclick="change(17,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c15" onclick="change(17,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c16" onclick="change(17,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c17" onclick="change(17,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c18" onclick="change(17,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c19" onclick="change(17,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c20" onclick="change(17,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c21" onclick="change(17,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c22" onclick="change(17,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c23" onclick="change(17,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c24" onclick="change(17,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr17c25" onclick="change(17,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr18c1" onclick="change(18,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c2" onclick="change(18,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c3" onclick="change(18,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c4" onclick="change(18,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c5" onclick="change(18,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c6" onclick="change(18,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c7" onclick="change(18,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c8" onclick="change(18,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c9" onclick="change(18,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c10" onclick="change(18,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c11" onclick="change(18,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c12" onclick="change(18,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c13" onclick="change(18,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c14" onclick="change(18,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c15" onclick="change(18,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c16" onclick="change(18,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c17" onclick="change(18,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c18" onclick="change(18,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c19" onclick="change(18,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c20" onclick="change(18,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c21" onclick="change(18,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c22" onclick="change(18,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c23" onclick="change(18,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c24" onclick="change(18,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr18c25" onclick="change(18,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr19c1" onclick="change(19,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c2" onclick="change(19,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c3" onclick="change(19,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c4" onclick="change(19,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c5" onclick="change(19,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c6" onclick="change(19,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c7" onclick="change(19,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c8" onclick="change(19,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c9" onclick="change(19,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c10" onclick="change(19,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c11" onclick="change(19,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c12" onclick="change(19,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c13" onclick="change(19,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c14" onclick="change(19,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c15" onclick="change(19,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c16" onclick="change(19,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c17" onclick="change(19,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c18" onclick="change(19,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c19" onclick="change(19,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c20" onclick="change(19,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c21" onclick="change(19,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c22" onclick="change(19,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c23" onclick="change(19,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c24" onclick="change(19,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr19c25" onclick="change(19,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr20c1" onclick="change(20,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c2" onclick="change(20,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c3" onclick="change(20,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c4" onclick="change(20,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c5" onclick="change(20,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c6" onclick="change(20,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c7" onclick="change(20,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c8" onclick="change(20,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c9" onclick="change(20,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c10" onclick="change(20,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c11" onclick="change(20,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c12" onclick="change(20,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c13" onclick="change(20,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c14" onclick="change(20,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c15" onclick="change(20,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c16" onclick="change(20,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c17" onclick="change(20,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c18" onclick="change(20,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c19" onclick="change(20,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c20" onclick="change(20,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c21" onclick="change(20,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c22" onclick="change(20,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c23" onclick="change(20,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c24" onclick="change(20,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr20c25" onclick="change(20,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr21c1" onclick="change(21,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c2" onclick="change(21,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c3" onclick="change(21,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c4" onclick="change(21,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c5" onclick="change(21,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c6" onclick="change(21,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c7" onclick="change(21,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c8" onclick="change(21,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c9" onclick="change(21,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c10" onclick="change(21,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c11" onclick="change(21,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c12" onclick="change(21,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c13" onclick="change(21,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c14" onclick="change(21,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c15" onclick="change(21,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c16" onclick="change(21,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c17" onclick="change(21,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c18" onclick="change(21,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c19" onclick="change(21,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c20" onclick="change(21,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c21" onclick="change(21,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c22" onclick="change(21,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c23" onclick="change(21,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c24" onclick="change(21,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr21c25" onclick="change(21,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr22c1" onclick="change(22,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c2" onclick="change(22,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c3" onclick="change(22,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c4" onclick="change(22,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c5" onclick="change(22,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c6" onclick="change(22,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c7" onclick="change(22,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c8" onclick="change(22,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c9" onclick="change(22,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c10" onclick="change(22,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c11" onclick="change(22,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c12" onclick="change(22,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c13" onclick="change(22,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c14" onclick="change(22,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c15" onclick="change(22,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c16" onclick="change(22,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c17" onclick="change(22,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c18" onclick="change(22,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c19" onclick="change(22,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c20" onclick="change(22,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c21" onclick="change(22,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c22" onclick="change(22,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c23" onclick="change(22,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c24" onclick="change(22,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr22c25" onclick="change(22,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr23c1" onclick="change(23,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c2" onclick="change(23,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c3" onclick="change(23,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c4" onclick="change(23,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c5" onclick="change(23,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c6" onclick="change(23,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c7" onclick="change(23,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c8" onclick="change(23,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c9" onclick="change(23,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c10" onclick="change(23,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c11" onclick="change(23,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c12" onclick="change(23,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c13" onclick="change(23,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c14" onclick="change(23,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c15" onclick="change(23,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c16" onclick="change(23,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c17" onclick="change(23,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c18" onclick="change(23,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c19" onclick="change(23,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c20" onclick="change(23,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c21" onclick="change(23,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c22" onclick="change(23,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c23" onclick="change(23,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c24" onclick="change(23,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr23c25" onclick="change(23,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr24c1" onclick="change(24,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c2" onclick="change(24,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c3" onclick="change(24,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c4" onclick="change(24,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c5" onclick="change(24,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c6" onclick="change(24,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c7" onclick="change(24,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c8" onclick="change(24,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c9" onclick="change(24,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c10" onclick="change(24,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c11" onclick="change(24,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c12" onclick="change(24,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c13" onclick="change(24,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c14" onclick="change(24,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c15" onclick="change(24,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c16" onclick="change(24,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c17" onclick="change(24,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c18" onclick="change(24,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c19" onclick="change(24,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c20" onclick="change(24,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c21" onclick="change(24,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c22" onclick="change(24,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c23" onclick="change(24,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c24" onclick="change(24,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr24c25" onclick="change(24,25)">&nbsp;</button></td>
      </tr>
      <tr>
        <td><button type="button" class="button" id="idr25c1" onclick="change(25,1)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c2" onclick="change(25,2)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c3" onclick="change(25,3)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c4" onclick="change(25,4)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c5" onclick="change(25,5)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c6" onclick="change(25,6)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c7" onclick="change(25,7)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c8" onclick="change(25,8)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c9" onclick="change(25,9)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c10" onclick="change(25,10)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c11" onclick="change(25,11)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c12" onclick="change(25,12)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c13" onclick="change(25,13)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c14" onclick="change(25,14)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c15" onclick="change(25,15)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c16" onclick="change(25,16)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c17" onclick="change(25,17)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c18" onclick="change(25,18)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c19" onclick="change(25,19)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c20" onclick="change(25,20)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c21" onclick="change(25,21)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c22" onclick="change(25,22)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c23" onclick="change(25,23)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c24" onclick="change(25,24)">&nbsp;</button></td>
        <td><button type="button" class="button" id="idr25c25" onclick="change(25,25)">&nbsp;</button></td>
      </tr>
    </table>
  </div>
  <div id="somediv">FIRST: X</div>
  <div id="positiondiv"><textarea id="testdiv" rows="20" cols="80"></textarea></div>
  <div id="position" hidden></div>
  </body>
</html>
