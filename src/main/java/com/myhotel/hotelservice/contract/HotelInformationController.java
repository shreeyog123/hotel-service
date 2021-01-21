package com.myhotel.hotelservice.contract;

import com.myhotel.hotelservice.model.response.HotelDetails;
import com.myhotel.hotelservice.model.request.HotelDetailsRequest;
import com.myhotel.hotelservice.model.request.HotelUpdateRequest;
import com.myhotel.hotelservice.model.response.Hotel;
import com.myhotel.hotelservice.model.response.HotelResponse;
import com.myhotel.hotelservice.service.hotelInformation.HotelInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class HotelInformationController implements HotelInformationContract {

    private final HotelInformationService hotelInformationService;

    public HotelInformationController(HotelInformationService hotelInformationService) {
        this.hotelInformationService = hotelInformationService;
    }

    @Override
    public ResponseEntity<HotelDetails> getAllHotelInformationByCity(final String city) {

        log.info("get all hotel details for city {} ", city);

        final HotelDetails hotelDetails = hotelInformationService.getAvailableHotelDetails(city);
        log.info("successfully fetch HotelDetails {}", hotelDetails);

        return ResponseEntity.ok().body(hotelDetails);
    }

    @Override
    @GetMapping(value = "/details/{hotelId}")
    public ResponseEntity<Hotel> getHotelDetailsByHotelId(final long hotelId) {

        log.info("get hotel details for hotel-id {}", hotelId);

        Hotel hotelDetails = hotelInformationService.getASpecificHotelDetails(hotelId);
        log.info("successfully fetch HotelDetails {}", hotelDetails);

        return ResponseEntity.ok().body(hotelDetails);
    }

    @Override
    public ResponseEntity<HotelResponse> addHotelDetails(final HotelDetailsRequest hotelDetailsRequest) {

        log.info("add hotel information hotelDetailsRequest {}", hotelDetailsRequest);

        final String message = hotelInformationService.addHotel(hotelDetailsRequest);
        log.info("message {}", message);

        return ResponseEntity.status(HttpStatus.CREATED).body(HotelResponse.builder().message(message).build());
    }

    @Override
    public ResponseEntity<HotelResponse> updateHotelDetails(final HotelUpdateRequest hotelUpdateRequest) {

        log.info("update hotel information hotelUpdateRequest {}", hotelUpdateRequest);

        final String message = hotelInformationService.updateHotel(hotelUpdateRequest);
        log.info("message {}", message);

        return ResponseEntity.accepted().body(HotelResponse.builder().message(message).build());
    }


}
