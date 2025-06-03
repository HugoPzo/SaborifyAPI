package mx.unam.aragon.saborifyapi.services;

import mx.unam.aragon.saborifyapi.entities.Recipe;
import mx.unam.aragon.saborifyapi.repositories.RecipeRepository;
import mx.unam.aragon.saborifyapi.services.interfaces.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Iterable<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipe(Integer id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    public Recipe updateRecipe(Integer id, Recipe updatedRecipe) {
        return recipeRepository.findById(id).map(recipe -> {
            recipe.setTitle(updatedRecipe.getTitle());
            recipe.setDescription(updatedRecipe.getDescription());
            recipe.setImage(updatedRecipe.getImage());
            recipe.setCategory(updatedRecipe.getCategory());
            recipe.setDifficulty(updatedRecipe.getDifficulty());
            recipe.setServings(updatedRecipe.getServings());
            recipe.setCookingTime(updatedRecipe.getCookingTime());
            recipe.setPreparationTime(updatedRecipe.getPreparationTime());
            recipe.setIngredients(updatedRecipe.getIngredients());

            return recipeRepository.save(recipe);
        }).orElse(null);
    }

    @Override
    public Recipe partialUpdateRecipe(Integer id, Recipe patch) {
        return recipeRepository.findById(id).map(recipe -> {
            if (patch.getTitle() != null) recipe.setTitle(patch.getTitle());
            if (patch.getDescription() != null) recipe.setDescription(patch.getDescription());
            if (patch.getImage() != null) recipe.setImage(patch.getImage());
            if (patch.getCategory() != null) recipe.setCategory(patch.getCategory());
            if (patch.getDifficulty() != null) recipe.setDifficulty(patch.getDifficulty());
            if (patch.getServings() != null) recipe.setServings(patch.getServings());
            if (patch.getCookingTime() != null) recipe.setCookingTime(patch.getCookingTime());
            if (patch.getPreparationTime() != null) recipe.setPreparationTime(patch.getPreparationTime());
            if (patch.getIngredients() != null) recipe.setIngredients(patch.getIngredients());

            return recipeRepository.save(recipe);
        }).orElse(null);
    }

    @Override
    public void deleteRecipe(Integer id) {
        recipeRepository.deleteById(id);
    }
}
