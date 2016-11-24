package br.ufg.inf.mds.strangecalendar.controller;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.enums.Interessados;
import br.ufg.inf.mds.strangecalendar.repository.EventoRepository;
import br.ufg.inf.mds.strangecalendar.repository.InteressadoRepository;
import br.ufg.inf.mds.strangecalendar.repository.RegionalRepository;
import br.ufg.inf.mds.strangecalendar.services.EventoService;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;

/**
 * Controlador das operações relacionadas a Evento.
 *
 * @author Isaias Tavares
 */
@Controller
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private InteressadoRepository interessadoRepository;
    
    @Autowired
    private RegionalRepository regionalRepository;

    public void cadastrarEvento(Evento evento) throws ServicoException {
    	eventoService.inserir(evento);
    }

    public List<Evento> buscarEventoPorData(LocalDate data) {
        List<Evento> eventosFiltrados = eventoRepository.findByData(data);
        return eventosFiltrados;
    }

    public List<Evento> buscarEventoPorPalavraChave(String palavraChave) {
        List<Evento> eventosFiltrados = eventoRepository.
        		findByDescricaoContainingIgnoreCase(palavraChave);
        return eventosFiltrados;
    }

    public List<Evento> buscarEventoPorInteressado(Interessados interessado) {
        List<Evento> eventosFiltrados = interessadoRepository.findByNome(interessado.getNome()).getEventos();
        return eventosFiltrados;
    }

    public List<Evento> buscarEventoPorRegional(Regional regional) {
        List<Evento> eventosFiltrados = regional.getEventos();
        return eventosFiltrados;  
    }

}
