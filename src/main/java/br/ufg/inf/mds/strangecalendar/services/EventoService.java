package br.ufg.inf.mds.strangecalendar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import br.ufg.inf.mds.strangecalendar.repository.EventoRepository;
import br.ufg.inf.mds.strangecalendar.repository.IRepository;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;

/**
 * Service de {@link Evento}
 *
 * @author Isaias Tavares
 */
@Service
public class EventoService extends AbstractService<Evento> {

	private static final long serialVersionUID = 6089495135689042953L;

	private EventoRepository eventoRepository;

	@Autowired
	public EventoService(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}

	@Override
	public IRepository<Evento> getRepositorio() {
		return eventoRepository;
	}

	@Override
	protected void antesInserir(Evento entidade) throws ServicoException {
		// TODO implementar validacao
	}
}
