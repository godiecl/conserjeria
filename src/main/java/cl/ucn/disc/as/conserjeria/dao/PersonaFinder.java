/*
 * Copyright (c) 2023. Arquitectura de Software, DISC, UCN.
 */

package cl.ucn.disc.as.conserjeria.dao;

import cl.ucn.disc.as.conserjeria.model.Persona;
import cl.ucn.disc.as.conserjeria.model.query.QPersona;
import io.ebean.Finder;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * The Finder of Persona.
 *
 * @author Diego Urrutia-Astorga.
 */
public class PersonaFinder extends Finder<Long, Persona> {


    /**
     * The Constructor.
     */
    public PersonaFinder() {
        super(Persona.class);
    }

    /**
     * Find a Persona by rut.
     *
     * @param rut to use.
     * @return the Persona.
     */
    public Optional<Persona> byRut(@NotNull String rut) {
        return new QPersona().rut.eq(rut).findOneOrEmpty();
    }
}
