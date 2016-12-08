package br.ufg.inf.mds.strangecalendar.view;

import br.ufg.inf.mds.strangecalendar.controller.EventoController;
import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;
import br.ufg.inf.mds.strangecalendar.util.Leitura;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;

public class ViewExcluirEvento {

	private Scanner scanner;
	private EventoController eventoController;

	public ViewExcluirEvento(Scanner scanner, ApplicationContext context) {
		this.scanner = scanner;
		this.eventoController = context.getBean(EventoController.class);
	}

	public void exibirExcluirEvento() {
		System.out.println("##### Bem Vindo a Opção de Excluir Evento #####\n");

		List<Evento> eventos = eventoController.listarEventos();

		if (eventos.isEmpty()) {
			System.out.println("Não existe nenhum evento cadastrado");
			return;
		}

		imprimirEventosEncontrados(eventos);

		Integer idEvento = Leitura.lerCampoIntegerObrigatorio("Informe o ID do "
				+ "evento que deseja excluir", getScanner());

		try {
			long idEventoLong = Long.parseLong(idEvento.toString());
			Evento evento = eventoController.excluirEventoPorId(idEventoLong);
			System.out.println("Evento excluído com sucesso!\nDetalhes do "
					+ "evento: " + evento.toString());
		} catch (ServicoException e) {
			System.out.println("Não foi possível excluir o evento com ID: "
					+ idEvento);
		}
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
