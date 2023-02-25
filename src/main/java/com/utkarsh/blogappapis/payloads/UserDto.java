package com.utkarsh.blogappapis.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * POJO Class made for User Entity
 * This de-couple entity(data layer) and controller(buisness layer)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotNull(message = "Name should not be empty")
    @Size(min = 4,message = "Username Must be Atleast 4 Character")
    private String name;

    @NotNull(message = "Email should not be empty")
    @Email( regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",message = "Your Given Email is not Valid")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 3,max = 10,message = "Password must be minimum of 3 char and maximum of 10 char")
    private String password;

    @NotBlank(message = "About should not be blank")
    private String about;

    private List<CommentDto> comments = new ArrayList<>();

    private Set<RoleDto> roles = new HashSet<>();

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
