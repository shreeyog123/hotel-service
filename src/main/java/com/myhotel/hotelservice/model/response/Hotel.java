package com.myhotel.hotelservice.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Hotel {

    private String hotelName;

    private List<RoomAvailable> roomAvailable;
}
