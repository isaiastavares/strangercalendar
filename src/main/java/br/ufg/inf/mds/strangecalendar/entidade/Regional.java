package br.ufg.inf.mds.strangecalendar.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Entidade contendo as informações
 * sobre as Regionais.
 *
 * @author Isaias Tavares
 */
@Entity
@Table(name = "regional")
public class Regional extends Entidade {

	private static final long serialVersionUID = -1638451160273934125L;

	@Column(length = 255, unique = true, nullable = false)
	private String nome;

	@Column(nullable = false)
	private String cidade;

	@Column(nullable = false)
	private String estado;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "regionais")
	private List<Evento> eventos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder
			.append(getId())
			.append(" - ")
			.append(getNome());

		return builder.toString();
	}
}
