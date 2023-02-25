package com.utkarsh.blogappapis.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO Class made for Category Entity
 * This de-couple entity(data layer) and controller(buisness layer)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

        private Long categoryId;

        @NotBlank
        @Size(min = 4,message = "Min size of title is 4")
        private String categoryTitle;

        @NotBlank
        @Size(min = 10,message = "Min size of description is 10")
        private String categoryDescription;

}
