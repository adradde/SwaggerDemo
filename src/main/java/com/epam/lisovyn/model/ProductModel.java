package com.epam.lisovyn.model;

/**
 * Created by Andrii_Lisovyn on 15.4.2016.
 */
public class ProductModel extends Model {

	private String article;
	private double price;
	private String color;


	public ProductModel(int id, String article, double price, String color) {
		super(id);
		this.article = article;
		this.price = price;
		this.color = color;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
