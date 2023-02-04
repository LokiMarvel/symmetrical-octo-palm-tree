package org.wines.authentications.dao;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDao {
    @JsonAlias({"Country","country"})
    private String country;
    @JsonAlias({"state","State"})
    private String state;
    @JsonAlias({"Distric","distric"})
    private String distirc;
    @JsonAlias({"Villae","village"})
    private String village;
    @JsonAlias({"street","Street"})
    private String street;
    @JsonAlias({"landMarks","landmarks","LandMarks","landmarks"})
    private String landmarks;
    @JsonAlias({"zip_code","zipcode","zipCode","ZipCode","zipCode","zip-code","Zip-Code"})
    private String zipCode;
}
