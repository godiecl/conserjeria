/*
 * Copyright (c) 2023. Arquitectura de Software, DISC, UCN.
 */

package cl.ucn.disc.as.conserjeria.services;

import cl.ucn.disc.as.conserjeria.dao.PersonaFinder;
import cl.ucn.disc.as.conserjeria.exceptions.SistemaException;
import cl.ucn.disc.as.conserjeria.model.Departamento;
import cl.ucn.disc.as.conserjeria.model.Edificio;
import cl.ucn.disc.as.conserjeria.model.Persona;
import io.ebean.Database;
import io.ebean.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

/**
 * The Implementation of Sistema.
 *
 * @author Arquitectura de Software.
 */
@Slf4j
public class SistemaImpl implements Sistema {

    /**
     * The Database connection.
     */
    private final Database database;

    /**
     * The Constructor.
     *
     * @param database to use.
     */
    public SistemaImpl(@NotNull Database database) {
        this.database = database;
    }

    /**
     * Initialize
     */
    @Override
    public void populate() {
        // build the persona
        Persona persona = Persona.builder()
                .rut("76086428-5")
                .nombre("Diego")
                .apellidos("Urrutia Astorga")
                .email("durrutia@ucn.cl")
                .telefono("+5622355166")
                .build();
        this.database.save(persona);
    }

    /**
     * {@inheritDoc}
     */
    public Edificio add(@NotNull Edificio edificio) {
        try {
            this.database.save(edificio);
        } catch (PersistenceException ex) {
            throw new SistemaException("DB error al agregar un Edificio", ex);
        }
        return edificio;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Departamento add(Departamento departamento, Edificio edificio) {
        // use a transaction
        try (Transaction transaction = this.database.beginTransaction()) {
            edificio.add(departamento);
            this.database.save(edificio);

            transaction.commit();
        }
        return departamento;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Persona> getPersonas() {
        return this.database.find(Persona.class).findList();
    }

    /**
     * Retrieve a Persona by rut.
     *
     * @param rut to search.
     * @return the Persona with rut.
     */
    @Override
    public Optional<Persona> getPersona(final String rut) {
        return new PersonaFinder().byRut(rut);
    }

}
