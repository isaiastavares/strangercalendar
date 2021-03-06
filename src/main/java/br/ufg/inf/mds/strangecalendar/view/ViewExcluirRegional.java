package br.ufg.inf.mds.strangecalendar.view;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import br.ufg.inf.mds.strangecalendar.controller.RegionalController;
import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;
import br.ufg.inf.mds.strangecalendar.util.Leitura;

/**
 *
 * @author android
 */
public class ViewExcluirRegional {

	private Scanner scanner;
	private RegionalController regionalController;

	public ViewExcluirRegional(Scanner scanner, ApplicationContext context) {
		this.scanner = scanner;
		this.regionalController = context.getBean(RegionalController.class);
	}

	public void exibirExcluirRegional() {
		System.out.println("##### Bem Vindo a exclusão de Regional #####\n");

		List<Regional> listRegionaisCadastradas = regionalController
				.listarRegionais();

		if (listRegionaisCadastradas.isEmpty()) {
			System.out.println("Não existe nenhuma regional cadastrada");
			return;
		}

		imprimirRegionaisEncontrados(listRegionaisCadastradas);

		Integer idRegional = Leitura.lerCampoIntegerObrigatorio("Informe o ID "
				+ "da regional que deseja excluir",
				getScanner());

		try {
			long idRegionalLong = Long.parseLong(idRegional.toString());
			Regional regional = regionalController
					.excluirRegionalPorId(idRegionalLong);
			System.out.println("Regional excluída com sucesso! Detalhes do "
					+ "evento: " + regional.toString());
		} catch (ServicoException e) {
			System.out.println("Não foi possível excluir a regional com ID: "
					+ idRegional);
		} catch (DataIntegrityViolationException e) {
			System.out.println("Não foi possível excluir a regional com ID: "
					+ idRegional + ".\n"
					+ "Delete os eventos assossiados a ela antes");
		}
	}

	private void imprimirRegionaisEncontrados(List<Regional> listRegionais) {
		listRegionais.forEach(regional -> {
			System.out.println(regional.toString());
		});
	}

	public Scanner getScanner() {
		return scanner;
	}

}
