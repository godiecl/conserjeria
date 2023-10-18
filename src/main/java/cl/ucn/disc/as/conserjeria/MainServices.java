/*
 * Copyright (c) 2023. Arquitectura de Software, DISC, UCN.
 */

package cl.ucn.disc.as.conserjeria;

import cl.ucn.disc.as.conserjeria.model.Departamento;
import cl.ucn.disc.as.conserjeria.model.Edificio;
import cl.ucn.disc.as.conserjeria.services.Sistema;
import cl.ucn.disc.as.conserjeria.services.SistemaImpl;
import io.ebean.DB;
import io.ebean.Database;
import lombok.extern.slf4j.Slf4j;

/**
 * The Main.
 *
 * @author Arquitectura de Software.
 */
@Slf4j
public final class MainServices {

    /**
     * The Main.
     *
     * @param args to use.
     */
    public static void main(String[] args) {

        log.debug("Starting Main ..");

        // get the database
        Database db = DB.getDefault();

        // the sistema
        Sistema sistema = new SistemaImpl(db);

        Edificio edificio = Edificio.builder()
                .nombre("Don Salomon")
                .direccion("Angamos #0610")
                .build();
        log.debug("Edificio: {}", edificio);

        edificio = sistema.add(edificio);
        log.debug("Edificio: {}", edificio);

        Departamento departamento = Departamento.builder()
                .piso(1)
                .numero(100)
                .build();
        log.debug("Departamento: {}", departamento);

        departamento = sistema.add(departamento, edificio);
        log.debug("Departamento: {}", departamento);

        /*

        // build the persona
        Persona persona = Persona.builder()
                .rut("76086428-5")
                .nombre("Diego")
                .apellidos("Urrutia Astorga")
                .email("durrutia@ucn.cl")
                .telefono("+5622355166")
                .build();
        log.debug("The Persona before db: ${}", persona);

        // save into the database
        db.save(persona);

        log.debug("The Persona after db: ${}", persona);

        // finder de personas
        PersonaFinder pf = new PersonaFinder();
        Optional<Persona> oPersona = pf.byRut("76086428-5");
        oPersona.ifPresent(p -> log.debug("Persona from db: {}", p));
        //*/

        log.debug("Done. :)");

    }

}
