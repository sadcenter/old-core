package net.sadcenter.commons.command;


import net.sadcenter.commons.helper.ChatHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class BukkitCommand extends org.bukkit.command.Command {

    private final Command command;

    public BukkitCommand(@NotNull Command command) {
        super(command.getName(), command.getDescription(), command.getUsage(), command.getAliases());
        this.command = command;
    }

    @Override
    public boolean execute(CommandSender sender, String string, String[] args) {
        if (this.command.isOnlyPlayerCommand() && !(sender instanceof Player)) {
            ChatHelper.sendMessage(sender, "&8>> &cTej komendy nie mozna wywolac z poziomu konsoli!");
            return true;
        }

        final String permission = this.command.getPermission();

        if (!permission.isEmpty() && !sender.hasPermission(permission)) {
            ChatHelper.sendMessage(sender, "&cNie posiadasz permisji &7" + permission);
            return true;
        }

        try {
            this.command.onCommand(sender, CommandBuilder.start(args, sender), args);
        } catch (CommandException commandException) {
            sender.sendMessage(ChatHelper.fixColor(commandException.getErrorMessage()));
            return false;
        }
        return true;
    }
}