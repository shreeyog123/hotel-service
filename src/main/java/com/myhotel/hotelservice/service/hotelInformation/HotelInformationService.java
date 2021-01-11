package com.myhotel.hotelservice.service.hotelInformation;

import com.myhotel.hotelservice.model.request.HotelDetailsRequest;

public interface HotelInformationService {

    String addHotel(final HotelDetailsRequest hotelDetailsRequest);
}
