package com.myhotel.hotelservice.model.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RoomAvailable {

    private String roomCode;

    private String roomType;

    private Integer price;

    private LocalDate availableStartDate;

    private LocalDate availableEndDate;


}
