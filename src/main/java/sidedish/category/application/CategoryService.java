package sidedish.category.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sidedish.category.domain.MainCategory;
import sidedish.category.domain.MainCategoryRepository;
import sidedish.category.domain.SubCategory;
import sidedish.category.domain.SubCategoryRepository;
import sidedish.category.presentation.dto.RequestMainCategoryDto;
import sidedish.category.presentation.dto.RequestSubCategoryDto;
import sidedish.category.presentation.dto.ResponseMainCategoryDto;
import sidedish.category.presentation.dto.ResponseSubCategoryDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public CategoryService(MainCategoryRepository mainCategoryRepository, SubCategoryRepository subCategoryRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    public ResponseMainCategoryDto addMainCategory(RequestMainCategoryDto dto) {
        return ResponseMainCategoryDto.of(mainCategoryRepository.save(dto.toEntity()));
    }

    public ResponseMainCategoryDto findMainCategory(Long mainCategoryId) {
        return ResponseMainCategoryDto.of(mainCategoryRepository.findById(mainCategoryId).orElseThrow());
    }

    public List<ResponseMainCategoryDto> findAllMainCategories() {
        return mainCategoryRepository.findAll().stream()
                .map(ResponseMainCategoryDto::of)
                .collect(Collectors.toList());
    }

    public ResponseSubCategoryDto addSubCategory(RequestSubCategoryDto dto) {
        MainCategory mainCategory = mainCategoryRepository.findById(dto.getParentId()).orElseThrow();
        SubCategory subCategory = dto.toEntity();

        subCategory.addMainCategory(mainCategory);

        return ResponseSubCategoryDto.of(subCategoryRepository.save(subCategory));
    }

    public ResponseSubCategoryDto findSubCategory(Long subCategoryId) {
        return ResponseSubCategoryDto.of(subCategoryRepository.findById(subCategoryId).orElseThrow());
    }

    public List<ResponseSubCategoryDto> findAllSubCategories() {
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        return subCategories.stream()
                .map(ResponseSubCategoryDto::of)
                .collect(Collectors.toList());
    }

}
