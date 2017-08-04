package org.unsa.dto;

public class UserTaskDto<T> {
	private HeaderDto headerDto;
	private T body;
	
	public HeaderDto getHeaderDto() {
		return headerDto;
	}
	public void setHeaderDto(HeaderDto headerDto) {
		this.headerDto = headerDto;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	
	
}
