package com.myhotel.hotelservice.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HOTEL_DETAILS_TABLE")
public class HotelDetailsEntity {

    @Id
    private Integer hotelId;

    @Column(name = "HOTEL_NAME")
    private String hotelName;

    @Column(name = "CITY")
    private String city;

    @ToString.Exclude
    @OneToMany(mappedBy = "hotelDetailsEntity", fetch = FetchType.EAGER)
    private List<RoomEntity> roomEntity = new ArrayList<>();
}
