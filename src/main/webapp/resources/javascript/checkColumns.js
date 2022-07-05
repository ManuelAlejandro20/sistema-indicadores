/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function handleCheckBox(indice) {                                
    PF("cToggler").columns[indice].className = "ui-state-default ui-helper-hidden align-center";
    var datosChild = document.getElementById("form:indicadores_data").childNodes;
    datosChild.forEach(function(child){
        child.children[indice + 2].className = "ui-helper-hidden align-center";
    });
    var checkBox = document.getElementsByClassName("ui-columntoggler-items")[0].childNodes[indice].childNodes[0].childNodes[1];
    var checkBoxIcon = checkBox.childNodes[0];                
    checkBox.className = "ui-chkbox-box ui-widget ui-corner-all ui-state-default";
    checkBoxIcon.className = "ui-chkbox-icon ui-icon ui-icon-blank";
}  
