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
    public int tokens = 0;
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
        ResultSet rs = null;
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

    public void setTokens(Player player, Integer combatExp, Integer combatLevel, Integer miningExp, Integer miningLevel, Integer foragingExp, Integer foragingLevel, Integer farmingExp, Integer farmingLevel) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("REPLACE INTO " + table + " (player," +
                    "combatExp," +
                    "combatLevel," +
                    "foragingExp," +
                    "foragingLevel," +
                    "farmingExp," +
                    "farmingLevel," +
                    "miningExp," +
                    "miningLevel," +
                    "fishingExp," +
                    "fishingLevel) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, String.valueOf(player.getUniqueId()));
            ps.setInt(2, combatExp);
            ps.setInt(3, combatLevel);
            ps.setInt(4, miningExp);
            ps.setInt(5, miningLevel);
            ps.setInt(6, foragingExp);
            ps.setInt(7, foragingLevel);
            ps.setInt(8, farmingExp);
            ps.setInt(9, farmingLevel);
            ps.executeUpdate();
            return;
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
        return;
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