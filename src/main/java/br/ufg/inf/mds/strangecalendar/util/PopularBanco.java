package br.ufg.inf.mds.strangecalendar.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import br.ufg.inf.mds.strangecalendar.entidade.Interessado;
import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.enums.Interessados;
import br.ufg.inf.mds.strangecalendar.repository.EventoRepository;
import br.ufg.inf.mds.strangecalendar.repository.InteressadoRepository;
import br.ufg.inf.mds.strangecalendar.repository.RegionalRepository;

/**
 * Created by clientes on 08/12/2016.
 */
@Controller
public class PopularBanco {

	@Autowired
	private RegionalRepository regionalRepository;

	@Autowired
	private InteressadoRepository interessadoRepository;

	@Autowired
	private EventoRepository eventoRepository;

	public void inicializarBanco() {
		cadastrarRegionais();
		cadastrarInteressados();
		cadastrarEventos();
	}

	private void cadastrarRegionais() {
		if (regionalRepository.findAll().isEmpty()) {
			Regional regional = new Regional();
			regional.setNome("UFG Goiânia");

			regionalRepository.save(regional);
		}
	}

	private void cadastrarInteressados() {
		if (interessadoRepository.findAll().isEmpty()) {
            for (Interessados interessado : Interessados.values()) {
            	interessadoRepository.save(
            			new Interessado(interessado.getNome(),
            			new ArrayList<Evento>()));
            }
        }
	}

	private void cadastrarEventos() {
		Regional regionalUFG = regionalRepository.findByNome("UFG Goiânia");
		Interessado comunidade = interessadoRepository.findByNome
				("Comunidade em Geral");
		Interessado docente = interessadoRepository.findByNome
				("Docentes");
		Interessado discentes = interessadoRepository.findByNome
				("Discentes");

		if (eventoRepository.findAll().isEmpty()) {

			for(Evento evento : getEventosPadroes()){
				evento.setRegionais(new HashSet<>());
				evento.setInteressados(new HashSet<>());
				evento.getRegionais().add(regionalUFG);

				if(evento.getDescricao().contains("CEPAE") || evento
						.getDescricao().contains("Sra. da Conceição")){
					evento.getInteressados().add(comunidade);
				}else{
					evento.getInteressados().add(docente);
					evento.getInteressados().add(discentes);
				}

				eventoRepository.save(evento);
			}
		}
	}

    private List<Evento> getEventosPadroes(){
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
