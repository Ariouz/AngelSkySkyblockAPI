package fr.angelsky.skyblockapi.accounts;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import fr.angelsky.angelskyapi.api.utils.builder.BarBuilder;
import fr.angelsky.skyblockapi.api.SkyBlockApiInstance;
import fr.angelsky.skyblockapi.managers.level.PlayerLevel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TempPlayer {

    // Level func
    // f(x,y) = 5 + (((y + 5) ^ 1.6) * 0.1) * ((x + 1) + (y / 10)) * ((x + 1) * 0.5) * 0.1

    private final SkyBlockApiInstance skyBlockApiInstance;

    private final UUID uuid;
    private final String playerName;
    private final Player player;

    private PlayerLevel playerLevel;
    private int upgradeTokens;
    private int goodnessPoints;
    private int evilPoints;


    private SuperiorPlayer superiorPlayer;

    public TempPlayer(SkyBlockApiInstance skyBlockApiInstance, UUID uuid, String playerName){
        this.skyBlockApiInstance = skyBlockApiInstance;
        this.uuid = uuid;
        this.playerName = playerName;
        this.player = Bukkit.getPlayer(this.uuid);
        init();
    }

    public void init(){
        this.superiorPlayer = SuperiorSkyblockAPI.getPlayer(this.uuid);

        double xp = skyBlockApiInstance.getManagerLoader().getSqlManager().getSkyblockAccount().getXp(this.uuid);
        int level = skyBlockApiInstance.getManagerLoader().getSqlManager().getSkyblockAccount().getLevel(this.uuid);
        int levelRank = skyBlockApiInstance.getManagerLoader().getSqlManager().getSkyblockAccount().getLevelRank(this.uuid);
        this.upgradeTokens = skyBlockApiInstance.getManagerLoader().getSqlManager().getSkyblockAccount().getUpgradeTokens(this.uuid);
        this.goodnessPoints = skyBlockApiInstance.getManagerLoader().getSqlManager().getSkyblockAccount().getGoodnessPoints(this.uuid);
        this.evilPoints = skyBlockApiInstance.getManagerLoader().getSqlManager().getSkyblockAccount().getEvilPoints(this.uuid);


        this.playerLevel = new PlayerLevel(skyBlockApiInstance, this);
        this.playerLevel.init(xp, level, levelRank);
    }

    public void saveAccount(){
        this.skyBlockApiInstance.getManagerLoader().getSqlManager().getSkyblockAccount().saveAccount(this.uuid,
                this.playerName,
                this.playerLevel.getXp(),
                this.playerLevel.getLevel(),
                this.playerLevel.getLevelRank(),
                this.upgradeTokens,
                this.goodnessPoints,
                this.evilPoints);
    }

    public String getBarProgress(int length, char c, ChatColor full, ChatColor empty){
        return new BarBuilder(this.playerLevel.getProgressPercentage()).setFullColor(full).setEmptyColor(empty).setSize(length).build();
    }

    public int getUpgradeTokens() {
        return upgradeTokens;
    }

    public void setUpgradeTokens(int upgradeTokens) {
        this.upgradeTokens = upgradeTokens;
    }

    public void addUpgradeTokens(int amount){
        this.upgradeTokens += amount;
    }

    public void removeUpgradeTokens(int amount){
        this.upgradeTokens -= amount;
    }

    public int getGoodnessPoints() {
        return goodnessPoints;
    }

    public void setGoodnessPoints(int goodnessPoints) {
        this.goodnessPoints = goodnessPoints;
    }

    public void addGoodnessPoints(int amount){
        this.goodnessPoints += amount;
    }

    public void removeGoodnessPoints(int amount){
        this.goodnessPoints -= amount;
    }

    public int getEvilPoints() {
        return evilPoints;
    }

    public void setEvilPoints(int evilPoints) {
        this.evilPoints = evilPoints;
    }

    public void addEvilPoints(int amount){
        this.evilPoints += amount;
    }

    public void removeEvilPoints(int amount){
        this.evilPoints -= amount;
    }

    public PlayerLevel getPlayerLevel() {
        return playerLevel;
    }

    public SuperiorPlayer getSuperiorPlayer() {
        return superiorPlayer;
    }

    public Island getIsland(){
        return this.superiorPlayer.getIsland();
    }

    public Player getPlayer() {
        return player;
    }
}
