package br.ufg.inf.mds.strangecalendar.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "interessado")
public class Interessado extends Entidade {

	private static final long serialVersionUID = -8336050828705247790L;

	@Column(length = 255, unique = true, nullable = false)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
