package br.ufg.inf.mds.strangecalendar.enums;

public enum Interessados {

	COMUNIDADE_EM_GERAL("Comunidade em Geral"),
	COORDENACAO_DE_CURSOS("Coordenações de Cursos"),
	DOCENTES("Docentes"),
	DISCENTES("Discentes");

	private String nome;

	Interessados(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
