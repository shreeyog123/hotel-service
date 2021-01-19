package com.myhotel.hotelservice.service.hotelInformation;

import com.myhotel.hotelservice.exception.HotelNotFoundException;
import com.myhotel.hotelservice.model.HotelDetails;
import com.myhotel.hotelservice.model.entity.HotelDetailsEntity;
import com.myhotel.hotelservice.model.entity.RoomEntity;
import com.myhotel.hotelservice.model.request.HotelDetailsRequest;
import com.myhotel.hotelservice.model.request.HotelRoom;
import com.myhotel.hotelservice.model.request.HotelUpdateRequest;
import com.myhotel.hotelservice.model.response.Hotel;
import com.myhotel.hotelservice.model.response.RoomAvailable;
import com.myhotel.hotelservice.repository.HotelDetailsRepository;
import com.myhotel.hotelservice.repository.RoomRepository;
import com.myhotel.hotelservice.utils.BusinessValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HotelInformationServiceImpl implements HotelInformationService{

    public static final String ADDED_SUCCESSFULLY = "hotel has been added successfully";
    public static final String YOU_WILL_NOT_BOOK_HOTELS_FOR_PREVIOUS_DAYS = "you will not book hotels for previous days";
    private static final String HOTEL_NOT_FOUND = "requested hotel is not available";
    public static final String HOTEL_ROOM_TYPE_IS_NOT_AVAILABLE = "Hotel Room type is not available in data-base, It should be QUEEN, KING, LARGE";

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
                    .roomType(r.getRoomType())
                    .price(r.getPrice())
                    .rooms(r.getAvailableRooms())
                    .hotelDetailsEntity(hotelDetailsEntity)
                    .build();
            roomRepository.save(room);
        });

        return ADDED_SUCCESSFULLY;
    }

    @Override
    public HotelDetails getAvailableHotelDetails(final String city) {

        List<Hotel> hotels = new ArrayList<>();

        final List<HotelDetailsEntity> hotelList = hotelDetailsRepository.findByCity(city);

        hotelList.forEach(hotel -> {
           final List<RoomEntity> rooms = hotel.getRoomEntity();
           List<RoomAvailable> roomsAvailable = new ArrayList<>();
           rooms.forEach(room ->{
                RoomAvailable r = RoomAvailable.builder()
                       .roomType(room.getRoomType())
                       .price(room.getPrice())
                       .availableRooms(room.getRooms())
                       .build();
               roomsAvailable.add(r);
           });
               Hotel a = Hotel.builder()
                       .hotelId(hotel.getHotelId())
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
    public Hotel getASpecificHotelDetails(final Long hotelId) {

        List<RoomAvailable> roomsAvailable = new ArrayList<>();

        HotelDetailsEntity hotelDetails =  getHotelByHotelId(hotelId);
        log.info("get hotel details {} for hotelId {} ", hotelDetails, hotelId);

        List<RoomEntity> rooms = hotelDetails.getRoomEntity();
        log.info("available rooms rooms {} ", rooms);

        rooms.forEach(room -> {
           RoomAvailable r=  RoomAvailable.builder()
                    .roomType(room.getRoomType())
                    .price(room.getPrice())
                    .availableRooms(room.getRooms())
                    .build();
            roomsAvailable.add(r);
        });

        return Hotel.builder()
                .hotelId(hotelDetails.getHotelId())
                .hotelName(hotelDetails.getHotelName())
                .roomAvailable(roomsAvailable)
                .build();
    }

    @Override
    public String updateHotel(final HotelUpdateRequest updateRequest) {

       HotelDetailsEntity hotelEntity = getHotelByHotelId(updateRequest.getHotelId());
       log.info("Hotel Entity {} ", hotelEntity.getRoomEntity());

       RoomEntity roomEntity = getRoomEntity(updateRequest, hotelEntity);
       log.info("roomEntity {} ", roomEntity);

       if(updateRequest.getStatus().equals("CONFIRMED")){
           roomEntity.setRooms(roomEntity.getRooms()-1);
           updateRoomForHotel(roomEntity);
       }
       else if(updateRequest.getStatus().equals("FEEDBACK") || updateRequest.getStatus().equals("BOOKING_CANCELLED")){
           roomEntity.setRooms(roomEntity.getRooms()+1);
           updateRoomForHotel(roomEntity);
       }

       return "successfully";
    }

    private void updateRoomForHotel(RoomEntity roomEntity) {
        RoomEntity savedEntity = roomRepository.save(roomEntity);
        log.info("saved room entity {} ", savedEntity);
    }

    private RoomEntity getRoomEntity(HotelUpdateRequest updateRequest, HotelDetailsEntity hotelEntity) {

        Optional<RoomEntity> roomEntity = hotelEntity.getRoomEntity().stream().filter(r -> r.getRoomType().equals(updateRequest.getRoomType())).findFirst();

        if (roomEntity.isPresent()) {
            return roomEntity.get();
        } else {
            throw new HotelNotFoundException(HOTEL_ROOM_TYPE_IS_NOT_AVAILABLE);
        }
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
}
