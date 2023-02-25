package com.utkarsh.blogappapis.payloads;

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
public class CommentDto {

    private Long id;

    private String commentContent;

}
