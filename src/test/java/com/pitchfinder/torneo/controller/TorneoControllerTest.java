package com.pitchfinder.torneo.controller;

import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.campo.entity.Campo;
import com.pitchfinder.torneo.services.TorneoService;
import com.pitchfinder.torneo.services.TorneoServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TorneoControllerTest {

    private TorneoController servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession session;


    /**
     * Parameters declaration.
     */
    private static final String NOME = "Champions Five";
    private static final String SPORT = "Tennis";
    private static final String TIPO = "Eliminazione diretta";
    private static final String STRUTTURA = "Partite singole";
    private static final String DATA_INIZIO = "2021-12-5";
    private static final String DATA_FINE = "2021-12-15";
    private static final String GIORNO_PARTITE = "Giovedì";
    private static final String MAX_SQUADRE = "12";
    private static final String MIN_PARTECIPANTI = "1";
    private static final String MAX_PARTECIPANTI = "5";
    private static final int ID_CAMPO = 1002;

    @BeforeAll
    void setUp() {

        servlet = new TorneoController();
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);


        Admin admin = new Admin();
        admin.setNome("Emanuele");
        admin.setCognome("Mezzi");
        admin.setUsername("memex99");
        admin.setPassword("password");

        Campo campo = new Campo();
        campo.setIdentificativo(ID_CAMPO);
        campo.setSport(SPORT);

        Mockito.when(mockedRequest.getSession()).thenReturn(session);
        Mockito.when(mockedRequest.getSession().getAttribute("admin")).thenReturn(admin);
        Mockito.when(mockedRequest.getSession().getAttribute("campo")).thenReturn(campo);

    }

    @Test
    void TC_21_1() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Lunghezza nome non valida";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    @Test
    void TC_21_2() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("?<<Champions?F");
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Formato nome non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_21_3() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Sport non selezionato";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_21_4() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Tipo non selezionato";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    @Test
    void TC_21_5() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Struttura non selezionata";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_21_6() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Data inizio non selezionata";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    @Test
    void TC_21_7() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn("5/13/1980");
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Formato data inizio non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_21_8() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Data fine non selezionata";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
     void TC_21_9() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn("1970-13-10");
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Formato data fine non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }


    @Test
    void TC_21_10() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Lunghezza giorno partite non valida";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_21_11() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn("<<>/Giove/dì");
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Formato giorno partite non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_21_12() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn("n");
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Formato numero squadre non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    @Test
    void TC_21_13() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn("51");
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Numero di squadre non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    @Test
    void TC_21_14() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn("an");
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Formato minimo partecipanti non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_21_15() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn("0");
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Numero minimo di partecipanti non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_21_16() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn("aap");

        String message = "Numero massimo di partecipanti non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_21_17() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn("20");

        String message = "Numero massimo di partecipanti non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    @Test
    void TC_21_18() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        servlet.doGet(mockedRequest, mockedResponse);
        Mockito.verify(mockedResponse).setContentType("Creazione avvenuta");

    }

    @AfterAll
    void tearDown() {
        servlet = null;
        mockedRequest = null;
        mockedResponse = null;
        session = null;
        TorneoService ts = new TorneoServiceImpl();
        ts.deleteTorneo(ID_CAMPO, NOME, Date.valueOf(DATA_INIZIO));

    }
}