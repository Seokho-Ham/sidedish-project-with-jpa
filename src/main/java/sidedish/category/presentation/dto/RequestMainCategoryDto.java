package sidedish.category.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sidedish.category.domain.MainCategory;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestMainCategoryDto {

    private String title;

    public MainCategory toEntity() {
        return new MainCategory(title);
    }
}
