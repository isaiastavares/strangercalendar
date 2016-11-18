/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.mds.strangecalendar.view;

import br.ufg.inf.mds.strangecalendar.Config;
import br.ufg.inf.mds.strangecalendar.controller.EventoController;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Leonardo
 */
public class ViewMenuPrincipal {
    
    private static ApplicationContext context;
    ViewCadastrarRegional viewCadastrarRegional = new ViewCadastrarRegional();
    ViewCadastrarEventos viewCadastrarEventos = new ViewCadastrarEventos();
    ViewBuscaEventos viewBuscaEventos = new ViewBuscaEventos();
    
    public void exibirMenuPrincipal(){
     context = new AnnotationConfigApplicationContext(Config.class);
     EventoController eventoController = context.
             getBean(EventoController.class);
        
     System.out.println("##### Sistema de Gestão do Calendário"
             + " Acadêmico da UFG #####");

        int opcao = 1;
        Scanner scanner = new Scanner(System.in);

        while (opcao != 0) {
            System.out.println("\n0 - Sair do programa.");
            System.out.println("1 - Cadastrar Regional.");
            System.out.println("2 - Cadastrar Evento.");
            System.out.println("3 - Pesquisar evento por data.");
            System.out.println("4 - Pesquisar evento por palavra chave.");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
            	//  atribuindo 100 a variavel opcao para poder 
                //  exibir o menu novamente.
                opcao = 100;
            }

            redirecionarAcao(opcao, scanner);
        }
        System.out.println("Fim do Programa");
        System.exit(0);
	}

    private void redirecionarAcao(int opcao, Scanner scanner) {
        
        switch (opcao) {
            	case 0:
            		break;
                case 1:
                	viewCadastrarRegional.exibirCadastroRegional(scanner);
                    break;
                case 2:
                	viewCadastrarEventos.exibirCadastroEvento(scanner);
                    break;
                case 3:
                	viewBuscaEventos.exibirBuscaEventoData(scanner);
                	break;
                case 4:
                	viewBuscaEventos.exibirBuscaEventoPalavraChave(scanner);
                    break;
                default:
                	System.out.println("Número Inválido. Tente novamente "
                                + "digitando um número válido.");
                    break;
            }
    }
}
