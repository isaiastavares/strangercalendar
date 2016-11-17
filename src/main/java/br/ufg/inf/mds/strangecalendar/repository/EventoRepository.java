package br.ufg.inf.mds.strangecalendar.repository;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufg.inf.mds.strangecalendar.entidade.Evento;

/**
 * Repositório de {@link Evento}
 *
 * @author Isaias Tavares
 */
@Repository
public interface EventoRepository extends IRepository<Evento> {

	public List<Evento> findByDescricaoContaining(String palavraChave);

	@Query("select e from Evento e where e.dataInicio >= :data and e.dataFim <= :data")
	public List<Evento> findByData(@Param("data") LocalDate data);

}
