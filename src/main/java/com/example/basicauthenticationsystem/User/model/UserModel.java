package com.example.basicauthenticationsystem.User.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
@Builder
public class UserModel {

    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @Builder.Default
    private  String role="user";

}
