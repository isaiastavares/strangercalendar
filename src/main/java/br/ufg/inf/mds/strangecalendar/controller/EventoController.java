package br.ufg.inf.mds.strangecalendar.controller;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import br.ufg.inf.mds.strangecalendar.entidade.Interessado;
import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.services.EventoService;
import br.ufg.inf.mds.strangecalendar.services.InteressadoService;
import br.ufg.inf.mds.strangecalendar.services.RegionalService;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;
import br.ufg.inf.mds.strangecalendar.util.Leitura;

/**
 * Controlador das operações relacionadas a Evento.
 *
 * @author Isaias Tavares
 */
@Controller
public class EventoController {

	private static final Logger LOG = LoggerFactory.getLogger(EventoController.class);

	@Autowired
	private EventoService eventoService;

	@Autowired
	private RegionalService regionalService;

	@Autowired
	private InteressadoService interessadoService;

	public void cadastrarEvento(Scanner scanner) {
		System.out.println("##### Bem Vindo ao Cadastro de Evento #####\n");

		List<Regional> listRegionais = regionalService.getRepositorio().findAll();
		if (listRegionais.isEmpty()) {
			System.out.println("Você não possui nenhuma regional cadastrada ainda. É necessário "
					+ "cadastrar pelo menos uma Regional para poder Cadastrar um Evento");
			return;
		}

		String descricao = Leitura.lerCampoStringObrigatorio("Informe a descrição do Evento", scanner);
		LocalDate dataInicio = Leitura.lerCampoDateObrigatorio("Informe a data de início do Evento (Formato: dd/MM/yyyy)", scanner);
		LocalDate dataFim = Leitura.lerCampoDateObrigatorio("Informe a data de término do Evento  (Formato: dd/MM/yyyy)", scanner);

		/**
		 * Daqui para frente está precisando de uma boa refatorado, mas faço isso depois
		 * agora o que importa é funcionar rsrs
		 */
		Set<Regional> regionaisEscolhidas = new LinkedHashSet<>();
		Map<Long, String> mapRegionais = new LinkedHashMap<>();
		for (Regional regional : listRegionais) {
			mapRegionais.put(regional.getId(), regional.getNome());
		}
		boolean adicionarRegional = true;

		while (adicionarRegional) {
			int idRegional = 0;
			do {
				System.out.println("Selecione a regional onde esse evento irá acontecer informando o número correspondente:");
				for (Entry<Long, String> regional : mapRegionais.entrySet()) {
					System.out.println(regional.getKey() + " - " + regional.getValue());
				}
				try {
					idRegional = Integer.parseInt(scanner.nextLine());
					if (idRegional < 1 || idRegional > mapRegionais.size()) {
						System.out.println("Número informado não corresponde a nenhuma Regional");
					}
		        } catch (NumberFormatException ex) {
		        	System.out.println("Entrada inválida. Informe um número inteiro correspondente a Regional");
		        }
			} while (idRegional < 1 || idRegional > mapRegionais.size());
			regionaisEscolhidas.add(listRegionais.get(idRegional - 1));

			adicionarRegional = Leitura.lerCampoBooleanObrigatorio("Deseja adicionar mais uma Regional? Digite 1 para SIM e 0 para NÃO.",
					scanner);
		}

		Set<Interessado> interessadosEscolhidos = new LinkedHashSet<>();
		List<Interessado> listInteressados = interessadoService.getRepositorio().findAll();
		Map<Long, String> mapInteressados = new LinkedHashMap<>();
		for (Interessado interessado : listInteressados) {
			mapInteressados.put(interessado.getId(), interessado.getNome());
		}
		boolean adicionarInteressado = true;

		while (adicionarInteressado) {
			int idInteressado = 0;
			do {
				System.out.println("Selecione o interessado no evento informando o número correspondente:");
				for (Entry<Long, String> interessado : mapInteressados.entrySet()) {
					System.out.println(interessado.getKey() + " - " + interessado.getValue());
				}
				try {
					idInteressado = Integer.parseInt(scanner.nextLine());
					if (idInteressado < 1 || idInteressado > mapInteressados.size()) {
						System.out.println("Número informado não corresponde a nenhum Interessado");
					}
		        } catch (NumberFormatException ex) {
		        	System.out.println("Entrada inválida. Informe um número inteiro correspondente ao Interessado");
		        }
			} while (idInteressado < 1 || idInteressado > mapInteressados.size());
			interessadosEscolhidos.add(listInteressados.get(idInteressado - 1));

			adicionarInteressado = Leitura.lerCampoBooleanObrigatorio("Deseja adicionar mais um Interessado? Digite 1 para SIM e 0 para NÃO.",
					scanner);
		}

		Evento evento = new Evento();
		evento.setDescricao(descricao);
		evento.setDataInicio(dataInicio);
		evento.setDataFim(dataFim);
		evento.setRegionais(regionaisEscolhidas);
		evento.setInteressados(interessadosEscolhidos);

		try {
			getEventoService().inserir(evento);
			System.out.println("\n##### Evento cadastrado com sucesso #####");
		} catch (ServicoException e) {
			System.out.println("\nNão foi possível cadastrar o Evento. Motivo: " + e.getMessage());
			LOG.trace(e.getMessage(), e);
		}
	}

	private EventoService getEventoService() {
		return eventoService;
	}

}
