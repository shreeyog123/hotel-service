package com.myhotel.hotelservice.repository;

import com.myhotel.hotelservice.model.entity.HotelDetailsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface HotelDetailsRepository extends CrudRepository<HotelDetailsEntity, Integer>{

    @Query(value = "from HotelDetailsEntity where city = :city", nativeQuery = false)
    List<HotelDetailsEntity> findByCity(@Param("city") final String city);
}
