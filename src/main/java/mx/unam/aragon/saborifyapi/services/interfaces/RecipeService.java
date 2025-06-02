package mx.unam.aragon.saborifyapi.services.interfaces;

import mx.unam.aragon.saborifyapi.entities.Recipe;

public interface RecipeService {
    public abstract Recipe createRecipe(Recipe recipe);
    public abstract Iterable<Recipe> getRecipes();
    public abstract Recipe getRecipe(Integer id);
    public abstract Recipe updateRecipe(Integer id, Recipe recipe);
    public abstract void deleteRecipe(Integer id);
}
