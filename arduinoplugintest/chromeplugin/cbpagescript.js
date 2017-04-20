
console.log("Starting DSC10 robotics integration " + window.location.href);

var editorElement = document.getElementById('editor');


if ( editorElement != null ) {
    document.body.style.backgroundColor ="green";
    afterLoadingInnerFrame();

} else {
    document.body.style.backgroundColor ="yellow";
    afterLoadingOuterFrame();

}


// Outer Frame Event handling

function afterLoadingOuterFrame(){
    // Install listener for
    chrome.runtime.onMessage.addListener(
        clickbuttonmessagehandler
    );
}

function clickbuttonmessagehandler (request , sender , sendResponse) {

    if ( ! sender.tab) {
        console.log("F0 - Received message from extension ");
        console.log(request)
        if ( request.action == "clickButton") {
            console.log("F0 - Executing clickButton");
            var btn = document.getElementById("cb_cf_flash_btn");
            btn.click();
            console.log("F0 - Sending response ");
            sendResponse({action : "clickButtonAck"});
        }
    }
}


// Inner Frame Event handling

function afterLoadingInnerFrame(){

    // Install listener for
    chrome.runtime.onMessage.addListener(
        cpmessagehandler
    );

}

function cpmessagehandler (request , sender , sendResponse) {

    if ( ! sender.tab) {
        console.log("F1 - Received message from extension ");
        console.log(request)
        if ( request.action == "paste") {
            editorTextArea = editorElement.getElementsByTagName("textarea")[0];
            editorTextArea.focus();
            document.execCommand('SelectAll');
            document.execCommand('Paste');
            sendResponse({action : "pasteack"});
        }
    }
}
