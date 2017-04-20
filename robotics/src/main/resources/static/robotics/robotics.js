ivr_code_header=
    "\n"+
    "#define HG7881_A_IA 10 // D10 --> Motor A Input A --> MOTOR A +\n"+
    "#define HG7881_A_IB 11 // D11 --> Motor A Input B --> MOTOR A -\n"+
    " \n"+
    "// functional connections\n"+
    "#define MOTOR_A_PWM HG7881_A_IA // Motor A PWM Speed\n"+
    "#define MOTOR_A_DIR HG7881_A_IB // Motor A Direction\n"+
    " \n"+
    "// the actual values for \"fast\" and \"slow\" depend on the motor\n"+
    "#define PWM_SLOW 85  // arbitrary slow speed PWM duty cycle\n"+
    "#define PWM_FAST 255 // arbitrary fast speed PWM duty cycle\n"+
    "#define PWM_NORMAL 170 // Middle of the road\n"+
    "#define DIR_DELAY 1000 // brief delay for abrupt motor changes\n"+
    "\n"+
    "int set_speed = PWM_FAST;\n"+
    " \n"+
    "void setup()\n"+
    "{\n"+
    "  pinMode( MOTOR_A_DIR, OUTPUT );\n"+
    "  pinMode( MOTOR_A_PWM, OUTPUT );\n"+
    "  stop_motor();\n"+
    "\n"+
    "  program();  \n"+
    "}\n"+
    "\n"+
    "void program() {\n";


    ivr_code_footer =
    "\n"+
    "}\n"+
    " \n"+
    "void loop()\n"+
    "{\n"+
    "\n"+
    "}\n"+
    "\n"+
    "void stop_motor() {\n"+
    "   digitalWrite( MOTOR_A_DIR, LOW );\n"+
    "   digitalWrite( MOTOR_A_PWM, LOW );\n"+
    "}\n"+
    "\n"+
    "void motor_ccw() {\n"+
    "   digitalWrite( MOTOR_A_DIR, LOW ); // direction = forward\n"+
    "   analogWrite( MOTOR_A_PWM, set_speed ); // PWM speed = fast\n"+
    "}\n"+
    "\n"+
    "void motor_cw() {\n"+
    "   digitalWrite( MOTOR_A_DIR, HIGH ); // direction = reverse\n"+
    "   analogWrite( MOTOR_A_PWM, 255-set_speed ); // PWM speed = fast\n"+
    "}\n"+
    "\n"+
    "void motorspeed(int speed ) {\n"+
    "  switch ( speed ) {\n"+
    "    case PWM_SLOW :\n"+
    "        set_speed = PWM_SLOW; break;\n"+
    "\n"+
    "    case PWM_FAST :\n"+
    "        set_speed = PWM_FAST; break;\n"+
    "\n"+
    "    case PWM_NORMAL : \n"+
    "      \n"+
    "    default:\n"+
    "        set_speed = PWM_NORMAL; break;\n"+
    "  }\n"+
    "}\n"+
    "\n"+
    "void motor_ccw(int seconds) {\n"+
    "   stop_motor();\n"+
    "   motor_ccw();\n"+
    "   delay(seconds * DIR_DELAY);\n"+
    "   stop_motor();\n"+
    "}\n"+
    "\n"+
    "void motor_cw(int seconds) {\n"+
    "   stop_motor();\n"+
    "   motor_cw();\n"+
    "   delay(seconds * DIR_DELAY);\n"+
    "   stop_motor();\n"+
    "}\n"+
    "\n"+
    "\n"+
    "\n"+
    "void control_wait(int seconds) {\n"+
    "  delay(seconds * DIR_DELAY);\n"+
    "  \n"+
    "}\n"+
    "\n"+
    "void setcolor() {\n"+
    "  \n"+
    "}\n"+
    "\n"+
    "\n"+
    "void motorspeed() {\n"+
    "  \n"+
    "}\n"+
    "\n"+
    "/*EOF*/";




ivr_outputcode = ivr_code_header;


