package org.wines.authentications.dao;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDao {

    @JsonAlias({"userName","username","UserName","USER","user"})
    private String username;
    @JsonAlias({"password","PASSWORD","passwd","pwd","Password"})
    private String password;
    @JsonAlias({"email","Email","mail","Mail"})
    private String email;
}
