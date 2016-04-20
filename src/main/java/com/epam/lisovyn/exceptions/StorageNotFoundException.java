package com.epam.lisovyn.exceptions;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * Created by Andrii_Lisovyn on 15.4.2016.
 */
public class StorageNotFoundException extends Exception{

	private int errCode;

	public StorageNotFoundException(Response.Status status) {
		super(NOT_FOUND.getReasonPhrase());
		this.errCode = NOT_FOUND.getStatusCode();
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
}
