package com.myhotel.hotelservice.service.hotelInformation;

import com.myhotel.hotelservice.model.HotelDetails;
import com.myhotel.hotelservice.model.request.HotelDetailsRequest;

import java.time.LocalDate;

public interface HotelInformationService {

    String addHotel(final HotelDetailsRequest hotelDetailsRequest);

    HotelDetails getAvailableHotelDetails( final String city);
}
