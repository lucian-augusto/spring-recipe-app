package com.lucianaugusto.recipeapp.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lucianaugusto.recipeapp.converters.RecipeCommandToRecipe;
import com.lucianaugusto.recipeapp.converters.RecipeToRecipeCommand;
import com.lucianaugusto.recipeapp.domain.Recipe;
import com.lucianaugusto.recipeapp.repositories.RecipeRepository;

public class RecipeServiceImplTest {
	
	RecipeServiceImpl recipeService;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}
	
	@Test
	public void testGetRecipes() throws Exception {
		Recipe recipe = new Recipe();
		HashSet<Recipe> recipeData = new HashSet<Recipe>();
		recipeData.add(recipe);
		
		when(recipeRepository.findAll()).thenReturn(recipeData);
		
		Set<Recipe> recipes = recipeService.getRecipes();
		
		assertEquals(recipes.size(), 1);
		verify(recipeRepository, times(1)).findAll();
		verify(recipeRepository, never()).findById(ArgumentMatchers.anyLong());
	}

	@Test
	public void testGetRecipesById() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		when(recipeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(recipe));
		
		Recipe foundRecipe = recipeService.findById(1L);
		
		assertNotNull("Null recipe returned", foundRecipe);
		verify(recipeRepository, times(1)).findById(ArgumentMatchers.anyLong());
		verify(recipeRepository, never()).findAll();
	}
	
	@Test
	public void testDeleteById() throws Exception {
		Long id = 1L;
		recipeService.deleteById(id);
		
		// No 'when', since the method has void return type
		
		verify(recipeRepository).deleteById(ArgumentMatchers.anyLong());
	}

}
