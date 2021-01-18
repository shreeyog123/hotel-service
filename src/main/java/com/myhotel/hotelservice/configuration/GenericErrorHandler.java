package com.myhotel.hotelservice.configuration;

import com.myhotel.hotelservice.exception.HotelNotFoundException;
import com.myhotel.hotelservice.model.response.GenericErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = "com.myhotel.hotelservice.contract")
public class GenericErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<GenericErrorResponse> hotelNotFound(final HotelNotFoundException exception){

        GenericErrorResponse errorResponse = GenericErrorResponse.builder()
                .errorCode(101)
                .errorMessage(exception.getMessage())
                .build();

        log.error("event=createWorkFlow, errorResponse = {}", errorResponse);

        return ResponseEntity.badRequest().body(errorResponse);


    }
}
