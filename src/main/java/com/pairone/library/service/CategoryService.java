package com.pairone.library.service;

import com.pairone.library.entity.Category;
import com.pairone.library.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryId(Integer id) {
        return  categoryRepository.findById(id).orElseThrow();
    }

}
