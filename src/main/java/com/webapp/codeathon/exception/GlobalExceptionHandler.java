package com.webapp.codeathon.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
//@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleBadCredentialsException(BadCredentialsException ex, WebRequest req) {
        ErrorDetails err = new ErrorDetails(ex.getMessage(), req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }
 
//	@ExceptionHandler(TokenNotFoundException.class)
//	public String exceptionHandler(TokenNotFoundException unfe) {
//		return unfe.getMessage();
//	}
	
	
	//USER EXCEPTION
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> userExceptionHandler(UserException ue,WebRequest req){
		
		ErrorDetails err= 
				new ErrorDetails(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorDetails> handleValidationException(ConstraintViolationException ex){
		
		StringBuilder errorMessage= new StringBuilder();
		
		for(ConstraintViolation<?> violation: ex.getConstraintViolations()) {
			errorMessage.append(violation.getMessage()+"\n");
		}
		
		ErrorDetails err= new ErrorDetails(errorMessage.toString(), "Validation Error", LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	//USER NOT FOUND
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDetails> userNotFoundException(UserNotFoundException unfe){
		String message = unfe.getErrorMessage();
		String user = unfe.getUser();
		ErrorDetails errorDetails = new ErrorDetails(message, user, null);
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	
//	@ExceptionHandler(UserNotFound.class)
//	public ResponseEntity<ErrorDetails> userNotFound(UserNotFound unf, WebRequest req){
//		ErrorDetails err= new ErrorDetails(unf.getMessage(), req.getDescription(false), LocalDateTime.now());
//		return new ResponseEntity<ErrorDetails>(err,HttpStatus.ACCEPTED);
//	}
	
//	@ExceptionHandler(UsernameNotFoundException.class)
//	public ResponseEntity<ErrorDetails> otherExceptionHandler(UsernameNotFoundException ue, WebRequest req){
//		ErrorDetails err= new ErrorDetails(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
//		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
//	}
}
