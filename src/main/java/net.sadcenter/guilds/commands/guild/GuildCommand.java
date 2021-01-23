package net.sadcenter.guilds.commands.guild;


import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.commands.guild.subcommands.*;
import net.sadcenter.guilds.managers.GuildManager;
import net.sadcenter.guilds.teleport.TeleportAPI;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GuildCommand extends Command {

    private final List<Command> cmds = new ArrayList<>();

    public GuildCommand(List<ItemStack> itemStacks, GuildManager guildManager, TeleportAPI teleportAPI) {
        super("g");
        cmds.add(new GuildLeaveCommand(guildManager));
        cmds.add(new GuildDeleteCommand(guildManager));
        cmds.add(new GuildCreateCommand(itemStacks));
        cmds.add(new GuildItemsCommand(itemStacks));
        cmds.add(new GuildPvPCommand());
        cmds.add(new GuildLeaderCommand());
        cmds.add(new GuildOwnerCommand());
        cmds.add(new GuildPermissionCommand());
        cmds.add(new GuildJoinCommand());
        cmds.add(new GuildInviteCommand());
        cmds.add(new GuildInfoCommand());
        cmds.add(new GuildPanelCommand());
        cmds.add(new GuildProlongCommand());
        cmds.add(new GuildProtectionCommand());
        cmds.add(new GuildEnlargeCommand());
        cmds.add(new GuildSecureCommand());
        cmds.add(new GuildSetBaseCommand());
        cmds.add(new GuildBaseCommand(teleportAPI));
        cmds.add(new GuildDepositBlockCommand());
        cmds.add(new GuildRegenerationCommand());
        cmds.add(new GuildKickCommand(guildManager));
    }

    @Override
    public void onCommand(CommandSender exec, CommandBuilder commandBuilder, String... args) throws CommandException {
        if (args.length == 0) {
            cmds.forEach(CommandHelper -> ChatHelper.sendMessage(exec, "&3/g " + CommandHelper.getName() + " &8>> &7" + CommandHelper.getDescription()));
            return;
        }

        Optional<Command> cmd = cmds.stream().filter(command -> command.getName().equalsIgnoreCase(args[0]) || command.getAliases().contains(args[0])).findFirst();

        if (!cmd.isPresent()) {
            ChatHelper.sendMessage(exec, "&cNie mozna znalezc takiej komendy!");
            return;
        }
        List<String> newArgs = new ArrayList<>();
        Collections.addAll(newArgs, args);
        newArgs.remove(0);
        cmd.get().onCommand(exec, CommandBuilder.start(newArgs.toArray(new String[0]), exec), newArgs.toArray(new String[0]));
    }

}