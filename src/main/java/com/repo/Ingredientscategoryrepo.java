package com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.IngredientsCategory;

@Repository
public interface Ingredientscategoryrepo extends JpaRepository<IngredientsCategory, Long> {

	List<IngredientsCategory> findByRestuarntId(Long id);
}
