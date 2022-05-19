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

        public SubCategory toEntity() {
            return new SubCategory(title);
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response {

        private final Long id;
        private final String title;
    }
}
