package com.grouptwo.legisrate.data;

import com.grouptwo.legisrate.model.Legislation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

/**
 * LegisRate
 * Final Group Project
 * C65 Java Full-Stack with React
 * The Software Guild
 *
 * @author Russell Taylor, Rosalinda Powell, Derek Roberts, John Michael Rondello, Abdulrasaq Saliu
 * Date: January 15, 2021
 *
 * LegislationDaoDB
 * The legislation data-access-object interface
 */
@Repository
@Profile("database")
public class LegislationDaoDB implements LegislationDao {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructs the LegislationDaoDB class
     * @param jdbcTemplate the JDBC Template
     */
    @Autowired
    public LegislationDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Adds new legislation to the `Legislation` table in the database
     * @param legislation the new legislation
     * @return the new legislation updated with an auto-generated legislationID
     */
    @Override
    public Legislation add(Legislation legislation) {
        final String sql = "INSERT INTO `Legislation`(`LegislationTitle`, `Enacted`, `Summary`/*, `Sponsor`, `PdfUrl`*/) VALUES(?, ?, ?/*, ?, ?*/);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, legislation.getTitle());
            statement.setBoolean(2, legislation.isActive());
            statement.setString(3, legislation.getSummary());
//            statement.setString(4, legislation.getSponsor());
//            statement.setString(5, legislation.getPdfUrl());
            return statement;
        }, keyHolder);
        legislation.setLegislationID(keyHolder.getKey().intValue());
        return legislation;
    }

    /**
     * Gets a list of all legislation from the `Legislation` table in the database
     * @return a list of all legislation
     */
    @Override
    public List<Legislation> getAllLegislation() {
        final String sql = "SELECT `LegislatureId`, `LegislationTitle`, `Enacted`, `Summary`/*, `Sponsor`, `PdfUrl`*/ FROM `Legislation`;";
        return jdbcTemplate.query(sql, new LegislationMapper());
    }

    /**
     * Gets specified legislation from the `Legislation` table in the database
     * @param legislationID the ID of the specified legislation
     * @return the specified legislation
     */
    @Override
    public Legislation getLegislation(int legislationID) {
        try {
            final String sql = "SELECT `LegislatureId`, `LegislationTitle`, `Enacted`, `Summary`/*, `Sponsor`, `PdfUrl`*/ FROM `Legislation` WHERE `LegislatureId` = ?;";
            return jdbcTemplate.queryForObject(sql, new LegislationMapper(), legislationID);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * Updates a specified legislation in the `Legislation` table in the database
     * @param legislation the specified legislation
     * @return true if the specified legislation exists and is updated
     */
    @Override
    public boolean update(Legislation legislation) {
        final String sql = "UPDATE `Legislation` SET `LegislationTitle` = ?, `Enacted` = ?, `Summary` = ?/*, `Sponsor` = ?, `PdfUrl` = ?*/ WHERE `LegislatureId` = ?;";
        return jdbcTemplate.update(sql, legislation.getTitle(),/* legislation.getSponsor(),*/ legislation.isActive(), legislation.getSummary(),/* legislation.getPdfUrl(),*/ legislation.getLegislationID()) > 0;
    }

    /**
     * Deletes a specified legislation from the `Legislation` table in the database
     * @param legislationID the ID of the specified legislation
     * @return true if the specified legislation exists and is deleted
     */
    @Override
    public boolean delete(int legislationID) {
            final String sql = "DELETE FROM `Legislation` WHERE `LegislatureId` = ?;";
            return jdbcTemplate.update(sql, legislationID) > 0;
    }

    /**
     * The legislation mapper class
     */
    private static final class LegislationMapper implements RowMapper<Legislation> {

        /**
         * Maps database rows to Legislation objects
         * @param rs the result set
         * @param index the index of the current row
         * @return the Legislation object
         * @throws SQLException an SQL exception
         */
        @Override
        public Legislation mapRow(ResultSet rs, int index) throws SQLException {
            Legislation legislation = new Legislation();
            legislation.setLegislationID(rs.getInt("LegislatureId"));
            legislation.setTitle(rs.getString("LegislationTitle"));
            legislation.setActive(rs.getBoolean("Enacted"));
            legislation.setSummary(rs.getString("Summary"));
//            legislation.setSponsor(rs.getString("Sponsor"));
//            legislation.setPdfUrl(rs.getString("PdfUrl"));
            return legislation;
        }

    }

}
