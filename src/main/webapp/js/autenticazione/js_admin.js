

    $(document).ready(function(){
    $("#profilo").click(function(){
        $("#profiloAdmin").show();
        $("#creaTorneo").hide();
        $("#creaEvento").hide();
        $("#modificaCampo").hide();
        $("#allTorneo").hide();
        $("#allEvento").hide();
        $("#allPartite").hide();
    });
    $("#crea_torneo").click(function(){
    $("#profiloAdmin").hide();
    $("#creaTorneo").show();
    $("#creaEvento").hide();
    $("#modificaCampo").hide();
    $("#allTorneo").hide();
    $("#allEvento").hide();
    $("#allPartite").hide();
});
    $("#crea_evento").click(function(){
    $("#profiloAdmin").hide();
    $("#creaTorneo").hide();
    $("#creaEvento").show();
    $("#modificaCampo").hide();
    $("#allTorneo").hide();
    $("#allEvento").hide();
    $("#allPartite").hide();
});
    $("#modifica_campo").click(function(){
    $("#profiloAdmin").hide();
    $("#creaTorneo").hide();
    $("#creaEvento").hide();
    $("#modificaCampo").show();
    $("#allTorneo").hide();
    $("#allEvento").hide();
    $("#allPartite").hide();
});
    $("#all_tornei").click(function(){
    $("#profiloAdmin").hide();
    $("#creaTorneo").hide();
    $("#creaEvento").hide();
    $("#modificaCampo").hide();
    $("#allTorneo").show();
    $("#allEvento").hide();
    $("#allPartite").hide();
});
    $("#all_evento").click(function(){
    $("#profiloAdmin").hide();
    $("#creaTorneo").hide();
    $("#creaEvento").hide();
    $("#modificaCampo").hide();
    $("#allTorneo").hide();
    $("#allEvento").show();
    $("#allPartite").hide();
});
    $("#all_partite").click(function(){
    $("#profiloAdmin").hide();
    $("#creaTorneo").hide();
    $("#creaEvento").hide();
    $("#modificaCampo").hide();
    $("#allTorneo").hide();
    $("#allEvento").hide();
    $("#allPartite").show();
});
});

