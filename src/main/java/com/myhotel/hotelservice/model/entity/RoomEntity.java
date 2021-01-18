package com.myhotel.hotelservice.model.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROOM_TABLE")
public class RoomEntity {

    @Id
    private String roomCode;

    @Column(name = "ROOM_TYPE")
    private String roomType;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "AVAILABLE_START_DATE")
    private LocalDate availableStartDate;

    @Column(name = "AVAILABLE_END_DATE")
    private LocalDate availableEndDate;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotelId", nullable = false)
    private HotelDetailsEntity hotelDetailsEntity;


}
