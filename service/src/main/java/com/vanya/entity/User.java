package com.vanya.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String info;

    @AttributeOverride(name = "legalName", column = @Column(name = "legal_name"))
    private String legalName;
}
