package com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.IngredientsItem;

@Repository
public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long>{

	List<IngredientsItem> findByRstuarantId(Long id);
}
