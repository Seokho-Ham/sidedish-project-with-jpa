package sidedish.category.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sidedish.category.domain.SubCategory;

@Getter
@AllArgsConstructor
public class RequestSubCategoryDto {

    private Long parentId;
    private String title;

    public SubCategory toEntity() {
        return new SubCategory(title);
    }
}
