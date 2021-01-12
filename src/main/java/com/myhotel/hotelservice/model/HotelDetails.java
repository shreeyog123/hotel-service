package com.myhotel.hotelservice.model;

import com.myhotel.hotelservice.model.response.Hotel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HotelDetails {

    private List<Hotel> hotels;


}
