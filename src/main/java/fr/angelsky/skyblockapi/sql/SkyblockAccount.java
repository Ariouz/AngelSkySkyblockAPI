package fr.angelsky.skyblockapi.sql;

import fr.angelsky.angelskyapi.api.AngelSkyApiInstance;

import fr.angelsky.skyblockapi.api.SkyBlockApiInstance;

import java.sql.SQLException;
import java.util.UUID;

public class SkyblockAccount {

    private final SkyBlockApiInstance skyBlockApiInstance;
    private final AngelSkyApiInstance angelSkyApiInstance;

    private final String TABLE = "skyblock_accounts";

    public SkyblockAccount(SkyBlockApiInstance skyBlockApiInstance, AngelSkyApiInstance angelSkyApiInstance){
        this.skyBlockApiInstance = skyBlockApiInstance;
        this.angelSkyApiInstance = angelSkyApiInstance;
    }

    public boolean accountExists(UUID uuid){
        String query = "SELECT * FROM %s WHERE player_uuid = '%s'";
        return (Boolean) angelSkyApiInstance.getApiManager().getSqlManager().getMySQL().query(String.format(query, TABLE, uuid.toString()), resultSet -> {
            try {
                return resultSet.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    public void createAccount(UUID uuid, String player_name, double xp){
        String query = "INSERT INTO %s (player_uuid, player_name, xp) VALUES ('%s', '%s', '%s')";
        angelSkyApiInstance.getApiManager().getSqlManager().getMySQL().update(String.format(query, TABLE, uuid.toString(), player_name, xp));
    }

    public void saveAccount(UUID uuid, String player_name, double xp, int level, int levelRank, int upgradeTokens, int goodnessPoints, int evilpoints){
        String query = "UPDATE %s SET player_name = '%s', xp = '%s', level = '%s', levelRank = '%s', upgradeTokens = '%s', goodnessPoints = '%s', evilPoints = '%s' WHERE player_uuid = '%s'";
        angelSkyApiInstance.getApiManager().getSqlManager().getMySQL().update(String.format(query, TABLE, player_name, xp, level, levelRank, upgradeTokens, goodnessPoints, evilpoints, uuid.toString()));
    }

    public double getXp(UUID uuid){
        String query = "SELECT * FROM " + TABLE + " WHERE player_uuid = '%s'";
        return (double) angelSkyApiInstance.getApiManager().getSqlManager().getMySQL().query(String.format(query, uuid.toString()), resultSet -> {
            try {
                if(resultSet.next()){
                    return resultSet.getDouble("xp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        });
    }

    public int getLevel(UUID uuid){
        String query = "SELECT * FROM " + TABLE + " WHERE player_uuid = '%s'";
        return (int) angelSkyApiInstance.getApiManager().getSqlManager().getMySQL().query(String.format(query, uuid.toString()), resultSet -> {
            try {
                if(resultSet.next()){
                    return resultSet.getInt("level");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        });
    }

    public int getLevelRank(UUID uuid){
        String query = "SELECT * FROM " + TABLE + " WHERE player_uuid = '%s'";
        return (int) angelSkyApiInstance.getApiManager().getSqlManager().getMySQL().query(String.format(query, uuid.toString()), resultSet -> {
            try {
                if(resultSet.next()){
                    return resultSet.getInt("levelRank");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        });
    }

    public int getUpgradeTokens(UUID uuid){
        String query = "SELECT * FROM " + TABLE + " WHERE player_uuid = '%s'";
        return (int) angelSkyApiInstance.getApiManager().getSqlManager().getMySQL().query(String.format(query, uuid.toString()), resultSet -> {
            try {
                if(resultSet.next()){
                    return resultSet.getInt("upgradeTokens");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        });
    }

    public int getGoodnessPoints(UUID uuid){
        String query = "SELECT * FROM " + TABLE + " WHERE player_uuid = '%s'";
        return (int) angelSkyApiInstance.getApiManager().getSqlManager().getMySQL().query(String.format(query, uuid.toString()), resultSet -> {
            try {
                if(resultSet.next()){
                    return resultSet.getInt("goodnessPoints");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        });
    }

    public int getEvilPoints(UUID uuid){
        String query = "SELECT * FROM " + TABLE + " WHERE player_uuid = '%s'";
        return (int) angelSkyApiInstance.getApiManager().getSqlManager().getMySQL().query(String.format(query, uuid.toString()), resultSet -> {
            try {
                if(resultSet.next()){
                    return resultSet.getInt("evilPoints");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        });
    }

}
