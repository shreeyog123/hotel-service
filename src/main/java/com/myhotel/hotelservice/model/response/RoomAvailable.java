package com.myhotel.hotelservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomAvailable {

    private String roomType;

    private long price;

    private long availableRooms;

}
