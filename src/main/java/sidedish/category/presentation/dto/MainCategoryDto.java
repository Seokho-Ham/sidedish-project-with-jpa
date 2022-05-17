package sidedish.category.presentation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sidedish.category.domain.MainCategory;

import java.util.List;

public class MainCategoryDto {

    @Getter
    @RequiredArgsConstructor
    public static class Request {

        private final String title;

        public MainCategory toEntity() {
            return new MainCategory(title);
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response {

        private final Long id;
        private final String title;
        private final List<SubCategoryDto.Response> subCategories;
    }
}
