package net.sadcenter.guilds.recipes;

import net.sadcenter.commons.recipe.IRecipe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

/**
 * @author sadcenter on 18.10.2020
 * @project LASTCORE
 */

public final class GoldenAppleRecipe implements IRecipe {

    @Override
    public Recipe createRecipe() {
        ShapedRecipe shapedRecipe = new ShapedRecipe(new ItemStack(Material.GOLDEN_APPLE));
        shapedRecipe.shape(" G ", "GAG", " G ");
        shapedRecipe.setIngredient('G', Material.GOLD_INGOT);
        shapedRecipe.setIngredient('A', Material.APPLE);
        return shapedRecipe;
    }

}
