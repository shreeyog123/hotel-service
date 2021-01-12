package com.myhotel.hotelservice.contract;

import com.myhotel.hotelservice.model.HotelDetails;
import com.myhotel.hotelservice.model.request.HotelDetailsRequest;
import com.myhotel.hotelservice.service.hotelInformation.HotelInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hotel")
public class HotelInformationController implements HotelInformationContract{

    private final HotelInformationService hotelInformationService;

    public HotelInformationController(HotelInformationService hotelInformationService) {
        this.hotelInformationService = hotelInformationService;
    }

    @Override
    @GetMapping(value = "/details")
    public ResponseEntity<HotelDetails> getHotelDetails(
            @RequestParam("city") String city) {

        HotelDetails hotelDetails = hotelInformationService.getAvailableHotelDetails(city);

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
