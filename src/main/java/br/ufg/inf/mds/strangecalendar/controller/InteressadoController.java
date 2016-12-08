package br.ufg.inf.mds.strangecalendar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.ufg.inf.mds.strangecalendar.entidade.Interessado;
import br.ufg.inf.mds.strangecalendar.services.InteressadoService;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;

/**
 * Controlador responsável pelas operações relacionadas ao Interessado
 *
 * @author Isaias Tavares
 */
@Controller
public class InteressadoController {

    @Autowired
    private InteressadoService interessadoService;

    public List<Interessado> listarInteressados() {
    	return interessadoService.getRepositorio().findAll();
    }

    public Interessado findInteressadoPorId(long idInteressado) throws ServicoException {
        return interessadoService.buscarPorId(idInteressado);
     }
}
