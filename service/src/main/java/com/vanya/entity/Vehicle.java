package com.vanya.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    private Integer id;
    private Integer carrierId;
    private String vehicleNumber;
    private String trailerNumber;
    private String driverName;
    private String driverContactInfo;
}

