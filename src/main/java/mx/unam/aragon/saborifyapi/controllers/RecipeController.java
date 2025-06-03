package mx.unam.aragon.saborifyapi.controllers;

import jakarta.validation.Valid;
import mx.unam.aragon.saborifyapi.entities.Recipe;
import mx.unam.aragon.saborifyapi.services.interfaces.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    // GET todos
    @GetMapping
    public ResponseEntity<Iterable<Recipe>> findAllRecipes() {
        return ResponseEntity.ok().body(recipeService.getRecipes());
    }

    // GET uno
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> findRecipe(@PathVariable Integer id) {
        Recipe recipe = recipeService.getRecipe(id);
        if (recipe == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receta no encontrada");
        }
        return ResponseEntity.ok(recipe);
    }

    // POST
    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody Recipe recipe) throws URISyntaxException {
        Recipe saved = recipeService.createRecipe(recipe);
        return ResponseEntity
                .created(new URI("/api/recipes/" + saved.getId()))
                .body(saved);
    }

    // PUT (actualización completa)
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Integer id, @Valid @RequestBody Recipe recipe) {
        Recipe updated = recipeService.updateRecipe(id, recipe);
        if (updated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receta no encontrada para actualizar");
        }
        return ResponseEntity.ok(updated);
    }

    // PATCH (actualización parcial)
    @PatchMapping("/{id}")
    public ResponseEntity<Recipe> partialUpdateRecipe(@PathVariable Integer id, @RequestBody Recipe recipe) {
        Recipe updated = recipeService.partialUpdateRecipe(id, recipe);
        if (updated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receta no encontrada para actualización parcial");
        }
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Integer id) {
        try {
            recipeService.deleteRecipe(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receta no encontrada para eliminar");
        }
    }

    // Manejador de errores de validación
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
}
