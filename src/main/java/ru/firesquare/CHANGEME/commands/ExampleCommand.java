package ru.firesquare.CHANGEME.commands;

import org.bukkit.command.CommandSender;
import redempt.redlib.commandmanager.CommandHook;
import ru.firesquare.CHANGEME.config.Messages;
import ru.firesquare.CHANGEME.ExamplePlugin;
import ru.firesquare.CHANGEME.utils.ChatUtils;

public class ExampleCommand {
    @CommandHook("example")
    public void exampleCommand(CommandSender sender) {
        sender.sendMessage(ChatUtils.translate(Messages.example));
    }

    @CommandHook("reload")
    public void reloadConfig(CommandSender sender) {
        ExamplePlugin.getInstance().reloadFileConfig();
        sender.sendMessage(ChatUtils.translate(Messages.reload));
    }
}
