package com.service.bean;

import com.model.Busca;
import com.service.IProcessador;
import com.service.ProcessadorFoneticoLassounski;
import com.service.ProcessadorFoneticoSoundex;
import com.utils.validator.ValidatorInput;

public class ProcessadorFoneticoService {

	private final static String SOUDEX_ALGORITHM = "soudex";
	private final static String LASSOUNSKI_ALGORITHM = "lassounski";
	
	private final static String TIPO_ALGORITMO_INVALIDO = "Opção não válida para tipo de algoritimo";
	private final static String ARGUMENTOS_JSON_INVALIDO = "Os argumentos de input do arquivo JSON não foram definidos corretamente.";
	
	private ValidatorInput validatorInput = new ValidatorInput();

	public String processSimilarityPhonetic(Busca busca) {

		IProcessador processador = null;

		if (validatorInput.validInputBusca(busca)) {

			switch (busca.getNameMethodSearch().toLowerCase()) {

			case SOUDEX_ALGORITHM:
				processador = new ProcessadorFoneticoSoundex();
				return ((ProcessadorFoneticoSoundex) processador).search(busca.getWords());

			case LASSOUNSKI_ALGORITHM:
				processador = new ProcessadorFoneticoLassounski();
				return ((ProcessadorFoneticoLassounski) processador).search(busca.getWords());

			default:
				return TIPO_ALGORITMO_INVALIDO;
			}
			
		} else {
			throw new IllegalArgumentException(ARGUMENTOS_JSON_INVALIDO);
		}

	}

}
