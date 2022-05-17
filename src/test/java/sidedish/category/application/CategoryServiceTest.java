package sidedish.category.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sidedish.category.domain.MainCategory;
import sidedish.category.domain.MainCategoryRepository;
import sidedish.category.domain.SubCategory;
import sidedish.category.domain.SubCategoryRepository;
import sidedish.category.presentation.dto.MainCategoryDto;
import sidedish.category.presentation.dto.SubCategoryDto;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService service;
    @Autowired
    MainCategoryRepository mainCategoryRepository;
    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Test
    @DisplayName("메인 카테고리를 저장한다.")
    void saveMainCategory() {

        MainCategoryDto.Request requestDto = new MainCategoryDto.Request("메인 요리");
        service.addMainCategory(requestDto);

        MainCategory mainCategory = mainCategoryRepository.findById(1L).orElseThrow();

        assertThat(mainCategory.getId()).isEqualTo(1L);
        assertThat(mainCategory.getTitle()).isEqualTo("메인 요리");
    }

    @Test
    @DisplayName("서브 카테고리를 저장한다.")
    void saveSideCategory() {

        MainCategoryDto.Request mainCategoryDto = new MainCategoryDto.Request("메인 요리");
        service.addMainCategory(mainCategoryDto);

        SubCategoryDto.Request requestDto = new SubCategoryDto.Request(1L, "육류");
        service.addSubCategory(requestDto);

        SubCategory subCategory = subCategoryRepository.findById(1L).orElseThrow();

        assertThat(subCategory.getTitle()).isEqualTo("육류");
        assertThat(subCategory.getMainCategory().getTitle()).isEqualTo("메인 요리");
    }
}
