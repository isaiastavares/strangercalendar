package br.ufg.inf.mds.strangecalendar.view;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.ufg.inf.mds.strangecalendar.Config;
import br.ufg.inf.mds.strangecalendar.util.PopularBanco;

/**
 * @author Leonardo
 */
public class ViewMenuPrincipal {

    private static ApplicationContext context;

    private ViewCadastrarRegional viewCadastrarRegional;
    private ViewCadastrarEventos viewCadastrarEventos;
    private ViewBuscaEventos viewBuscaEventos;
    private ViewExcluirEvento viewExcluirEvento;
    private ViewEditarEvento viewEditarEvento;
    private ViewExcluirRegional viewExcluirRegional;
    private ViewListarRegionais viewListarRegionais;

    public void exibirMenuPrincipal() {
        context = new AnnotationConfigApplicationContext(Config.class);
        PopularBanco popularBanco = context.getBean(PopularBanco.class);
        popularBanco.inicializarBanco();

        Scanner scanner = new Scanner(System.in);

        viewCadastrarRegional = new ViewCadastrarRegional(scanner, context);
        viewCadastrarEventos = new ViewCadastrarEventos(scanner, context);
        viewBuscaEventos = new ViewBuscaEventos(scanner, context);
        viewExcluirEvento = new ViewExcluirEvento(scanner, context);
        viewEditarEvento = new ViewEditarEvento(scanner, context);
        viewExcluirRegional = new ViewExcluirRegional(scanner, context);
        viewListarRegionais = new ViewListarRegionais(context);

        System.out.println("##### Sistema de Gestão do Calendário"
                + " Acadêmico da UFG #####");

        int opcao = 1;

        while (opcao != 0) {
            System.out.println("\n0 - Sair do programa.");
            System.out.println("1 - Cadastrar Regional.");
            System.out.println("2 - Cadastrar Evento.");
            System.out.println("3 - Pesquisar evento por Data.");
            System.out.println("4 - Pesquisar evento por Palavra Chave.");
            System.out.println("5 - Pesquisar evento por Regional");
            System.out.println("6 - Pesquisar evento por Interessado.");
            System.out.println("7 - Excluir Evento.");
            System.out.println("8 - Excluir Regional.");
            System.out.println("9 - Editar Evento.");
            System.out.println("10 - Listar regionais.");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                //  atribuindo 100 a variavel opcao para poder
                //  exibir o menu novamente.
                opcao = 100;
            }

            redirecionarAcao(opcao);
        }
        System.out.println("Fim do Programa");
        System.exit(0);
    }

    private void redirecionarAcao(int opcao) {
        switch (opcao) {
        	case 0:
        		break;
            case 1:
            	viewCadastrarRegional.exibirCadastroRegional();
                break;
            case 2:
            	viewCadastrarEventos.exibirCadastroEvento();
                break;
            case 3:
            	viewBuscaEventos.exibirBuscaEventoData();
            	break;
            case 4:
            	viewBuscaEventos.exibirBuscaEventoPalavraChave(null);
                break;
            case 5:
                viewBuscaEventos.exibirBuscaEventoPorRegional();
                break;
            case 6:
            	viewBuscaEventos.exibirBuscaEventoPorInteressado(null);
                break;
            case 7:
            	viewExcluirEvento.exibirExcluirEvento();
                break;
            case 8:
            	viewExcluirRegional.exibirExcluirRegional();
                break;
            case 9:
            	viewEditarEvento.exibirEditarEvento();
                break;
            case 10:
            	viewListarRegionais.listarRegionais();
                break;
            default:
            	System.out.println("Número Inválido. Tente novamente "
                            + "digitando um número válido.");
                break;
        }
    }
}
