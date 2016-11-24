package br.ufg.inf.mds.strangecalendar.enums;

import br.ufg.inf.mds.strangecalendar.entidade.Interessado;

public enum Interessados {

	COMUNIDADE_EM_GERAL(1, "Comunidade em Geral"),
	COORDENACAO_DE_CURSOS(2, "Coordenações de Cursos"),
	DOCENTES(3, "Docentes"),
	DISCENTES(4, "Discentes");

	private Integer id;
	private String nome;

	Interessados(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o interessado a partir do ID.
	 *
	 * @param id do interessado
	 * @return {@link Interessado} a partir do ID.
	 */
	public static Interessados fromId(Integer id) {
		if (id != null) {
			for (Interessados interessado : Interessados.values()) {
				if (id == interessado.getId()) {
					return interessado;
				}
			}
		}
		return null;
	}
}
