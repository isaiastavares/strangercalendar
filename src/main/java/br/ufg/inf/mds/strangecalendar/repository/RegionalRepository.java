package br.ufg.inf.mds.strangecalendar.repository;

import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório de {@link Regional}
 *
 * @author Isaias Tavares
 */
@Repository
public interface RegionalRepository extends IRepository<Regional> {

	public Optional<Regional> findByNomeIgnoreCase(String nome);

	public Regional findByNome(String nome);

}
