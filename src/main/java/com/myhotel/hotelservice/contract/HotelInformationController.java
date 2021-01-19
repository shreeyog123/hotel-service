package com.myhotel.hotelservice.contract;

import com.myhotel.hotelservice.model.HotelDetails;
import com.myhotel.hotelservice.model.request.HotelDetailsRequest;
import com.myhotel.hotelservice.model.request.HotelUpdateRequest;
import com.myhotel.hotelservice.model.response.Hotel;
import com.myhotel.hotelservice.service.hotelInformation.HotelInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("hotel")
public class HotelInformationController implements HotelInformationContract{

    private final HotelInformationService hotelInformationService;

    public HotelInformationController(HotelInformationService hotelInformationService) {
        this.hotelInformationService = hotelInformationService;
    }

    @Override
    @GetMapping(value = "/details")
    public ResponseEntity<HotelDetails> getAllHotelDetails(@RequestParam("city") String city){

        HotelDetails hotelDetails = hotelInformationService.getAvailableHotelDetails(city);

        return ResponseEntity.ok().body(hotelDetails);
    }

    @Override
    @GetMapping(value = "/details/{hotelId}")
    public ResponseEntity<Hotel> getAHotelDetails(@PathVariable("hotelId") long hotelId){

        Hotel hotelDetails = hotelInformationService.getASpecificHotelDetails(hotelId);

        return ResponseEntity.ok().body(hotelDetails);
    }

    @Override
    @PostMapping(value = "/add")
    public ResponseEntity<String> addHotel(
            @RequestBody final HotelDetailsRequest hotelDetailsRequest) {

        String message = hotelInformationService.addHotel(hotelDetailsRequest);

        return ResponseEntity.ok().body(message);
    }

    @Override
    @PutMapping(value = "/hotel")
    public ResponseEntity<String> updateHotel(@RequestBody final HotelUpdateRequest hotelUpdateRequest) {

        String message = hotelInformationService.updateHotel(hotelUpdateRequest);

        return ResponseEntity.ok().body(message);
    }
}
