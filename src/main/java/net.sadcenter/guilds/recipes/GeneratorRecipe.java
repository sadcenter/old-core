package net.sadcenter.guilds.recipes;

import net.sadcenter.commons.recipe.IRecipe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

/**
 * @author sadcenter on 08.11.2020
 * @project LastCore
 */

public final class GeneratorRecipe implements IRecipe {
    @Override
    public Recipe createRecipe() {
        ShapedRecipe shapedRecipe = new ShapedRecipe(new ItemStack(Material.ENDER_STONE));
        shapedRecipe.shape("SSS", "SXS", "SSS");
        shapedRecipe.setIngredient('S', Material.STONE);
        shapedRecipe.setIngredient('X', Material.REDSTONE);
        return shapedRecipe;
    }
}
