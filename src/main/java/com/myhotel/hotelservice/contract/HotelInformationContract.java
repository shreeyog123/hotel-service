package com.myhotel.hotelservice.contract;

import com.myhotel.hotelservice.model.HotelDetails;
import com.myhotel.hotelservice.model.request.HotelDetailsRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@Api(value = "hotel")
public interface HotelInformationContract {

   @ApiOperation(
            value = "details",
            response = HotelDetails.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET"
    )
    ResponseEntity<HotelDetails> getHotelDetails(
            @ApiParam(value = "city for filter hotel") final String city
                );

    @ApiOperation(
            value = "add",
            response =String.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    ResponseEntity<String> addHotel(
            @ApiParam(value = "request for add hotel") final HotelDetailsRequest hotelDetailsRequest);
}
