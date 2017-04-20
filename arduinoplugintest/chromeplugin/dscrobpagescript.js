console.log("Starting DSC 10 Robotics content script");

responsefunction = null;

window.addEventListener("message", function(event) {
    // We only accept messages from ourselves
    if (event.source != window)
        return;

    if (event.data.type && (event.data.type == "FROM_PAGE")) {
        console.log("Content script received: " + event.data.text);

        chrome.runtime.sendMessage({action: "copyack"}, function(response) {
            console.log(response);
        });



    }
}, false);






chrome.runtime.onMessage.addListener(
    function (request , sender , sendResponse) {


        if ( ! sender.tab) {
            console.log("Received message from extension ");
            console.log(request);
            if ( request.action == "copy") {
                window.postMessage({ type: "FROM_CS", text: "Hello from the content script!" }, "*");
                //console.log(editorElement);
            }
        }
    }
);

function onValueChange() {
    if ( editorElement.value == "copy") {
        return;
    } else {
        editorElement.focus();
        document.execCommand('SelectAll');
        document.execCommand('Copy');
        if ( responsefunction != null )
            responsefunction({action : "copyack"});
    }
}