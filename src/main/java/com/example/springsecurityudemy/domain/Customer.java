package com.example.springsecurityudemy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    /*
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY): -  useful when serializing. you dont to send the hashed
    password back to the UI.
    In Spring Boot and Jackson, @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) is an annotation
    configuration that specifies that the associated property should only be used for serialization
    (writing JSON), and it should be ignored during deserialization (reading JSON).

    This is particularly useful in scenarios where you want to include a property in the
    JSON response sent to the client, but you don't want the client to send that property
    back when sending JSON data to the server.
    * */

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String role;


    /*
    * Using @JsonProperty(access = JsonProperty.Access.READ_ONLY) on a field in a Java class
    * indicates that the property should only be used for deserialization (reading JSON),
    * and it should be ignored during serialization (writing JSON).

    This annotation is useful in scenarios where you want to include a property in the JSON
    * request sent to the server, but you don't want the property to be included in the
    * JSON response sent back to the client.
    * */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Authorities> authoritiesSet;


}
