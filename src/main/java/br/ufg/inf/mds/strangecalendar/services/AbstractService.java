package br.ufg.inf.mds.strangecalendar.services;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.inf.mds.strangecalendar.entidade.Entidade;
import br.ufg.inf.mds.strangecalendar.repository.IRepository;
import br.ufg.inf.mds.strangecalendar.services.exceptions.NaoEncontradoException;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;

public abstract class AbstractService<E extends Entidade>
													implements Serializable {

	private static final long serialVersionUID = -3064318938495857779L;

	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractService.class);

	@Transactional(readOnly = true)
	public final E buscarPorId(Long id) throws NaoEncontradoException {
		E entidade = getRepositorio().findOne(id);

		if (entidade == null) {
			throw new NaoEncontradoException(String.format("Não foi possível "
					+ "encontrar entidade com ID %d", id));
		}

		return entidade;
	}

	@Transactional
	public final void inserir(E entidade) throws ServicoException {
		LOG.info("Incluindo " + entidade.getClass().getName());

		antesInserir(entidade);
		getRepositorio().save(entidade);

		LOG.info(entidade.getClass().getName() + " salvo com sucesso");
	}

	@Transactional
	public final void atualizar(E entidade) throws ServicoException {
		LOG.info("Atualizando " + entidade.getClass().getName()
				+ " com id " + entidade.getId());

		if (entidade.getId() == null) {
			throw new ServicoException("Não deve acionar método atualizar "
					+ "para entidades sem ID");
		}

		getRepositorio().save(entidade);

		LOG.info(entidade.getClass().getName() + " com id " + entidade.getId()
				+ " atulizado com sucesso");
	}

	@Transactional
	public final E excluir(Long id) throws ServicoException {
		E entidade = getRepositorio().findOne(id);

		if(entidade == null) {
			throw new ServicoException(
					String.format("Nao é possível excluir entidade com ID %d. "
							+ "Entidade não existe.", id));
		}

		getRepositorio().delete(entidade);

		return entidade;
	}

	protected abstract IRepository<E> getRepositorio();

	protected abstract void antesInserir(E entidade) throws ServicoException;

}
