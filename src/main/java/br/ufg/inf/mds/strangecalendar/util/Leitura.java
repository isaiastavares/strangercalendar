package br.ufg.inf.mds.strangecalendar.util;

import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

public final class Leitura {

	private Leitura() {
	}

	public static String lerCampoObrigatorio(String mensagem, Scanner scanner) {
		String valor = "";
		do {
			System.out.println(mensagem);
			valor = scanner.nextLine();
		} while (StringUtils.isBlank(valor));

		return valor;
	}

}
