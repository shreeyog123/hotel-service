package com.myhotel.hotelservice.service.hotelInformation;

import com.myhotel.hotelservice.model.entity.HotelDetailsEntity;
import com.myhotel.hotelservice.model.entity.RoomEntity;
import com.myhotel.hotelservice.model.request.HotelDetailsRequest;
import com.myhotel.hotelservice.model.request.HotelRoom;
import com.myhotel.hotelservice.repository.HotelDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HotelInformationServiceImpl implements HotelInformationService{

    private final HotelDetailsRepository hotelDetailsRepository;

    public HotelInformationServiceImpl(HotelDetailsRepository hotelDetailsRepository) {
        this.hotelDetailsRepository = hotelDetailsRepository;
    }

    @Override
    public String addHotel(final HotelDetailsRequest hotelDetailsRequest) {

        List<HotelRoom> rooms = hotelDetailsRequest.getHotelRoom();

        Set<RoomEntity> roomEntities = new HashSet<>();
        rooms.forEach(r-> {

           RoomEntity room = RoomEntity.builder()
                    .availableStartDate(r.getAvailableStartDate())
                    .availableEndDate(r.getAvailableEndDate())
                    .roomType(r.getRoomType())
                    .roomCode(r.getRoomCode())
                    .price(r.getPrice())
                    .build();

            roomEntities.add(room);
        });


        HotelDetailsEntity hotelDetailsEntity = HotelDetailsEntity.builder()
                .hotelId(hotelDetailsRequest.getHotelId())
                .hotelName(hotelDetailsRequest.getHotelName())
                .roomEntity(roomEntities)
                .build();

        hotelDetailsRepository.save(hotelDetailsEntity);


        return "added successfully";
    }
}
