package ru.meeral.task17.recipe;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeDAO recipeDAO;

    @PersistenceContext
    private EntityManager entityManager;

    public RecipeService(RecipeRepository recipeRepository, RecipeDAO recipeDAO) {
        this.recipeRepository = recipeRepository;
        this.recipeDAO = recipeDAO;
    }

    @Transactional
    public void addRecipe(String name, Map<String, String> ingredients) {
        Recipe recipe = new Recipe(name, ingredients);
        recipeRepository.save(recipe);
    }

    @Transactional
    public Optional<Recipe> findByNameContaining(String name) {
        return entityManager.createQuery("SELECT r FROM Recipe r WHERE r.name LIKE :name", Recipe.class)
                .setParameter("name", "%" + name + "%")
                .getResultList()
                .stream()
                .findFirst();
    }

    @Transactional
    public boolean deleteRecipe(String name) {
        return recipeDAO.deleteByName(name);
    }
}