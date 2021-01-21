package com.myhotel.hotelservice.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HotelDetails {

    private List<Hotel> hotels;


}
