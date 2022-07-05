function buttonDisable(){      
    var mensajeUsername = document.getElementById('j_username');
    var mensajePassword = document.getElementById('j_password');        
 
    var mensajeUsernameInvalido = (mensajeUsername.getAttribute("aria-invalid") === 'true');
    var mensajePasswordInvalido = (mensajePassword.getAttribute("aria-invalid") === 'true');

    if(!mensajeUsernameInvalido && !mensajePasswordInvalido && mensajeUsername.reportValidity()){
        PF('submitButton').disable();
    }

}        