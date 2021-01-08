package com.grouptwo.legisrate.data;

import com.grouptwo.legisrate.model.Legislation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
public class LegislationDaoDB {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LegislationDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Adds new legislation to the `Legislation` table in the database
     * @param legislation the new legislation
     * @return the new legislation updated with an auto-generated legislationID
     */
    public Legislation add(Legislation legislation) {
        return new Legislation();
    }

    /**
     * Gets a list of all legislation from the `Legislation` table in the database
     * @return a list of all legislation
     */
    public List<Legislation> getAllLegislation() {
        return new ArrayList<>();
    }

    /**
     * Gets specified legislation from the `Legislation` table in the database
     * @param legislationID the ID of the specified legislation
     * @return the specified legislation
     */
    public Legislation getLegislation(int legislationID) {
        return new Legislation();
    }

    /**
     * Updates a specified legislation in the `Legislation` table in the database
     * @param legislation the specified legislation
     * @return true if the specified legislation exists and is updated
     */
    public boolean update(Legislation legislation) {
        return true;
    }

    /**
     * Deletes a specified legislation from the `Legislation` table in the database
     * @param legislationID the ID of the specified legislation
     * @return true if the specified legislation exists and is deleted
     */
    public boolean delete(int legislationID) {
        return true;
    }

}