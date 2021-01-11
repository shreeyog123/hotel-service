package com.myhotel.hotelservice.contract;

import com.myhotel.hotelservice.model.request.HotelDetailsRequest;
import com.myhotel.hotelservice.service.hotelInformation.HotelInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotel")
public class HotelInformationController implements HotelInformationContract{

    private final HotelInformationService hotelInformationService;

    public HotelInformationController(HotelInformationService hotelInformationService) {
        this.hotelInformationService = hotelInformationService;
    }

    @Override
    @PostMapping(value = "/add")
    public ResponseEntity<String> addHotel(@RequestBody final HotelDetailsRequest hotelDetailsRequest) {

        String message = hotelInformationService.addHotel(hotelDetailsRequest);

        return ResponseEntity.ok().body(message);
    }
}
