codebendertab = null;
dscroboticstab = null;

// Installs the pageAction button
chrome.runtime.onInstalled.addListener(function() {
    // Replace all rules ...
    chrome.declarativeContent.onPageChanged.removeRules(undefined, function() {
        // With a new rule ...
        chrome.declarativeContent.onPageChanged.addRules([
            {// That fires when a page's URL contains a 'g' ...
                conditions: [
                    new chrome.declarativeContent.PageStateMatcher({
                        pageUrl: { urlContains: 'index.html' },
                    })
                ],
                // And shows the extension's page action.
                actions: [ new chrome.declarativeContent.ShowPageAction() ]
            }
        ]);
    });
});


// Installs the pageAction click handler
chrome.pageAction.onClicked.addListener(pageActionClicked);


// Page Action Click handler
function pageActionClicked (tab) {
    dscroboticstab = tab;
    console.log("Page Action clicked from tab " + tab.url);

    // Update codedendertab if not present

    chrome.tabs.query({url : "https://edu.codebender.cc/class/mbgas"} ,cbtabqueryresult);
}

function cbtabqueryresult ( tabarr) {

    if ((tabarr instanceof  Array) && ( tabarr.length > 0 )) {
        codebendertab = tabarr[0];
        console.log("Found tab " + codebendertab.id)
        startCBCopyPaste();
    } else {
        console.log("Creating codedender tab ");
        chrome.tabs.create( {
            active : false,
            url : "https://edu.codebender.cc/class/mbgas"
        } , function(tab) {
            console.log("New tab opened for codebender");
            codebendertab = tab;
            startCBCopyPaste();
        });
    }
}

chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse) {
        console.log(sender.tab +
            " from tab");
        if (request.action == "copyack")
            sendMessageToCB();

    });


function startCBCopyPaste() {

 console.log(" Starting CB copy paste");
 console.log(" Sending message to DSC10 tab");

    chrome.tabs.sendMessage(dscroboticstab.id , {action: "copy"},
        function(response) {
            console.log("Response received from DSC10 tab");
            console.log(response);

        }

    );

}


numtries = 0;
function sendMessageToCB() {

    console.log(" Sending paste message to CB tab try " + numtries);
    chrome.tabs.sendMessage(codebendertab.id , {action: "paste"} ,
        function (response) {
            console.log("Response received from CB tab");
            console.log(response);
            if ( ! response && numtries < 3) {
                console.log(" Retrying message To CB tab");
                numtries ++;
                setTimeout(sendMessageToCB, 1000);
            } else if ( response && response.action == "pasteack") {
                numtries = 0;
                sendClickButtonToCB();
            }
        });
}

function sendClickButtonToCB() {

    console.log(" Sending clickButton message to CB tab try " + numtries);
    chrome.tabs.sendMessage(codebendertab.id , {action: "clickButton"} ,
        function (response) {
            console.log("Response received from CB tab");
            console.log(response);
            if ( ! response && numtries < 3) {
                console.log(" Retrying message To CB tab");
                numtries ++;
                setTimeout(sendClickButtonToCB, 1000);
            } else if ( response && response.action == "clickButtonAck") {
                console.log("ALl done");
            }
        });
}