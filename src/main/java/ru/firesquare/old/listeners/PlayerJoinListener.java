package ru.firesquare.old.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.firesquare.old.config.Config;
import ru.firesquare.old.config.Messages;
import ru.firesquare.old.OldPlugin;
import ru.firesquare.old.utils.ChatUtils;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) {
        Long first_join = e.getPlayer().getFirstPlayed()/1000L;
        Long old_at = Config.old_at + (System.currentTimeMillis()/1000L - Config.timestamp) / Config.division;
        if (first_join < old_at) {
            if (!e.getPlayer().hasPermission("group." + Config.group)) {
                User user = OldPlugin.getPermissions().getUserManager().getUser(e.getPlayer().getUniqueId());
                Node node = Node.builder("group." + Config.group).build();
                user.data().add(node);
                OldPlugin.getPermissions().getUserManager().saveUser(user);
                e.getPlayer().sendMessage(ChatUtils.translate(Messages.become_old));
            }
        }
    }
}
