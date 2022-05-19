package sidedish.category.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sidedish.category.domain.MainCategory;
import sidedish.category.domain.MainCategoryRepository;
import sidedish.category.domain.SubCategory;
import sidedish.category.domain.SubCategoryRepository;
import sidedish.category.presentation.dto.MainCategoryDto;
import sidedish.category.presentation.dto.SubCategoryDto;

import java.util.List;

@Service
public class CategoryService {

    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    public CategoryService(MainCategoryRepository mainCategoryRepository, SubCategoryRepository subCategoryRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    public MainCategory addMainCategory(MainCategoryDto.Request dto) {
        return mainCategoryRepository.save(dto.toEntity());
    }

    public MainCategory findMainCategory(Long mainCategoryId) {
        return mainCategoryRepository.findById(mainCategoryId).orElseThrow();
    }

    public List<MainCategory> findAllMainCategories() {
        return mainCategoryRepository.findAll();
    }

    public SubCategory addSubCategory(SubCategoryDto.Request dto) {
        MainCategory mainCategory = mainCategoryRepository.findById(dto.getParentId()).orElseThrow();
        SubCategory subCategory = dto.toEntity();
        subCategory.changeMainCategory(mainCategory);
        return subCategoryRepository.save(subCategory);
    }

    public SubCategory findSubCategory(Long subCategoryId) {
        return subCategoryRepository.findById(subCategoryId).orElseThrow();
    }

    public List<SubCategory> findAllSubCategories() {
        return subCategoryRepository.findAll();
    }

}
