package com.myhotel.hotelservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelDetailsRequest {

    private Long hotelId;
    private String hotelName;
    private String city;

    private List<HotelRoom> hotelRoom;


}
