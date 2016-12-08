package br.ufg.inf.mds.strangecalendar.services;

import br.ufg.inf.mds.strangecalendar.entidade.Evento;
import br.ufg.inf.mds.strangecalendar.entidade.Interessado;
import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.repository.EventoRepository;
import br.ufg.inf.mds.strangecalendar.repository.IRepository;
import br.ufg.inf.mds.strangecalendar.repository.InteressadoRepository;
import br.ufg.inf.mds.strangecalendar.repository.RegionalRepository;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;
import br.ufg.inf.mds.strangecalendar.util.EventosPadroes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;

/**
 * Service de {@link Evento}
 *
 * @author Isaias Tavares
 */
@Service
public class EventoService extends AbstractService<Evento> {

	private static final long serialVersionUID = 6089495135689042953L;

	private EventoRepository eventoRepository;
	private RegionalRepository regionalRepository;
	private InteressadoRepository interessadoRepository;

	@Autowired
	public EventoService(EventoRepository eventoRepository,
						 RegionalRepository regionalRepository,
						 InteressadoRepository interessadoRepository) {
		this.eventoRepository = eventoRepository;
		this.regionalRepository = regionalRepository;
		this.interessadoRepository = interessadoRepository;
	}

	@PostConstruct
	public void initialize() {
		Regional regionalUFG = regionalRepository.findByNome("UFG");
		Interessado comunidade = interessadoRepository.findByNome
				("Comunidade em Geral");
		Interessado docente = interessadoRepository.findByNome
				("Docentes");
		Interessado discentes = interessadoRepository.findByNome
				("Discentes");

		if (eventoRepository.findAll().isEmpty()) {

			for(Evento evento : EventosPadroes.getEventosPadroes()){
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

	@Override
	public IRepository<Evento> getRepositorio() {
		return eventoRepository;
	}

	@Override
	protected void antesInserir(Evento entidade) throws ServicoException {
		// TODO implementar validacao
	}
}
