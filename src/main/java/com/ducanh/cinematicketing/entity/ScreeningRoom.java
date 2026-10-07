package com.ducanh.cinematicketing.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "screening_rooms",
    uniqueConstraints = @UniqueConstraint(
            name = "uk_screening_rooms_cinema_name",
            columnNames = {"cinema_id", "name"}
    ))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreeningRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "seat_count", nullable = false)
    private Integer seatCount;

    @Column(name = "features")
    private String features;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;
}
