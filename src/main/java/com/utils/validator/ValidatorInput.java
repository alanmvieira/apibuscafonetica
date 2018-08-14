package com.utils.validator;

import com.model.Busca;

public class ValidatorInput {

	public boolean validInputBusca(Busca busca) {

		if (busca.getNameMethodSearch() == null || busca.getWords() == null || busca.getNameMethodSearch().isEmpty() || busca.getWords().size() == 0) {
			return false;
		}

		return true;

	}
}
