package br.ufg.inf.mds.strangecalendar.repository;

import org.springframework.stereotype.Repository;

import br.ufg.inf.mds.strangecalendar.entidade.Interessado;

@Repository
public interface InteressadoRepository extends IRepository<Interessado> {

	public Interessado findByNome(String nome);

}
