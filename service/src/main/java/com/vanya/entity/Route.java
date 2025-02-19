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
@Table(name = "routes")
public class Route {

    @Id
    private Integer id;
    private Integer customerId;
    private String startPoint;
    private String endPoint;
    private Integer price;
    private String status;
    public Instant createdAt;
}
