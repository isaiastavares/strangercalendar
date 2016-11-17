package br.ufg.inf.mds.strangecalendar.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Entidade contendo informações sobres os
 * Interessados no Evento.
 *
 * @author Isaias Tavares
 */
@Entity
@Table(name = "interessado")
public class Interessado extends Entidade {

	private static final long serialVersionUID = -8336050828705247790L;

	@Column(length = 255, unique = true, nullable = false)
	private String nome;

	@ManyToMany(mappedBy = "interessados")
	private List<Evento> eventos;

	/**
     * Construtor Padrao
     */
    public Interessado() {
        super();
    }

    /**
     * Construtor passando o nome do interessado e os eventos
     *
     * @param nome nome do interessado
     * @param eventos lista de eventos relacionados ao interessado
     */
    public Interessado(String nome, List<Evento> eventos) {
        this.nome = nome;
        this.eventos = eventos;
    }

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
