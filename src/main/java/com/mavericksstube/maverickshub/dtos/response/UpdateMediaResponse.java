package com.mavericksstube.maverickshub.dtos.response;

import com.mavericksstube.maverickshub.models.Category;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UpdateMediaResponse {
    private Long id;
    private String url;
    private String description;
    private Category category;
}
