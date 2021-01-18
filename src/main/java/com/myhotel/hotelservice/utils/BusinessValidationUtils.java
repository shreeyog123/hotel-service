package com.myhotel.hotelservice.utils;


import com.myhotel.hotelservice.model.entity.RoomEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BusinessValidationUtils {

    public  List<RoomEntity> getAvailableListOfRooms(List<RoomEntity> rooms, LocalDate startDate, LocalDate endDate) {

        return rooms.stream().filter(r -> validateDates(r,startDate,endDate)).collect(Collectors.toList());
    }

    private boolean validateDates(RoomEntity r, LocalDate startDate, LocalDate endDate) {

        final boolean startDateFlag = r.getAvailableStartDate().isEqual(startDate) ||  r.getAvailableStartDate().isAfter(startDate) ;
        final boolean endDateFlag = r.getAvailableEndDate().isEqual(endDate) ||  r.getAvailableEndDate().isBefore(endDate) ;

        return startDateFlag && endDateFlag;
    }


}
