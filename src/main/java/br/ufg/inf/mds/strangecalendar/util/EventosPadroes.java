package br.ufg.inf.mds.strangecalendar.util;

import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clientes on 08/12/2016.
 */
public class EventosPadroes {

    public static List<Evento> getEventosPadroes(){
        List<Evento> eventosPadroes = new ArrayList<>();

        Evento cepae = new Evento();
        cepae.setDescricao("Período das aulas do 2º semestre do CEPAE");
        cepae.setDataInicio(LocalDateTime.parse("08/08/2016 00:00" ,
                Leitura.DATE_FORMATTER));
        cepae.setDataFim(LocalDateTime.parse("10/12/2016 00:00" ,
                Leitura.DATE_FORMATTER));

        Evento trancamento = new Evento();
        trancamento.setDescricao("Trancamento 2016-2");
        trancamento.setDataFim(LocalDateTime.parse("17/08/2016 00:00" ,
                Leitura.DATE_FORMATTER));
        trancamento.setDataInicio(LocalDateTime.parse("09/12/2016 00:00" ,
                Leitura.DATE_FORMATTER));

        Evento periodoAula = new Evento();
        periodoAula.setDescricao("Período de aulas do 2º semestre de 2016");
        periodoAula.setDataInicio(LocalDateTime.parse("18/08/2016 00:00" ,
                Leitura.DATE_FORMATTER));
        periodoAula.setDataFim(LocalDateTime.parse("23/12/2016 00:00" ,
                Leitura.DATE_FORMATTER));

        Evento feriadoMunicipal = new Evento();
        feriadoMunicipal.setDescricao("Dia de Nossa Sra. da Conceição " +
                "(Padroeira Cidade de Goiás)");
        feriadoMunicipal.setDataInicio(LocalDateTime.parse("08/12/2016 00:00" ,
                Leitura.DATE_FORMATTER));
        feriadoMunicipal.setDataFim(LocalDateTime.parse("08/12/2016 00:00" ,
                Leitura.DATE_FORMATTER));

        Evento formados2016 = new Evento();
        formados2016.setDescricao("Período das aulas do 2º semestre do CEPAE");
        formados2016.setDataInicio(LocalDateTime.parse("09/12/2016 00:00" ,
                Leitura.DATE_FORMATTER));
        formados2016.setDataFim(LocalDateTime.parse("23/01/2017 00:00" ,
                Leitura.DATE_FORMATTER));


        eventosPadroes.add(cepae);
        eventosPadroes.add(trancamento);
        eventosPadroes.add(periodoAula);
        eventosPadroes.add(feriadoMunicipal);
        eventosPadroes.add(formados2016);

        return eventosPadroes;
    }
}
