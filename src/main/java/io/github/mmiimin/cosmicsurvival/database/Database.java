package io.github.mmiimin.cosmicsurvival.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.entity.Player;

import io.github.mmiimin.cosmicsurvival.plugin.CosmicSurvival;


public abstract class Database {
    CosmicSurvival plugin;
    Connection connection;
    public String table = "playerData";

    public Database(CosmicSurvival instance){
        plugin = instance;
    }

    public abstract Connection getSQLConnection();

    public abstract void load();

    public void initialize(){
        connection = getSQLConnection();
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE player = ?");
            ResultSet rs = ps.executeQuery();
            close(ps,rs);

        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Unable to retrieve connection", ex);
        }
    }

    public Integer getTokens(String playerUUID,String findingData) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM " + table + " WHERE player = '"+playerUUID+"';");

            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString("player").equalsIgnoreCase(playerUUID.toLowerCase())){
                    return rs.getInt(findingData);
                }
            }
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
            }
        }
        return 0;
    }
    
    //test

    public void setTokens(Player player, Integer combatExp, Integer combatLevel, Integer miningExp,
                          Integer miningLevel, Integer foragingExp, Integer foragingLevel, Integer farmingExp,
                          Integer farmingLevel,Integer fishingExp, Integer fishingLevel,  Integer craftingExp, Integer craftingLevel, Integer achievementExp,
                          Integer achievementLevel,Integer accessorySlot10,Integer accessorySlot11,Integer accessorySlot20
            ,Integer accessorySlot21,Integer accessorySlot30,Integer accessorySlot31,Integer runeSlot1,Integer runeSlot2,Integer runeSlot3
            ,Integer setting,Integer runeCooldown1,Integer runeCooldown2,Integer runeCooldown3,Integer statsSTR,Integer statsDEF,Integer statsDEX,Integer statsSRV,Integer statsPoint) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("REPLACE INTO " + table + " (player," +
                    "combatExp," +
                    "combatLevel," +
                    "miningExp," +
                    "miningLevel," +
                    "foragingExp," +
                    "foragingLevel," +
                    "farmingExp," +
                    "farmingLevel," +
                    "fishingExp," +
                    "fishingLevel, " +
                    "craftingExp," +
                    "craftingLevel," +
                    "achievementExp," +
                    "achievementLevel," +
                    "accessorySlot10," +
                    "accessorySlot11," +
                    "accessorySlot20," +
                    "accessorySlot21," +
                    "accessorySlot30," +
                    "accessorySlot31," +
                    "runeSlot1," +
                    "runeSlot2," +
                    "runeSlot3," +
                    "setting," +
                    "runeCooldown1," +
                    "runeCooldown2," +
                    "runeCooldown3," +
                    "statsSTR," +
                    "statsDEF," +
                    "statsDEX," +
                    "statsSRV," +
                    "statsPoint)" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, String.valueOf(player.getUniqueId()));
            ps.setInt(2, combatExp);
            ps.setInt(3, combatLevel);
            ps.setInt(4, miningExp);
            ps.setInt(5, miningLevel);
            ps.setInt(6, foragingExp);
            ps.setInt(7, foragingLevel);
            ps.setInt(8, farmingExp);
            ps.setInt(9, farmingLevel);
            ps.setInt(10, fishingExp);
            ps.setInt(11, fishingLevel);
            ps.setInt(12, craftingExp);
            ps.setInt(13, craftingLevel);
            ps.setInt(14, achievementExp);
            ps.setInt(15, achievementLevel);
            ps.setInt(16, accessorySlot10);
            ps.setInt(17, accessorySlot11);
            ps.setInt(18, accessorySlot20);
            ps.setInt(19, accessorySlot21);
            ps.setInt(20, accessorySlot30);
            ps.setInt(21, accessorySlot31);
            ps.setInt(22, runeSlot1);
            ps.setInt(23, runeSlot2);
            ps.setInt(24, runeSlot3);
            ps.setInt(25, setting);
            ps.setInt(26, runeCooldown1);
            ps.setInt(27, runeCooldown2);
            ps.setInt(28, runeCooldown3);
            ps.setInt(29, statsSTR);
            ps.setInt(30, statsDEF);
            ps.setInt(31, statsDEX);
            ps.setInt(32, statsSRV);
            ps.setInt(33, statsPoint);

            ps.executeUpdate();
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
            }
        }
    }


    public void close(PreparedStatement ps,ResultSet rs){
        try {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        } catch (SQLException ex) {
            Error.close(plugin, ex);
        }
    }
}
