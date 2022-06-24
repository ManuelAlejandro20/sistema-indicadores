/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function checkColumns(num){               
    if(num === 0 || num === 7){
        PF('columns').hide();
        if(num === 0){
            var elemento = document.getElementById("form:tableCheckBox").getElementsByTagName('input');
            elemento[2].checked = true;
            updateTableCheckBox();
            updateIndicadores();
        }      
    }                 
}       
