package com.myhotel.hotelservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelRoom {

    private String roomType;
    private long price;
    private long availableRooms;
}
