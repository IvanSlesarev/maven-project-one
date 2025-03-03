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
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;
    private String vehicleNumber;
    private String trailerNumber;

    @AttributeOverride(name = "driverName", column = @Column(name = "driver_name"))
    private String driverName;

    @AttributeOverride(name = "driverContactInfo", column = @Column(name = "driver_contact_info"))
    private String info;
}

