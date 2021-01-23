package net.sadcenter.guilds;


import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.google.common.collect.Sets;
import com.keenant.tabbed.Tabbed;
import com.mongodb.DB;
import lombok.Getter;
import lombok.SneakyThrows;
import net.sadcenter.commons.CommonPlugin;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.api.SignAPI;
import net.sadcenter.guilds.commands.admin.AntyCheatCommand;
import net.sadcenter.guilds.commands.admin.UnBlackListCommand;
import net.sadcenter.guilds.commands.guild.GuildCommand;
import net.sadcenter.guilds.commands.user.*;
import net.sadcenter.guilds.config.impl.AntyLogoutConfiguration;
import net.sadcenter.guilds.config.impl.GroupConfiguration;
import net.sadcenter.guilds.config.impl.MainConfiguration;
import net.sadcenter.guilds.config.impl.ServerSettingsConfiguration;
import net.sadcenter.guilds.config.impl.storage.ServerSettingsStorage;
import net.sadcenter.guilds.drop.DropConfiguration;
import net.sadcenter.guilds.drop.listeners.DropClick;
import net.sadcenter.guilds.events.PlayerDamageByPlayerEvent;
import net.sadcenter.guilds.listeners.*;
import net.sadcenter.guilds.managers.*;
import net.sadcenter.guilds.packets.FakeEnchantPacketAdapter;
import net.sadcenter.guilds.packets.FakeHpPacketAdapter;
import net.sadcenter.guilds.packets.PlayerIncognitoPacketAdapter;
import net.sadcenter.guilds.packets.TabCompletePacketAdapater;
import net.sadcenter.guilds.recipes.GeneratorRecipe;
import net.sadcenter.guilds.recipes.GoldenAppleRecipe;
import net.sadcenter.guilds.runnables.AutoMessageRunnable;
import net.sadcenter.guilds.runnables.DepositRunnable;
import net.sadcenter.guilds.runnables.MessagesRunnable;
import net.sadcenter.guilds.runnables.SortRunnable;
import net.sadcenter.guilds.storage.Settings;
import net.sadcenter.guilds.teleport.TeleportAPI;
import net.sadcenter.guilds.utils.ParserUtils;
import net.sadcenter.mongo.MongoPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import pl.meme.gui.InventoryAPI;

import java.io.File;
import java.util.stream.Collectors;

@Getter
public final class LastCore extends JavaPlugin {

    private static LastCore instance;
    private Tabbed tabbed;
    private final ProtectionManager protectionManager = new ProtectionManager();
    private MuteManager muteManager;
    private final AntyLogoutManager antyLogoutManager = new AntyLogoutManager();
    private ServerSettingsConfiguration serverSettingsConfiguration;
    private ItemShopManager itemShopManager;
    private BlacklistManager blacklistManager;
    private TeleportAPI teleportManager;
    private TopManager topManager;
    private TpaCommand tpaCommand;
    private DB mongoDatabase;
    private AntyLogoutConfiguration antyLogoutConfiguration;
    private UserManager userManager;
    private GuildManager guildManager;
    private MainConfiguration configuration;
    private GroupConfiguration groupConfiguration;
    private SignAPI sign;
    private final VanishManager vanishManager = new VanishManager();
    private DropConfiguration dropConfiguration;
    // @Getter
    //private static Settings settings;

    @NotNull
    public static LastCore getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        ServerSettingsStorage storage = serverSettingsConfiguration.getStorage();
        storage.setEnabledCase(Settings.isEnabledCase());
        storage.setEnabledDiamondsItems(Settings.isEnabledDiamondsItems());
        storage.setEnabledEnchanting(Settings.isEnabledEnchanting());
        storage.setEnabledGuildItems(Settings.isEnabledGuildItems());
        storage.setEnabledCase(Settings.isEnabledCase());
        storage.setEnabledPvp(Settings.isEnabledPvp());
        storage.setEnabledTnt(Settings.isEnabledTnt());
        storage.setEnabledGuilds(Settings.isEnabledGuilds());

        serverSettingsConfiguration.saveConfiguration();
        configuration.saveConfiguration();

        getUserManager().saveUsers();
        getGuildManager().saveGuilds();

