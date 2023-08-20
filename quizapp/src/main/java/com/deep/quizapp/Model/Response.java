package com.deep.quizapp.Model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {

	private Long id;
	private String resOption;
	
	
	public Response() {
		
	}
	
	
	public Response(Long id, String resOption) {
		super();
		this.id = id;
		this.resOption = resOption;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getResOption() {
		return resOption;
	}
	public void setResOption(String resOption) {
		this.resOption = resOption;
	}
	
}
