package br.ufg.inf.mds.strangecalendar.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.repository.IRepository;
import br.ufg.inf.mds.strangecalendar.repository.RegionalRepository;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;

@Service
public class RegionalService extends AbstractService<Regional> {

	private static final long serialVersionUID = 6089495135689042953L;

	private RegionalRepository regionalRepository;

	@Autowired
	public RegionalService(RegionalRepository regionalRepository) {
		this.regionalRepository = regionalRepository;
	}

	@Override
	protected IRepository<Regional> getRepositorio() {
		return regionalRepository;
	}

	@Override
	protected void antesInserir(Regional regional) throws ServicoException {
		Optional<Regional> optional = regionalRepository.findByNomeIgnoreCase(regional.getNome());

		if(optional.isPresent() && optional.get().getId() != regional.getId()) {
			throw new ServicoException("Já existe uma Regional com o mesmo nome");
		}
	}
}
