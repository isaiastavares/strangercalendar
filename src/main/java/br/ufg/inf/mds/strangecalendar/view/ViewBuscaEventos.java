package br.ufg.inf.mds.strangecalendar.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.joda.time.LocalDate;
import org.springframework.context.ApplicationContext;

import br.ufg.inf.mds.strangecalendar.controller.EventoController;
import br.ufg.inf.mds.strangecalendar.controller.InteressadoController;
import br.ufg.inf.mds.strangecalendar.controller.RegionalController;
import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import br.ufg.inf.mds.strangecalendar.entidade.Interessado;
import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.enums.Interessados;
import br.ufg.inf.mds.strangecalendar.services.exceptions.NaoEncontradoException;
import br.ufg.inf.mds.strangecalendar.util.Leitura;

/**
 * @author Leonardo
 */
public class ViewBuscaEventos {

	private Scanner scanner;
	private EventoController eventoController;
	private InteressadoController interessadoController;
	private RegionalController regionalController;

	public ViewBuscaEventos(Scanner scanner, ApplicationContext context) {
		this.scanner = scanner;
		this.eventoController = context.getBean(EventoController.class);
		this.interessadoController = context
				.getBean(InteressadoController.class);
		this.regionalController = context
                        .getBean(RegionalController.class);
	}

	public void exibirBuscaEventoData() {
		System.out.println("##### Bem Vindo a Pesquisa de "
				+ "Evento Por Data #####\n");

		LocalDate data = Leitura.lerCampoDateObrigatorio("Informe a "
                        + "data(Formato: dd/MM/yyyy)", getScanner());

		List<Evento> eventosFiltrados = buscarEventosPorData(data);

		if (eventosFiltrados.isEmpty()) {
			System.out.println("Não encontrei nenhum evento "
                                + "nessa data");
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

		List<Interessado> listInteressadosCadastradas = 
                        interessadoController.listarInteressados();

		int idInteressado = selecionarInteressado(
                        listInteressadosCadastradas);
		Interessados interessadoEscolhido = Interessados
                        .fromId(idInteressado);

		List<Evento> eventosFiltrados = buscarEventosPorInteressado(
				interessadoEscolhido);

		if (eventosFiltrados.isEmpty()) {
			System.out.println("Não encontrei nenhum evento "
					+ "para esse interessado");
			return;
		}

		imprimirEventosEncontrados(eventosFiltrados);
	}

    public void exibirBuscaEventoPorRegional() {
		System.out.println("##### Bem Vindo a Pesquisa de Evento "
				+ "Por Regional #####\n");

		List<Regional> listRegionaisCadastradas = regionalController
				.listarRegionais();

                if ( ! listRegionaisCadastradas.isEmpty()) {
                    long idRegional = selecionarRegional(listRegionaisCadastradas);

                    List<Evento> eventosFiltrados = new ArrayList<>();
                    try {
                            eventosFiltrados = buscarEventosPorRegional(idRegional);
                    } catch (NaoEncontradoException e) {
                            System.out.println("Não existe nenhuma regional "
                                    + "com o ID: " + idRegional);
                    }

                    if (eventosFiltrados.isEmpty()) {
                            System.out.println("Não encontrei nenhum evento "
                                            + "para essa regional");
                            return;
                    }

                    imprimirEventosEncontrados(eventosFiltrados);
                }else{
                    System.out.println("Não existe nenhuma regional cadastrada");
                    return;
                }
	}
    
	private int selecionarInteressado(List<Interessado> listInteressados) {
		int idInteressado = 0;
        do {
            System.out.println("Selecione o interessado no evento informando o"
                    + " número correspondente:");

            listInteressados.forEach(interessado -> {
            	System.out.println(interessado.toString());
            });

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

    private int selecionarRegional(List<Regional> listRegionais) {
		int idRegional = 0;
        do {
            System.out.println("Selecione a regional do evento informando o"
                    + " número correspondente:");

            listRegionais.forEach(regional -> {
            	System.out.println(regional.toString());
            });

            try {
                idRegional = Integer.parseInt(getScanner().nextLine());
                if (idRegional < 1 || idRegional > listRegionais.size()) {
                    System.out.println("Número informado não corresponde a "
                            + "nenhuma Regional");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida. Informe um número inteiro"
                        + " correspondente a Regional");
            }
        } while (idRegional < 1 || idRegional > listRegionais.size());
		return idRegional;
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

	private List<Evento> buscarEventosPorInteressado(
                Interessados interessado) {
		List<Evento> eventosEncontrados = eventoController
				.buscarEventoPorInteressado(interessado);

		return eventosEncontrados;
	}

    private List<Evento> buscarEventosPorRegional(long idRegional)
    		throws NaoEncontradoException {

		List<Evento> eventosEncontrados = eventoController
				.buscarEventoPorRegional(idRegional);

		return eventosEncontrados;
	}

	private void imprimirEventosEncontrados(List<Evento> eventosFiltrados) {
		System.out.println("A Pesquisa retornou os seguintes "
                        + "resultados:\n");
		eventosFiltrados.forEach(evento -> {
			System.out.println(evento.toString());
		});
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
}
