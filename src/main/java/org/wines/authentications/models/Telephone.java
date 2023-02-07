package org.wines.authentications.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="telephone")
public class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="telephone_id")
    private Long id;

    @Column(name="mobile_number")
    private String mobileNumber;

    @Column(name="country_code")
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "email")
    private User email;
}
