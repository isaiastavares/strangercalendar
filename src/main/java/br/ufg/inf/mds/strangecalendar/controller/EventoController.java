package br.ufg.inf.mds.strangecalendar.controller;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import br.ufg.inf.mds.strangecalendar.entidade.Interessado;
import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.repository.EventoRepository;
import br.ufg.inf.mds.strangecalendar.services.EventoService;
import br.ufg.inf.mds.strangecalendar.services.InteressadoService;
import br.ufg.inf.mds.strangecalendar.services.RegionalService;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;
import br.ufg.inf.mds.strangecalendar.util.Leitura;

/**
 * Controlador das operações relacionadas a Evento.
 *
 * @author Isaias Tavares
 */
@Controller
public class EventoController {

    private static final Logger LOG = LoggerFactory.getLogger(EventoController.class);

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("dd/MM/yyyy");

    @Autowired
    private EventoService eventoService;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private RegionalService regionalService;

    @Autowired
    private InteressadoService interessadoService;

    public void cadastrarEvento(Evento evento) throws ServicoException {

        getEventoService().inserir(evento);

    }

    public List<Evento> buscarEventoPorData(LocalDate data) {

        List<Evento> eventosFiltrados = eventoRepository.
                findByData(data);
        return eventosFiltrados;
    }

    public List<Evento> buscarEventoPorPalavraChave(String palavraChave) {

        List<Evento> eventosFiltrados = eventoRepository.
                findByDescricaoContaining(palavraChave);
        return eventosFiltrados;
    }

    private EventoService getEventoService() {
        return eventoService;
    }

}
