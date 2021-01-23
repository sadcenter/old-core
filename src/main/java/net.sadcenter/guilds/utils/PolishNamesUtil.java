package net.sadcenter.guilds.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author sadcenter on 08.08.2020
 * @project LASTCORE
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PolishNamesUtil {

    @Contract(pure = true) 
    public static @NotNull String getPolishName(final Material material, final short dur) {
        if (material == Material.AIR) {
            return "powietrze";
        }
        else if (material == Material.STONE) {
            return "kamien";
        }
        else if (material == Material.GRASS) {
            return "trawa";
        }
        else if (material == Material.DIRT) {
            return "dirt";
        }
        else if (material == Material.COBBLESTONE) {
            return "bruk";
        }
        else if (material == Material.WOOD) {
            return "drewno";
        }
        else if (material == Material.SAPLING) {
            return "sadzonka";
        }
        else if (material == Material.BEDROCK) {
            return "skala macierzysta";
        }
        else if (material == Material.WATER) {
            return "woda";
        }
        else if (material == Material.STATIONARY_WATER) {
            return "woda";
        }
        else if (material == Material.LAVA) {
            return "lava";
        }
        else if (material == Material.STATIONARY_LAVA) {
            return "lava";
        }
        else if (material == Material.SAND) {
            return "piasek";
        }
        else if (material == Material.GRAVEL) {
            return "zwir";
        }
        else if (material == Material.GOLD_ORE) {
            return "ruda zlota";
        }
        else if (material == Material.IRON_ORE) {
            return "ruda zelaza";
        }
        else if (material == Material.COAL_ORE) {
            return "ruda wegla";
        }
        else if (material == Material.LOG) {
            return "pien";
        }
        else if (material == Material.LEAVES) {
            return "liscie";
        }
        else if (material == Material.SPONGE) {
            return "gabka";
        }
        else if (material == Material.GLASS) {
            return "szklo";
        }
        else if (material == Material.LAPIS_ORE) {
            return "ruda lazurytu";
        }
        else if (material == Material.LAPIS_BLOCK) {
            return "blok lazurytu";
        }
        else if (material == Material.DISPENSER) {
            return "dozownik";
        }
        else if (material == Material.SANDSTONE) {
            return "piaskoiec";
        }
        else if (material == Material.NOTE_BLOCK) {
            return "szafa grajaca";
        }
        else if (material == Material.BED_BLOCK) {
            return "lozko";
        }
        else if (material == Material.POWERED_RAIL) {
            return "zasilane tory";
        }
        else if (material == Material.DETECTOR_RAIL) {
            return "tory detekcyjne";
        }
        else if (material == Material.PISTON_STICKY_BASE) {
            return "lepki tlok";
        }
        else if (material == Material.WEB) {
            return "siec";
        }
        else if (material == Material.LONG_GRASS) {
            return "dluga trawa";
        }
        else if (material == Material.DEAD_BUSH) {
            return "martwy krzak";
        }
        else if (material == Material.PISTON_BASE) {
            return "tlok";
        }
        else if (material == Material.PISTON_EXTENSION) {
            return "tlok";
        }
        else if (material == Material.WOOL) {
            return "welna";
        }
        else if (material == Material.PISTON_MOVING_PIECE) {
            return "tlok";
        }
        else if (material == Material.YELLOW_FLOWER) {
            return "zolty kwiatek";
        }
        else if (material == Material.RED_ROSE) {
            return "czerwona roza";
        }
        else if (material == Material.BROWN_MUSHROOM) {
            return "brazowy grzyb";
        }
        else if (material == Material.RED_MUSHROOM) {
            return "czerwony grzyb";
        }
        else if (material == Material.GOLD_BLOCK) {
            return "block zlota";
        }
        else if (material == Material.IRON_BLOCK) {
            return "blok zelaza";
        }
        else if (material == Material.DOUBLE_STEP) {
            return "podwojne polplytki";
        }
        else if (material == Material.STEP) {
            return "polplytka";
        }
        else if (material == Material.BRICK) {
            return "cegla";
        }
        else if (material == Material.TNT) {
            return "TNT";
        }
        else if (material == Material.BOOKSHELF) {
            return "regal na ksiazki";
        }
        else if (material == Material.MOSSY_COBBLESTONE) {
            return "zarosniety mchem bruk";
        }
        else if (material == Material.OBSIDIAN) {
            return "obsydian";
        }
        else if (material == Material.TORCH) {
            return "pochodnia";
        }
        else if (material == Material.FIRE) {
            return "ogine";
        }
        else if (material == Material.MOB_SPAWNER) {
            return "spawner mobow";
        }
        else if (material == Material.WOOD_STAIRS) {
            return "derwniane schody";
        }
        else if (material == Material.CHEST) {
            return "skrzynka";
        }
        else if (material == Material.REDSTONE_WIRE) {
            return "czerwony proch";
        }
        else if (material == Material.DIAMOND_ORE) {
            return "ruda diamentu";
        }
        else if (material == Material.DIAMOND_BLOCK) {
            return "diamentowy blok";
        }
        else if (material == Material.WORKBENCH) {
            return "stol rzemieslniczy";
        }
        else if (material == Material.CROPS) {
            return "nasiona";
        }
        else if (material == Material.SOIL) {
            return "gleba";
        }
        else if (material == Material.FURNACE) {
            return "piecyk";
        }
        else if (material == Material.BURNING_FURNACE) {
            return "palacy sie piecyk";
        }
        else if (material == Material.SIGN_POST) {
            return "tabliczka";
        }
        else if (material == Material.WOODEN_DOOR) {
            return "drewniane drzwi";
        }
        else if (material == Material.LADDER) {
            return "drabinka";
        }
        else if (material == Material.RAILS) {
            return "tory";
        }
        else if (material == Material.COBBLESTONE_STAIRS) {
            return "kamienne schody";
        }
        else if (material == Material.WALL_SIGN) {
            return "tabliczka";
        }
        else if (material == Material.LEVER) {
            return "dzwignia";
        }
        else if (material == Material.STONE_PLATE) {
            return "kamienna plyta";
        }
        else if (material == Material.IRON_DOOR_BLOCK) {
            return "zelazne drzwi";
        }
        else if (material == Material.WOOD_PLATE) {
            return "drewniana plytka";
        }
        else if (material == Material.REDSTONE_ORE) {
            return "ruda czerwonego kamienia";
        }
        else if (material == Material.GLOWING_REDSTONE_ORE) {
            return "swiecacy czerwony kamien";
        }
        else if (material == Material.REDSTONE_TORCH_OFF) {
            return "zgaszona czerwona pochodnia";
        }
        else if (material == Material.REDSTONE_TORCH_ON) {
            return "czerwona pochodnia";
        }
        else if (material == Material.STONE_BUTTON) {
            return "kamienny przycisk";
        }
        else if (material == Material.SNOW) {
            return "snieg";
        }
        else if (material == Material.ICE) {
            return "lod";
        }
        else if (material == Material.SNOW_BLOCK) {
            return "block sniegu";
        }
        else if (material == Material.CACTUS) {
            return "kaktus";
        }
        else if (material == Material.CLAY) {
            return "glina";
        }
        else if (material == Material.SUGAR_CANE_BLOCK) {
            return "trzcina cukrowa";
        }
        else if (material == Material.JUKEBOX) {
            return "szafa grajaca";
        }
        else if (material == Material.FENCE) {
            return "plotek";
        }
        else if (material == Material.PUMPKIN) {
            return "dynia";
        }
        else if (material == Material.NETHERRACK) {
            return "block netherowy";
        }
        else if (material == Material.SOUL_SAND) {
            return "piasek dusz";
        }
        else if (material == Material.GLOWSTONE) {
            return "jasnopyl";
        }
        else if (material == Material.PORTAL) {
            return "portal";
        }
        else if (material == Material.JACK_O_LANTERN) {
            return "smiecaca dynia";
        }
        else if (material == Material.CAKE_BLOCK) {
            return "ciasto";
        }
        else if (material == Material.DIODE_BLOCK_OFF) {
            return "dioda";
        }
        else if (material == Material.DIODE_BLOCK_ON) {
            return "dioda";
        }
        else if (material == Material.STAINED_GLASS) {
            return "witraz";
        }
        else if (material == Material.TRAP_DOOR) {
            return "klapa";
        }
        else if (material == Material.MONSTER_EGGS) {
            return "jajko z potworem";
        }
        else if (material == Material.SMOOTH_BRICK) {
            return "gladkie cegly";
        }
        else if (material == Material.HUGE_MUSHROOM_1) {
            return "duzy grzyb";
        }
        else if (material == Material.HUGE_MUSHROOM_2) {
            return "duzy grzyb";
        }
        else if (material == Material.IRON_FENCE) {
            return "zelazny plotek";
        }
        else if (material == Material.THIN_GLASS) {
            return "cienkie szklo";
        }
        else if (material == Material.MELON_BLOCK) {
            return "melon";
        }
        else if (material == Material.PUMPKIN_STEM) {
            return "dynia";
        }
        else if (material == Material.MELON_STEM) {
            return "melon";
        }
        else if (material == Material.VINE) {
            return "winorosla";
        }
        else if (material == Material.FENCE_GATE) {
            return "furtka";
        }
        else if (material == Material.BRICK_STAIRS) {
            return "ceglane schody";
        }
        else if (material == Material.SMOOTH_STAIRS) {
            return "gladkie schody";
        }
        else if (material == Material.MYCEL) {
            return "mycel";
        }
        else if (material == Material.WATER_LILY) {
            return "lilia wodna";
        }
        else if (material == Material.NETHER_BRICK) {
            return "netherowe cegly";
        }
        else if (material == Material.NETHER_FENCE) {
            return "netherowy plotek";
        }
        else if (material == Material.NETHER_BRICK_STAIRS) {
            return "netherowe schody";
        }
        else if (material == Material.NETHER_WARTS) {
            return "bordawka";
        }
        else if (material == Material.ENCHANTMENT_TABLE) {
            return "stol do zaklec";
        }
        else if (material == Material.BREWING_STAND) {
            return "statyw alchemiczny";
        }
        else if (material == Material.CAULDRON) {
            return "kociol";
        }
        else if (material == Material.ENDER_PORTAL) {
            return "portal do kresu";
        }
        else if (material == Material.ENDER_PORTAL_FRAME) {
            return "portal do kresu";
        }
        else if (material == Material.ENDER_STONE) {
            return "kamien z kresu";
        }
        else if (material == Material.DRAGON_EGG) {
            return "jajko smoka";
        }
        else if (material == Material.REDSTONE_LAMP_OFF) {
            return "lampa na czerwony proszek";
        }
        else if (material == Material.REDSTONE_LAMP_ON) {
            return "lampa na czerwony proszek";
        }
        else if (material == Material.WOOD_DOUBLE_STEP) {
            return "podwojna drewniana polplytka";
        }
        else if (material == Material.WOOD_STEP) {
            return "drewniana polplytka";
        }
        else if (material == Material.COCOA) {
            return "kakao";
        }
        else if (material == Material.SANDSTONE_STAIRS) {
            return "schody z piaskowca";
        }
        else if (material == Material.EMERALD_ORE) {
            return "ruda szmaragdow";
        }
        else if (material == Material.ENDER_CHEST) {
            return "skrzynia kresu";
        }
        else if (material == Material.TRIPWIRE_HOOK) {
            return "zaczep na linke";
        }
        else if (material == Material.TRIPWIRE) {
            return "linka";
        }
        else if (material == Material.EMERALD_BLOCK) {
            return "blok szmaragdow";
        }
        else if (material == Material.SPRUCE_WOOD_STAIRS) {
            return "shody z ciemnego drewna";
        }
        else if (material == Material.BIRCH_WOOD_STAIRS) {
            return "schody z jasnego drewna";
        }
        else if (material == Material.JUNGLE_WOOD_STAIRS) {
            return "schody z junglowego drewna";
        }
        else if (material == Material.COMMAND) {
            return "blok komend";
        }
        else if (material == Material.BEACON) {
            return "magiczna latarnia";
        }
        else if (material == Material.COBBLE_WALL) {
            return "brukowy plot";
        }
        else if (material == Material.FLOWER_POT) {
            return "doniczka";
        }
        else if (material == Material.CARROT) {
            return "marchewka";
        }
        else if (material == Material.POTATO) {
            return "ziemniak";
        }
        else if (material == Material.WOOD_BUTTON) {
            return "drewniany guzik";
        }
        else if (material == Material.SKULL) {
            return "glowa";
        }
        else if (material == Material.ANVIL) {
            return "kowadlo";
        }
        else if (material == Material.TRAPPED_CHEST) {
            return "skrzynka z polapka";
        }
        else if (material == Material.GOLD_PLATE) {
            return "zlota plytka";
        }
        else if (material == Material.IRON_PLATE) {
            return "zelazna plytka";
        }
        else if (material == Material.REDSTONE_COMPARATOR_OFF) {
            return "komparator";
        }
        else if (material == Material.REDSTONE_COMPARATOR_ON) {
            return "komparator";
        }
        else if (material == Material.DAYLIGHT_DETECTOR) {
            return "wykrywacz swaitla";
        }
        else if (material == Material.REDSTONE_BLOCK) {
            return "block czerwonego kamienia";
        }
        else if (material == Material.QUARTZ_ORE) {
            return "ruda kwarcu";
        }
        else if (material == Material.HOPPER) {
            return "zbiornik";
        }
        else if (material == Material.QUARTZ_BLOCK) {
            return "blok kwarcu";
        }
        else if (material == Material.QUARTZ_STAIRS) {
            return "kwarcowe schody";
        }
        else if (material == Material.ACTIVATOR_RAIL) {
            return "tory aktywacyjne";
        }
        else if (material == Material.DROPPER) {
            return "podajnik";
        }
        else if (material == Material.STAINED_CLAY) {
            return "kolorowa glina";
        }
        else if (material == Material.STAINED_GLASS_PANE) {
            return "kolorowy witraz";
        }
        else if (material == Material.LEAVES_2) {
            return "liscie";
        }
        else if (material == Material.LOG_2) {
            return "pien";
        }
        else if (material == Material.ACACIA_STAIRS) {
            return "akacjowe schody";
        }
        else if (material == Material.DARK_OAK_STAIRS) {
            return "schody z ciemnego drewna";
        }
        else if (material == Material.HAY_BLOCK) {
            return "blok siana";
        }
        else if (material == Material.CARPET) {
            return "dywan";
        }
        else if (material == Material.HARD_CLAY) {
            return "utrwardzona glina";
        }
        else if (material == Material.COAL_BLOCK) {
            return "blok wegla";
        }
        else if (material == Material.PACKED_ICE) {
            return "zbity lod";
        }
        else if (material == Material.DOUBLE_PLANT) {
            return "roslina";
        }
        else if (material == Material.IRON_SPADE) {
            return "zelazna lopata";
        }
        else if (material == Material.IRON_PICKAXE) {
            return "zelazny kilof";
        }
        else if (material == Material.IRON_AXE) {
            return "zelazna siekiera";
        }
        else if (material == Material.FLINT_AND_STEEL) {
            return "zaplaniczka";
        }
        else if (material == Material.APPLE) {
            return "jablko";
        }
        else if (material == Material.BOW) {
            return "luk";
        }
        else if (material == Material.ARROW) {
            return "strzala";
        }
        else if (material == Material.COAL) {
            return "wegiel";
        }
        else if (material == Material.DIAMOND) {
            return "diament";
        }
        else if (material == Material.IRON_INGOT) {
            return "sztabka zelaza";
        }
        else if (material == Material.GOLD_INGOT) {
            return "sztabka zlota";
        }
        else if (material == Material.IRON_SWORD) {
            return "zelazny miecz";
        }
        else if (material == Material.WOOD_SWORD) {
            return "drewniany miecz";
        }
        else if (material == Material.WOOD_SPADE) {
            return "drewniana lopata";
        }
        else if (material == Material.WOOD_PICKAXE) {
            return "drewniany kilof";
        }
        else if (material == Material.WOOD_AXE) {
            return "drewniany toporek";
        }
        else if (material == Material.STONE_SWORD) {
            return "kamienny miecz";
        }
        else if (material == Material.STONE_SPADE) {
            return "kamienna lopata";
        }
        else if (material == Material.STONE_PICKAXE) {
            return "kamienny kilof";
        }
        else if (material == Material.STONE_AXE) {
            return "kamienny toporek";
        }
        else if (material == Material.DIAMOND_SWORD) {
            return "diamentowy miecz";
        }
        else if (material == Material.DIAMOND_SPADE) {
            return "diamentowa lopata";
        }
        else if (material == Material.DIAMOND_PICKAXE) {
            return "diamentowy kilof";
        }
        else if (material == Material.DIAMOND_AXE) {
            return "diamentowy toporek";
        }
        else if (material == Material.STICK) {
            return "patyk";
        }
        else if (material == Material.BOWL) {
            return "miska";
        }
        else if (material == Material.MUSHROOM_SOUP) {
            return "zupa z nogi arcrosa";
        }
        else if (material == Material.GOLD_SWORD) {
            return "zloty miecz";
        }
        else if (material == Material.GOLD_SPADE) {
            return "zlota lopata";
        }
        else if (material == Material.GOLD_PICKAXE) {
            return "zloty kilof";
        }
        else if (material == Material.GOLD_AXE) {
            return "zloty toporek";
        }
        else if (material == Material.STRING) {
            return "nic";
        }
        else if (material == Material.FEATHER) {
            return "piorko";
        }
        else if (material == Material.SULPHUR) {
            return "proch";
        }
        else if (material == Material.WOOD_HOE) {
            return "drewniana motyka";
        }
        else if (material == Material.STONE_HOE) {
            return "kamienna motyka";
        }
        else if (material == Material.IRON_HOE) {
            return "zelazna motyka";
        }
        else if (material == Material.DIAMOND_HOE) {
            return "diamentowa motyka";
        }
        else if (material == Material.GOLD_HOE) {
            return "zlota motyka";
        }
        else if (material == Material.SEEDS) {
            return "nasiona";
        }
        else if (material == Material.WHEAT) {
            return "siano";
        }
        else if (material == Material.BREAD) {
            return "chleb";
        }
        else if (material == Material.LEATHER_HELMET) {
            return "skorzany helm";
        }
        else if (material == Material.LEATHER_CHESTPLATE) {
            return "skorzana klata";
        }
        else if (material == Material.LEATHER_LEGGINGS) {
            return "skorzane spodnie";
        }
        else if (material == Material.LEATHER_BOOTS) {
            return "skorzane buty";
        }
        else if (material == Material.CHAINMAIL_HELMET) {
            return "kolczaty helm";
        }
        else if (material == Material.CHAINMAIL_CHESTPLATE) {
            return "kolczata klata";
        }
        else if (material == Material.CHAINMAIL_LEGGINGS) {
            return "kolczate spodnie";
        }
        else if (material == Material.CHAINMAIL_BOOTS) {
            return "kolczate buty";
        }
        else if (material == Material.IRON_HELMET) {
            return "zelazny helm";
        }
        else if (material == Material.IRON_CHESTPLATE) {
            return "zelazna klata";
        }
        else if (material == Material.IRON_LEGGINGS) {
            return "zelazne spodnie";
        }
        else if (material == Material.IRON_BOOTS) {
            return "zelazne buty";
        }
        else if (material == Material.DIAMOND_HELMET) {
            return "diamentowy helm";
        }
        else if (material == Material.DIAMOND_CHESTPLATE) {
            return "diamentowa klata";
        }
        else if (material == Material.DIAMOND_LEGGINGS) {
            return "diamentowe spodnie";
        }
        else if (material == Material.DIAMOND_BOOTS) {
            return "diamentowe buty";
        }
        else if (material == Material.GOLD_HELMET) {
            return "zloty helm";
        }
        else if (material == Material.GOLD_CHESTPLATE) {
            return "zlota klata";
        }
        else if (material == Material.GOLD_LEGGINGS) {
            return "zlote spodnie";
        }
        else if (material == Material.GOLD_BOOTS) {
            return "zlote buty";
        }
        else if (material == Material.FLINT) {
            return "krzemien";
        }
        else if (material == Material.PORK) {
            return "zarcie";
        }
        else if (material == Material.GRILLED_PORK) {
            return "usmarzone zarcie";
        }
        else if (material == Material.PAINTING) {
            return "malowidlo";
        }
        else if (material == Material.GOLDEN_APPLE) {
            if (dur == 0) {
                return "zlote jablko";
            }
            return "kox";
        } else {
            if (material == Material.SIGN) {
                return "tabliczka";
            }
            else if (material == Material.WOOD_DOOR) {
                return "drewniane drzwi";
            }
            else if (material == Material.BUCKET) {
                return "wiadro";
            }
            else if (material == Material.WATER_BUCKET) {
                return "wiadro wody";
            }
            else if (material == Material.LAVA_BUCKET) {
                return "wiadro lavy";
            }
            else if (material == Material.MINECART) {
                return "wozek";
            }
            else if (material == Material.SADDLE) {
                return "siodlo";
            }
            else if (material == Material.IRON_DOOR) {
                return "zelazne drzwi";
            }
            else if (material == Material.REDSTONE) {
                return "czerwony proch";
            }
            else if (material == Material.SNOW_BALL) {
                return "sniezka";
            }
            else if (material == Material.BOAT) {
                return "lodka";
            }
            else if (material == Material.LEATHER) {
                return "skora";
            }
            else if (material == Material.MILK_BUCKET) {
                return "wiadro mleka";
            }
            else if (material == Material.CLAY_BRICK) {
                return "cegla";
            }
            else if (material == Material.CLAY_BALL) {
                return "glina";
            }
            else if (material == Material.SUGAR_CANE) {
                return "trzcina curkowa";
            }
            else if (material == Material.PAPER) {
                return "papier";
            }
            else if (material == Material.BOOK) {
                return "ksiazka";
            }
            else if (material == Material.SLIME_BALL) {
                return "kula glutu";
            }
            else if (material == Material.STORAGE_MINECART) {
                return "wozek ze skrzynia";
            }
            else if (material == Material.POWERED_MINECART) {
                return "lokomotywa";
            }
            else if (material == Material.EGG) {
                return "jajo srutupufa";
            }
            else if (material == Material.COMPASS) {
                return "kompas";
            }
            else if (material == Material.FISHING_ROD) {
                return "wedka";
            }
            else if (material == Material.WATCH) {
                return "zegar";
            }
            else if (material == Material.GLOWSTONE_DUST) {
                return "jasnopyl";
            }
            else if (material == Material.RAW_FISH) {
                return "surowa ryba";
            }
            else if (material == Material.COOKED_FISH) {
                return "ugotowana ryba";
            }
            else if (material == Material.INK_SACK) {
                return "atrament";
            }
            else if (material == Material.BONE) {
                return "kosc";
            }
            else if (material == Material.SUGAR) {
                return "cukier";
            }
            else if (material == Material.CAKE) {
                return "ciasto";
            }
            else if (material == Material.BED) {
                return "lozko";
            }
            else if (material == Material.DIODE) {
                return "przekaznik";
            }
            else if (material == Material.COOKIE) {
                return "ciasteczko";
            }
            else if (material == Material.MAP) {
                return "mapa";
            }
            else if (material == Material.SHEARS) {
                return "nozyce";
            }
            else if (material == Material.MELON) {
                return "arbuz";
            }
            else if (material == Material.PUMPKIN_SEEDS) {
                return "nasienie dyni";
            }
            else if (material == Material.MELON_SEEDS) {
                return "nasienie arbuza";
            }
            else if (material == Material.RAW_BEEF) {
                return "tatar";
            }
            else if (material == Material.COOKED_BEEF) {
                return "befsztyk";
            }
            else if (material == Material.RAW_CHICKEN) {
                return "kurczak";
            }
            else if (material == Material.COOKED_CHICKEN) {
                return "kurczak z rozna";
            }
            else if (material == Material.ROTTEN_FLESH) {
                return "zgnila stopa srutupufa";
            }
            else if (material == Material.ENDER_PEARL) {
                return "oko endermana";
            }
            else if (material == Material.BLAZE_ROD) {
                return "rozdzka plomienia";
            }
            else if (material == Material.GHAST_TEAR) {
                return "lza ducha";
            }
            else if (material == Material.GOLD_NUGGET) {
                return "fragment zlotej protezy";
            }
            else if (material == Material.NETHER_STALK) {
                return "brodawa";
            }
            else if (material == Material.POTION) {
                return "mikstura";
            }
            else if (material == Material.GLASS_BOTTLE) {
                return "pusta butla 0,7";
            }
            else if (material == Material.SPIDER_EYE) {
                return "oko pajaka";
            }
            else if (material == Material.FERMENTED_SPIDER_EYE) {
                return "zgnile oko pajaka";
            }
            else if (material == Material.BLAZE_POWDER) {
                return "plomienna koka";
            }
            else if (material == Material.MAGMA_CREAM) {
                return "krem na przyszcze";
            }
            else if (material == Material.BREWING_STAND_ITEM) {
                return "aparatura do bimbru";
            }
            else if (material == Material.CAULDRON_ITEM) {
                return "kociol panoramixa";
            }
            else if (material == Material.EYE_OF_ENDER) {
                return "wszechwidzace oko";
            }
            else if (material == Material.SPECKLED_MELON) {
                return "lsniacy melon";
            }
            else if (material == Material.MONSTER_EGG) {
                return "kinder niespodzianka";
            }
            else if (material == Material.EXP_BOTTLE) {
                return "butla z expem";
            }
            else if (material == Material.FIREBALL) {
                return "ognisty cyberpierd";
            }
            else if (material == Material.BOOK_AND_QUILL) {
                return "ksiazka z piorem";
            }
            else if (material == Material.WRITTEN_BOOK) {
                return "zapisana ksiazka";
            }
            else if (material == Material.EMERALD) {
                return "szmaragd";
            }
            else if (material == Material.ITEM_FRAME) {
                return "ramka na przedmiot";
            }
            else if (material == Material.FLOWER_POT_ITEM) {
                return "doniczka";
            }
            else if (material == Material.CARROT_ITEM) {
                return "marchw";
            }
            else if (material == Material.POTATO_ITEM) {
                return "zimniok";
            }
            else if (material == Material.BAKED_POTATO) {
                return "pieczony zimniok";
            }
            else if (material == Material.POISONOUS_POTATO) {
                return "trujacy zimniok";
            }
            else if (material == Material.EMPTY_MAP) {
                return "pusta mapa";
            }
            else if (material == Material.GOLDEN_CARROT) {
                return "zlota marchw";
            }
            else if (material == Material.SKULL_ITEM) {
                return "morda";
            }
            else if (material == Material.CARROT_STICK) {
                return "bicz z marchewka";
            }
            else if (material == Material.NETHER_STAR) {
                return "pentagram";
            }
            else if (material == Material.PUMPKIN_PIE) {
                return "placek z dynii";
            }
            else if (material == Material.FIREWORK) {
                return "gwiazdka pirotechniczna";
            }
            else if (material == Material.FIREWORK_CHARGE) {
                return "gwiazdka pirotechniczna";
            }
            else if (material == Material.ENCHANTED_BOOK) {
                return "zakleta ksiazka";
            }
            else if (material == Material.REDSTONE_COMPARATOR) {
                return "komparator";
            }
            else if (material == Material.NETHER_BRICK_ITEM) {
                return "piekielne cegly";
            }
            else if (material == Material.QUARTZ) {
                return "kwarc";
            }
            else if (material == Material.EXPLOSIVE_MINECART) {
                return "granat";
            }
            else if (material == Material.HOPPER_MINECART) {
                return "ssacy wozek";
            }
            else if (material == Material.IRON_BARDING) {
                return "zelazny korpierz";
            }
            else if (material == Material.GOLD_BARDING) {
                return "zloty korpierz";
            }
            else if (material == Material.DIAMOND_BARDING) {
                return "diamentowy korpierz";
            }
            else if (material == Material.LEASH) {
                return "lasso";
            }
            return material.name();
        }
    }
}
