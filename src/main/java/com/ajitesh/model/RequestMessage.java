package com.ajitesh.model;

import java.util.List;

public class RequestMessage {
	
	private String requestId;
	private String emailAddress;
	
	private List<People> people;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public List<People> getPeople() {
		return people;
	}

	public void setPeople(List<People> people) {
		this.people = people;
	}

	@Override
	public String toString() {
		return "RequestMessage [requestId=" + requestId + ", emailAddress=" + emailAddress + ", people=" + people + "]";
	}
	
	

	
	
	

}
