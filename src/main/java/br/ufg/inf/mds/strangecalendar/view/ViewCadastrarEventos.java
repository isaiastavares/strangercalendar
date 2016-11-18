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
import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.repository.EventoRepository;
import br.ufg.inf.mds.strangecalendar.services.EventoService;
import br.ufg.inf.mds.strangecalendar.services.InteressadoService;
import br.ufg.inf.mds.strangecalendar.services.RegionalService;
import br.ufg.inf.mds.strangecalendar.util.Leitura;
import java.util.List;
import java.util.Scanner;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Leonardo
 */
public class ViewCadastrarEventos {

    private static final Logger LOG = LoggerFactory.
            getLogger(RegionalController.class);

    private static ApplicationContext context;
    @Autowired

    public void exibirCadastroEvento(Scanner scanner) {
        System.out.println("##### Bem Vindo ao Cadastro de Evento #####\n");

        if (!existeRegionaisCadastradas()) {
            System.out.println("Você não possui nenhuma regional cadastrada "
                    + "ainda. É necessário cadastrar pelo menos uma Regional "
                    + "para poder Cadastrar um Evento");
            return;
        }

        String descricao = Leitura.lerCampoStringObrigatorio("Informe a "
                + "descrição do Evento", scanner);

        LocalDate dataInicio = Leitura.lerCampoDateObrigatorio("Informe a data "
                + "de início do Evento (Formato: dd/MM/yyyy)", scanner);

        LocalDate dataFim = Leitura.lerCampoDateObrigatorio("Informe a data de "
                + "término do Evento  (Formato: dd/MM/yyyy)", scanner);

        Evento evento = new Evento();
        evento = popularObjetoEvento(evento, descricao, dataInicio, dataFim);
        
        inserirEvento(evento);
    }

    private boolean existeRegionaisCadastradas() {

        context = new AnnotationConfigApplicationContext(Config.class);
        RegionalService regionalService = context.
                getBean(RegionalService.class);
        boolean existeRegionais = true;
        List<Regional> listRegionais = regionalService.
                getRepositorio().findAll();
        if (listRegionais.isEmpty()) {
            existeRegionais = false;
        }
        return existeRegionais;
    }

    private Evento popularObjetoEvento(Evento evento, String descricao, 
            LocalDate dataInicio, LocalDate dataFim) {

        evento.setDescricao(descricao);
        evento.setDataInicio(dataInicio);
        evento.setDataFim(dataFim);
        
        return evento;
    }

    private void inserirEvento(Evento evento) {
        context = new AnnotationConfigApplicationContext(Config.class);
        EventoController eventoController = context.
                getBean(EventoController.class);
        eventoController.cadastrarEvento(evento);}
}