        antyLogoutManager.getMap().asMap().keySet().forEach(uuid -> antyLogoutManager.getMap().invalidate(uuid));
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @SneakyThrows
    @Override
    public void onEnable() {
        this.configuration = new MainConfiguration(new File(getDataFolder(), "options.json"));
        this.mongoDatabase = new MongoPlugin(getDataFolder()).getConnection().getMongoClient().getDB("Sentria");
        this.groupConfiguration = new GroupConfiguration(new File(getDataFolder(), "groups.json"));
        this.antyLogoutConfiguration = new AntyLogoutConfiguration(new File(getDataFolder(), "antylogout.json"));
        this.itemShopManager = new ItemShopManager();
        this.blacklistManager = new BlacklistManager();
        this.dropConfiguration = new DropConfiguration(new File(getDataFolder(), "drops.json"));
        this.tabbed = new Tabbed(this);
        this.sign = new SignAPI(this);
        this.userManager = new UserManager(mongoDatabase);
        this.serverSettingsConfiguration = new ServerSettingsConfiguration(new File(getDataFolder(), "serversettings.json"));
        this.guildManager = new GuildManager(this);
        this.muteManager = new MuteManager();
        // this.mongoDatabase = new ItemShopManager(new HashSet<>(), mongoDatabase.getCollection("itemshop"));
        this.guildManager.loadGuilds();
        this.userManager.loadUsers();
        this.topManager = new TopManager(userManager.getUsers().values(), guildManager.getGuilds().values());
        this.blacklistManager.loadBlacklists();
        this.itemShopManager.load();
        this.muteManager.load();
        PluginManager pluginManager = getServer().getPluginManager();
        teleportManager = new TeleportAPI(pluginManager, this);
        pluginManager.registerEvents(new AsyncChatListener(muteManager), this);
        pluginManager.registerEvents(new DropClick(), this);
        pluginManager.registerEvents(new PlayerJoinListener(vanishManager, tabbed, topManager, configuration), this);
        pluginManager.registerEvents(new EntityDamageByEntityListener(antyLogoutManager, antyLogoutConfiguration.getStorage(), protectionManager), this);
        pluginManager.registerEvents(new InventoryOpenListener(), this);
        pluginManager.registerEvents(new EnchantListener(this), this);
        pluginManager.registerEvents(new BlockPlaceListener(), this);
        pluginManager.registerEvents(new PlayerDamageByPlayerEvent(null), this);
        pluginManager.registerEvents(new BlockBreakListener(guildManager), this);
        pluginManager.registerEvents(new PlayerLoginListener(blacklistManager), this);
        pluginManager.registerEvents(new net.sadcenter.guilds.drop.listeners.BlockBreakListener(Sets.newHashSet(dropConfiguration.getDrops().getDrops().values()), dropConfiguration.getDrops()), this);
        pluginManager.registerEvents(new PlayerDeathListener(antyLogoutManager, this), this);
        pluginManager.registerEvents(new CaseListener(), this);
        pluginManager.registerEvents(new PlayerTeleportListener(), this);
        pluginManager.registerEvents(new RandomTeleportListener(this, getServer().getScheduler()), this);
        pluginManager.registerEvents(new Listener() {
            @EventHandler
            public void onClick(InventoryClickEvent event) {
                Inventory inventory = event.getClickedInventory();
                if (inventory == null)
                    return;


                if ((inventory.getType() == InventoryType.ENCHANTING && event.getSlot() == 1))
                    event.setCancelled(true);
            }

            @EventHandler
            public void onClose(InventoryCloseEvent event) {
                if (event.getInventory().getType() == InventoryType.ENCHANTING)
                    event.getInventory().setItem(1, null);
            }
        }, this);

        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new PlayerIncognitoPacketAdapter(this));
        protocolManager.addPacketListener(new FakeEnchantPacketAdapter(this));
        protocolManager.addPacketListener(new TabCompletePacketAdapater(this));
        protocolManager.addPacketListener(new FakeHpPacketAdapter(this));

