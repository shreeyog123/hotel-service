package com.myhotel.hotelservice.contract;

import com.myhotel.hotelservice.model.HotelDetails;
import com.myhotel.hotelservice.model.request.HotelDetailsRequest;
import com.myhotel.hotelservice.model.request.HotelUpdateRequest;
import com.myhotel.hotelservice.model.response.Hotel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Api(value = "hotel")
public interface HotelInformationContract {

   @ApiOperation(
            value = "details",
            response = HotelDetails.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET"
    )
    ResponseEntity<HotelDetails> getAllHotelDetails(
            @ApiParam(value = "city for filter hotel") final String city
     );

    @ApiOperation(
            value = "details/{hotelId}",
            response = HotelDetails.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET"
    )
    ResponseEntity<Hotel> getAHotelDetails(
            @ApiParam(value = "get hotel details by hotelId") final long hotelId

    );

    @ApiOperation(
            value = "add",
            response =String.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    ResponseEntity<String> addHotel(
            @ApiParam(value = "request for add hotel") final HotelDetailsRequest hotelDetailsRequest);


 @ApiOperation(
         value = "update-room",
         response =String.class,
         produces = MediaType.APPLICATION_JSON_VALUE,
         httpMethod = "PUT"
 )
 ResponseEntity<String> updateHotel(
         @ApiParam(value = "request for update hotel room") final HotelUpdateRequest hotelDetailsRequest);

}
