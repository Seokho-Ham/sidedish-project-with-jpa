package sidedish.category.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sidedish.category.domain.MainCategory;
import sidedish.category.domain.MainCategoryRepository;
import sidedish.category.domain.SubCategory;
import sidedish.category.domain.SubCategoryRepository;
import sidedish.category.presentation.dto.RequestMainCategoryDto;
import sidedish.category.presentation.dto.RequestSubCategoryDto;
import sidedish.category.presentation.dto.ResponseMainCategoryDto;
import sidedish.category.presentation.dto.ResponseSubCategoryDto;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
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

        RequestMainCategoryDto requestDto = new RequestMainCategoryDto("메인 요리");
        ResponseMainCategoryDto mainCategory1 = service.addMainCategory(requestDto);

        MainCategory mainCategory = mainCategoryRepository.findById(mainCategory1.getId()).orElseThrow();

        assertThat(mainCategory.getId()).isEqualTo(mainCategory.getId());
        assertThat(mainCategory.getTitle()).isEqualTo("메인 요리");
    }

    @Test
    @DisplayName("서브 카테고리를 저장한다.")
    void saveSideCategory() {

        RequestMainCategoryDto mainCategoryDto = new RequestMainCategoryDto("메인 요리");
        ResponseMainCategoryDto mainCategory = service.addMainCategory(mainCategoryDto);

        RequestSubCategoryDto requestDto = new RequestSubCategoryDto(mainCategory.getId(), "육류");
        service.addSubCategory(requestDto);

        SubCategory subCategory = subCategoryRepository.findById(1L).orElseThrow();

        assertThat(subCategory.getTitle()).isEqualTo("육류");
        assertThat(subCategory.getMainCategory().getTitle()).isEqualTo("메인 요리");
    }

    @Test
    @DisplayName("전체 메인 카테고리를 조회한다.")
    void searchMainCategory() {
        RequestMainCategoryDto mainCategoryDto1 = new RequestMainCategoryDto("메인 요리");
        RequestMainCategoryDto mainCategoryDto2 = new RequestMainCategoryDto("국/찌개");
        RequestMainCategoryDto mainCategoryDto3 = new RequestMainCategoryDto("반찬");

        service.addMainCategory(mainCategoryDto1);
        service.addMainCategory(mainCategoryDto2);
        service.addMainCategory(mainCategoryDto3);

        List<ResponseMainCategoryDto> result = service.findAllMainCategories();

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getTitle()).isEqualTo("메인 요리");
        assertThat(result.get(1).getTitle()).isEqualTo("국/찌개");
        assertThat(result.get(2).getTitle()).isEqualTo("반찬");
    }

    @Test
    @DisplayName("메인 카테고리를 조회한 뒤, 서브 카테고리에 접근할때 데이터를 가져올 수 있어야 한다.")
    void getSubCategoriesFromMain() {
        RequestMainCategoryDto mainCategoryDto1 = new RequestMainCategoryDto("메인 요리");
        ResponseMainCategoryDto mainCategory1 = service.addMainCategory(mainCategoryDto1);

        RequestSubCategoryDto dto1 = new RequestSubCategoryDto(mainCategory1.getId(), "육류");
        RequestSubCategoryDto dto2 = new RequestSubCategoryDto(mainCategory1.getId(), "해산물");

        service.addSubCategory(dto1);
        service.addSubCategory(dto2);

        ResponseMainCategoryDto mainCategoryDto = service.findMainCategory(mainCategory1.getId());
        List<ResponseSubCategoryDto> subCategories = mainCategoryDto.getSubCategories();

        assertThat(subCategories.size()).isEqualTo(2);
        assertThat(subCategories.get(0).getTitle()).isEqualTo("육류");
        assertThat(subCategories.get(1).getTitle()).isEqualTo("해산물");
    }

}
