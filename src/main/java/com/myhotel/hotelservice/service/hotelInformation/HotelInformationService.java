package com.myhotel.hotelservice.service.hotelInformation;

import com.myhotel.hotelservice.model.HotelDetails;
import com.myhotel.hotelservice.model.request.HotelDetailsRequest;
import com.myhotel.hotelservice.model.request.HotelUpdateRequest;
import com.myhotel.hotelservice.model.response.Hotel;

public interface HotelInformationService {

    String addHotel(final HotelDetailsRequest hotelDetailsRequest);

    HotelDetails getAvailableHotelDetails(final String city);

    Hotel getASpecificHotelDetails(final Long hotelId);

    String updateHotel(final HotelUpdateRequest hotelUpdateRequest);
}
