package br.ufg.inf.mds.strangecalendar.view;

import java.util.List;

import org.springframework.context.ApplicationContext;

import br.ufg.inf.mds.strangecalendar.controller.RegionalController;
import br.ufg.inf.mds.strangecalendar.entidade.Regional;

/**
 * Lista as Regionais cadastradas no sistema
 * @author android
 */
public class ViewListarRegionais {

    private RegionalController regionalController;

    public ViewListarRegionais(ApplicationContext context) {
		this.regionalController = context.getBean(RegionalController.class);
	}

    public void listarRegionais(){
        System.out.println("##### Bem Vindo a listagem de Regionais #####\n");

		List<Regional> listRegionaisCadastradas = regionalController
				.listarRegionais();

		if (listRegionaisCadastradas.isEmpty()) {
			System.out.println("Não existe nenhuma regional cadastrada");
		} else {
            imprimirRegionaisEncontrados(listRegionaisCadastradas);
        }
    }

    private void imprimirRegionaisEncontrados(List<Regional> listRegionais) {
		listRegionais.forEach(regional -> {
			System.out.println(regional.toString());
		});
	}

}
