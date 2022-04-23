$( document ).ready(function() {
    var x = document.getElementById("filtroConvenios");
    x.style.display = "none";
});
function myFunctionB1() {
    var x = document.getElementById("filtroConvenios");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";      
    }
    
}