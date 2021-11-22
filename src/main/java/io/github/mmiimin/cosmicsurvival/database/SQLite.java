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
        dbname = plugin.getConfig().getString("SQLite.Filename", "playerData");
    }

    public String SQLiteCreateTokensTable = "CREATE TABLE IF NOT EXISTS playerData (" +
            "`player` varchar(32) NOT NULL," +
            "`combatExp` int(11) NOT NULL," +
            "`combatLevel` int(11) NOT NULL," +
            "`foragingExp` int(11) NOT NULL," +
            "`foragingLevel` int(11) NOT NULL," +
            "`farmingExp` int(11) NOT NULL," +
            "`farmingLevel` int(11) NOT NULL," +
            "`miningExp` int(11) NOT NULL," +
            "`miningLevel` int(11) NOT NULL," +
            "`fishingExp` int(11) NOT NULL," +
            "`fishingLevel` int(11) NOT NULL," +
            "`craftingExp` int(11) NOT NULL," +
            "`craftingLevel` int(11) NOT NULL," +
            "`achievementExp` int(11) NOT NULL," +
            "`achievementLevel` int(11) NOT NULL," +
            "`accessorySlot10` int(11) NOT NULL," +
            "`accessorySlot11` int(11) NOT NULL," +
            "`accessorySlot20` int(11) NOT NULL," +
            "`accessorySlot21` int(11) NOT NULL," +
            "`accessorySlot30` int(11) NOT NULL," +
            "`accessorySlot31` int(11) NOT NULL," +
            "`runeSlot1` int(11) NOT NULL," +
            "`runeSlot2` int(11) NOT NULL," +
            "`runeSlot3` int(11) NOT NULL," +
            "`setting` int(11) NOT NULL," +
            "`runeCooldown1` int(11) NOT NULL," +
            "`runeCooldown2` int(11) NOT NULL," +
            "`runeCooldown3` int(11) NOT NULL," +
            "`statsSTR` int(11) NOT NULL," +
            "`statsDEF` int(11) NOT NULL," +
            "`statsDEX` int(11) NOT NULL," +
            "`statsSRV` int(11) NOT NULL," +
            "`statsPoint` int(11) NOT NULL," +


            "PRIMARY KEY (`player`)" +
            ");";


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
            plugin.getLogger().log(Level.SEVERE, "JDBC library is missing");
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
