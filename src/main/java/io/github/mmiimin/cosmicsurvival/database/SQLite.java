package io.github.mmiimin.cosmicsurvival.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import io.github.mmiimin.cosmicsurvival.plugin.CosmicSurvival;

public class SQLite extends Database{
    String dbname;
    public SQLite(CosmicSurvival instance){
        super(instance);
        dbname = plugin.getConfig().getString("SQLite.Filename", "table_name"); // Set the table name here e.g. player_kills
    }

    public String SQLiteCreateTokensTable = "CREATE TABLE IF NOT EXISTS table_name (" + // make sure to put your table name in here too.
            "`player` varchar(32) NOT NULL," + // This creates the different columns you will save data too. varchar(32) Is a string, int = integer
            "`kills` int(11) NOT NULL," +
            "`total` int(11) NOT NULL," +
            "`Exp` int(11) NOT NULL," +
            "`Level` int(11) NOT NULL," +
            "`Accessory_Slot` int(11) NOT NULL," +
            "`Rune_Slot` int(11) NOT NULL," +
            "`Preferences` int(11) NOT NULL," +
            "PRIMARY KEY (`player`)" +  // This is creating 3 columns Player, Kills, Total. Primary key is what you are going to use as your indexer. Here we want to use player so
            ");"; // we can search by player, and get kills and total. If you have somehow were searching kills it would provide total and player.


    public Connection getSQLConnection() {
        File dataFolder = new File(plugin.getDataFolder(), dbname+".db");
        if (!dataFolder.exists()){
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "File write error: "+dbname+".db");
            }
        }
        try {
            if(connection!=null&&!connection.isClosed()){
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return connection;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE,"SQLite exception on initialize", ex);
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().log(Level.SEVERE, "You need the SQLite JDBC library. Google it. Put it in /lib folder.");
        }
        return null;
    }

    public void load() {
        connection = getSQLConnection();
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(SQLiteCreateTokensTable);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initialize();
    }
}
