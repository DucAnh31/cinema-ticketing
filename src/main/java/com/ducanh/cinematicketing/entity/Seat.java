package com.ducanh.cinematicketing.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats",
    uniqueConstraints = @UniqueConstraint(
            name = "uk_seats_position_room",
            columnNames = {"position", "room_id"}
    ))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "position", nullable = false)
    private String position;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private ScreeningRoom room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seat_type_id", nullable = false)
    private SeatType seatType;
}
