package com.myhotel.hotelservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomAvailable {

    private Long large;
    private Long queen;
    private Long king;
}
