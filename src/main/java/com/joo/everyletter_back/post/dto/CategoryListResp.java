package com.joo.everyletter_back.post.dto;

import com.joo.everyletter_back.common.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@AllArgsConstructor
@ToString
@Getter
public class CategoryListResp {
    List<Category> categoryList;
}
