package com.pitchfinder.torneo.controller;

import com.pitchfinder.autenticazione.dao.UtenteDAO;
import com.pitchfinder.autenticazione.dao.UtenteDAOImpl;
import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.squadra.dao.SquadraDAO;
import com.pitchfinder.squadra.dao.SquadraDAOImpl;
import com.pitchfinder.squadra.entity.Squadra;
import com.pitchfinder.torneo.dao.TorneoDAO;
import com.pitchfinder.torneo.dao.TorneoDAOImpl;
import com.pitchfinder.torneo.entity.Torneo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IscrizioneTorneoControllerTest {
    private ServletContext mockedServletContext;
    private RequestDispatcher mockedDispatcher;
    private IscrizioneTorneoController servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession session;
    private Utente utente;
    private Torneo torneo;
    private Squadra s;
    private Squadra s1;
    private TorneoDAO torneoDAO = new TorneoDAOImpl();
    private UtenteDAO utenteDAO = new UtenteDAOImpl();
    private SquadraDAO squadraDAO = new SquadraDAOImpl();


    @BeforeAll
    public void start(){
        servlet = new IscrizioneTorneoController();
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        mockedServletContext = Mockito.mock(ServletContext.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);

        //Create tournament
        torneo = new Torneo("serieA", "tipo", "gironi", "lunedi", "memex99", 20, 2, 12, Date.valueOf("2022-12-10"),  Date.valueOf("2023-12-10"), 1002);
        torneoDAO.doSaveTorneo(torneo);

        //Create User
        utente = new Utente("manuzzi97@gmail.com","memex97","Emanuele","Mezzi","ciao",Date.valueOf("1998-12-10"));
        utenteDAO.doSaveUtente(utente);

        //Create teams
        s = new Squadra("juve", torneo.getNome(), torneo.getDataInizio(), torneo.getCampoIdentificativo(),2,"lucia",utente.getEmail());
        squadraDAO.doSaveSquadra(s);
        s1 = new Squadra("ve", torneo.getNome(), torneo.getDataInizio(), torneo.getCampoIdentificativo(),2,"lucia", utente.getEmail());
        squadraDAO.doSaveSquadra(s1);

        Mockito.doReturn(session).when(mockedRequest).getSession();
        Mockito.doReturn(utente).when(session).getAttribute("utente");
    }

    /**
     *Test case: the lenght of name of team.
     */
    @Test
    public void TC_2_2_1(){

        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("Lucia");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("Lorenzo");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("Pinolo");




        String message = "Lunghezza nome squadra non valida";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void TC_2_2_2(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");


        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("NomeSquad!");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("Lucia");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("Lorenzo");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("Pinolo");



        String message = "Formato nome squadra non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void TC_2_2_3(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("NomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("3333");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("Lucia");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("Lorenzo");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("Pinolo");


        String message = "Lunghezza numero dei giocatori non valida";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void TC_2_2_4(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("NomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("e");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("Lucia");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("Lorenzo");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("Pinolo");



        String message = "Formato numero giocatori non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void TC_2_2_5(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");


        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("NomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("Lorenzo");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("Pinolo");



        String message = "Lunghezza nome giocatore non valida";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void TC_2_2_6(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("NomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("!Ff");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("Lorenzo");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("Pinolo");



        String message = "Formato Nome giocatore non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
        public void TC_2_2_7(){
            Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

            Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
            Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
            Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

            Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("NomeSquadra");
            Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
            //Player
            Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("Lucia");
            Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
            Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
            Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

            Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("");
            Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("Pinolo");

            String message = "Lunghezza nome capitano non valida";

            IllegalArgumentException exception;
            exception = assertThrows(IllegalArgumentException.class,
                    () -> servlet.doGet(mockedRequest, mockedResponse));
            assertEquals(message, exception.getMessage());
    }

    @Test
    public void TC_2_2_8(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("NomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("Lucia");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("dww!");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("Pinolo");



        String message = "Formato nome capitano non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void TC_2_2_9(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("NomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("Lucia");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("Luca");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("");



        String message = "Lunghezza cognome capitano non valida";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void TC_2_2_10(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("NomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("Lucia");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("Luca");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("sdw!");



        String message = "Formato cognome capitano non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void TC_2_2_11() throws ServletException, IOException {
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("NomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("Lucia");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("Luca");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("Sigillo");
        Mockito.doReturn(mockedServletContext).when(mockedRequest).getServletContext();
        Mockito.doReturn(mockedDispatcher).when(mockedServletContext).getRequestDispatcher("/view/torneo/dettagliTorneo.jsp");

        servlet.doPost(mockedRequest, mockedResponse);
        Mockito.verify(mockedResponse).setContentType("Iscrizione avvenuta con successo");

    }
    @Test
    public void squadraNull(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("Lucia");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("Luca");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("");



        String message = "Nome squadra non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }
    @Test
    public void numeroGiocatoriNull(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("nomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn(null);
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("Lucia");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("Luca");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("cognome");



        String message = "Numero dei giocatori non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }
    @Test
    public void numeroGiocatoriNonAdattoAlTorneo(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("nomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("13");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("Lucia");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("Luca");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("cognome");



        String message = "Il numero dei giocatori non rispetta le direttive del torneo";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void nomeCapitanoNull(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("nomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("Lucia");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("cognome");



        String message = "Nome capitano non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void cognomeCapitanoNull(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("nomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("Lucia");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("null");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn(null);



        String message = "Cognome capitano non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void nomeGiocatoreNull(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("nomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("Gaeta");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("null");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("ciao");



        String message = "Nome giocatore non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void cognomeGiocatoreNull(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("nomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("null");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("null");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("ciao");



        String message = "Cognome giocatore non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void lunghezzaCognomeGiocatoreNonValida(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("nomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("pippo");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("null");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("ciao");



        String message = "Lunghezza cognome giocatore non valida";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }


    @Test
    public void formatoCognomeGiocatoreNonValido(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("nomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("pippo");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("nxn!");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("null");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("ciao");



        String message = "Formato cognome giocatore non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void utenteNonValido(){
        Mockito.when(mockedRequest.getParameter("conferma")).thenReturn("conferma");

        Mockito.when(mockedRequest.getParameter("nomeTorneo")).thenReturn("serieA");
        Mockito.when(mockedRequest.getParameter("dataTorneo")).thenReturn("2022-12-10");
        Mockito.when(mockedRequest.getParameter("campoTorneo")).thenReturn("1002");

        Mockito.doReturn(null).when(session).getAttribute("utente");

        Mockito.when(mockedRequest.getParameter("nomeSquadra")).thenReturn("nomeSquadra");
        Mockito.when(mockedRequest.getParameter("nGiocatori")).thenReturn("2");
        //Player
        Mockito.when(mockedRequest.getParameter("nome0")).thenReturn("pippo");
        Mockito.when(mockedRequest.getParameter("cognome0")).thenReturn("nxn!");
        Mockito.when(mockedRequest.getParameter("nome1")).thenReturn("Lucio");
        Mockito.when(mockedRequest.getParameter("cognome1")).thenReturn("Sinto");

        Mockito.when(mockedRequest.getParameter("nomeCapitano")).thenReturn("null");
        Mockito.when(mockedRequest.getParameter("cognomeCapitano")).thenReturn("ciao");



        String message = "Utente non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
        Mockito.doReturn(utente).when(session).getAttribute("utente");
    }
    


    @AfterAll
    public void clean(){
        servlet = null;
        mockedRequest = null;
        mockedResponse = null;
        session = null;

        //remove torneo
        torneoDAO.doRemoveTorneo(torneo);
        //remove utente
        utenteDAO.doRemoveUtente(utente);
        //remove squadre
        squadraDAO.doRemoveSquadra(s);
        squadraDAO.doRemoveSquadra(s1);


    }

}
