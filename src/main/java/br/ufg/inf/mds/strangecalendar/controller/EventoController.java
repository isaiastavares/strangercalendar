package br.ufg.inf.mds.strangecalendar.controller;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import br.ufg.inf.mds.strangecalendar.entidade.Interessado;
import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.enums.Interessados;
import br.ufg.inf.mds.strangecalendar.repository.EventoRepository;
import br.ufg.inf.mds.strangecalendar.repository.InteressadoRepository;
import br.ufg.inf.mds.strangecalendar.services.EventoService;
import br.ufg.inf.mds.strangecalendar.services.RegionalService;
import br.ufg.inf.mds.strangecalendar.services.exceptions.NaoEncontradoException;
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
    private RegionalService regionalService;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private InteressadoRepository interessadoRepository;

    public void cadastrarEvento(Evento evento) throws ServicoException {
    	eventoService.inserir(evento);
    }

    public List<Evento> listarEventos() {
    	return eventoService.getRepositorio().findAll();
    }

    public Evento excluirEventoPorId(Long idEvento) throws ServicoException {
    	return eventoService.excluir(idEvento);
    }

    public void atualizarEvento(Evento evento) throws ServicoException {
    	eventoService.atualizar(evento);
    }

    public Evento buscarEventoPorId(Long id) throws ServicoException {
        return eventoRepository.findById(id);
    }

    public List<Evento> buscarEventoPorData(LocalDateTime data) {
        return eventoRepository.findByData(data);
    }

    public List<Evento> buscarEventoPorPalavraChave(String palavraChave) {
    	return eventoRepository.
        		findByDescricaoContainingIgnoreCase(palavraChave);
    }

    public List<Evento> buscarEventoPorInteressado(Interessados interessado) {
        return interessadoRepository
        		.findByNome(interessado.getNome()).getEventos();
    }

    public List<Evento> buscarEventoPorRegional(long idRegional)
    		throws NaoEncontradoException {

        return regionalService.buscarPorId(idRegional).getEventos();
    }

    public List<Evento> buscarEventosPorPalavraChaveERegional(String palavraChave, Set<Regional> regionais) {
        return eventoRepository.findByDescricaoContainingIgnoreCaseAndRegionaisIn(palavraChave, regionais);
    }

    public List<Evento> buscarEventosPorInteressadoERegional(Set<Interessado> interessados, Set<Regional> regionais) {
        return eventoRepository.findByInteressadosInAndRegionaisIn(interessados, regionais);
    }

}