/**** EVENT Javascript *****/
var isDateValidEvento=false;
var isNameValidEvento=false;
var isGuestValidEvento=false;
var isDescriptionValidEvento=false;
var isTimeStrValidEvento=false;
var isTimeEndValidEvento=false;
var isSitsEvento=false;

    /** validate Nome **/
    function validateNameEvento(){

        if( $("#creation-name-Evento").val().match("^[a-zA-Z0-9\u00C0-\u00ff'\\s]+$")){
            if($("#creation-name-Evento").val().length < 1 || $("#creation-name-Evento").val().length>50) {
                $("#name-evento-valid").text("La lunghezza del nome non è valida");
                $("#name-evento-valid").css("color", "#FF0000");
                isNameValidEvento = false;
            }else{
                $("#name-evento-valid").text("Valido");
                $("#name-evento-valid").css("color","#4CAF50");
                isNameValidEvento=true;
            }
        }else{
            $("#name-evento-valid").text("Il formato del nome non è valido");
            $("#name-evento-valid").css("color","#FF0000");
            isNameValidEvento=false;
        }
    }


    /** Validate Data **/
    /** min Date **/
    function minDateEvento() {
        var currentDate = new Date();
        var dd = String(currentDate.getDate()).padStart(2, '0');
        var mm = String(currentDate.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = currentDate.getFullYear();

        currentDate = yyyy + '-' + mm + '-' + dd;
        $('#creation-data-Evento').attr("min",currentDate);
    }
    /** max Date **/
    function maxDateEvento(){
        var currentDate = new Date();
        var dd = String(currentDate.getDate()).padStart(2, '0');
        var mm = String(currentDate.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = currentDate.getFullYear();
        currentDate = (yyyy+10) + '-' + mm + '-' + dd;

        $('#creation-data-Evento').attr("max",currentDate);
    }
    /** valiDateEvento **/
    function valiDateEvento(){
        if(document.getElementById("creation-data-Evento").value.match("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")){
            $("#date-evento-valid").text("La data è valida, rispetta il formato");
            $("#date-evento-valid").css("color","#4CAF50");
            isDateValidEvento=true;
        }else{
            $("#date-evento-valid").text("La data non rispetta il formato");
            $("#date-evento-valid").css("color","#FF0000");
            isDateValidEvento=false;
        }
    }

    /** validateTimeStrEvento **/
    function validateTimeStrEvento(){
        if(document.getElementById("creation-timestr-Evento").value.match("^[0-9]{2}:[0-9]{2}$")){
            $("#time-evento-str-valid").text("L'orario di inizio è valido, rispetta il formato");
            $("#time-evento-str-valid").css("color","#4CAF50");
            isTimeStrValidEvento=true;
        }else{
            $("#time-evento-str-valid").text("L'orario di inizio non rispetta il formato");
            $("#time-evento-str-valid").css("color","#FF0000");
            isTimeStrValidEvento=false;
        }
    }

    /** validateTimeEndEvento **/
    function validateTimeEndEvento(){
        if(document.getElementById("creation-timeend-Evento").value.match("^[0-9]{2}:[0-9]{2}$")){
            $("#time-evento-end-valid").text("L'orario di fine è valido, rispetta il formato");
            $("#time-evento-end-valid").css("color","#4CAF50");
            isTimeEndValidEvento=true;
        }else{
            $("#time-evento-end-valid").text("L'orario di fine non rispetta il formato");
            $("#time-evento-end-valid").css("color","#FF0000");
            isTimeEndValidEvento=false;
        }
    }

    /** validate Ospite **/
    function validateGuestEvento(){

        if( $("#creation-guest-Evento").val().match("^[ a-zA-z\u00C0-\u00ff\']+")){
            if($("#creation-guest-Evento").val().length < 1 || $("#creation-guest-Evento").val().length>20) {
                $("#guest-evento-valid").text("La lunghezza del nome dell’ospite non è valida");
                $("#guest-evento-valid").css("color", "#FF0000");
                isGuestValidEvento = false;
            }else{
                $("#guest-evento-valid").text("Nome Valido");
                $("#guest-evento-valid").css("color","#4CAF50");
                isGuestValidEvento=true;
            }
        }else{
            $("#guest-evento-valid").text("Il formato dell'ospite non è valido");
            $("#guest-evento-valid").css("color","#FF0000");
            isGuestValidEvento=false;
        }
    }

    /** validate Descrizione **/
    function validateDescriptionEvento(){

        if( $("#creation-description-Evento").val().match("^[ a-zA-z\u00C0-\u00ff\']+")){
            if($("#creation-description-Evento").val().length < 1 || $("#creation-description-Evento").val().length>500) {
                $("#description-evento-valid").text("La lunghezza della descrizione non è valida");
                $("#description-evento-valid").css("color", "#FF0000");
                isDescriptionValidEvento = false;
            }else{
                $("#description-evento-valid").text("Descrizione Valida");
                $("#description-evento-valid").css("color","#4CAF50");
                isDescriptionValidEvento=true;
            }
        }else{
            $("#description-evento-valid").text("Il formato della descrizione non è valido");
            $("#description-evento-valid").css("color","#FF0000");
            isDescriptionValidEvento=false;
        }
    }

    /** validateSitsEvento **/
    function validateSitsEvento(){
        if(document.getElementById("creation-player-Evento").value.match("^[0-9]+$")){
            $("#sits-evento-valid").text("Il numero dei posti disponibili è valido, rispetta il formato");
            $("#sits-evento-valid").css("color","#4CAF50");
            isSitsEvento=true;
        }else{
            $("#sits-evento-valid").text("Il numero dei posti disponibili non rispetta il formato");
            $("#sits-evento-valid").css("color","#FF0000");
            isSitsEvento=false;
        }
    }

function validateEvento(){
        validateNameEvento();
        valiDateEvento();
        validateTimeStrEvento();
        validateTimeEndEvento();
        validateGuestEvento();
        validateDescriptionEvento();
        validateSitsEvento();

        if(isDateValidEvento && isNameValidEvento
            && isGuestValidEvento && isDescriptionValidEvento
            && isTimeStrValidEvento && isTimeEndValidEvento && isSitsEvento ){
            return true;
        }
        else {
            return false;
        }

}

    $(document).ready(function(){
        minDateEvento();
        maxDateEvento();
    });

/**** END EVENT Javascript *****/
