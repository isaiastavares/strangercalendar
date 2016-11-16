package br.ufg.inf.mds.strangecalendar.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.joda.time.LocalDate;

@Entity
@Table(name = "evento")
public class Evento extends Entidade {

	private static final long serialVersionUID = 5810659606259781926L;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private LocalDate dataInicio;

	@Column(nullable = false)
	private LocalDate dataFim;

	@OneToMany
	private List<Regional> regionais;

	@OneToMany
	private List<Interessado> interessados;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public List<Regional> getRegionais() {
		return regionais;
	}

	public void setRegionais(List<Regional> regionais) {
		this.regionais = regionais;
	}

	public List<Interessado> getInteressados() {
		return interessados;
	}

	public void setInteressados(List<Interessado> interessados) {
		this.interessados = interessados;
	}

}
