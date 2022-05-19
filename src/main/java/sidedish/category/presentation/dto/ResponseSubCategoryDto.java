package sidedish.category.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sidedish.category.domain.SubCategory;

@Getter
@AllArgsConstructor
public class ResponseSubCategoryDto {
    private final Long id;
    private final Long parentId;
    private final String title;

    public static ResponseSubCategoryDto of(SubCategory subCategory) {

        return new ResponseSubCategoryDto(subCategory.getId(), subCategory.getMainCategory().getId(),subCategory.getTitle());
    }
}
