package br.ufg.inf.mds.strangecalendar.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.ufg.inf.mds.strangecalendar.entidade.Regional;

/**
 * Repositório de {@link Regional}
 *
 * @author Isaias Tavares
 */
@Repository
public interface RegionalRepository extends IRepository<Regional> {

	public Optional<Regional> findByNomeIgnoreCase(String nome);

}
