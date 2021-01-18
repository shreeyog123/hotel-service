package com.myhotel.hotelservice.service.hotelInformation;

import com.myhotel.hotelservice.model.HotelDetails;
import com.myhotel.hotelservice.model.request.HotelDetailsRequest;
import com.myhotel.hotelservice.model.response.Hotel;

import java.time.LocalDate;

public interface HotelInformationService {

    String addHotel(final HotelDetailsRequest hotelDetailsRequest);

    HotelDetails getAvailableHotelDetails(final String city, final LocalDate startDate, final LocalDate endDate);

    Hotel getASpecificHotelDetails(final Long hotelId, final LocalDate startDate, final LocalDate endDate);
}
