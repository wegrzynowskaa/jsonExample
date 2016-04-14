/*
 * Copyright (c) 2016 Ryanair Ltd. All rights reserved.
 */

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

public class Recipe {

    private String name;
    private String description;
    @JsonDeserialize(using = IngredientsDeserializer.class)
    private List<Ingredient> ingredients;
    private String instruction;

    public Recipe() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
