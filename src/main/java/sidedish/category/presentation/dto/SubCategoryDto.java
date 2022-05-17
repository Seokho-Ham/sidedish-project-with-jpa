package sidedish.category.presentation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sidedish.category.domain.MainCategory;
import sidedish.category.domain.SubCategory;

public class SubCategoryDto {

    @Getter
    @RequiredArgsConstructor
    public static class Request {

        private final Long parentId;
        private final String title;

        public SubCategory toEntity(MainCategory mainCategory) {
            return new SubCategory(title, mainCategory);
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response {

        private final Long id;
        private final String title;
    }
}
