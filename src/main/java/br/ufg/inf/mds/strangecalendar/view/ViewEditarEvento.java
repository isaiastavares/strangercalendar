package br.ufg.inf.mds.strangecalendar.view;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.joda.time.LocalDate;
import org.springframework.context.ApplicationContext;

import br.ufg.inf.mds.strangecalendar.controller.EventoController;
import br.ufg.inf.mds.strangecalendar.controller.InteressadoController;
import br.ufg.inf.mds.strangecalendar.controller.RegionalController;
import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import br.ufg.inf.mds.strangecalendar.entidade.Interessado;
import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;
import br.ufg.inf.mds.strangecalendar.util.Leitura;

/**
 * View relacionada a editar eventos.
 *
 * @author Lucas Campos
 */
public class ViewEditarEvento {

	private Scanner scanner;
	private EventoController eventoController;
	private InteressadoController interessadoController;
	private RegionalController regionalController;
	
	public ViewEditarEvento(Scanner scanner, ApplicationContext context) {
		this.scanner = scanner;
		this.eventoController = context.getBean(EventoController.class);
		this.interessadoController = context
				.getBean(InteressadoController.class);
		this.regionalController = context.getBean(RegionalController.class);
	}

	public void exibirEditarEvento() {
		System.out.println("##### Bem Vindo a Opção de Editar Evento #####\n");

		List<Evento> eventos = eventoController.listarEventos();

		if (eventos.isEmpty()) {
			System.out.println("Não existe nenhum evento cadastrado");
			return;
		}

		imprimirEventosEncontrados(eventos);

		Integer idEvento = Leitura.lerCampoIntegerObrigatorio("Informe o ID do "
				+ "evento que deseja editar", getScanner());

		try {
			long idEventoLong = Long.parseLong(idEvento.toString());
			
			Evento evento = eventoController.buscarEventoPorId(idEventoLong);
			
			String descricao = Leitura.lerCampoStringObrigatorio("Informe a nova "
	                + "descrição do Evento", getScanner());

	        LocalDate dataInicio = Leitura.lerCampoDateObrigatorio("Informe a nova data "
	                + "de início do Evento (Formato: dd/MM/yyyy)", getScanner());

	        LocalDate dataFim = Leitura.lerCampoDateObrigatorio("Informe a nova data de "
	                + "término do Evento  (Formato: dd/MM/yyyy)", getScanner());
	        
	        evento = popularObjetoEvento(evento, descricao, dataInicio, dataFim,
	                adicionarRegional(evento), adicionarInteressado(evento));
	        
	        eventoController.atualizarEvento(evento);
	        
	        System.out.println("Evento editado com sucesso! Detalhes do "
					+ "evento: " + evento.toString());
			
		} catch (ServicoException e) {
			System.out.println("Não existe evento com o ID: "	+ idEvento);
		}
	}
	
	private Set<Regional> adicionarRegional(Evento evento) {
        List<Regional> listRegionaisCadastradas = regionalController
        		.listarRegionais();
        Set<Regional> regionaisEscolhidas = new LinkedHashSet<>();
        Map<Long, String> mapRegionais = new LinkedHashMap<>();
        mapRegionais = populaMapRegionais(mapRegionais,
                listRegionaisCadastradas);
        boolean adicionarRegional = true;

        while (adicionarRegional) {
            regionaisEscolhidas = adicionarRegionalSelecionadaNaList(
                    mapRegionais, regionaisEscolhidas,
                    listRegionaisCadastradas);

            adicionarRegional = Leitura.lerCampoBooleanObrigatorio("Deseja "
                    + "adicionar mais uma Regional? Digite 1 para SIM "
                    + "e 0 para NÃO.", getScanner());
        }

        return regionaisEscolhidas;
    }

    private Set<Interessado> adicionarInteressado(Evento evento) {
        List<Interessado> listInteressadosCadastradas = interessadoController
        		.listarInteressados();
        Set<Interessado> interessadosEscolhidas = new LinkedHashSet<>();
        boolean adicionarInteressado = true;

        while (adicionarInteressado) {
            interessadosEscolhidas = adicionarInteressadoSelecionadaNaList(
                    interessadosEscolhidas, listInteressadosCadastradas);

            adicionarInteressado = Leitura.lerCampoBooleanObrigatorio("Deseja "
                    + "adicionar mais um interessado ? Digite 1 para SIM "
                    + "e 0 para NÃO.", getScanner());
        }

        return interessadosEscolhidas;
    }
    
    private Map<Long, String> populaMapRegionais(Map<Long, String> mapRegionais,
            List<Regional> listRegionaisCadastradas) {

        for (Regional regional : listRegionaisCadastradas) {
            mapRegionais.put(regional.getId(), regional.getNome());
        }
        return mapRegionais;
    }

    private Set<Regional> adicionarRegionalSelecionadaNaList(
            Map<Long, String> mapRegionais, Set<Regional> regionaisEscolhidas,
            List<Regional> listRegionaisCadastradas) {

        int idRegional = 0;
        do {
            System.out.println("Selecione a regional onde esse evento irá "
                    + "acontecer informando o número correspondente:");
            for (Map.Entry<Long, String> regional : mapRegionais.entrySet()) {
                System.out.println(regional.getKey() + " - "
                        + regional.getValue());
            }
            try {
                idRegional = Integer.parseInt(getScanner().nextLine());
                if (idRegional < 1 || idRegional > mapRegionais.size()) {
                    System.out.println("Número informado não corresponde a "
                            + "nenhuma Regional");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida. Informe um número inteiro"
                        + " correspondente a Regional");
            }
        } while (idRegional < 1 || idRegional > mapRegionais.size());
        regionaisEscolhidas.add(listRegionaisCadastradas.get(idRegional - 1));

        return regionaisEscolhidas;
    }

    private Set<Interessado> adicionarInteressadoSelecionadaNaList(
            Set<Interessado> interessadosEscolhidos,
            List<Interessado> listInteressadosCadastrados) {

        int idInteressado = selecionarInteressado(listInteressadosCadastrados);

        interessadosEscolhidos.add(listInteressadosCadastrados.
                get(idInteressado - 1));
        return interessadosEscolhidos;
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
    
    private Evento popularObjetoEvento(Evento evento, String descricao,
            LocalDate dataInicio, LocalDate dataFim, Set<Regional> listRegional,
            Set<Interessado>listInteressados) {

        evento.setDescricao(descricao);
        evento.setDataInicio(dataInicio);
        evento.setDataFim(dataFim);
        evento.setRegionais(listRegional);
        evento.setInteressados(listInteressados);

        return evento;
    }
    
    private void imprimirEventosEncontrados(List<Evento> eventos) {
		eventos.forEach(evento -> {
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
