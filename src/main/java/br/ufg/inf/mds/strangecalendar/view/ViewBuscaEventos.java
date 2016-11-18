/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.mds.strangecalendar.view;

import br.ufg.inf.mds.strangecalendar.Config;
import br.ufg.inf.mds.strangecalendar.controller.EventoController;
import br.ufg.inf.mds.strangecalendar.controller.RegionalController;
import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import br.ufg.inf.mds.strangecalendar.util.Leitura;
import java.util.List;
import java.util.Scanner;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Leonardo
 */
public class ViewBuscaEventos {

    private static final Logger LOG = LoggerFactory.
            getLogger(RegionalController.class);

    private static ApplicationContext context;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.
            forPattern("dd/MM/yyyy");
    
    public void exibirBuscaEventoData(Scanner scanner) {
        System.out.println("##### Bem Vindo a Pesquisa de Evento "
                + "Por Data #####\n");

        LocalDate data = Leitura.lerCampoDateObrigatorio("Informe a "
                + "data (Formato: dd/MM/yyyy)", scanner);

        List<Evento> eventosFiltrados = buscarEventosPorData(data);

        if (!existeEventosParaFiltro(eventosFiltrados)) {
            			System.out.println("Não encontrei nenhum evento"
                                + "nessa data");
                                return;
        }
        
        imprimirEventosEncontrados(eventosFiltrados);
    }

     public void exibirBuscaEventoPalavraChave(Scanner scanner) {
        System.out.println("##### Bem Vindo a Pesquisa de Evento Por "
                + "Palavra Chave #####\n");

        String palavraChave = Leitura.lerCampoStringObrigatorio(""
                        + "Informe a palavra chave", scanner);

        List<Evento> eventosFiltrados = 
                buscarEventosPorPalavraChave(palavraChave);

        if (!existeEventosParaFiltro(eventosFiltrados)) {
            		System.out.println("Não encontrei nenhum evento"
                                + "nessa data");
                                return;
        }
        
        imprimirEventosEncontrados(eventosFiltrados);
    }
    private List<Evento> buscarEventosPorData(LocalDate data) {

        context = new AnnotationConfigApplicationContext(Config.class);
        EventoController eventoController = context.getBean(
                EventoController.class);
        List<Evento> eventosEncontrados = eventoController.
                buscarEventoPorData(data);

        return eventosEncontrados;
    }
    
    private List<Evento> buscarEventosPorPalavraChave(String palavraChave){
        context = new AnnotationConfigApplicationContext(Config.class);
        EventoController eventoController = context.getBean(
                EventoController.class);
        List<Evento> eventosEncontrados = eventoController.
                buscarEventoPorPalavraChave(palavraChave);
        
        return eventosEncontrados;
    }

    private boolean existeEventosParaFiltro(List<Evento> eventosFiltrados) {
        boolean existeEventos = true;
        if (eventosFiltrados.isEmpty()) {
            existeEventos = false;
        }
        return existeEventos;
    }

    private void imprimirEventosEncontrados(List<Evento> eventosFiltrados) {
        System.out.println("A Pesquisa retornou os seguintes resultados:\n");
		for (Evento evento : eventosFiltrados) {
			System.out.println("Nome: " + evento.getDescricao() +
				"; Data Início: " + evento.getDataInicio().
                                        toString(DATE_FORMATTER) +
				"; Data Término: " + evento.getDataFim().
                                        toString(DATE_FORMATTER));
		}
    }
}
