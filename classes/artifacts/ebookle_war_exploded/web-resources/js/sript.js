function changeSize(alpha) {
    var fontSize = parseInt($(".reader").css("font-size"));
    fontSize = fontSize + alpha + "px";
    $(".reader").css({'font-size':fontSize});
}
function changeWidth(alpha) {
    var widthSize = parseInt($(".reader").css("width"));
    widthSize = widthSize + alpha + "px";
    $(".reader").css({'width':widthSize});
}