package com.utkarsh.blogappapis.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailErrorResponse {
    private String email;
    private Boolean status;
}
