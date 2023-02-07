package org.wines.authentications.dao;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TelephoneDao {

    @JsonAlias({"MobileNumber","mobilenumber","mobile","number"})
    private String mobileNumber;
    @JsonAlias({"countryCode","CountryCode","countrycode"})
    private String countryCode;
}
