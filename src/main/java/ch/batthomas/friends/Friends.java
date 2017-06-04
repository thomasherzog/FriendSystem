package ch.batthomas.friends;

import ch.batthomas.friends.command.FriendCommand;
import ch.batthomas.friends.command.MessageCommand;
import ch.batthomas.friends.dao.FriendsQuery;
import ch.batthomas.friends.database.MySQLConnector;
import ch.batthomas.friends.i18n.I18NManager;
import ch.batthomas.friends.listener.ConnectionListener;
import ch.batthomas.friends.util.ConfigHelper;
import ch.batthomas.friends.util.MojangAPIHelper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.md_5.bungee.api.plugin.Plugin;

/**
 *
 * @author batthomas
 */
public class Friends extends Plugin {

    private ConfigHelper config;

    private MySQLConnector mysql;
    private FriendsQuery query;

    private MojangAPIHelper mojang;
    private I18NManager i18n;

    @Override
    public void onEnable() {
        try {
            mojang = new MojangAPIHelper(this);
            i18n = new I18NManager("messages");
            initConfig();
            initMySQL();
            registerListeners();
            registerCommands();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Friends.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initConfig() throws IOException {
        config = new ConfigHelper("config", "config", this);
    }

    private void initMySQL() throws SQLException {
        mysql = new MySQLConnector(this);
        mysql.connect();
        query = new FriendsQuery(mysql);
    }

    private void registerListeners() {
        getProxy().getPluginManager().registerListener(this, new ConnectionListener(this));
    }

    private void registerCommands() {
        getProxy().getPluginManager().registerCommand(this, new FriendCommand(this));
        getProxy().getPluginManager().registerCommand(this, new MessageCommand(this));
    }

    public ConfigHelper getConfig() {
        return config;
    }

    public MySQLConnector getMySQL() {
        return mysql;
    }

    public FriendsQuery getQuery() {
        return query;
    }

    public MojangAPIHelper getMojangAPIHelper() {
        return mojang;
    }
    
    public I18NManager getI18NManager(){
        return i18n;
    }
}
