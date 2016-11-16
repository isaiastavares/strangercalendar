package br.ufg.inf.mds.strangecalendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.ufg.inf.mds.strangecalendar.entidade.Regional;
import br.ufg.inf.mds.strangecalendar.services.RegionalService;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;

@Controller
public class RegionalController {

	@Autowired
	private RegionalService regionalService;

	public void salvarRegional(String nome, String cidade, String estado) throws ServicoException {
		Regional regional = new Regional();
		regional.setNome(nome);
		regional.setCidade(cidade);
		regional.setEstado(estado);

		regionalService.inserir(regional);
	}

}
