package com.pitchfinder.prenotazione.entity;

import java.util.Date;
import java.util.Objects;

public class Prenotazione {
    /**
     * Is the user email.
     */
    private String utenteEmail;
    /**
     * Is the event name.
     */
    private String eventoNome;
    /**
     * Is the event's date.
     */
    private Date eventoData;
    /**
     * Is the code of booking.
     */
    private int codicePrenotazione;

    /**
     * Is the void constructor.
     */
    public Prenotazione() {
    }

    /**
     *Is the Constructor.
     * @param utenteEmail - is the user email.
     * @param eventoNome - is the name of the event.
     * @param eventoData - is the date of the event.
     * @param codicePrenotazione - is the code of booking.
     */
    public Prenotazione(final String utenteEmail, final String eventoNome, final Date eventoData, final int codicePrenotazione) {
        this.utenteEmail = utenteEmail;
        this.eventoNome = eventoNome;
        this.eventoData = eventoData;
        this.codicePrenotazione = codicePrenotazione;
    }

    /**
     * Return utenteEmail.
     * @return String
     */
    public String getUtenteEmail() {
        return utenteEmail;
    }

    /**
     * Sets the variable utenteEmail.
     * @param utenteEmail - is the utente email.
     */
    public void setUtenteEmail(final String utenteEmail) {
        this.utenteEmail = utenteEmail;
    }

    /**
     * Returns eventoNome.
     * @return string
     */
    public String getEventoNome() {
        return eventoNome;
    }

    /**
     * Sets eventoNome.
     * @param eventoNome - is the name of event
     */
    public void setEventoNome(final String eventoNome) {
        this.eventoNome = eventoNome;
    }

    /**
     * Returns eventoData.
     * @return Date
     */
    public Date getEventoData() {
        return eventoData;
    }

    /**
     * Sets eventoData.
     * @param eventoData - is the date of the even.
     */
    public void setEventoData(final Date eventoData) {
        this.eventoData = eventoData;
    }

    /**
     * Returns codiceP.
     * @return int
     */
    public int getCodicePrenotazione() {
        return codicePrenotazione;
    }

    /**
     * Sets the booking code.
     * @param codicePrenotazione - is the code of booking.
     */
    public void setCodicePrenotazione(final int codicePrenotazione) {
        this.codicePrenotazione = codicePrenotazione;
    }

    /**
     * Takes Object o and if o is equal to this return true else return false.
     * @param o - is istance of the class Prenotazione.
     * @return boolean
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Prenotazione that = (Prenotazione) o;
        return codicePrenotazione == that.codicePrenotazione && Objects.equals(utenteEmail, that.utenteEmail)
                && Objects.equals(eventoNome, that.eventoNome) && Objects.equals(eventoData, that.eventoData);
    }

    /**
     * Returns a hash value for an object.
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(utenteEmail, eventoNome, eventoData, codicePrenotazione);
    }

    /**
     *Returns a string that can be considered as the
     * "textual representation" of the object
     * on which it is invoked.
     * @return String
     */
    @Override
    public String toString() {
        return "Prenotazione{" + "utenteEmail='" + utenteEmail + '\''
                + ", eventoNome='" + eventoNome + '\''
                + ", eventoData=" + eventoData
                + ", codicePrenotzione=" + codicePrenotazione
                + '}';
    }
}
