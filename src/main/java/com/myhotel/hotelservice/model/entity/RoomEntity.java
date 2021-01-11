package com.myhotel.hotelservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROOM_TABLE")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String roomCode;

    @Column(name = "ROOM_TYPE")
    private String roomType;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "AVAILABLE_START_DATE")
    private LocalDate availableStartDate;

    @Column(name = "AVAILABLE_END_DATE")
    private LocalDate availableEndDate;

    @ManyToOne
    private HotelDetailsEntity hotelDetailsEntity;




}
