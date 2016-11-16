package br.ufg.inf.mds.strangecalendar.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "interessado")
public class Interessado extends Entidade {

	private static final long serialVersionUID = -8336050828705247790L;

	@Column(length = 255, unique = true, nullable = false)
	private String nome;

	@ManyToMany(mappedBy = "interessados")
	private List<Evento> eventos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
}
