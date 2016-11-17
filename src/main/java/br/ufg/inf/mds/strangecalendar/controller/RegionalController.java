package br.ufg.inf.mds.strangecalendar.controller;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.services.RegionalService;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;
import br.ufg.inf.mds.strangecalendar.util.Leitura;

/**
 * Controlador responsável pelas operações
 * relacionadas a Regional
 *
 * @author Isaias Tavares
 */
@Controller
public class RegionalController {

	private static final Logger LOG = LoggerFactory.getLogger(RegionalController.class);

	@Autowired
	private RegionalService regionalService;

	public void cadastrarRegional(Scanner scanner) {
		System.out.println("##### Bem Vindo ao Cadastro de Regional #####\n");

		String nome = Leitura.lerCampoStringObrigatorio("Informe o nome da Regional", scanner);
		String cidade = Leitura.lerCampoStringObrigatorio("Informe a cidade onde está localizada a Regional", scanner);
		String estado = Leitura.lerCampoStringObrigatorio("Informe o estado onde está localizada a Regional", scanner);

		Regional regional = new Regional();
		regional.setNome(nome);
		regional.setCidade(cidade);
		regional.setEstado(estado);

		try {
			getRegionalService().inserir(regional);
			System.out.println("\n##### Regional cadastrada com sucesso #####");
		} catch (ServicoException e) {
			System.out.println("\nNão foi possível cadastrar a Regional. Motivo: " + e.getMessage());
			LOG.trace(e.getMessage(), e);
		}
	}

	private RegionalService getRegionalService() {
		return regionalService;
	}

}
