package com.glamourjewellery.glamour_jewellery.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPojo {

    private Long categoryId;

    private String description;

    private MultipartFile image;

}
