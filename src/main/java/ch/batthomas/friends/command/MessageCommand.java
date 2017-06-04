package ch.batthomas.friends.command;

import ch.batthomas.friends.Friends;
import ch.batthomas.friends.i18n.I18NManager;
import ch.batthomas.friends.util.MessageBuilder;
import ch.batthomas.friends.util.MojangAPIHelper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author batthomas
 */
public class MessageCommand extends Command {

    private final I18NManager i18n;
    private final Friends plugin;
    private final MojangAPIHelper mojang;

    public MessageCommand(Friends plugin) {
        super("msg");
        i18n = plugin.getI18NManager();
        mojang = plugin.getMojangAPIHelper();
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) cs;
            i18n.changeLocale(player.getLocale());
            try {
                if (args.length > 1) {
                    sendMessage(player, args);
                } else {
                    sendHelpMessage(player);
                }
            } catch (IOException | SQLException ex) {
                Logger.getLogger(MessageCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void sendMessage(ProxiedPlayer player, String[] args) throws IOException, SQLException {
        ProxiedPlayer to = plugin.getProxy().getPlayer(args[0]);
        if (to != null && to.isConnected()) {
            if (!player.getName().equalsIgnoreCase(to.getName())) {
                UUID uuid = mojang.getUUID(args[0]);
                if (plugin.getQuery().isFriend(player.getUniqueId(), uuid)) {
                    if (plugin.getQuery().getSetting(uuid, "togglemessages")) {

                        StringBuilder sb = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            sb.append(args[i]);
                        }
                        to.sendMessage(new TextComponent("§7" + player.getName() + " » " + to.getName() + " :§7" + sb.toString()));
                        player.sendMessage(new TextComponent("§7" + player.getName() + " » " + to.getName() + " :§7" + sb.toString()));
                    } else {
                        new MessageBuilder(i18n).addReplacedText("msgnotpossible", "%PLAYER%", to.getName()).sendMessage(player);
                    }
                } else {
                    new MessageBuilder(i18n).addReplacedText("friendnot", "%PLAYER%", to.getName()).sendMessage(player);
                }
            } else {
                new MessageBuilder(i18n).addText("msgself").sendMessage(player);
            }
        } else {
            new MessageBuilder(i18n).addReplacedText("notonline", "%PLAYER%", args[0]).sendMessage(player);
        }

    }

    private void sendHelpMessage(ProxiedPlayer player) {
        new MessageBuilder(i18n).addText("helpline8").sendMessage(player);
        new MessageBuilder(i18n).addText("helpline9").sendMessage(player);
        new MessageBuilder(i18n).addText("helpline10").sendMessage(player);
        new MessageBuilder(i18n).addText("helpline11").sendMessage(player);
        new MessageBuilder(i18n).addText("helpline12").sendMessage(player);
        new MessageBuilder(i18n).addText("separator").sendMessage(player);
    }
}
