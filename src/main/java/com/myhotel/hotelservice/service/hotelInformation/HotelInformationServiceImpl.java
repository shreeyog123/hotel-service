package com.myhotel.hotelservice.service.hotelInformation;

import com.myhotel.hotelservice.model.HotelDetails;
import com.myhotel.hotelservice.model.entity.HotelDetailsEntity;
import com.myhotel.hotelservice.model.entity.RoomEntity;
import com.myhotel.hotelservice.model.request.HotelDetailsRequest;
import com.myhotel.hotelservice.model.request.HotelRoom;
import com.myhotel.hotelservice.model.response.Hotel;
import com.myhotel.hotelservice.model.response.RoomAvailable;
import com.myhotel.hotelservice.repository.HotelDetailsRepository;
import com.myhotel.hotelservice.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class HotelInformationServiceImpl implements HotelInformationService{

    public static final String ADDED_SUCCESSFULLY = "hotel has been added successfully";
    private static final String AVAILABLE_STATUS ="AVAILABLE";

    private final HotelDetailsRepository hotelDetailsRepository;
    private final RoomRepository roomRepository;

    public HotelInformationServiceImpl(HotelDetailsRepository hotelDetailsRepository, RoomRepository roomRepository) {
        this.hotelDetailsRepository = hotelDetailsRepository;
        this.roomRepository = roomRepository;
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
                    .status(AVAILABLE_STATUS)
                    .hotelDetailsEntity(hotelDetailsEntity)
                    .build();
            roomRepository.save(room);
        });

        return ADDED_SUCCESSFULLY;
    }

    @Override
    public HotelDetails getAvailableHotelDetails(final String city) {

        List<Hotel> hotels = new ArrayList<>();

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();

        List<HotelDetailsEntity> list = hotelDetailsRepository.findByCity(city);


        list.forEach( hotel -> {

            RoomAvailable roomAvailable = RoomAvailable.builder()

                    .large(getAvailableRoomCount(hotel, "Large", startDate, endDate))
                    .king(getAvailableRoomCount(hotel, "King", startDate, endDate))
                    .queen(getAvailableRoomCount(hotel, "Queen", startDate, endDate))
                    .build();
            Hotel a = Hotel.builder()
                    .hotelName(hotel.getHotelName())
                    .roomAvailable(roomAvailable)
                    .build();
            hotels.add(a);
                }
        );

        return HotelDetails.builder()
                .hotels(hotels)
                .build();
    }

    private long getAvailableRoomCount(final HotelDetailsEntity hotel,
                                       final String roomType,
                                       final LocalDate startDate,
                                       final LocalDate endDate) {
        return hotel.getRoomEntity().stream()

                .filter(s -> s.getAvailableStartDate().isAfter(startDate)
                        || s.getAvailableEndDate().isBefore(endDate)
                        || s.getAvailableStartDate().isBefore(startDate)
                        || s.getAvailableEndDate().isAfter(endDate)
                )
                .filter(l -> l.getRoomType().equals(roomType)).count();
    }
}
