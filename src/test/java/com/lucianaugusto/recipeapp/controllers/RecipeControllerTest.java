package com.lucianaugusto.recipeapp.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lucianaugusto.recipeapp.domain.Recipe;
import com.lucianaugusto.recipeapp.services.RecipeService;

public class RecipeControllerTest {
	
	@Mock
	RecipeService recipeService;
	
	RecipeController recipeController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		recipeController = new RecipeController(recipeService);
	}
	
	@Test
	public void GetRecipeTest() throws Exception {
		Long id = 1L;
		Recipe recipe = new Recipe();
		recipe.setId(id);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		
		when(recipeService.findById(ArgumentMatchers.anyLong())).thenReturn(recipe);
		
		mockMvc.perform(get("/recipe/show/" + id))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/show"))
			.andExpect(model().attributeExists("recipe"));
	}

}