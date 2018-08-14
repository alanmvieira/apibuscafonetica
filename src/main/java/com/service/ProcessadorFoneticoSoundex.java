package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.formater.FormatadorSaida;
import com.utils.transformador.TransformadorLexical;

public class ProcessadorFoneticoSoundex implements IProcessador {

	TransformadorLexical transformadorLexical = new TransformadorLexical();
	
	private String soundex(String s) {
		char[] x = s.toUpperCase().toCharArray();
		char firstLetter = x[0];

		for (int i = 0; i < x.length; i++) {
			switch (x[i]) {

			case 'B':
			case 'F':
			case 'P':
			case 'V':
				x[i] = '1';
				break;

			case 'C':
			case 'G':
			case 'J':
			case 'K':
			case 'Q':
			case 'S':
			case 'X':
			case 'Z':
				x[i] = '2';
				break;

			case 'D':
			case 'T':
				x[i] = '3';
				break;

			case 'L':
				x[i] = '4';
				break;

			case 'M':
			case 'N':
				x[i] = '5';
				break;

			case 'R':
				x[i] = '6';
				break;

			default:
				x[i] = '0';
				break;
			}
		}

		String output = removeDuplicate(firstLetter, x);

		output = output + "0000";
		return output.substring(0, 4);
	}

	private String removeDuplicate(char letter, char[] elements) {

		String output = "" + letter;
		for (int i = 1; i < elements.length; i++) {
			if (elements[i] != elements[i - 1] && elements[i] != '0') {
				output += elements[i];
			}
		}
		return output;

	}

	public String search(List<String> wordsInput) {

		ProcessadorFoneticoSoundex processadorFoneticoSoundex = new ProcessadorFoneticoSoundex();
		Map<String, List<String>> similarityWords = new HashMap<String, List<String>>();

		for (String word : wordsInput) {

			word = transformadorLexical.removeNonAlphabetical(word);

			if (!word.isEmpty()) {
				String cod = processadorFoneticoSoundex.soundex(word);

				if (similarityWords.containsKey(cod)) {
					similarityWords.get(cod).add(word);
				} else {
					List<String> words = new ArrayList<>();
					words.add(word);
					similarityWords.put(cod, words);
				}
			}

		}

		FormatadorSaida formatadorSaida = new FormatadorSaida();
		String resultFinal = formatadorSaida.defineFinalResultTextToSoundex(similarityWords);
		
		return resultFinal;
		
	}

}
