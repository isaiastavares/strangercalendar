package br.ufg.inf.mds.strangecalendar.controller;

import java.util.List;

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
    	regionalService.inserir(regional);
    }

    public List<Regional> listarRegionais() {
    	return regionalService.getRepositorio().findAll();
    }

    public Regional excluirRegionalPorId(long idEvento) throws ServicoException {
       return regionalService.excluir(idEvento);
    }

}
