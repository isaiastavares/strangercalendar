package br.ufg.inf.mds.strangecalendar.services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import br.ufg.inf.mds.strangecalendar.entidade.Interessado;
import br.ufg.inf.mds.strangecalendar.enums.Interessados;
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

	/**
     * Inserindo os interessados na primeira vez que a aplicação subir...
     */
    @PostConstruct
    public void initialize() {
        if (interessadoRepository.findAll().isEmpty()) {
            for (Interessados interessado : Interessados.values()) {
            	interessadoRepository.save(
            			new Interessado(interessado.getNome(),
            			new ArrayList<Evento>()));
            }
        }
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
