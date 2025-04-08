package com.vanya.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NamedEntityGraph (
        name = "UserWithRole",
        attributeNodes = {
                @NamedAttributeNode("role"),
                @NamedAttributeNode("info")
        }
)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@EqualsAndHashCode(of = "username")
public class User implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String info;
    private String legalName;
}
