package br.ufg.inf.mds.strangecalendar.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import br.ufg.inf.mds.strangecalendar.controller.EventoController;
import br.ufg.inf.mds.strangecalendar.controller.InteressadoController;
import br.ufg.inf.mds.strangecalendar.controller.RegionalController;
import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import br.ufg.inf.mds.strangecalendar.entidade.Interessado;
import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.enums.Interessados;
import br.ufg.inf.mds.strangecalendar.services.exceptions.NaoEncontradoException;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;
import br.ufg.inf.mds.strangecalendar.util.Leitura;

/**
 * @author Leonardo
 */
public class ViewBuscaEventos {

	private static final Logger LOG = LoggerFactory
    		.getLogger(ViewBuscaEventos.class);

	private Scanner scanner;
	private EventoController eventoController;
	private InteressadoController interessadoController;
	private RegionalController regionalController;

	public ViewBuscaEventos(Scanner scanner, ApplicationContext context) {
		this.scanner = scanner;
		this.eventoController = context.getBean(EventoController.class);
		this.interessadoController = context
				.getBean(InteressadoController.class);
		this.regionalController = context.getBean(RegionalController.class);
	}

	public void exibirBuscaEventoData() {
		System.out.println("##### Bem Vindo a Pesquisa de "
				+ "Evento Por Data #####\n");

		LocalDateTime data = Leitura.lerCampoDateTimeObrigatorio("Informe a "
            + "data e hora(Formato: dd/MM/yyyy hh:mm)", getScanner());

		List<Evento> eventosFiltrados = buscarEventosPorData(data);

		if (eventosFiltrados.isEmpty()) {
			System.out.println("Não encontrei nenhum evento "
                                + "nessa data");
			return;
		}

		imprimirEventosEncontrados(eventosFiltrados);
	}

	public void exibirBuscaEventoPalavraChave(Set<Regional> regionais) {
		System.out.println("##### Bem Vindo a Pesquisa de Evento "
				+ "Por Palavra Chave #####\n");

		String palavraChave = Leitura.lerCampoStringObrigatorio(
				"Informe a palavra chave", getScanner());

		List<Evento> eventosFiltrados = new ArrayList<Evento>();
		if (regionais == null || regionais.isEmpty()) {
			eventosFiltrados = buscarEventosPorPalavraChave(
					palavraChave);
		} else {
			eventosFiltrados = eventoController.buscarEventosPorPalavraChaveERegional(palavraChave, regionais);
		}

		if (eventosFiltrados.isEmpty()) {
			System.out.println("Não encontrei nenhum evento "
					+ "por essa palavra chave");
			return;
		}

		imprimirEventosEncontrados(eventosFiltrados);
	}

	public void exibirBuscaEventoPorInteressado(Set<Regional> regionais) {
		System.out.println("##### Bem Vindo a Pesquisa de Evento "
				+ "Por Interessado #####\n");

		List<Interessado> listInteressadosCadastradas =
                        interessadoController.listarInteressados();

		int idInteressado = selecionarInteressado(listInteressadosCadastradas);
		try {
			Interessado interessado = interessadoController.findInteressadoPorId(idInteressado);
			Set<Interessado> setInteressado = new HashSet<Interessado>();
			setInteressado.add(interessado);
			Interessados interessadoEscolhido = Interessados.fromId(idInteressado);

			List<Evento> eventosFiltrados = new ArrayList<>();
			if (regionais == null || regionais.isEmpty()) {
				eventosFiltrados = buscarEventosPorInteressado(interessadoEscolhido);
			} else {
				eventosFiltrados = eventoController.buscarEventosPorInteressadoERegional(setInteressado, regionais);
			}

			if (eventosFiltrados.isEmpty()) {
				System.out.println("Não encontrei nenhum evento "
						+ "para esse interessado");
				return;
			}

			imprimirEventosEncontrados(eventosFiltrados);
		} catch (ServicoException e) {
			LOG.error(e.getMessage(), e);
		}
	}

    public void exibirBuscaEventoPorRegional() {
		System.out.println("##### Bem Vindo a Pesquisa de Evento "
				+ "Por Regional #####\n");

		List<Regional> listRegionaisCadastradas = regionalController
				.listarRegionais();

        if (listRegionaisCadastradas.isEmpty()) {
        	System.out.println("Não existe nenhuma regional cadastrada");
            return;
        } else {
        	long idRegional = selecionarRegional(listRegionaisCadastradas);

            try {
				Regional regional =
						regionalController.findRegionalPorId(idRegional);

				Set<Regional> setRegional = new HashSet<Regional>();
				setRegional.add(regional);

				int opcao = 1;
		        while (opcao != 0) {
		        	System.out.println("Você está na Regional " + regional.getNome() +
		        			". Escolha uma das opções abaixo: ");
		        	System.out.println("\n0 - Sair do menu.");
		            System.out.println("1 - Listar todos os eventos da Regional.");
		            System.out.println("2 - Pesquisar evento por Palavra Chave.");
		            System.out.println("3 - Pesquisar evento por Interessado.");

		            try {
		                opcao = Integer.parseInt(scanner.nextLine());
		            } catch (NumberFormatException ex) {
		                //  atribuindo 100 a variavel opcao para poder
		                //  exibir o menu novamente.
		                opcao = 100;
		            }

		            switch (opcao) {
			        	case 0:
			        		break;
			            case 1:
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
			                break;
			            case 2:
			            	exibirBuscaEventoPalavraChave(setRegional);
			                break;
			            case 3:
			            	exibirBuscaEventoPorInteressado(setRegional);
			                break;
			            default:
			            	System.out.println("Número Inválido. Tente novamente "
			                            + "digitando um número válido.");
			                break;
			        }
		        }
			} catch (ServicoException e) {
				LOG.error(e.getMessage(), e);
			}
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

	private List<Evento> buscarEventosPorData(LocalDateTime data) {
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
