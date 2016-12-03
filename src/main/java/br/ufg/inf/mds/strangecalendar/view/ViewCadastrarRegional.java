package br.ufg.inf.mds.strangecalendar.view;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import br.ufg.inf.mds.strangecalendar.controller.RegionalController;
import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;
import br.ufg.inf.mds.strangecalendar.util.Leitura;

/**
 *
 * @author Leonardo
 */
public class ViewCadastrarRegional {

	private static final Logger LOG = LoggerFactory
			.getLogger(ViewCadastrarRegional.class);

	private Scanner scanner;
	private RegionalController regionalController;

    public ViewCadastrarRegional(Scanner scanner, ApplicationContext context) {
    	this.scanner = scanner;
    	this.regionalController = context.getBean(RegionalController.class);
    }

    public void exibirCadastroRegional() {
        System.out.println("##### Bem Vindo ao Cadastro de Regional #####\n");

        String nome = Leitura.lerCampoStringObrigatorio("Informe o nome "
                + "da Regional", getScanner());
        String cidade = Leitura.lerCampoStringObrigatorio("Informe a "
                + "cidade onde está localizada a Regional", getScanner());
        String estado = Leitura.lerCampoStringObrigatorio("Informe o "
                + "estado onde está localizada a Regional", getScanner());

        Regional regional = new Regional();
        regional = popularObjetoRegional(regional, nome, cidade, estado);

        inserirRegional(regional);
    }

    private Regional popularObjetoRegional(Regional regional, String nome,
            String cidade, String estado) {

        regional.setNome(nome);
        regional.setCidade(cidade);
        regional.setEstado(estado);

        return regional;
    }

    private void inserirRegional(Regional regional) {
        try {
            regionalController.cadastrarRegional(regional);
            System.out.println("\n##### Regional cadastrada com sucesso #####");
        } catch (ServicoException e) {
			System.out.println("\nNão foi possível cadastrar a "
                                + "Regional. Motivo: " + e.getMessage());
			LOG.trace(e.getMessage(), e);
		}
    }

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

}
