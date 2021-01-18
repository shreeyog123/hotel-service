package com.myhotel.hotelservice.contract;

import com.myhotel.hotelservice.model.HotelDetails;
import com.myhotel.hotelservice.model.request.HotelDetailsRequest;
import com.myhotel.hotelservice.model.response.Hotel;
import com.myhotel.hotelservice.service.hotelInformation.HotelInformationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("hotel")
public class HotelInformationController implements HotelInformationContract{

    private final HotelInformationService hotelInformationService;

    public HotelInformationController(HotelInformationService hotelInformationService) {
        this.hotelInformationService = hotelInformationService;
    }

    @Override
    @GetMapping(value = "/details")
    public ResponseEntity<HotelDetails> getAllHotelDetails(
            @RequestParam("city") String city,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        HotelDetails hotelDetails = hotelInformationService.getAvailableHotelDetails(city,startDate,endDate);

        return ResponseEntity.ok().body(hotelDetails);
    }

    @Override
    @GetMapping(value = "/details/{hotelId}")
    public ResponseEntity<Hotel> getAHotelDetails(
            @PathVariable("hotelId") Long hotelId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        Hotel hotelDetails = hotelInformationService.getASpecificHotelDetails(hotelId,startDate,endDate);

        return ResponseEntity.ok().body(hotelDetails);
    }

    @Override
    @PostMapping(value = "/add")
    public ResponseEntity<String> addHotel(
            @RequestBody final HotelDetailsRequest hotelDetailsRequest) {

        String message = hotelInformationService.addHotel(hotelDetailsRequest);

        return ResponseEntity.ok().body(message);
    }
}
