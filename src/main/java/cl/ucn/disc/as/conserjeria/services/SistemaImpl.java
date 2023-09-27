/*
 * Copyright (c) 2023. Arquitectura de Software, DISC, UCN.
 */

package cl.ucn.disc.as.conserjeria.services;

import cl.ucn.disc.as.conserjeria.exceptions.SistemaException;
import cl.ucn.disc.as.conserjeria.model.Departamento;
import cl.ucn.disc.as.conserjeria.model.Edificio;
import io.ebean.Database;
import io.ebean.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.persistence.PersistenceException;

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


}
