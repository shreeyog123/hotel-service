package com.myhotel.hotelservice.model.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HotelDetailsRequest {

    private Integer hotelId;
    private String hotelName;

    private List<HotelRoom> hotelRoom;



}
