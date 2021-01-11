package com.myhotel.hotelservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HOTEL_DETAILS_TABLE")
public class HotelDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hotelId;

    @Column(name = "HOTEL_NAME")
    private String hotelName;

    @OneToMany(cascade=CascadeType.ALL)
    private Set<RoomEntity> roomEntity;
}
