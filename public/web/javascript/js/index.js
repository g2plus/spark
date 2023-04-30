$(function() {
    $("ul#menu>.box").mouseenter(function() {
        $(this).children("ul").css("display", "block");
    });
    $("ul#menu>.box").mouseleave(function() {
        $(this).children("ul").hide();
    });
});