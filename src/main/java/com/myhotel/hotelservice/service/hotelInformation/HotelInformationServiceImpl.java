package com.myhotel.hotelservice.service.hotelInformation;

import com.myhotel.hotelservice.exception.HotelNotFoundException;
import com.myhotel.hotelservice.model.HotelDetails;
import com.myhotel.hotelservice.model.entity.HotelDetailsEntity;
import com.myhotel.hotelservice.model.entity.RoomEntity;
import com.myhotel.hotelservice.model.request.HotelDetailsRequest;
import com.myhotel.hotelservice.model.request.HotelRoom;
import com.myhotel.hotelservice.model.response.Hotel;
import com.myhotel.hotelservice.model.response.RoomAvailable;
import com.myhotel.hotelservice.repository.HotelDetailsRepository;
import com.myhotel.hotelservice.repository.RoomRepository;
import com.myhotel.hotelservice.utils.BusinessValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HotelInformationServiceImpl implements HotelInformationService{

    public static final String ADDED_SUCCESSFULLY = "hotel has been added successfully";
    private static final String HOTEL_NOT_FOUND = "requested hotel is not available";

    private final HotelDetailsRepository hotelDetailsRepository;
    private final RoomRepository roomRepository;
    private final BusinessValidationUtils validationUtils;

    public HotelInformationServiceImpl(HotelDetailsRepository hotelDetailsRepository, RoomRepository roomRepository, BusinessValidationUtils validationUtils) {
        this.hotelDetailsRepository = hotelDetailsRepository;
        this.roomRepository = roomRepository;
        this.validationUtils = validationUtils;
    }

    @Override
    public String addHotel(final HotelDetailsRequest hotelDetailsRequest) {

        HotelDetailsEntity hotelDetailsEntity = HotelDetailsEntity.builder()
                .hotelId(hotelDetailsRequest.getHotelId())
                .hotelName(hotelDetailsRequest.getHotelName())
                .city(hotelDetailsRequest.getCity())
                .build();

        hotelDetailsRepository.save(hotelDetailsEntity);

        List<HotelRoom> rooms = hotelDetailsRequest.getHotelRoom();

        rooms.forEach(r-> {
            RoomEntity room = RoomEntity.builder()
                    .availableStartDate(r.getAvailableStartDate())
                    .availableEndDate(r.getAvailableEndDate())
                    .roomType(r.getRoomType())
                    .roomCode(r.getRoomCode())
                    .price(r.getPrice())
                    .hotelDetailsEntity(hotelDetailsEntity)
                    .build();
            roomRepository.save(room);
        });

        return ADDED_SUCCESSFULLY;
    }

    @Override
    public HotelDetails getAvailableHotelDetails(final String city, final LocalDate startDate, final LocalDate endDate) {

        List<Hotel> hotels = new ArrayList<>();

        final List<HotelDetailsEntity> hotelList = hotelDetailsRepository.findByCity(city);

        hotelList.forEach(hotel -> {

           final List<RoomEntity> rooms = getAvailableRooms(hotel.getRoomEntity(),startDate, endDate);
           List<RoomAvailable> roomsAvailable = new ArrayList<>();
           rooms.forEach(room ->{

                RoomAvailable r = RoomAvailable.builder()
                       .roomCode(room.getRoomCode())
                       .roomType(room.getRoomType())
                       .price(room.getPrice())
                       .availableStartDate(room.getAvailableStartDate())
                       .availableEndDate(room.getAvailableEndDate())
                       .build();
               roomsAvailable.add(r);
           });
               Hotel a = Hotel.builder()
                       .hotelName(hotel.getHotelName())
                       .roomAvailable(roomsAvailable)
                       .build();
               hotels.add(a);

          });

        return HotelDetails.builder()
                .hotels(hotels)
                .build();
    }

    @Override
    public Hotel getASpecificHotelDetails(final Long hotelId, final LocalDate startDate, final LocalDate endDate) {

        List<RoomAvailable> roomsAvailable = new ArrayList<>();

        HotelDetailsEntity hotelDetails =  getHotelByHotelId(hotelId);
        log.info("get hotel details {} for hotelId {} ", hotelDetails, hotelId);

        List<RoomEntity> rooms = validationUtils.getAvailableListOfRooms(hotelDetails.getRoomEntity(),startDate,endDate);
        log.info("filter rooms by toDate and formDate available rooms {} ", rooms);

        rooms.forEach(room -> {
           RoomAvailable r=  RoomAvailable.builder()
                    .roomCode(room.getRoomCode())
                    .roomType(room.getRoomType())
                    .price(room.getPrice())
                    .availableStartDate(room.getAvailableStartDate())
                    .availableEndDate(room.getAvailableEndDate())
                    .build();
            roomsAvailable.add(r);
        });

        return Hotel.builder()
                .hotelName(hotelDetails.getHotelName())
                .roomAvailable(roomsAvailable)
                .build();
    }

    private HotelDetailsEntity getHotelByHotelId(final Long hotelId) {
        if(hotelId != null) {
            Optional<HotelDetailsEntity> guestEntity = hotelDetailsRepository.findById(hotelId);
            if (guestEntity.isPresent()) {
                return guestEntity.get();
            } else {
                throw new HotelNotFoundException(HOTEL_NOT_FOUND);
            }
        }
        throw new HotelNotFoundException(HOTEL_NOT_FOUND);
    }

    private List<RoomEntity> getAvailableRooms(final List<RoomEntity> hotelRooms,
                                       final LocalDate startDate,
                                       final LocalDate endDate) {
        return validationUtils.getAvailableListOfRooms(hotelRooms,startDate, endDate);
    }
}
