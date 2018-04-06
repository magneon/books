package br.com.saraiva.commons.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceResponse {

	private BestPriceResponse bestPrice;

	public BestPriceResponse getBestPrice() {
		return bestPrice;
	}

	public void setBestPrice(BestPriceResponse bestPrice) {
		this.bestPrice = bestPrice;
	}

}
