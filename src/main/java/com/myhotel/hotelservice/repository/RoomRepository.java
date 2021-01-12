package com.myhotel.hotelservice.repository;

import com.myhotel.hotelservice.model.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<RoomEntity,String> {
}
