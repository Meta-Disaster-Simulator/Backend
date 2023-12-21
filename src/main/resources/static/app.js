var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/wsconnect');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/info', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        stompClient.subscribe('/topic/new', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        stompClient.subscribe('/topic/chat', function (greeting) {
            showGreeting(JSON.parse(greeting.body).command);
        });

    });
}

function disconnect() {

    stompClient.send("/app/disconnect", {}, "hi")
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}



function sendName() {
    var dto = {
        'nickname' : 'kangmingi',
        'score':1000,
        'pos_x':3.14,
        'pos_y':3.14,
        'pos_z':3.14,
        'rot_x':3.14,
        'rot_y':3.14,
        'rot_z':3.14,
        'is_jump':false,
        'is_walk':false,
        'is_run':false
    }
    stompClient.send("/topic/info", {}, JSON.stringify(dto));
}

function showGreeting(message) {
    // 메시지를 HTML에 추가하는 부분을 보장하기 위해 .text()를 사용하여 XSS 공격을 방지합니다.
    $("#greetings").append($("<tr>").append($("<td>").text(message)));
}
function sendNew(){
    var dto = {
        'nickname' : 'kangmingi',
        'score':1000,
        'islogin':true
    }
    stompClient.send("/topic/new", {}, JSON.stringify(dto))

}
function sendchat(){
    var dto = {
        'nickname' : 'kangmingi',
        'command' : 'hi working chat'
    }
    stompClient.send("/app/chat", {}, JSON.stringify(dto))

}



$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#new" ).click(function() { sendNew(); });
    $( "#chat" ).click(function() { sendchat(); });

});