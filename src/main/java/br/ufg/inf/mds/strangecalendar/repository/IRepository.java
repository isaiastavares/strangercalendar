package br.ufg.inf.mds.strangecalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.ufg.inf.mds.strangecalendar.entidade.Entidade;

@NoRepositoryBean
public interface IRepository<E extends Entidade> extends JpaRepository<E, Long>{

}
