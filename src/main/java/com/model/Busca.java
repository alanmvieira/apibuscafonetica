package com.model;

import java.util.List;

public class Busca {

	private String nameMethodSearch;
	private List<String> words;

	public Busca() {
		super();
	}

	public Busca(String nameMethodSearch, List<String> words) {
		super();
		this.nameMethodSearch = nameMethodSearch;
		this.words = words;
	}

	public Busca(String nameMethodSearch) {
		super();
		this.nameMethodSearch = nameMethodSearch;

	}

	public String getNameMethodSearch() {
		return nameMethodSearch;
	}

	public void setNameMethodSearch(String nameMethodSearch) {
		this.nameMethodSearch = nameMethodSearch;
	}

	public List<String> getWords() {
		return words;
	}

	public void setWords(List<String> words) {
		this.words = words;
	}

}
