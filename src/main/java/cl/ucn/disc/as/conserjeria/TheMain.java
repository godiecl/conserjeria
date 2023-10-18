/*
 * Copyright (c) 2023. Arquitectura de Software, DISC, UCN.
 */

package cl.ucn.disc.as.conserjeria;

import cl.ucn.disc.as.conserjeria.ui.ApiRestServer;
import cl.ucn.disc.as.conserjeria.ui.WebController;
import lombok.extern.slf4j.Slf4j;

/**
 * The Main.
 *
 * @author Arquitectura de Software.
 */
@Slf4j
public final class TheMain {

    /**
     * The Main.
     *
     * @param args to use.
     */
    public static void main(String[] args) {

        log.debug("Starting Main ..");

        ApiRestServer.start(7070, new WebController());

        log.debug("Done. :)");

    }

}
