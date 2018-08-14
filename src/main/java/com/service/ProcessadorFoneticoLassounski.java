package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.formater.FormatadorSaida;
import com.model.MatchableString;
import com.repository.DictionaryRepository;
import com.utils.transformador.TransformadorLexical;

public class ProcessadorFoneticoLassounski implements IProcessador {

	private DictionaryRepository dictionaryRepository = new DictionaryRepository();

	public ProcessadorFoneticoLassounski() {
	}

	public String search(List<String> wordsToProcess) {

		Set<String> words = new HashSet<String>(wordsToProcess);

		Map<String, List<String>> similarityWords = null;

		Set<String> dicionarioEntrada = dictionaryRepository.getWords();

		Map<MatchableString, List<String>> dicionario = normalize(dicionarioEntrada);
		Map<MatchableString, List<String>> argumentWords = normalize(words);

		similarityWords = processamentoBuscaFonetica(argumentWords, dicionario);

		FormatadorSaida formatadorSaida = new FormatadorSaida();
		String resultFinal = formatadorSaida.defineFinalResultTextToLassounski(similarityWords);

		return resultFinal;
	}

	private Map<String, List<String>> processamentoBuscaFonetica(Map<MatchableString, List<String>> argumentWords,
			Map<MatchableString, List<String>> dictionary) {

		Map<String, List<String>> matchedWords = new HashMap<>();

		argumentWords.forEach((matchableArgument, arguments) -> {
			List<String> dictionaryWords = dictionary.get(matchableArgument);

			arguments.stream().forEach((argument) -> {
				matchedWords.put(argument, dictionaryWords);
			});

		});

		return matchedWords;
	}

	private Map<MatchableString, List<String>> normalize(Set<String> wordSet) {

		Map<MatchableString, List<String>> normalizedMap = new HashMap<>();
		TransformadorLexical normalizer = new TransformadorLexical();

		wordSet.stream().forEach((word) -> {
			MatchableString normalizedWord = new MatchableString(normalizer.normalize(word));

			if (normalizedMap.containsKey(normalizedWord)) {
				normalizedMap.get(normalizedWord).add(word);
			} else {
				List<String> words = new ArrayList<>();

				words.add(word);
				normalizedMap.put(normalizedWord, words);
			}
		});

		return normalizedMap;
	}

}
