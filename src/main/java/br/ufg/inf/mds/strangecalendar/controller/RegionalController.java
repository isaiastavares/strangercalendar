package br.ufg.inf.mds.strangecalendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.services.RegionalService;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;

/**
 * Controlador responsável pelas operações relacionadas a Regional
 *
 * @author Isaias Tavares
 */
@Controller
public class RegionalController {

    @Autowired
    private RegionalService regionalService;

    public void cadastrarRegional(Regional regional) throws ServicoException {
        getRegionalService().inserir(regional);
    }

    private RegionalService getRegionalService() {
        return regionalService;
    }

}
