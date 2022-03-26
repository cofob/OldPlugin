package ru.firesquare.CHANGEME.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.firesquare.CHANGEME.config.Messages;
import ru.firesquare.CHANGEME.utils.ChatUtils;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) {
        e.getPlayer().sendMessage(ChatUtils.translate(Messages.join));
    }
}
