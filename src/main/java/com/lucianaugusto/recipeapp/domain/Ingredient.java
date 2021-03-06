package com.lucianaugusto.recipeapp.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"}) // Avoiding the Circular reference problem that results in a StackOverflow error by excluding 
//the equals and hashcodes for the dominant side of the relationship on the non-dominant class
@Entity
public class Ingredient {

//	Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String description;
	private BigDecimal amount;
	
	@OneToOne(fetch = FetchType.EAGER) // Explicitly setting the fetch type
	private UnitOfMeasure uom;
	
	@ManyToOne // No cascade (default value = none)
	private Recipe recipe;

//		Constructors
	
	
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
		this.description = description;
		this.amount = amount;
		this.uom = uom;
		this.recipe = recipe;
	}

	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
		this.description = description;
		this.amount = amount;
		this.uom = uom;
	}

	public Ingredient() {
	}
}
