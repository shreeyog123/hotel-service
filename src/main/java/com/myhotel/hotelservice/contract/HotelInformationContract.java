package com.myhotel.hotelservice.contract;

import com.myhotel.hotelservice.model.response.HotelDetails;
import com.myhotel.hotelservice.model.request.HotelDetailsRequest;
import com.myhotel.hotelservice.model.request.HotelUpdateRequest;
import com.myhotel.hotelservice.model.response.GenericErrorResponse;
import com.myhotel.hotelservice.model.response.Hotel;
import com.myhotel.hotelservice.model.response.HotelResponse;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "hotel")
@RequestMapping("hotel")
public interface HotelInformationContract {

    @ApiOperation(
            value = "Get All Hotel Details by city name",
            response = HotelDetails.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "hotel information has been fetched successfully", response = HotelDetails.class),
            @ApiResponse(code = 400, message = "Business validation fail", response = GenericErrorResponse.class),
            @ApiResponse(code = 401, message = "Authentication failed."),
            @ApiResponse(code = 403, message = "You are not authorized to do this operation."),
            @ApiResponse(code = 404, message = "Resource not found.")
    })
    @GetMapping(value = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HotelDetails> getAllHotelInformationByCity(
            @ApiParam(value = "filter by city") @RequestParam("city") final String city
    );

    @ApiOperation(
            value = "Get Hotel Details by hotel id",
            response = HotelDetails.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "hotel information has been fetched successfully", response = HotelDetails.class),
            @ApiResponse(code = 400, message = "Business validation fail", response = GenericErrorResponse.class),
            @ApiResponse(code = 401, message = "Authentication failed."),
            @ApiResponse(code = 403, message = "You are not authorized to do this operation."),
            @ApiResponse(code = 404, message = "Resource not found.")
    })
    @GetMapping(value = "/details/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Hotel> getHotelDetailsByHotelId(
            @ApiParam(value = "get hotel details by hotelId") @PathVariable("hotelId") final long hotelId
    );


    @ApiOperation(
            value = "Request for add new hotel information",
            response = HotelResponse.class,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Hotel information has been added successfully.", response = HotelResponse.class),
            @ApiResponse(code = 400, message = "Business validation fail", response = GenericErrorResponse.class),
            @ApiResponse(code = 401, message = "Authentication failed."),
            @ApiResponse(code = 403, message = "You are not authorized to do this operation."),
            @ApiResponse(code = 404, message = "Resource not found.")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HotelResponse> addHotelDetails(
            @ApiParam(value = "request for add hotel information") @RequestBody final HotelDetailsRequest hotelDetailsRequest);


    @ApiOperation(
            value = "Request update hotel information",
            response = HotelResponse.class,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "PUT"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Hotel Room information has been updated successfully", response = HotelResponse.class),
            @ApiResponse(code = 400, message = "Business validation fail", response = GenericErrorResponse.class),
            @ApiResponse(code = 401, message = "Authentication failed."),
            @ApiResponse(code = 403, message = "You are not authorized to do this operation."),
            @ApiResponse(code = 404, message = "Resource not found.")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HotelResponse> updateHotelDetails(
            @ApiParam(value = "request for update hotel room") @RequestBody final HotelUpdateRequest hotelDetailsRequest);

}
