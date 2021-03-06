<%@ page import="java.util.ArrayList" %>
<%@ page import="com.pitchfinder.torneo.entity.Torneo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pitchfinder.partita.entity.Partita" %>
<%@ page import="com.pitchfinder.evento.entity.Evento" %><%--
  Created by IntelliJ IDEA.
  User: memex_99
  Date: 04/01/2021
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>


    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
    <script language="JavaScript" type="text/javascript" src="js/occupazione/js_occupation.js"></script>
    <title>PitchFinder</title>
    <link href="css/admin_profile/style_adminProfile.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<body>
<%
    List<Torneo> tornei = new ArrayList<>();
    List<Evento> eventi = new ArrayList<>();
    List<Partita> partite = new ArrayList<>();
    tornei = (List<Torneo>) application.getAttribute("tornei");
    eventi = (List<Evento>) application.getAttribute("eventi");
    partite = (List<Partita>) application.getAttribute("partite");
%>

<div class="hero">
    <div class="rowFirst">

        <div class="col3-admin">
            <div class="list-group-admin">
                <a href="#" class="list-group-item-admin" id="profilo">Profilo</a>
                <a href="#" class="list-group-item-admin" id="crea_torneo">Crea Torneo</a>
                <a href="#" class="list-group-item-admin" id="crea_evento">Crea Evento</a>
                <a href="#" class="list-group-item-admin" id="modifica_campo">Modifica Campo</a>
                <a href="#" class="list-group-item-admin" id="all_tornei">Visualizza Torneo</a>
                <a href="#" class="list-group-item-admin" id="all_evento">Visualizza Evento</a>
                <a href="#" class="list-group-item-admin" id="all_partite">Visualizza Partite</a>
            </div>
        </div>



        <div class="col9-admin">
            <div class="card-body">



                <div class="card-body-admin" id="profiloAdmin">
                    <div class="row1">
                        <div class="col-md-12-admin">
                            <h4><span><i class="fas fa-cog"></i></span>Profilo</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">
                        <div class="col-md-12-admin">
                            <input type="hidden" value="${message}" id="flagMessage">
                            <%
                                String result = (String) application.getAttribute("result");
                                if(result != null && result.equals("1")){
                            %>
                            <script>
                                $(document).ready( function(){
                                    setTimeout(() => {alert(document.getElementById("flagMessage").value);}, 500)
                                    }
                                );
                            </script>

                        <% application.setAttribute("result","0");
                                }%>
                            <div class="data">
                                <div class="offset-4 col-8">

                                    <p>${admin.username}</p>
                                    <p>${admin.nome}</p>
                                    <p>${admin.cognome}</p>
                                    <br>
                                    <br>
                                    <form action = "autentication" method = "get">
                                        <input type="hidden" name="flag" value="4">
                                        <input name="submit_esci" type="submit" class="btn_impostazioni" id = "log_out"
                                               value="Logout"> <!-- button per uscire dall'area dell'amministratore-->
                                    </form>
                                    <br>
                                    <br>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>








                <div class="card-body-admin" id="creaTorneo" style="display: none"> <!-- div utenti presenti nel DB-->
                    <div class="row1">
                        <div class="colo-title-admin">
                            <h4><span><i class="fas fa-database"></i></span> Creazione Torneo</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">

                        <div class="container">
                            <form action="torneoServlet" method="get" onsubmit="return validateButton()">
                                <input type="hidden" name="flag" value="1">
                                <input type="hidden" name="idCampo" value="1002">
                                <div class="form-group">
                                    <label for="uname">Nome:</label>
                                    <input type="text" class="form-control" id="uname" name="nome" oninput="validateName()" onblur="validateName()" required>
                                    <div id="valid_nome">Il nome non può superare 50 caratteri.</div>

                                </div>

                                <div class="form-group">
                                    <label for="sport">Sport:</label>
                                    <select name="sport" id="sport" onchange="validateSport()" onblur="validateSport()">
                                        <option value="null" selected></option>
                                        <option value="Tennis">Tennis</option>
                                    </select>
                                    <div id="valid_sport">Seleziona uno sport del torneo.</div>
                                </div>
                                <div class="form-group">
                                    <label for="tipo">Tipo:</label>
                                    <select name="tipo" id="tipo" onchange="validateTipo()" onblur="validateTipo()">
                                        <option value="null" selected></option>
                                        <option value="Gironi">Gironi</option>
                                        <option value="Eliminazione diretta">Eliminazione diretta</option>
                                        <option value="Gironi + Eliminazione diretta">Gironi + Eliminazione diretta</option>
                                    </select>
                                    <div id="valid_tipo">Seleziona una tipologia del torneo.</div>
                                </div>
                                <div class="form-group">
                                    <label for="struttura">Struttura:</label>
                                    <select name="struttura" id="struttura" onchange="validateStruttura()" onblur="validateStruttura()">
                                        <option value="null" selected></option>
                                        <option value="Partite singole">Partite singole</option>
                                        <option value="Partite doppie">Partite doppie</option>
                                    </select>
                                    <div id="valid_struttura">Seleziona una struttra del torneo.</div>
                                </div>

                                <div class="form-group">
                                    <label for="data_inizio">Data Inizio:</label>
                                    <input type="date" class="form-control" id="data_inizio" name="dataInizio" required onchange="validateDataInizio(); activeDataFine(); activeGiornoPartite()" onblur="validateDataInizio(); validateAllDate()">
                                    <div id="valid_dataInizio">Inserisci una data di inizio.</div>
                                </div>
                                <div class="form-group">
                                    <label for="data_fine">Data Fine:</label>
                                    <input type="date" class="form-control" id="data_fine" name="dataFine" required onclick="minDateFine(); maxDateFine()" disabled  onchange="validateDataFine(); activeGiornoPartite()" onblur="validateDataFine(); validateAllDate()">
                                    <div id="valid_dataFine">Inserisci una data di fine.</div>
                                    <div id="valid_AllSquadre">Inserisci una data di fine successiva alla data di inizio.</div>
                                </div>
                                <div class="form-group">
                                    <label for="giornoPartite">Giorno Partite:</label>
                                    <input type="text" list="daysPartite" name="giornoPartite" id="giornoPartite" oninput="validateGiornoPartite()" onblur="validateGiornoPartite()"/>
                                    <datalist id="daysPartite">
                                        <option value="Lunedì">Lunedì</option>
                                        <option value="Martedì">Martedì</option>
                                        <option value="Mercoledì">Mercoledì</option>
                                        <option value="Giovedì">Giovedì</option>
                                        <option value="Venerdì">Venerdì</option>
                                        <option value="Sabato">Sabato</option>
                                        <option value="Domenica">Domenica</option>
                                    </datalist>

                                    <div id="valid_giornoPartite">Seleziona un giorno in cui si giocheranno le partite.</div>
                                </div>

                                <div class="form-group">
                                    <label for="maxSquadre">Numero squadre torneo:</label>
                                    <input type="text" class="form-control" id="maxSquadre" name="maxSquadre" min="1" max="20" required oninput="validateSquadre()" onchange="validateSquadre()" onblur="validateSquadre()">
                                    <div id="valid_squadra">Il numero delle squadre deve essere maggiore di 1 e minore di 20.</div>
                                </div>
                                <div class="form-group">
                                    <label for="minPartecipanti">Numero minimo di giocatori per squadra:</label>
                                    <input type="text" class="form-control" id="minPartecipanti" name="minPartecipanti" min="1" max="5" required oninput="validateMinPartecipanti()" onchange="validateMinPartecipanti()" onblur="validateMinPartecipanti()">
                                    <div id="valid_minParteci">Il numero minimo di giocatori deve essere maggiore di 1 e minore di 5.</div>
                                </div>
                                <div class="form-group">
                                    <label for="maxPartecipanti">Numero massimo di giocatori per squadra:</label>
                                    <input type="text" class="form-control" id="maxPartecipanti" name="maxPartecipanti" min="5" max="12" required oninput="validateMaxPartecipanti()" onchange="validateMaxPartecipanti()" onblur="validateMaxPartecipanti()">
                                    <div id="valid_maxParteci">Il numero massimo di giocatori deve essere maggiore di 5 e minore di 12.</div>
                                </div>

                                <button type="submit" class="btn btn-primary" id="createTorneo">Crea torneo</button>
                            </form>

                        </div>


                    </div>
                </div>



                <div class="card-body-admincreaEvento" id="creaEvento" style="display: none">
                    <div class="row1">
                        <div class="colo-title-admin">
                            <h4><span><i class="fas fa-database"></i></span> Crezione Evento</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">
                        <div class="container">
                            <form action="EventoAdminController" class="was-validated" method="post" id="form-creation" onsubmit="return validateEvento()">
                                <div class="form-group">
                                    <label for="creation-name-Evento" id="creation-label-name-Evento">Nome dell'evento:</label>
                                    <input type="text" class="form-control" id="creation-name-Evento" placeholder="Inserisci il nome dell'evento" name="nome" required onkeyup="validateNameEvento()"><br>
                                    <div class="valid-feedback" id="name-evento-valid">Valido</div>
                                    <div class="invalid-feedback" id="name-evento-invalid">La lunghezza del nome non è valida</div>
                                </div>
                                <div class="form-group">
                                    <label for="creation-timestr-Evento" id="creation-label-str-Evento">Orario Inizio: (XX:YY)</label>
                                    <input type="text" id="creation-timestr-Evento" class="form-control" name="orarioInizio" required onkeyup="validateTimeStrEvento()"><br>
                                    <div class="valid-feedback" id="time-evento-str-valid">Valido</div>
                                    <div class="invalid-feedback" id="time-evento-str-invalid">Inserire l’orario di inizio</div>
                                </div>
                                <div class="form-group">
                                    <label for="creation-timeend-Evento" id="creation-label-end-Evento">Orario Fine: (XX:YY)</label>
                                    <input type="text" id="creation-timeend-Evento" class="form-control" name="orarioFine" required onkeyup="validateTimeEndEvento()"><br>
                                    <div class="valid-feedback" id="time-evento-end-valid">Valido</div>
                                    <div class="invalid-feedback" id="time-evento-end-invalid">Inserisci l’orario di fine</div>
                                </div>
                                <div class="form-group">
                                    <label for="creation-data-Evento" id="creation-label-data-Evento">Data evento: (AAAA-MM-GG)</label>
                                    <input type="text" id="creation-data-Evento" class="form-control" name="data" required onkeyup="valiDateEvento()"><br>
                                    <div class="valid-feedback" id="date-evento-valid">Valido</div>
                                    <div class="invalid-feedback" id="date-evento-invalid">Inserire la data</div>
                                </div>
                                <div class="form-group">
                                    <label for="creation-guest-Evento" id="creation-label-guest">Nome Ospite:</label>
                                    <input type="text" id="creation-guest-Evento" class="form-control" name="ospite" required onkeyup="validateGuestEvento()"><br>
                                    <div class="valid-feedback" id="guest-evento-valid">Valido</div>
                                    <div class="invalid-feedback" id="guest-evento-invalid">La lunghezza del nome dell’ospite non è valida</div>
                                </div>
                                <div class="form-group">
                                    <label for="creation-description-Evento" id="creation-label-description">Descrizione :</label><br>
                                    <input id="creation-description-Evento" class="form-control" name="descrizione" required onkeyup="validateDescriptionEvento()"><br>
                                    <div class="valid-feedback" id="description-evento-valid">Valido</div>
                                    <div class="invalid-feedback" id="description-evento-invalid">La lunghezza della descrizione non è valida</div>
                                </div>


                                <div class="form-group">
                                    <label for="creation-player-Evento" id="creation-label-player">Posti disponibili:</label>
                                    <input type="text" id="creation-player-Evento" class="form-control" name="postiDisponibili" required onkeyup="validateSitsEvento()"><br>
                                    <div class="valid-feedback" id="sits-evento-valid">Valido</div>
                                    <div class="invalid-feedback" id="sits-evento-invalid">Il numero di posti disponibili non è valido</div>
                                </div>



                                <p> Inserisci un'immagine per l'evento</p>
                                <div class="form-group form-check radioEvent">
                                    <label class="form-check-label">
                                        <div class="form-group form-check">
                                            <input type="radio" class="form-check-input" id="image1" name="immagine" value="1">
                                            <label for="image1">
                                                <img src="images/events/image1.jpg" alt="Trulli" width="120" height="70">
                                            </label>
                                        </div>
                                        <div class="form-group form-check">
                                        <input type="radio" class="form-check-input" id="image2" name="immagine" value="2">
                                        <label for="image2">
                                            <img src="images/events/image2.jpg" alt="asd" width="120" height="70">
                                        </label>
                                        </div>
                                        <div class="form-group form-check">
                                        <input type="radio" class="form-check-input" id="image3" name="immagine" value="3">
                                        <label for="image3">
                                            <img src="images/events/image3.jpg" alt="dsa" width="120" height="70">
                                        </label>
                                        </div>
                                        <div class="form-group form-check">
                                        <input type="radio" class="form-check-input" id="default" name="immagine" value="default">
                                        <label for="default">senza Immagine</label><br>
                                        </div>
                                    </label>
                                </div>
                                <button type="submit" class="btn btn-primary" name="submit" id="submit">Crea Evento</button>
                            </form>
                        </div>
                    </div>
                </div>


                <div class="card-body-adminmodificaCampo" id="modificaCampo" style="display: none;"> <!-- div ordini presenti nel DB-->
                    <div class="row1">
                        <div class="col-md-12-admin">
                            <h4><span><i class="fas fa-database"></i></span> Modifica disponibilità campo</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1" id="ModificaDispCampo">
                        <!--Div per la creazione-->
                        <div class="wrapper">
                            <div class="inner">
                                <form action="ModificaDispCampoController">

                                    <div class="form-row">
                                        <div class="form-wrapper">

                                            <table>
                                                <tr>
                                                    <td>
                                                        <label for="data">Data</label>
                                                    </td>
                                                    <td>
                                                        <input type="date" class="form-control datepicker-here" name="data" id="data" required><br>
                                                    </td>
                                                    <td>
                                                        <small id="small-creation-data"> Inserire una data valida(DD/MM/YYYY)</small><br>
                                                    </td>
                                                </tr>
                                                <tr>

                                                </tr>
                                            </table>
                                        </div>
                                        <div class="form-wrapper">
                                            <table>
                                                <tr>
                                                    <td>
                                                        <label for="inizio">Orario Inizio</label>
                                                    </td>
                                                    <td>
                                                        <input type="time" name="inizio" id="inizio"  size="5" required><br>
                                                    </td>
                                                    <td>
                                                        <small id="small-creation-timestr"> Inserire un orario di inizio valido(HH:MM)</small><br>

                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="form-wrapper">
                                            <table>
                                                <tr>
                                                    <td>
                                                        <label for="fine">Orario Fine</label>
                                                    </td>
                                                    <td>
                                                        <input type="time"  name="fine" id="fine" size="5" required><br>

                                                    </td>
                                                    <td>
                                                        <small id="small-creation-timeend"> Inserire un orario di fine valido(HH:MM)</small><br>
                                                    </td>
                                                </tr>
                                            </table>
                                             </div>
                                    </div>
                                    <div class="form-row last">
                                        <div class="form-wrapper">
                                            <label for="idcampo">Sport</label>
                                            <select name="idcampo" id="idcampo" class="form-control">
                                                <option value="1002">Tennis</option>
                                            </select>
                                        </div>
                                    </div>

                                    <% if(session.getAttribute("admin")!=null){ %>
                                    <button type="submit" name="Occupa" class="btn_impostazioni" value="Occupa" data-text="Occupa" id="Occupa">
                                        <span>Occupa</span>
                                    </button>

                                    <button type="submit" name="Libera" class="btn_impostazioni" value="Libera" data-text="Libera" id="Libera">
                                        <span>Libera</span>
                                    </button>
                                    <%} %>
                                </form>
                            </div>
                            <!--- Esito --->
                            <% String esito = (String) request.getAttribute("risultatoOccupa");
                            String esito1= (String) request.getAttribute("risultatoLibera");
                                if(esito!= null){
                                    if(esito.equals("1")){
                                        request.setAttribute("risultatoOccupa",null);}%>
                                <script>
                                alert("La modifica va a buon fine");
                                </script>
                            <%}else if (esito1!= null){
                                            if(esito.equals("1")){
                                                request.setAttribute("risultatoLibera",null);}%>
                                <script>
                                        alert("La liberazione va a buon fine");
                                </script>
                                <%}%>

                        </div>
                    </div>
                </div>


                <div class="card-body-adminallTorneo" id="allTorneo" style="display: none;">
                    <div class="row1">
                        <div class="colo-title-admin">
                            <h4><span><i class="fas fa-database"></i></span> Tornei creati</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">
                        <div class="data">
                            <table class="responsive-table" style="width: 100%">

                                <thead>
                                <tr>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Data Inizio</th>
                                    <th scope="col">Data Fine</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>

                                <tbody id="tbody_torneo">
                                <% for(Torneo t : tornei) {%>
                                <tr id = "<%= t.getDataInizio()%>">
                                    <th scope = "row"> <%= t.getNome()%> </th>
                                    <td data-title = "Data Inizio"> <%= t.getDataInizio()%> </td>
                                    <td data-title = "Data Fine"> <%= t.getDataFine()%> </td>
                                    <td data-title = "Remove">
                                        <form action="torneoServlet" method="get">
                                            <input type="hidden" name="flag" value="2">
                                            <input type="hidden" name="idCampo" value="1002">
                                            <input type="hidden" name="nome" value="<%= t.getNome()%>">
                                            <input type="hidden" name="dataInizio" value="<%= t.getDataInizio()%>">
                                            <input type="hidden" name="dataFine" value="<%= t.getDataFine()%>">
                                            <input type="hidden" name="giornoPartite" value="<%= t.getGiornoPartite()%>">
                                            <button class = "remove" name = "<%= t.getDataInizio()%>">
                                                Remove
                                            </button>

                                        </form>

                                    </td>
                                </tr>
                                <%}%>

                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>


                <div class="card-body-adminallEvento" id="allEvento" style="display: none;">
                    <div class="row1">
                        <div class="colo-title-admin">
                            <h4><span><i class="fas fa-database"></i></span> Eventi creati</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">
                        <div class="data">
                            <table class="responsive-table" style="width: 100%">

                                <thead>
                                <tr>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Data</th>
                                    <th scope="col">Ospite</th>
                                    <th scope="col">Orario</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>

                                <tbody id="tbody_evento">
                                <% for (Evento e : eventi) { %>
                                <tr>
                                    <th scope = "row"> <%= e.getName()%> </th>
                                    <td data-title = "Data"> <%= e.getDate()%> </td>
                                    <td data-title = "Ospite"> <%= e.getGuest()%> </td>
                                    <td data-title = "Orario"> <%= e.getStartHour()%> - <%= e.getEndHour()%> </td>
                                    <td data-title = "Remove">
                                        <form action="EventoAdminDeleteController" method="get">
                                            <input type="hidden" name="nome" value="<%= e.getName()%>">
                                            <input type="hidden" name="data" value="<%= e.getDate()%>">
                                            <button class = "remove">
                                                Remove
                                            </button>

                                        </form>
                                    </td>
                                </tr>
                                <%}%>

                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>



                <div class="card-body-adminallPartite" id="allPartite" style="display: none;">
                    <div class="row1">
                        <div class="colo-title-admin">
                            <h4><span><i class="fas fa-database"></i></span> Partite create</h4>
                            <hr>
                            <input type="date" id="filter-date" name="filter-date">
                            <input type="time" id="filter-time" name="filter-time">
                            <input type="button" class="remove" id="filter-button" name="filter-button" value="Filtra" onclick="filterMatch()">
                        </div>
                    </div>
                    <div class="row1">
                        <div class="data">
                            <table class="responsive-table" style="width: 90%">

                                <thead>
                                <tr>
                                    <th scope="col">IdCampo</th>
                                    <th scope="col">UtenteEmail</th>
                                    <th scope="col">Data</th>
                                    <th scope="col">Orario Inizio</th>
                                    <th scope="col">Orario Fine</th>
                                    <th scope="col">Show</th>
                                </tr>
                                </thead>

                                <tbody id="tbody_partite">

                                <% for(Partita partita : partite) { %>
                                <tr>
                                    <th scope = "row"> <%=partita.getIdCampo() %></th>
                                    <td data-title = "UtenteEmail"> <%= partita.getEmailUtente()%> </td>
                                    <td data-title = "Data"> <%=partita.getData() %></td>
                                    <td data-title = "Orario Inizio"> <%=partita.getOrarioInizio()%></td>
                                    <td data-title = "Orario Fine"><%=partita.getOrarioFine()%>  </td>
                                    <td data-title = "Show">
                                        <button class = "remove" name = "<%=partita.getIdPartita()%>"
                                                onclick = "showPlayer(name)">  <!-- button per visualizzare-->
                                            Giocatori
                                        </button>
                                    </td>
                                </tr>
                                <% }%>
                                </tbody>
                            </table>
                            <!--- players table --->
                            <div class="data" id="players-table">
                            </div>
                            <!--- players table --->
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>


<script src="js/autenticazione/js_admin.js"></script>
<script src="js/autenticazione/js_admin_showMatch.js"></script>
<script src="js/autenticazione/js_checkTorneo.js"></script>


</head>
</body>
</html>
