package br.ufg.inf.mds.strangecalendar.view;

import java.util.List;
import java.util.Scanner;

import org.joda.time.LocalDate;
import org.springframework.context.ApplicationContext;

import br.ufg.inf.mds.strangecalendar.controller.EventoController;
import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import br.ufg.inf.mds.strangecalendar.entidade.Interessado;
import br.ufg.inf.mds.strangecalendar.enums.Interessados;
import br.ufg.inf.mds.strangecalendar.services.InteressadoService;
import br.ufg.inf.mds.strangecalendar.util.Leitura;

/**
 * @author Leonardo
 */
public class ViewBuscaEventos {

	private ApplicationContext context;
	private Scanner scanner;
	private EventoController eventoController;

	public ViewBuscaEventos(Scanner scanner, ApplicationContext context) {
		this.scanner = scanner;
		this.context = context;
		this.eventoController = context.getBean(EventoController.class);
	}

	public void exibirBuscaEventoData() {
		System.out.println("##### Bem Vindo a Pesquisa de "
				+ "Evento Por Data #####\n");

		LocalDate data = Leitura.lerCampoDateObrigatorio("Informe a data "
				+ "(Formato: dd/MM/yyyy)", getScanner());

		List<Evento> eventosFiltrados = buscarEventosPorData(data);

		if (eventosFiltrados.isEmpty()) {
			System.out.println("Não encontrei nenhum evento nessa data");
			return;
		}

		imprimirEventosEncontrados(eventosFiltrados);
	}

	public void exibirBuscaEventoPalavraChave() {
		System.out.println("##### Bem Vindo a Pesquisa de Evento "
				+ "Por Palavra Chave #####\n");

		String palavraChave = Leitura.lerCampoStringObrigatorio(
				"Informe a palavra chave", getScanner());

		List<Evento> eventosFiltrados = buscarEventosPorPalavraChave(
				palavraChave);

		if (eventosFiltrados.isEmpty()) {
			System.out.println("Não encontrei nenhum evento "
					+ "por essa palavra chave");
			return;
		}

		imprimirEventosEncontrados(eventosFiltrados);
	}

	public void exibirBuscaEventoPorInteressado() {
		System.out.println("##### Bem Vindo a Pesquisa de Evento "
				+ "Por Interessado #####\n");

		InteressadoService interessadoService = getContext()
				.getBean(InteressadoService.class);
		List<Interessado> listInteressadosCadastradas = interessadoService
				.getRepositorio().findAll();

		int idInteressado = selecionarInteressado(listInteressadosCadastradas);
		Interessados interessadoEscolhido = Interessados.fromId(idInteressado);

		List<Evento> eventosFiltrados = buscarEventosPorInteressado(
				interessadoEscolhido);

		if (eventosFiltrados.isEmpty()) {
			System.out.println("Não encontrei nenhum evento "
					+ "para esse interessado");
			return;
		}

		imprimirEventosEncontrados(eventosFiltrados);
	}

	private int selecionarInteressado(List<Interessado> listInteressados) {
		int idInteressado = 0;
        do {
            System.out.println("Selecione o interessado no evento informando o"
                    + " número correspondente:");
            for (Interessado interessado : listInteressados) {
                System.out.println(interessado.getId() + " - "
                		+ interessado.getNome());
            }
            try {
                idInteressado = Integer.parseInt(getScanner().nextLine());
                if (idInteressado < 1
                		|| idInteressado > listInteressados.size()) {
                    System.out.println("Número informado não corresponde a "
                            + "nenhum Interessado");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida. Informe um número inteiro"
                        + " correspondente ao Interessado");
            }
        } while (idInteressado < 1 || idInteressado > listInteressados.size());
		return idInteressado;
	}

	private List<Evento> buscarEventosPorData(LocalDate data) {
		List<Evento> eventosEncontrados = eventoController
				.buscarEventoPorData(data);

		return eventosEncontrados;
	}

	private List<Evento> buscarEventosPorPalavraChave(String palavraChave) {
		List<Evento> eventosEncontrados = eventoController
				.buscarEventoPorPalavraChave(palavraChave);

		return eventosEncontrados;
	}

	private List<Evento> buscarEventosPorInteressado(Interessados interessado) {
		List<Evento> eventosEncontrados = eventoController
				.buscarEventoPorInteressado(interessado);

		return eventosEncontrados;
	}

	private void imprimirEventosEncontrados(List<Evento> eventosFiltrados) {
		System.out.println("A Pesquisa retornou os seguintes resultados:\n");
		for (Evento evento : eventosFiltrados) {
			System.out.println(evento.toString());
		}
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}
}
