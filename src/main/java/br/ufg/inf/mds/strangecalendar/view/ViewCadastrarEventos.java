package br.ufg.inf.mds.strangecalendar.view;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import br.ufg.inf.mds.strangecalendar.controller.EventoController;
import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import br.ufg.inf.mds.strangecalendar.entidade.Interessado;
import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.services.InteressadoService;
import br.ufg.inf.mds.strangecalendar.services.RegionalService;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;
import br.ufg.inf.mds.strangecalendar.util.Leitura;

/**
 *
 * @author Leonardo
 */
public class ViewCadastrarEventos {

    private static final Logger LOG = LoggerFactory.getLogger(ViewCadastrarEventos.class);

    private ApplicationContext context;
    private Scanner scanner;

    public ViewCadastrarEventos(Scanner scanner, ApplicationContext context) {
    	this.scanner = scanner;
    	this.context = context;
    }

    public void exibirCadastroEvento() {
        System.out.println("##### Bem Vindo ao Cadastro de Evento #####\n");

        if (!existeRegionaisCadastradas()) {
            System.out.println("Você não possui nenhuma regional cadastrada "
                    + "ainda. É necessário cadastrar pelo menos uma Regional "
                    + "para poder Cadastrar um Evento");
            return;
        }

        String descricao = Leitura.lerCampoStringObrigatorio("Informe a "
                + "descrição do Evento", getScanner());

        LocalDate dataInicio = Leitura.lerCampoDateObrigatorio("Informe a data "
                + "de início do Evento (Formato: dd/MM/yyyy)", getScanner());

        LocalDate dataFim = Leitura.lerCampoDateObrigatorio("Informe a data de "
                + "término do Evento  (Formato: dd/MM/yyyy)", getScanner());

        Evento evento = new Evento();
        evento = popularObjetoEvento(evento, descricao, dataInicio, dataFim,
                adicionarRegional(evento), adicionarInteressado(evento));

        inserirEvento(evento);
    }

    private boolean existeRegionaisCadastradas() {
        RegionalService regionalService = context.getBean(RegionalService.class);

        boolean existeRegionais = true;
        List<Regional> listRegionais = regionalService.
                getRepositorio().findAll();
        if (listRegionais.isEmpty()) {
            existeRegionais = false;
        }
        return existeRegionais;
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

    private void inserirEvento(Evento evento) {
        EventoController eventoController = getContext().getBean(EventoController.class);

        try {
            eventoController.cadastrarEvento(evento);
            System.out.println("\n##### Evento cadastrado com sucesso #####");
        } catch (ServicoException ex) {
            System.out.println("\nNão foi possível cadastrar o Evento. Motivo:"
                    + ex.getMessage());
			LOG.trace(ex.getMessage(), ex);}
    }

    private Set<Regional> adicionarRegional(Evento evento) {
        RegionalService regionalService = getContext().getBean(RegionalService.class);

        List<Regional> listRegionaisCadastradas = regionalService.
                getRepositorio().findAll();
        Set<Regional> regionaisEscolhidas = new LinkedHashSet<>();
        Map<Long, String> mapRegionais = new LinkedHashMap<>();
        mapRegionais = populaMapRegioanis(mapRegionais,
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
        InteressadoService interessadoService = getContext().getBean(InteressadoService.class);

        List<Interessado> listInteressadosCadastradas = interessadoService.getRepositorio().findAll();
        Set<Interessado> interessadosEscolhidas = new LinkedHashSet<>();
        boolean adicionarInteressado = true;

        while (adicionarInteressado) {
            interessadosEscolhidas = adicionarInteressadoSelecionadaNaList(
                    interessadosEscolhidas, listInteressadosCadastradas);

            adicionarInteressado = Leitura.lerCampoBooleanObrigatorio("Deseja "
                    + "adicionar mais uma Regional? Digite 1 para SIM "
                    + "e 0 para NÃO.", getScanner());
        }

        return interessadosEscolhidas;
    }

    private Map<Long, String> populaMapRegioanis(Map<Long, String> mapRegionais,
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
                System.out.println(interessado.getId() + " - " + interessado.getNome());
            }
            try {
                idInteressado = Integer.parseInt(getScanner().nextLine());
                if (idInteressado < 1 || idInteressado > listInteressados.size()) {
                    System.out.println("Número informado não corresponde a "
                            + "nenhum Interessado");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida. Informe um número inteiro "
                        + "correspondente ao Interessado");
            }
        } while (idInteressado < 1 || idInteressado > listInteressados.size());
		return idInteressado;
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
