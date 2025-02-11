package com.scaler.practiceservicedec24.Advices;

import com.scaler.practiceservicedec24.Dtos.ErrorDto;
import com.scaler.practiceservicedec24.Exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
     @ExceptionHandler(ProductNotFoundException.class)
     public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
         ErrorDto errorDto = new ErrorDto();
         errorDto.setMessage(productNotFoundException.getMessage());
         ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
         return responseEntity;
     }
}
