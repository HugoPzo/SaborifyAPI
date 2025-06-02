package mx.unam.aragon.saborifyapi.controllers;


import mx.unam.aragon.saborifyapi.entities.Recipe;
import mx.unam.aragon.saborifyapi.services.interfaces.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity<Iterable<Recipe>> findAllRecipes() {
        return ResponseEntity.ok().body(recipeService.getRecipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> findRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) throws URISyntaxException {
        Recipe saved = recipeService.createRecipe(recipe);
        return ResponseEntity
                .created(new URI("/api/recipes/" + saved.getId()))
                .body(saved);
    }


}
