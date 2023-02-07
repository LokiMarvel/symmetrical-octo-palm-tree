package org.wines.authentications.models;

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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="addres_id")
    private Long addressId;

    @Column(name="country")
    private String country;
    @Column(name="state")
    private String state;
    @Column(name="distric")
    private String distric;
    @Column(name="village")
    private String village;
    @Column(name="street")
    private String street;
    @Column(name="landmarks")
    private String landmarks;
    @Column(name="zip_code")
    private String zipCode;

    @ManyToOne
    @JoinColumn(name="email")
    private User email;
}
