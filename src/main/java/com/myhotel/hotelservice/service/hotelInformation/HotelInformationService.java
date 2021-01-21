package com.myhotel.hotelservice.service.hotelInformation;

import com.myhotel.hotelservice.model.response.HotelDetails;
import com.myhotel.hotelservice.model.request.HotelDetailsRequest;
import com.myhotel.hotelservice.model.request.HotelUpdateRequest;
import com.myhotel.hotelservice.model.response.Hotel;

public interface HotelInformationService {

    String ADDED_SUCCESSFULLY = "hotel has been added successfully";
    String YOU_WILL_NOT_BOOK_HOTELS_FOR_PREVIOUS_DAYS = "you will not book hotels for previous days";
    String HOTEL_NOT_FOUND = "requested hotel is not available";
    String HOTEL_ROOM_TYPE_IS_NOT_AVAILABLE = "Hotel Room type is not available in data-base, It should be QUEEN, KING, LARGE";
    String REQUEST_HAS_BEEN_UPDATED_SUCCESSFULLY = "request has been updated successfully";

    String CONFIRMED = "CONFIRMED";
    String CANCELED ="CANCELED";
    String FEEDBACK = "FEEDBACK";

    String addHotel(final HotelDetailsRequest hotelDetailsRequest);

    HotelDetails getAvailableHotelDetails(final String city);

    Hotel getASpecificHotelDetails(final Long hotelId);

    String updateHotel(final HotelUpdateRequest hotelUpdateRequest);
}
