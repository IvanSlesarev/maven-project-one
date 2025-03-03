package com.vanya.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cargo_request")
public class CargoRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    private String startPoint;
    private String endPoint;
    private String description;
    private Integer weight;
    private Integer volume;

    @Enumerated(EnumType.STRING)
    private CargoRequestStatus CargoRequestStatus;
}
