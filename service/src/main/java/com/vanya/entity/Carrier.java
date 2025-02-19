package com.vanya.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "carriers")
public class Carrier {

    @Id
    private Integer carrierId;
    private Integer userId;
    private String legalName;
    private String contactInfo;
}