function ivr_outputtext(input_xml){

    console.log(input_xml);

    var parser = new DOMParser();
    var xmlDoc = parser.parseFromString(input_xml,"text/xml");
    var blocks = xmlDoc.getElementsByTagName("block");

    if ( blocks.length <3 ) {
        alert("Not enough blocks");
    }

    var startblocktype = blocks[0].getAttribute('type');

    var endblocktype = blocks[blocks.length-1].getAttribute('type');

    if ( startblocktype == 'event_whenflagclicked') {
        if ( endblocktype == 'control_stop') {
            // Now we're talking
            var nblk =  nextBlock(blocks[0]);


            processblocks(nblk);



        } else {
            alert("Program must end with stop block");
        }

    } else {

        alert("Program must start with Flag block");

    }


}



function processblocks(nblk) {
    while (nblk ){
        var blocktype = nblk.getAttribute('type');
        console.log("saw block with type" + blocktype);
        switch (blocktype) {
            case "wedo_motorcounterclockwise" :
                ivr_wedo_motor_ccw(nblk);
                break;
            case "wedo_motorclockwise" :
                ivr_wedo_motor_cw(nblk);
                break;

            case "wedo_motorspeed" :
                ivr_wedo_motorspeed(nblk);
                break;

            case "wedo_setcolor" :
                ivr_wedo_setcolor(nblk);
                break;

            case "control_wait" :
                ivr_control_wait(nblk);
                break;


            case "control_repeat" :
                ivr_control_repeat(nblk);
            default:
                ivr_default_handler;
                break;
        }

        nblk = nextBlock(nblk);

    }
}


function nextBlock(block) {
    for ( var i = 0 ; i < block.children.length ; i++) {
        if ( block.children[i].tagName == "next") {
            var n = block.children[i];
            for ( var j = 0; j < n.children.length; j++) {
                if (n.children[j].tagName == "block") {
                    return n.children[j];
                }
            }
        }
    }
}

function ivr_control_repeat(block){

    var n;
    for ( var i = 0 ; i < block.children.length ; i ++) {
        if ( block.children[i].tagName == "value") {
            n = block.children[i];

            break;
        }
    }

    var numrepeat = n.getElementsByTagName("field")[0].childNodes[0].nodeValue;
    var emit = " for ( int i =0 ; i < " + numrepeat + " ; i ++ ) { ";

    ivr_outputcode = ivr_outputcode + emit;


    var stmt;
    for ( var i = 0 ; i < block.children.length ; i ++) {
        if ( block.children[i].tagName == "statement") {
            stmt = block.children[i];

            break;
        }
    }

    var subblock;

    for ( var i = 0 ; i < stmt.children.length ; i ++) {
        if ( stmt.children[i].tagName == "block") {
            subblock = stmt.children[i];

            break;
        }
    }

    processblocks(subblock);

    var emit = " };";
    ivr_outputcode = ivr_outputcode + emit;
}


function ivr_default_handler() {

    alert("Unknown block found");
}

function ivr_wedo_motor_ccw(block) {

    var emit = "motor_ccw(" + block.getElementsByTagName("field")[0].childNodes[0].nodeValue + ");";
    ivr_outputcode = ivr_outputcode + emit;
}

function ivr_wedo_motor_cw(block) {

    var emit = "motor_cw(" + block.getElementsByTagName("field")[0].childNodes[0].nodeValue + ");";
    ivr_outputcode = ivr_outputcode + emit;
}

function ivr_control_wait(block) {
    var emit = "control_wait(" + block.getElementsByTagName("field")[0].childNodes[0].nodeValue + ");";
    ivr_outputcode = ivr_outputcode + emit;
}

function ivr_wedo_setcolor(block) {
    var emit = "setcolor(\"" + block.getElementsByTagName("field")[0].childNodes[0].nodeValue + "\");";
    ivr_outputcode = ivr_outputcode + emit;
}

function ivr_wedo_motorspeed(block) {
    var emit = "motorspeed();";
   // var emit = "motorspeed(\"" + block.getElementsByTagName("field")[0].childNodes[0].nodeValue + "\");";
    ivr_outputcode = ivr_outputcode + emit;
}
