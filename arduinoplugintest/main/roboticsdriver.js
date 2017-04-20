/**
 * Created by asaxena on 4/11/17.
 */

function runOnArduino() {
    alert("Will run codebender now");

    var a = document.createElement("a");
    a.href ="http://www.google.com";
    var evt = document.createEvent("MouseEvents");
    //the tenth parameter of initMouseEvent sets ctrl key
    evt.initMouseEvent("click", true, true, window, 0, 0, 0, 0, 0,
        true, false, false, false, 0, null);
    a.dispatchEvent(evt);

}