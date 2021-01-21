package com.myhotel.hotelservice.service.hotelInformation;

import com.myhotel.hotelservice.exception.HotelNotFoundException;
import com.myhotel.hotelservice.model.response.HotelDetails;
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
public class HotelInformationServiceImpl implements HotelInformationService {

    private final HotelDetailsRepository hotelDetailsRepository;
    private final RoomRepository roomRepository;
    private final BusinessValidationUtils validationUtils;

    public HotelInformationServiceImpl(HotelDetailsRepository hotelDetailsRepository, RoomRepository roomRepository, BusinessValidationUtils validationUtils) {
        this.hotelDetailsRepository = hotelDetailsRepository;
        this.roomRepository = roomRepository;
        this.validationUtils = validationUtils;
    }

    @Override
    public HotelDetails getAvailableHotelDetails(final String city) {

        final List<HotelDetailsEntity> hotelList = hotelDetailsRepository.findByCity(city);
        log.info("successfully get hotelList {}  ", hotelList);

        final List<Hotel> hotels = mapHotelRequest(hotelList);
        log.info("mapped hotels response {}", hotels);

        return HotelDetails.builder()
                .hotels(hotels)
                .build();
    }

    @Override
    public Hotel getASpecificHotelDetails(final Long hotelId) {

        List<RoomAvailable> roomsAvailable = new ArrayList<>();

        final HotelDetailsEntity hotelDetails = getHotelByHotelId(hotelId);
        log.info("get hotel details {} for hotelId {} ", hotelDetails, hotelId);

        List<RoomEntity> rooms = hotelDetails.getRoomEntity();
        log.info("available rooms rooms {} ", rooms);

        rooms.forEach(room -> {
            RoomAvailable r = RoomAvailable.builder()
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

        if (updateRequest.getStatus().equals("CONFIRMED")) {
            roomEntity.setRooms(roomEntity.getRooms() - 1);
            updateRoomForHotel(roomEntity);
        } else if (updateRequest.getStatus().equals("FEEDBACK") || updateRequest.getStatus().equals("CANCELED")) {
            roomEntity.setRooms(roomEntity.getRooms() + 1);
            updateRoomForHotel(roomEntity);
        }

        return REQUEST_HAS_BEEN_UPDATED_SUCCESSFULLY;
    }

    @Override
    public String addHotel(final HotelDetailsRequest hotelDetailsRequest) {

        final HotelDetailsEntity hotelDetailsEntity = HotelDetailsEntity.builder()
                .hotelId(hotelDetailsRequest.getHotelId())
                .hotelName(hotelDetailsRequest.getHotelName())
                .city(hotelDetailsRequest.getCity())
                .build();

        hotelDetailsRepository.save(hotelDetailsEntity);

        List<HotelRoom> rooms = hotelDetailsRequest.getHotelRoom();

        rooms.forEach(r -> {
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

    private List<Hotel> mapHotelRequest(final List<HotelDetailsEntity> hotelList) {
        List<Hotel> hotels = new ArrayList<>();
        hotelList.forEach(hotel -> {
            final List<RoomEntity> rooms = hotel.getRoomEntity();
            List<RoomAvailable> roomsAvailable = new ArrayList<>();
            rooms.forEach(room -> {
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
        return hotels;
    }

    private void updateRoomForHotel(final RoomEntity roomEntity) {
        RoomEntity savedEntity = roomRepository.save(roomEntity);
        log.info("saved room entity {} ", savedEntity);
    }

    private RoomEntity getRoomEntity(final HotelUpdateRequest updateRequest, final HotelDetailsEntity hotelEntity) {

        Optional<RoomEntity> roomEntity = hotelEntity.getRoomEntity().stream().filter(r -> r.getRoomType().equals(updateRequest.getRoomType())).findFirst();

        if (roomEntity.isPresent()) {
            return roomEntity.get();
        } else {
            throw new HotelNotFoundException(HOTEL_ROOM_TYPE_IS_NOT_AVAILABLE);
        }
    }


    private HotelDetailsEntity getHotelByHotelId(final Long hotelId) {
        if (hotelId != null) {
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
