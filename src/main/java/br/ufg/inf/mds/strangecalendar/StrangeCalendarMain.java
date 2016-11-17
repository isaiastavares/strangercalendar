package br.ufg.inf.mds.strangecalendar;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.ufg.inf.mds.strangecalendar.controller.EventoController;
import br.ufg.inf.mds.strangecalendar.controller.RegionalController;

/**
 * Classe Main do Projeto Strange Calendar
 *
 * @author Isaias Tavares
 */
public class StrangeCalendarMain {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(Config.class);

        System.out.println("##### Sistema de Gestão do Calendário Acadêmico da UFG #####");

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
            	// atribuindo 100 a variavel opcao para poder exibir o menu novamente.
                opcao = 100;
            }

            switch (opcao) {
            	case 0:
            		break;
                case 1:
                	RegionalController regionalController = context.getBean(RegionalController.class);
                	regionalController.cadastrarRegional(scanner);
                    break;
                case 2:
                	EventoController eventoController = context.getBean(EventoController.class);
                	eventoController.cadastrarEvento(scanner);
                    break;
                case 3:
                	System.out.println("Opção ainda não implementada");
                	break;
                case 4:
                	System.out.println("Opção ainda não implementada");
                    break;
                default:
                	System.out.println("Número Inválido. Tente novamente digitando um número válido.");
                    break;
            }
        }
        System.out.println("Fim do Programa");
        System.exit(0);
	}

}
