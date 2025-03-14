package ru.meeral.task17.recipe;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class RecipeDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Recipe recipe) {
        entityManager.persist(recipe);
    }

    public Optional<Recipe> findByName(String name) {
        TypedQuery<Recipe> query = entityManager.createQuery(
                "SELECT r FROM Recipe r WHERE LOWER(r.name) LIKE LOWER(:name)", Recipe.class);
        query.setParameter("name", "%" + name + "%");
        List<Recipe> results = query.getResultList();
        return results.stream().findFirst();
    }

    @Transactional
    public boolean deleteByName(String name) {
        Query query = entityManager.createQuery("DELETE FROM Recipe r WHERE r.name = :name");
        query.setParameter("name", name);
        return query.executeUpdate() > 0;
    }
}
