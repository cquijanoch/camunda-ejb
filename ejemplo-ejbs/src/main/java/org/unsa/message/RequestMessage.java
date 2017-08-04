package org.unsa.message;

import org.unsa.dto.HeaderDto;

public class RequestMessage<T> {
	private HeaderDto header;
	private T body;
	
	public HeaderDto getHeader() {
		return header;
	}

	public void setHeader(HeaderDto header) {
		this.header = header;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
}
