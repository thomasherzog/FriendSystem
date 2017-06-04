package ch.batthomas.friends.listener;

import ch.batthomas.friends.Friends;
import ch.batthomas.friends.i18n.I18NManager;
import ch.batthomas.friends.util.MessageBuilder;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 *
 * @author batthomas
 */
public class ConnectionListener implements Listener {

    private final Friends plugin;
    private final I18NManager i18n;

    public ConnectionListener(Friends plugin) {
        this.plugin = plugin;
        i18n = plugin.getI18NManager();
    }

    @EventHandler
    public void onLogin(PostLoginEvent e) {
        try {
            ProxiedPlayer player = e.getPlayer();
            plugin.getQuery().addPlayer(player);
            List<UUID> uuids = plugin.getQuery().getFriends(player);
            for (UUID uuid : uuids) {
                ProxiedPlayer friend = plugin.getProxy().getPlayer(uuid);
                if (friend != null && friend.isConnected() && plugin.getQuery().getSetting(friend.getUniqueId(), "togglenotify")) {
                    i18n.changeLocale(friend.getLocale());
                    new MessageBuilder(i18n).addReplacedText("notifyonline", "%PLAYER%", player.getName()).sendMessage(friend);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent e) {
        try {
            ProxiedPlayer player = e.getPlayer();
            List<UUID> uuids = plugin.getQuery().getFriends(player);
            for (UUID uuid : uuids) {
                ProxiedPlayer friend = plugin.getProxy().getPlayer(uuid);
                if (friend != null && friend.isConnected() && plugin.getQuery().getSetting(friend.getUniqueId(), "togglenotify")) {
                    i18n.changeLocale(friend.getLocale());
                    new MessageBuilder(i18n).addReplacedText("notifyoffline", "%PLAYER%", player.getName()).sendMessage(friend);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
