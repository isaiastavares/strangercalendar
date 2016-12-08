package br.ufg.inf.mds.strangecalendar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.inf.mds.strangecalendar.entidade.Interessado;
import br.ufg.inf.mds.strangecalendar.repository.IRepository;
import br.ufg.inf.mds.strangecalendar.repository.InteressadoRepository;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;

/**
 * Service de {@link Interessado}
 *
 * @author Isaias Tavares
 */
@Service
public class InteressadoService extends AbstractService<Interessado> {

	private static final long serialVersionUID = 6089495135689042953L;

	private InteressadoRepository interessadoRepository;

	@Autowired
	public InteressadoService(InteressadoRepository eventoRepository) {
		this.interessadoRepository = eventoRepository;
	}

	@Override
	public IRepository<Interessado> getRepositorio() {
		return interessadoRepository;
	}

	@Override
	protected void antesInserir(Interessado entidade) throws ServicoException {
		// TODO implementar validacao
	}
}
