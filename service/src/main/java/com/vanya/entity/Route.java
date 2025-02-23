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
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @AttributeOverride(name = "startPoint", column = @Column(name = "start_point"))
    private String startPoint;

    @AttributeOverride(name = "endPoint", column = @Column(name = "end_point"))
    private String endPoint;
    private Double price;

    @Enumerated(EnumType.STRING)
    private RouteStatus status;
}
