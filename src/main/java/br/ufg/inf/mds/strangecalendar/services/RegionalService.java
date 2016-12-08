package br.ufg.inf.mds.strangecalendar.services;

import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.repository.IRepository;
import br.ufg.inf.mds.strangecalendar.repository.RegionalRepository;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * Service de {@link Regional}
 *
 * @author Isaias Tavares
 */
@Service
public class RegionalService extends AbstractService<Regional> {

	private static final long serialVersionUID = 6089495135689042953L;

	private RegionalRepository regionalRepository;

	@Autowired
	public RegionalService(RegionalRepository regionalRepository) {
		this.regionalRepository = regionalRepository;
	}

	@Override
	public IRepository<Regional> getRepositorio() {
		return regionalRepository;
	}

	@PostConstruct
	public void initialize() {
		if (regionalRepository.findAll().isEmpty()) {
			Regional regional = new Regional();
			regional.setNome("UFG");
			regional.setCidade("Goiânia");
			regional.setEstado("Goiás");

			regionalRepository.save(regional);
		}
	}

	@Override
	protected void antesInserir(Regional regional) throws ServicoException {
		Optional<Regional> optional = regionalRepository
				.findByNomeIgnoreCase(regional.getNome());

		if(optional.isPresent() && optional.get().getId() != 
                        regional.getId()) {
			throw new ServicoException("Já existe uma Regional "
					+ "com o mesmo nome");
		}
	}
}
