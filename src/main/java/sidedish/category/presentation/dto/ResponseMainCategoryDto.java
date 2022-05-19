package sidedish.category.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sidedish.category.domain.MainCategory;
import sidedish.category.domain.SubCategory;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMainCategoryDto {

    private Long id;
    private String title;
    private List<ResponseSubCategoryDto> subCategories;

    public static ResponseMainCategoryDto of(MainCategory mainCategory) {
        List<ResponseSubCategoryDto> subCategories = mainCategory.getSubCategories()
                .stream()
                .map(ResponseSubCategoryDto::of)
                .collect(Collectors.toList());

        return new ResponseMainCategoryDto(mainCategory.getId(), mainCategory.getTitle(), subCategories);
    }
}
