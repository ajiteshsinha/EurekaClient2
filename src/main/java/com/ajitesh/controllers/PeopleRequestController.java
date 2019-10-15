package com.ajitesh.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.ajitesh.exception.InValidRequestException;
import com.ajitesh.model.RequestMessage;
import com.ajitesh.model.ResponseMessage;;

@RestController
@RequestMapping("/verify")
public class PeopleRequestController {
	
	Logger log = LoggerFactory.getLogger(PeopleRequestController.class);
	
	

	
	@PostMapping("/people")
	public ResponseMessage verifyPeople(@RequestBody RequestMessage requestMessage, WebRequest webRequest) throws InValidRequestException {
		
		log.info("Got request to verify : " + requestMessage);
		
		ResponseMessage responseMessage =  new ResponseMessage();
		webRequest.setAttribute("requestMessage", requestMessage, RequestAttributes.SCOPE_REQUEST);
		
		if(isValidRequestMessage(requestMessage)) {
			responseMessage.setStatus("ok");
			responseMessage.setDetails("processed " + requestMessage.getPeople().size() + " for " + requestMessage.getEmailAddress());
		}else {
			throw new InValidRequestException();
		}
		return responseMessage;
	}
	
	private boolean isValidRequestMessage(RequestMessage requestMessage) {
		
		boolean isValid = false;
		
		if(!requestMessage.getRequestId().isEmpty() && !requestMessage.getEmailAddress().isEmpty()
				&&  requestMessage.getPeople().size() > 0) {
			isValid = true;
		}
		return isValid;
	}
	
	@ExceptionHandler(InValidRequestException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseMessage sendInvalidRequestMessage(WebRequest request) {
		ResponseMessage responseMessage =  new ResponseMessage();
		responseMessage.setStatus("fail");
		RequestMessage requestMessage = (RequestMessage) request.getAttribute("requestMessage", RequestAttributes.SCOPE_REQUEST);
		System.out.println(requestMessage);
		if(requestMessage.getRequestId().isEmpty()) {
			responseMessage.setDetails("RequestId is null or blank");
		}else if(requestMessage.getEmailAddress().isEmpty()) {
			responseMessage.setDetails("emailAdress is null or blank");
		}else if(requestMessage.getPeople().size() == 0) {
			responseMessage.setDetails("People list is null or blank");
		}
		
		return responseMessage;
		
	}

}
