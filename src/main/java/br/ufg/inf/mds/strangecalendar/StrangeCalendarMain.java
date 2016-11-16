package br.ufg.inf.mds.strangecalendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.ufg.inf.mds.strangecalendar.controller.RegionalController;
import br.ufg.inf.mds.strangecalendar.services.exceptions.ServicoException;

public class StrangeCalendarMain {

	public static void main(String[] args) {
		// Acessa o contexto do Spring Framework a partir de um método estático.
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
//        RegionalController regionalController = context.getBean(RegionalController.class);
//		try {
//			regionalController.salvarRegional("UFG Goiânia", "Goiânia", "GO");
//		} catch (ServicoException e) {
//			e.printStackTrace();
//		}
	}

}
