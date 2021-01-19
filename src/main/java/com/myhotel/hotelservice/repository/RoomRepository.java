package com.myhotel.hotelservice.repository;

import com.myhotel.hotelservice.model.entity.RoomEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface RoomRepository extends CrudRepository<RoomEntity,String> {

   /* @Transactional
    @Modifying
    @Query(value = "delete from RoomEntity where roomCode = :roomCode")
    void removeByRoomId(@Param("roomCode") String roomCode);*/
}
