package com.myhotel.hotelservice.contract;

import com.myhotel.hotelservice.model.HotelDetails;
import com.myhotel.hotelservice.model.request.HotelDetailsRequest;
import com.myhotel.hotelservice.model.response.Hotel;
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
    ResponseEntity<HotelDetails> getAllHotelDetails(
            @ApiParam(value = "city for filter hotel") final String city,
            @ApiParam(value = "get hotel details by from date") final LocalDate startDate,
            @ApiParam(value = "get hotel details by end date") final LocalDate endDate
     );

    @ApiOperation(
            value = "details/{hotelId}",
            response = HotelDetails.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET"
    )
    ResponseEntity<Hotel> getAHotelDetails(
            @ApiParam(value = "get hotel details by hotelId") final Long hotelId,
            @ApiParam(value = "get hotel details by from date") final LocalDate startDate,
            @ApiParam(value = "get hotel details by end date") final LocalDate endDate
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
