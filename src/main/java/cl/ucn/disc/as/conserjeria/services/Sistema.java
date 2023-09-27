/*
 * Copyright (c) 2023. Arquitectura de Software, DISC, UCN.
 */

package cl.ucn.disc.as.conserjeria.services;

import cl.ucn.disc.as.conserjeria.model.Departamento;
import cl.ucn.disc.as.conserjeria.model.Edificio;

/**
 * The Sistema.
 *
 * @author Arquitectura de Software.
 */
public interface Sistema {

    /**
     * Crear un Edificio al sistema.
     *
     * @param edificio a agregar.
     * @return el Edificio agregado.
     */
    Edificio add(Edificio edificio);


    /**
     * Crea y asocia un Departamento a un Edificio.
     *
     * @param departamento a agregar.
     * @param edificio     a asociar.
     * @return the Departamento.
     */
    Departamento add(Departamento departamento, Edificio edificio);

}
