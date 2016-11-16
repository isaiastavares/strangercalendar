package br.ufg.inf.mds.strangecalendar.repository;

import org.springframework.stereotype.Repository;

import br.ufg.inf.mds.strangecalendar.entidade.Regional;

@Repository
public interface RegionalRepository extends IRepository<Regional> {

	public Regional findByNome(String nome);

}
