package com.utils.transformador;

public class TransformadorLexical {

	public String removeNonAlphabetical(String word) {
		return word.replaceAll("(\\W|\\d)", "");
	}

	private String removeSpecialLettersAfterFirstOne(String word) {
		return word.substring(0, 1) + word.substring(1).replaceAll("(a|A|e|E|i|I|o|O|u|U|h|H|w|W|y|Y)", "");
	}

	private String transformRepeatedLettersInOnlyOneLetter(String word) {
		
		return word.chars().mapToObj(character -> String.valueOf((char) character)).reduce((acc, character) -> {
			if (!acc.endsWith(character)) {
				acc += character;
			}
			return acc;
		}).get();
		
	}

	public String normalize(String word) {
		
		word = word.toLowerCase();
		word = removeNonAlphabetical(word);
		word = removeSpecialLettersAfterFirstOne(word);
		word = transformRepeatedLettersInOnlyOneLetter(word);
		
		return word;
	}
}
