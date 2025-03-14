package ru.meeral.task17.recipe;
import jakarta.persistence.*;
import java.util.Map;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    @MapKeyColumn(name = "ingredient")
    @Column(name = "quantity")
    private Map<String, String> ingredients;

    public Recipe() {}

    public Recipe(String name, Map<String, String> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Map<String, String> getIngredients() { return ingredients; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setIngredients(Map<String, String> ingredients) { this.ingredients = ingredients; }
}