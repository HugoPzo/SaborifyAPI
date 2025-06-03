package mx.unam.aragon.saborifyapi.repositories;

import mx.unam.aragon.saborifyapi.entities.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
}
