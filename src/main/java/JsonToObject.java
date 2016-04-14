/*
 * Copyright (c) 2016 Ryanair Ltd. All rights reserved.
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonToObject {

    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Recipe recipeWithOneIngredient = objectMapper.readValue(new File("recipeWithObject.json"), Recipe.class);
            Recipe recipeWithIngredientList = objectMapper.readValue(new File("recipeWithList.json"), Recipe.class);

            //przykład z jednym obiektem
            List<Ingredient> ingredients = recipeWithOneIngredient.getIngredients();
            Ingredient oneIngredient = ingredients.get(0);
            System.out.println(oneIngredient.getName() + " " + oneIngredient.getMeasure() + "\n\n");

            //przykład z listą obiektów
            ingredients = recipeWithIngredientList.getIngredients();
            for (Ingredient item : ingredients) {
                System.out.println((ingredients.indexOf(item) + 1) + ". " + item.getName() + " " + item.getMeasure() +
                        "\n");

            }

        } catch (IOException e) {
            System.err.println("Coś nie pykło.." + e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    private static void toJsonExample() {

        ObjectMapper objectMapper = new ObjectMapper();
        Recipe recipe = createCustomRecipe();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("recipeWithList.json"), recipe);
        } catch (IOException e) {
            System.err.println("Coś poszło nie tak: " + e.getMessage());
        }

    }

    private static Recipe createCustomRecipe() {

        Recipe recipe = new Recipe();
        recipe.setName("Pierogi ruskie");
        recipe.setDescription("Tradycyjny rodzaj pierogów, z farszem ziemniaczano - serowym.");
        recipe.setInstruction("1. Mąkę zagnieść z ciepłą wodą na ciasto. \n " +
                "2. Ziemniaki dokładnie rozgnieść. \n " +
                "3. Cebulę drobno pokroić, posolić i podsmażyć na maśle na złoty kolor. \n" +
                "4. Ser dokładnie rozmieszać z cebulką i ziemniakami. \n" +
                "5. Wykrawać okręgi z ciasta, napełnić ciastem i zagnieść na pierogi. \n" +
                "6. Najlepiej podawać z zasmażką z smażonej cebuli bądź cebuli z boczkiem, polane kwaśną śmietaną. ");
        recipe.setIngredients(getRecipeIngredients());

        return recipe;
    }

    private static List<Ingredient> getRecipeIngredients() {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();

        Ingredient flour = new Ingredient();
        flour.setName("Mąka");
        flour.setMeasure("300 g");
        Ingredient water = new Ingredient();
        water.setName("Ciepła woda");
        water.setMeasure("200 ml");
        Ingredient potatoes = new Ingredient();
        potatoes.setName("Ziemniaki");
        potatoes.setMeasure("0,5 kg");
        Ingredient cottageCheese = new Ingredient();
        cottageCheese.setName("Ser biały półtłusty");
        cottageCheese.setMeasure("500 g");
        Ingredient onion = new Ingredient();
        onion.setName("Cebula");
        onion.setMeasure("2 sztuki, duże");

        ingredients.add(flour);
        ingredients.add(water);
        ingredients.add(potatoes);
        ingredients.add(cottageCheese);

        return ingredients;
    }
}
