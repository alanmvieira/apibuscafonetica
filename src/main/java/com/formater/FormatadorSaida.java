package com.formater;

import java.util.List;
import java.util.Map;

public class FormatadorSaida {

	public String defineFinalResultTextToSoundex(Map<String, List<String>> similarityWords) {

		StringBuilder saida = new StringBuilder("");

		similarityWords.forEach((k, v) -> {

			if (v != null) {
				if (v.size() > 1) {
					saida.append(v);
				}
			}
			
		});

		String resultFinal = getText(saida);

		return resultFinal;
	}
	
	public String defineFinalResultTextToLassounski(Map<String, List<String>> similarityWords) {

		StringBuilder saida = new StringBuilder("");

		similarityWords.forEach((k, v) -> {

			if (v != null) {
				if (v.size() > 1) {
					v.add(k);
					saida.append(v);
				}
			}
			
		});

		String resultFinal = getText(saida);

		return resultFinal;
	}
	
	private String getText(StringBuilder saida){
		if (!saida.toString().isEmpty()) {
			return "O(s) conjunto(s) de similaridade fonética encontrada é/são: " + saida;
		} else {
			return "Não foi encontrado nenhum conjunto de similaridade fonética";
		}
	}
	
}
