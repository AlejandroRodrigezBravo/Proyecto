package com.arodriguezbravo.catalago.excepciones;

public class SvcException extends Exception{

	/**
	 * Número de serie
	 */
	private static final long serialVersionUID = -57372163251370398L;
	
	public SvcException(){
		super();
	}
	
	public SvcException(String msg) {
		super(msg);
	}

	public SvcException(Exception ex){
		super(ex);
	}

}
