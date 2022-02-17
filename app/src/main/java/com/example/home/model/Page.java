package com.example.home.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Page{

	@SerializedName("hits")
	private List<HitsItem> hits;

	@SerializedName("exhaustiveTypo")
	private boolean exhaustiveTypo;

	@SerializedName("hitsPerPage")
	private int hitsPerPage;

	@SerializedName("processingTimeMS")
	private int processingTimeMS;

	@SerializedName("query")
	private String query;

	@SerializedName("nbHits")
	private int nbHits;

	@SerializedName("page")
	private int page;

	@SerializedName("params")
	private String params;

	@SerializedName("nbPages")
	private int nbPages;

	@SerializedName("exhaustiveNbHits")
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