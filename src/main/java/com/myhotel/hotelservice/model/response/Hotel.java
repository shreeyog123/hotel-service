package com.myhotel.hotelservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Hotel {

    private String hotelName;

    private RoomAvailable roomAvailable;
}