        pluginManager.registerEvents(new PlayerInteractListener(antyLogoutManager, antyLogoutConfiguration.getStorage()), this);
        pluginManager.registerEvents(new ExplodeListener(), this);
        pluginManager.registerEvents(new WaterListeners(getServer().getScheduler(), this), this);
        pluginManager.registerEvents(new CommandListener(configuration.getStorage(), antyLogoutManager, antyLogoutConfiguration.getStorage()), this);
        pluginManager.registerEvents(new PlayerConsumeListener(this, this.getServer().getScheduler()), this);
        //  pluginManager.registerEvents(new GeneratorListeners(this), this);
        pluginManager.registerEvents(new PlayerQuitListener(antyLogoutManager), this);
        pluginManager.registerEvents(new CraftingListener(), this);
        pluginManager.registerEvents(new PlayerMoveListener(antyLogoutManager, antyLogoutConfiguration.getStorage()), this);

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.runTaskTimer(this, new MessagesRunnable(protectionManager, vanishManager, userManager, antyLogoutManager, antyLogoutConfiguration.getStorage()), 20L, 20L);
        scheduler.runTaskTimer(this, new SortRunnable(tabbed, topManager, userManager, guildManager), 20L * 60L, 20L * 60L);
        scheduler.runTaskTimerAsynchronously(this, new AutoMessageRunnable(), 20L * 30L, 20L * 30L);
        scheduler.runTaskTimer(this, new DepositRunnable(), 20L * 20L, 20L * 20L);
        new InventoryAPI(this);
        ServerSettingsStorage serverSettingsStorage = serverSettingsConfiguration.getStorage();
        Settings.setEnabledCase(serverSettingsStorage.isEnabledCase());
        Settings.setEnabledDiamondsItems(serverSettingsStorage.isEnabledDiamondsItems());
        Settings.setEnabledEnchanting(serverSettingsStorage.isEnabledEnchanting());
        Settings.setEnabledGuildItems(serverSettingsStorage.isEnabledGuildItems());
        Settings.setEnabledPvp(serverSettingsStorage.isEnabledPvp());
        Settings.setEnabledTnt(serverSettingsStorage.isEnabledTnt());
        Settings.setEnabledGuilds(serverSettingsStorage.isEnabledGuilds());
        Settings.setGuildItems(configuration
                .getStorage()
                .getGuildItems()
                .stream()
                .map(ParserUtils::parseItemStack)
                .collect(Collectors.toList()));
        new CommonPlugin().getCommandManager().registerCommands(
                new RankingCommand(),
                new ClearCommand(),
                new TpposCommand(),
                new ProtectionCommand(protectionManager),
                new ResetujRankingCommand(),
                new TimeCommand(),
                new InvseeCommand(),
                new CobblexCommand(),
                new ResetujStatyCommand(),
                new GuildCommand(Settings.getGuildItems(), guildManager, teleportManager),
                new BlockCommand(),
                new TpacceptCommand(),
                (tpaCommand = new TpaCommand()),
                new GameModeCommand(),
                new SpawnCommand(),
                new SetSpawnCommand(),
                new GodCommand(),
                new FlyCommand(),
                new AdminPanelCommand(),
                new SlowModeCommand(),
                new ItemShopCommand(),
                new ItemShopAdminCommand(),
                new SetHomeCommand(),
                new HomeCommand(),
                new MsgCommand(),
                new ReplyCommand(),
                new IgnoreCommand(),
                new TempMuteCommand(),
                new MuteCommand(),
                new UnMuteCommand(muteManager),
                new HealCommand(),
                new KnockBackCommand(),
                new VanishCommand(),
                new DayCommand(),
                new NightCommand(),
                new IncognitoCommand(this),
                new StatsCommand(),
                new DropCommand(),
                new TurboDropCommand(userManager),
                new KitCommand(),
                new SpeedCommand(),
                new DepositCommand(),
                new RepairCommand(),
                new ChatOptionsCommand(),
                new TpCommand(),
                new BlackListCommand(),
                new DelWarpCommand(),
                new WarpCommand(),
                new SetHomeCommand(),
                new TpHereCommand(),
                new CaseCommand(),
                new SetWarpCommand(configuration),
                new EnchantCommand(),
                new EnderChestCommand(),
                new AntyCheatCommand(blacklistManager),
                new UnBlackListCommand(blacklistManager));
        Bukkit.addRecipe(new GoldenAppleRecipe().createRecipe());
        Bukkit.addRecipe(new GeneratorRecipe().createRecipe());
    }

}
