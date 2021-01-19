package com.myhotel.utils;

import com.myhotel.hotelservice.model.entity.RoomEntity;
import com.myhotel.hotelservice.utils.BusinessValidationUtils;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BusinessValidationUtilsTest {

    BusinessValidationUtils validationUtils = new BusinessValidationUtils();

    @Test
    public void filterIfRoomIsNotAvailableForFilterDates(){

        LocalDate startDate = LocalDate.now().plusDays(10);
        LocalDate endDate = LocalDate.now().plusDays(10);

        List<RoomEntity> listOfRooms = validationUtils.getAvailableListOfRooms(getRooms(), startDate, endDate);

        Assert.assertEquals(0, listOfRooms.size());

    }

    @Test
    public void returnListIfAnyRoomIsAvailableForFilterDates(){

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(5);

        List<RoomEntity> listOfRooms = validationUtils.getAvailableListOfRooms(getRooms(), startDate, endDate);

        Assert.assertEquals(2, listOfRooms.size());

    }

    @Test
    public void returnListIfAnyRoomIsAvailableForFilterDates_Test2(){

        LocalDate startDate = LocalDate.now().plusMonths(2);
        LocalDate endDate = LocalDate.now().plusMonths(2).plusDays(5);

        List<RoomEntity> listOfRooms = validationUtils.getAvailableListOfRooms(getRooms(), startDate, endDate);

        Assert.assertEquals(1, listOfRooms.size());

    }

    @Test
    public void returnListsIfAnyRoomIsAvailableForFilterDates_dateRangeIsInside(){

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(5);

        List<RoomEntity> listOfRooms = validationUtils.getAvailableListOfRooms(getRooms(), startDate, endDate);

        Assert.assertEquals(2, listOfRooms.size());

    }

    @Test
    public void returnListsIfAnyRoomIsAvailableForFilterDates_ifRequestedDateRangeIsInside(){

        LocalDate startDate = LocalDate.now().plusMonths(2).plusDays(2);
        LocalDate endDate = LocalDate.now().plusMonths(2).plusDays(5);

        List<RoomEntity> listOfRooms = validationUtils.getAvailableListOfRooms(getRooms(), startDate, endDate);

        Assert.assertEquals(1, listOfRooms.size());

    }

    @Test
    public void startDateMustBeToday(){

        LocalDate startDate = LocalDate.now();

        boolean flag = validationUtils.startDateMustBeTodayOrAfter(startDate);

        Assert.assertEquals(true, flag);

    }

    @Test
    public void startDateMustBeAfterToday(){

        LocalDate startDate = LocalDate.now().plusDays(10);

        boolean flag = validationUtils.startDateMustBeTodayOrAfter(startDate);

        Assert.assertEquals(true, flag);

    }

    @Test
    public void startDateMustNotBeBeforeToday(){

        LocalDate startDate = LocalDate.now().minusDays(10);

        boolean flag = validationUtils.startDateMustBeTodayOrAfter(startDate);

        Assert.assertEquals(false, flag);

    }

    private List<RoomEntity> getRooms(){

        /*List<RoomEntity> rooms = new ArrayList<>();

        RoomEntity room1  = RoomEntity.builder()
                .roomCode("Q101")
                .roomType("Queen")
                .availableStartDate(LocalDate.now())
                .availableEndDate(LocalDate.now().plusDays(5)).build();

        RoomEntity room2  = RoomEntity.builder()
                .roomCode("Q102")
                .roomType("Queen")
                .availableStartDate(LocalDate.now().plusMonths(2))
                .availableEndDate(LocalDate.now().plusMonths(2).plusDays(5)).build();

        RoomEntity room3  = RoomEntity.builder()
                .roomCode("K102")
                .roomType("King")
                .availableStartDate(LocalDate.now().plusDays(1))
                .availableEndDate(LocalDate.now().plusDays(4)).build();

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        return rooms;*/

        return null;

    }
}
