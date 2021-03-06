package com.pitchfinder.torneo.dao;

import com.pitchfinder.singleton.ConPool;
import com.pitchfinder.torneo.entity.Torneo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * This class manages the Torneo dao.
 */

public class TorneoDAOImpl implements TorneoDAO {

    /**
     * This method makes the Torneo object persist
     * in the database.
     *
     * @param torneo object
     * @return boolean -> true: execute success / false: execute failed
     */
    @Override
    public boolean doSaveTorneo(final Torneo torneo) {

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("insert into Torneo(Nome, DataInizio,"
                    + "CampoIdentificativo, AdminUsername, Tipo, Struttura, NumeroSquadre, DataFine,"
                    + "MinNumeroPartecipantiSquadra, MaxNumeroPartecipantiSquadra, GiornoPartite)"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, torneo.getNome());
            ps.setDate(2, torneo.getDataInizio());
            ps.setInt(3, torneo.getCampoIdentificativo());
            ps.setString(4, torneo.getAdminUsername());
            ps.setString(5, torneo.getTipo());
            ps.setString(6, torneo.getStruttura());
            ps.setInt(7, torneo.getNumeroSquadre());
            ps.setDate(8, torneo.getDataFine());
            ps.setInt(9, torneo.getMinNumeroPartecipantiPerSquadra());
            ps.setInt(10, torneo.getMaxNumeroPartecipantiPerSquadra());
            ps.setString(11, torneo.getGiornoPartite());
            ps.executeUpdate();
            return true;
        } catch (SQLException s) {
            return false;
        }
    }

    /**
     * This method remove Torneo object
     * from database.
     *
     * @param torneo object
     * @return boolean -> true: execute success / false: execute failed
     */
    @Override
    public boolean doRemoveTorneo(Torneo torneo) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("delete from Torneo where Nome = ? and DataInizio = ? and CampoIdentificativo = ?");

            ps.setString(1, torneo.getNome());
            ps.setDate(2, torneo.getDataInizio());
            ps.setInt(3, torneo.getCampoIdentificativo());

            return ps.executeUpdate() == 1;
        } catch (SQLException s) {
            return false;
        }
    }

    /**
     * This method allows to get all the tournaments
     * from the database.
     *
     * @return A List of Torneo items.
     */
    public List<Torneo> doRetrieveAllTornei() {

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from torneo");
            ResultSet rs = ps.executeQuery();

            List<Torneo> tornei = new ArrayList<>();

            while (rs.next()) {
                Torneo t = new Torneo();
                t.setNome(rs.getString(1));
                t.setDataInizio(rs.getDate(2));
                t.setCampoIdentificativo(rs.getInt(3));
                t.setAdminUsername(rs.getString(4));
                t.setTipo(rs.getString(5));
                t.setStruttura(rs.getString(6));
                t.setNumeroSquadre(rs.getInt(7));
                t.setDataFine(rs.getDate(8));
                t.setMinNumeroPartecipantiPerSquadra(rs.getInt(9));
                t.setMaxNumeroPartecipantiPerSquadra(rs.getInt(10));
                t.setGiornoPartite(rs.getString(11));
                tornei.add(t);

            }
            return tornei;

        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }

    /**
     * This mehod allows to check if other tournaments have been scheduled in the same period.
     *
     * @param dataInizio start data of the tournament
     * @param dataFine   end data of the tournament
     * @param idCampo    pitch identifier
     * @return boolean : true -> there are other tournaments / false -> empty at the time
     */
    public boolean doCheckTorneo(Date dataInizio, Date dataFine, int idCampo) {

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from torneo where CampoIdentificativo = ? and "
                    + "((DataInizio <= ? and DataFine > ?) or"
                    + "(DataInizio < ? and DataFine >= ?))");

            ps.setInt(1, idCampo);
            ps.setDate(2, dataInizio);
            ps.setDate(3, dataInizio);
            ps.setDate(4, dataFine);
            ps.setDate(5, dataFine);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException s) {
            throw new RuntimeException(s);
        }

    }

    /**
     * This method allows to get a tournament
     * from the database.
     *
     * @param nome       name of the tournament
     * @param dataInizio start date of the tournament
     * @param idCampo    end date of the tournament
     * @return Torneo item
     */
    public Torneo doRetrieveTorneo(String nome, Date dataInizio, int idCampo) {

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from torneo "
                    + "where Nome = ? and DataInizio = ? and CampoIdentificativo = ?");

            ps.setString(1, nome);
            ps.setDate(2, dataInizio);
            ps.setInt(3, idCampo);

            ResultSet rs = ps.executeQuery();

            Torneo t = null;

            while (rs.next()) {

                t = new Torneo();
                t.setNome(rs.getString(1));
                t.setDataInizio(rs.getDate(2));
                t.setCampoIdentificativo(rs.getInt(3));
                t.setAdminUsername(rs.getString(4));
                t.setTipo(rs.getString(5));
                t.setStruttura(rs.getString(6));
                t.setNumeroSquadre(rs.getInt(7));
                t.setDataFine(rs.getDate(8));
                t.setMinNumeroPartecipantiPerSquadra(rs.getInt(9));
                t.setMaxNumeroPartecipantiPerSquadra(rs.getInt(10));
                t.setGiornoPartite(rs.getString(11));

            }

            return t;

        } catch (SQLException s) {
            return null;
        }

    }

    /**
     * doRetrieveNIscritti.
     * @param torneo - Torneo.
     * @return int
     */
    @Override
    public int doRetrieveNIscritti(Torneo torneo) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select count(Nome) from squadra "
                    + "where TorneoNome = ? and TorneoDataInizio = ? and TorneoCampoIdentificativo = ?");
            ps.setString(1, torneo.getNome());
            ps.setDate(2, torneo.getDataInizio());
            ps.setInt(3, torneo.getCampoIdentificativo());

            ResultSet rs = ps.executeQuery();
            int n = 0;
            if (rs.next()) {
                n = rs.getInt(1);
            }

            return n;

        } catch (SQLException throwables) {
            return 0;
        }
    }

}
