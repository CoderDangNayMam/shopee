package com.vti.shoppee.config.Annotation;

import com.vti.shoppee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductIDExistsValidator implements ConstraintValidator<ProductIDExists, Integer> {

    @Autowired
    ProductRepository productRepository;

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return productRepository.existsById(integer);
    }
}
