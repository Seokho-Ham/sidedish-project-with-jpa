package sidedish.category.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sidedish.category.application.CategoryService;
import sidedish.category.domain.MainCategory;
import sidedish.category.domain.SubCategory;
import sidedish.category.presentation.dto.RequestMainCategoryDto;
import sidedish.category.presentation.dto.RequestSubCategoryDto;
import sidedish.category.presentation.dto.ResponseMainCategoryDto;
import sidedish.category.presentation.dto.ResponseSubCategoryDto;
import sidedish.dto.ResponseDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/main")
    public ResponseDto<ResponseMainCategoryDto> registerMainCategory(@RequestBody RequestMainCategoryDto requestDto) {
        return new ResponseDto<>(HttpStatus.OK, categoryService.addMainCategory(requestDto));
    }

    @PostMapping("/sub")
    public ResponseDto<ResponseSubCategoryDto> registerSubCategory(@RequestBody RequestSubCategoryDto requestDto) {
        return new ResponseDto<>(HttpStatus.OK, categoryService.addSubCategory(requestDto));
    }

    @GetMapping("/main/{id}")
    public ResponseDto<ResponseMainCategoryDto> getSingleMainCategory(@PathVariable long id) {
        return new ResponseDto<>(HttpStatus.OK, categoryService.findMainCategory(id));
    }

    @GetMapping("/main")
    public ResponseDto<List<ResponseMainCategoryDto>> getMainCategories() {
        return new ResponseDto<>(HttpStatus.OK, categoryService.findAllMainCategories());
    }

    @GetMapping("/sub/{id}")
    public ResponseDto<ResponseSubCategoryDto> getSingleSubCategory(@PathVariable long id) {
        return new ResponseDto<>(HttpStatus.OK, categoryService.findSubCategory(id));
    }

    @GetMapping("/sub")
    public ResponseDto<List<ResponseSubCategoryDto>> getSubCategories() {
        return new ResponseDto<>(HttpStatus.OK, categoryService.findAllSubCategories());
    }
}
