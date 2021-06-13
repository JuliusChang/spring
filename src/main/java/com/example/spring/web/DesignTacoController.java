package com.example.spring.web;

import com.example.spring.Ingredient;
import com.example.spring.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/design")
@Slf4j
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO","FLOUR Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO","Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF","Ground Beef",Ingredient.Type.PROTEIN),
                new Ingredient("CARN","Carnitas",Ingredient.Type.PROTEIN),
                new Ingredient("TMTO","Diced Tomatoes",Ingredient.Type.VEGGIES),
                new Ingredient("LETC","Lettuce",Ingredient.Type.VEGGIES),
                new Ingredient("CHED","Cheddar",Ingredient.Type.CHEESE),
                new Ingredient("JACK","Monterrey Jack",Ingredient.Type.CHEESE),
                new Ingredient("SLSA","Salsa",Ingredient.Type.SAUCE),
                new Ingredient("SRCR","Sour Cream",Ingredient.Type.SAUCE)
        );
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type :
                types) {
            model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients,type));
        }
        model.addAttribute("design",new Taco());
        return "design";



    }


    @PostMapping
    public String processDesign(Taco design){
        log.info("Processing design:" + design);
        return "redirect:/orders/current";
    }


    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type){
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
