package com.example.home.model;

import java.util.List;

public class Page{
	private List<HitsItem> hits;
	private boolean exhaustiveTypo;
	private int hitsPerPage;
	private int processingTimeMS;
	private String query;
	private int nbHits;
	private int page;
	private String params;
	private int nbPages;
	private boolean exhaustiveNbHits;

	public List<HitsItem> getHits(){
		return hits;
	}

	public boolean isExhaustiveTypo(){
		return exhaustiveTypo;
	}

	public int getHitsPerPage(){
		return hitsPerPage;
	}

	public int getProcessingTimeMS(){
		return processingTimeMS;
	}

	public String getQuery(){
		return query;
	}

	public int getNbHits(){
		return nbHits;
	}

	public int getPage(){
		return page;
	}

	public String getParams(){
		return params;
	}

	public int getNbPages(){
		return nbPages;
	}

	public boolean isExhaustiveNbHits(){
		return exhaustiveNbHits;
	}
}