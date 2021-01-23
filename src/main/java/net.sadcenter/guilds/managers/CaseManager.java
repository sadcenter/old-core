package net.sadcenter.guilds.managers;

import lombok.Getter;
import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.commons.helper.RandomHelper;
import net.sadcenter.guilds.storage.Settings;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * @author sadcenter on 15.09.2020
 * @project LASTCORE
 */
public final class CaseManager {

    @Getter
    private static final List<ItemStack> caseItems;

    @Getter
    private static final List<ItemStack> cobblexDrops = Arrays.asList(
            new ItemStack(Material.ENDER_PEARL, 1),
            new ItemStack(Material.WATER_BUCKET),
            new ItemStack(Material.APPLE, 6),
            new ItemStack(Material.SLIME_BLOCK, 2),
            new ItemStack(Material.SULPHUR, 8),
            new ItemStack(Material.EMERALD, 8)

    );

    static {
        if (Settings.isEnabledDiamondsItems()) {
            caseItems = Arrays.asList(
                    new ItemStack(Material.ENDER_PEARL, 3),
                    ItemBuilder.of(Material.GOLDEN_APPLE).setAmount(1).setDurability(1).build(),
                    ItemBuilder.of(Material.GOLDEN_APPLE).setAmount(3).setDurability(1).build(),
                    ItemBuilder.of(Material.GOLDEN_APPLE).setAmount(8).setDurability(1).build(),
                    ItemBuilder.of(Material.GOLDEN_APPLE).setAmount(2).build(),
                    ItemBuilder.of(Material.GOLDEN_APPLE).setAmount(4).build(),
                    ItemBuilder.of(Material.GOLDEN_APPLE).setAmount(8).build(),
                    ItemBuilder.of(Material.GOLDEN_APPLE).setAmount(12).build(),
                    ItemBuilder.of(Material.GOLDEN_APPLE).setAmount(16).build(),
                    ItemBuilder.of(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 4).build(),
                    ItemBuilder.of(Material.BOW).addEnchantment(Enchantment.ARROW_FIRE, 1).build(),
                    ItemBuilder.of(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(),
                    ItemBuilder.of(Material.DIAMOND_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(),
                    ItemBuilder.of(Material.DIAMOND_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(),
                    ItemBuilder.of(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(),
                    ItemBuilder.of(Material.IRON_PICKAXE).setName("&3&lZelazne 6/3/3").addEnchantment(Enchantment.DIG_SPEED, 6).addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build(),
                    ItemBuilder.of(Material.IRON_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5).addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build(),
                    ItemBuilder.of(Material.DIAMOND_PICKAXE).setName("&3&lKilof znakomitego gornika (6/3/3)").addEnchantment(Enchantment.DIG_SPEED, 6).addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build(),
                    ItemBuilder.of(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5).addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build(),
                    ItemBuilder.of(Material.GOLD_INGOT).setAmount(64).build(),
                    ItemBuilder.of(Material.GOLD_BLOCK).setAmount(16).build(),
                    ItemBuilder.of(Material.GOLD_BLOCK).setAmount(8).build(),
                    ItemBuilder.of(Material.GOLD_BLOCK).setAmount(4).build(),
                    ItemBuilder.of(Material.TNT).setAmount(16).build(),
                    ItemBuilder.of(Material.WATER_BUCKET).setAmount(8).build()
            );
        } else {
            caseItems = Arrays.asList(
                    new ItemStack(Material.ENDER_PEARL, 3),
                    ItemBuilder.of(Material.GOLDEN_APPLE).setAmount(1).setDurability(1).build(),
                    ItemBuilder.of(Material.GOLDEN_APPLE).setAmount(3).setDurability(1).build(),
                    ItemBuilder.of(Material.GOLDEN_APPLE).setAmount(8).setDurability(1).build(),
                    ItemBuilder.of(Material.GOLDEN_APPLE).setAmount(2).build(),
                    ItemBuilder.of(Material.GOLDEN_APPLE).setAmount(4).build(),
                    ItemBuilder.of(Material.GOLDEN_APPLE).setAmount(8).build(),
                    ItemBuilder.of(Material.GOLDEN_APPLE).setAmount(12).build(),
                    ItemBuilder.of(Material.GOLDEN_APPLE).setAmount(16).build(),
                    ItemBuilder.of(Material.IRON_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 3).build(),
                    ItemBuilder.of(Material.BOW).addEnchantment(Enchantment.ARROW_FIRE, 1).build(),
                    ItemBuilder.of(Material.IRON_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(),
                    ItemBuilder.of(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(),
                    ItemBuilder.of(Material.IRON_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(),
                    ItemBuilder.of(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(),
                    ItemBuilder.of(Material.IRON_PICKAXE).setName("&3&lZelazne 6/3/3").addEnchantment(Enchantment.DIG_SPEED, 6).addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build(),
                    ItemBuilder.of(Material.IRON_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5).addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build(),
                    ItemBuilder.of(Material.DIAMOND_PICKAXE).setName("&3&lKilof znakomitego gornika (6/3/3)").addEnchantment(Enchantment.DIG_SPEED, 6).addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build(),
                    ItemBuilder.of(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5).addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build(),
                    ItemBuilder.of(Material.GOLD_INGOT).setAmount(64).build(),
                    ItemBuilder.of(Material.GOLD_BLOCK).setAmount(16).build(),
                    ItemBuilder.of(Material.GOLD_BLOCK).setAmount(8).build(),
                    ItemBuilder.of(Material.GOLD_BLOCK).setAmount(4).build(),
                    ItemBuilder.of(Material.TNT).setAmount(16).build(),
                    ItemBuilder.of(Material.WATER_BUCKET).setAmount(8).build()
            );
        }
    }

    public static ItemStack getRandomCaseItemStack() {
        return caseItems.get(RandomHelper.nextInt(caseItems.size()));
    }

    public static ItemStack getRandomCobblexItemStack() {
        return cobblexDrops.get(RandomHelper.nextInt(cobblexDrops.size()));
    }


}
