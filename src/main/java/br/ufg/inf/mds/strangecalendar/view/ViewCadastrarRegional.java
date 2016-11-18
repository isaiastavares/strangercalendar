/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.mds.strangecalendar.view;

import br.ufg.inf.mds.strangecalendar.Config;
import br.ufg.inf.mds.strangecalendar.controller.RegionalController;
import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;
import br.ufg.inf.mds.strangecalendar.util.Leitura;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Leonardo
 */
public class ViewCadastrarRegional {

    private static ApplicationContext context;
    
    private static final Logger LOG = LoggerFactory.getLogger(RegionalController.class);

    public void exibirCadastroRegional(Scanner scanner) {
        System.out.println("##### Bem Vindo ao Cadastro de Regional #####\n");

        String nome = Leitura.lerCampoStringObrigatorio("Informe o nome"
                + "da Regional", scanner);
        String cidade = Leitura.lerCampoStringObrigatorio("Informe a "
                + "cidade onde está localizada a Regional", scanner);
        String estado = Leitura.lerCampoStringObrigatorio("Informe o "
                + "estado onde está localizada a Regional", scanner);

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
            context = new AnnotationConfigApplicationContext(Config.class);
        RegionalController regionalController = context.getBean(RegionalController.class);
            regionalController.cadastrarRegional(regional);
            System.out.println("\n##### Regional cadastrada com sucesso #####");
        } catch (ServicoException e) {
			System.out.println("\nNão foi possível cadastrar a "
                                + "Regional. Motivo: " + e.getMessage());
			LOG.trace(e.getMessage(), e);
		}
    }
}
