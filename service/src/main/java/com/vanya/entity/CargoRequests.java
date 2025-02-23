package com.vanya.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cargo_requests")
public class CargoRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Route route;
    @AttributeOverride(name = "description", column = @Column(name = "cargo_description"))
    private String description;
    private Integer weight;
    private Integer volume;

    @Enumerated(EnumType.STRING)
    private CargoRequestStatus CargoRequestStatus;
}
