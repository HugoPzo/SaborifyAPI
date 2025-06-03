package mx.unam.aragon.saborifyapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no debe exceder los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String title;

    @Size(max = 500, message = "La descripción no debe exceder los 500 caracteres")
    @Column(nullable = true)
    private String description;

    @Column(nullable = true, insertable = false, columnDefinition = "VARCHAR(255) DEFAULT 'https://static.thenounproject.com/png/1077596-200.png'")
    private String image;

    @NotBlank(message = "La categoría es obligatoria")
    @Column(nullable = false)
    private String category;

    @Min(value = 1, message = "La dificultad mínima es 1")
    @Max(value = 5, message = "La dificultad máxima es 5")
    @Column(nullable = true)
    private Integer difficulty;

    @Min(value = 1, message = "Debe haber al menos una porción")
    @Column(nullable = true)
    private Integer servings;

    @Min(value = 0, message = "El tiempo de cocción no puede ser negativo")
    @Column(name = "cooking_time")
    private Integer cookingTime;

    @Min(value = 0, message = "El tiempo de preparación no puede ser negativo")
    @Column(name = "preparation_time")
    private Integer preparationTime;

    @NotEmpty(message = "Debe incluir al menos un ingrediente")
    @ElementCollection
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "ingredient")
    private List<String> ingredients;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public Recipe() {}

    public Recipe(int id, String title, String description, String image, String category, Integer difficulty,
                  Integer servings, Integer cookingTime, Integer preparationTime, List<String> ingredients,
                  Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.category = category;
        this.difficulty = difficulty;
        this.servings = servings;
        this.cookingTime = cookingTime;
        this.preparationTime = preparationTime;
        this.ingredients = ingredients;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public Integer getDifficulty() { return difficulty; }

    public void setDifficulty(Integer difficulty) { this.difficulty = difficulty; }

    public Integer getServings() { return servings; }

    public void setServings(Integer servings) { this.servings = servings; }

    public Integer getCookingTime() { return cookingTime; }

    public void setCookingTime(Integer cookingTime) { this.cookingTime = cookingTime; }

    public Integer getPreparationTime() { return preparationTime; }

    public void setPreparationTime(Integer preparationTime) { this.preparationTime = preparationTime; }

    public List<String> getIngredients() { return ingredients; }

    public void setIngredients(List<String> ingredients) { this.ingredients = ingredients; }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                ", difficulty=" + difficulty +
                ", servings=" + servings +
                ", cookingTime=" + cookingTime +
                ", preparationTime=" + preparationTime +
                ", ingredients=" + ingredients +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id &&
                Objects.equals(title, recipe.title) &&
                Objects.equals(description, recipe.description) &&
                Objects.equals(image, recipe.image) &&
                Objects.equals(category, recipe.category) &&
                Objects.equals(difficulty, recipe.difficulty) &&
                Objects.equals(servings, recipe.servings) &&
                Objects.equals(cookingTime, recipe.cookingTime) &&
                Objects.equals(preparationTime, recipe.preparationTime) &&
                Objects.equals(ingredients, recipe.ingredients) &&
                Objects.equals(createdAt, recipe.createdAt) &&
                Objects.equals(updatedAt, recipe.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, image, category, difficulty, servings,
                cookingTime, preparationTime, ingredients, createdAt, updatedAt);
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    @Transient
    public Integer getTotalTime() {
        if (preparationTime == null && cookingTime == null) return null;
        int prep = preparationTime != null ? preparationTime : 0;
        int cook = cookingTime != null ? cookingTime : 0;
        return prep + cook;
    }
}
