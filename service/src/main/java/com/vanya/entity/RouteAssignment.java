package com.vanya.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "route_assignments")
public class RouteAssignment {

    @Id
    private Integer assignment;
    private Integer route;
    private Integer carrier;
    private Integer vehicle;
    private Instant assigned;
}

