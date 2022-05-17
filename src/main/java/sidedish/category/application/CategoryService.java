package sidedish.category.application;

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

    public CategoryService(MainCategoryRepository mainCategoryRepository, SubCategoryRepository subCategoryRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    public void addMainCategory(MainCategoryDto.Request dto) {
        mainCategoryRepository.save(dto.toEntity());
    }

    public MainCategory findMainCategory(Long mainCategoryId) {
        return mainCategoryRepository.findById(mainCategoryId).orElseThrow();
    }

    public List<MainCategory> findAllMainCategories() {
        return mainCategoryRepository.findAll();
    }

    public void addSubCategory(SubCategoryDto.Request dto) {
        MainCategory mainCategory = mainCategoryRepository.findById(dto.getParentId()).orElseThrow();
        subCategoryRepository.save(dto.toEntity(mainCategory));
    }

    public SubCategory findSubCategory(Long subCategoryId) {
        return subCategoryRepository.findById(subCategoryId).orElseThrow();
    }

    public List<SubCategory> findAllSubCategories() {
        return subCategoryRepository.findAll();
    }

}
