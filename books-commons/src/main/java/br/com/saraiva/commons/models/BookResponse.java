package br.com.saraiva.commons.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookResponse {

	private String sku;
	private String name;
	private String brand;
	private PriceResponse price;
	
	public BookResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public PriceResponse getPrice() {
		return price;
	}

	public void setPrice(PriceResponse price) {
		this.price = price;
	}

}