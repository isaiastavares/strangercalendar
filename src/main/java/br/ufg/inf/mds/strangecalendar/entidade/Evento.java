package br.ufg.inf.mds.strangecalendar.entidade;

import br.ufg.inf.mds.strangecalendar.util.Leitura;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidade contendo informações sobre
 * o Evento.
 *
 * @author Isaias Tavares
 */
@Entity
@Table(name = "evento")
public class Evento extends Entidade {

	private static final long serialVersionUID = 5810659606259781926L;
	private static final String SEPARATOR = "\n";

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private LocalDateTime dataInicio;

	@Column(nullable = false)
	private LocalDateTime dataFim;

	@ManyToMany
	@JoinTable(name = "eventos_regionais",
			joinColumns = {@JoinColumn(name = "id_evento")},
			inverseJoinColumns = {@JoinColumn(name = "id_regional")})
	private Set<Regional> regionais;

	@ManyToMany
	@JoinTable(name = "eventos_interessados",
			joinColumns = {@JoinColumn(name = "id_evento")},
			inverseJoinColumns = {@JoinColumn(name = "id_interessado")})
	private Set<Interessado> interessados;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public Set<Regional> getRegionais() {
		return regionais;
	}

	public void setRegionais(Set<Regional> regionais) {
		this.regionais = regionais;
	}

	public Set<Interessado> getInteressados() {
		return interessados;
	}

	public void setInteressados(Set<Interessado> interessados) {
		this.interessados = interessados;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder
			.append("----------------------------------")
			.append(SEPARATOR)
			.append("Id: ")
			.append(getId())
			.append(SEPARATOR)
			.append("Nome: ")
			.append(getDescricao())
			.append(SEPARATOR)
			.append("Data Início: ")
			.append(getDataInicio().toString(Leitura.DATE_FORMATTER))
			.append(SEPARATOR)
			.append("Data Término: ")
			.append(getDataFim().toString(Leitura.DATE_FORMATTER))
			.append(SEPARATOR)
			.append("----------------------------------")
			.append(SEPARATOR);
		return builder.toString();
	}
}
