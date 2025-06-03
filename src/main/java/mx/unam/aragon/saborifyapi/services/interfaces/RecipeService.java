package mx.unam.aragon.saborifyapi.services.interfaces;

import mx.unam.aragon.saborifyapi.entities.Recipe;

public interface RecipeService {
    Recipe createRecipe(Recipe recipe);
    Iterable<Recipe> getRecipes();
    Recipe getRecipe(Integer id);
    Recipe updateRecipe(Integer id, Recipe recipe);
    Recipe partialUpdateRecipe(Integer id, Recipe recipe);
    void deleteRecipe(Integer id);
}
