package org.unsa.message;

public class ResponseMessage<T > {
	
	private T body;

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
}
