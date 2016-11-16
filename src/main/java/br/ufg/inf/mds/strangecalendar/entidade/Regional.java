package br.ufg.inf.mds.strangecalendar.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
}
