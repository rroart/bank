package com.catware.model;

public class SigningBasketResponse201 {
	private LinksSigningBasket _links = null;

	private String basketId = null;

	private String transactionStatus;

	public LinksSigningBasket get_links() {
		return _links;
	}

	public void set_links(LinksSigningBasket _links) {
		this._links = _links;
	}

	public String getBasketId() {
		return basketId;
	}

	public void setBasketId(String basketId) {
		this.basketId = basketId;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

}
