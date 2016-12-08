package br.ufg.inf.mds.strangecalendar.repository;

import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import org.joda.time.LocalDateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório de {@link Evento}
 *
 * @author Isaias Tavares
 */
@Repository
public interface EventoRepository extends IRepository<Evento> {

	public List<Evento> findByDescricaoContainingIgnoreCase(
			String palavraChave);

	@Query("select e from Evento e where "
			+ "e.dataInicio <= :data and e.dataFim >= :data")
	public List<Evento> findByData(@Param("data") LocalDateTime data);
	
	public Evento findById(Long id);

}
