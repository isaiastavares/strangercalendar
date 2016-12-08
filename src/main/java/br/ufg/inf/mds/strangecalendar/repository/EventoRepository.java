package br.ufg.inf.mds.strangecalendar.repository;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import br.ufg.inf.mds.strangecalendar.entidade.Interessado;
import br.ufg.inf.mds.strangecalendar.entidade.Regional;

/**
 * Repositório de {@link Evento}
 *
 * @author Isaias Tavares
 */
@Repository
public interface EventoRepository extends IRepository<Evento> {

	public List<Evento> findByDescricaoContainingIgnoreCase(
			String palavraChave);

	public List<Evento> findByDescricaoContainingIgnoreCaseAndRegionaisIn(
			String palavraChave,
			Set<Regional> regionais);

	@Query("select e from Evento e where "
			+ "e.dataInicio <= :data and e.dataFim >= :data")
	public List<Evento> findByData(@Param("data") LocalDateTime data);

	public Evento findById(Long id);

	public List<Evento> findByInteressadosInAndRegionaisIn(Set<Interessado> interessados, Set<Regional> regionais);
}
