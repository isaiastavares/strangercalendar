package br.ufg.inf.mds.strangecalendar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.repository.IRepository;
import br.ufg.inf.mds.strangecalendar.repository.RegionalRepository;

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
}
