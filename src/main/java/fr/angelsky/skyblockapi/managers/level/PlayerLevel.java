package fr.angelsky.skyblockapi.managers.level;

import fr.angelsky.skyblockapi.accounts.TempPlayer;
import fr.angelsky.skyblockapi.api.SkyBlockApiInstance;
import fr.angelsky.skyblockapi.events.player.PlayerNextLevelEvent;
import fr.angelsky.skyblockapi.events.player.PlayerNextRankEvent;
import org.bukkit.Bukkit;

public class PlayerLevel {

    private final SkyBlockApiInstance skyBlockApiInstance;

    private final TempPlayer tempPlayer;
    private double xp;
    private int level;
    private int levelRank;

    private String levelColor;
    private LevelRank levelRankObject;

    public static final int MAX_LEVEL_PER_RANK = 150;
    public static final int MAX_RANK = 29;

    public PlayerLevel(SkyBlockApiInstance skyBlockApiInstance, TempPlayer tempPlayer){
        this.skyBlockApiInstance = skyBlockApiInstance;
        this.tempPlayer = tempPlayer;
    }

    public void init(double xp, int level, int levelRank){
        this.xp = xp;
        this.level = level;
        this.levelRank = levelRank;
    }

    public void refreshLevels(LevelRankManager lvlRankManager, LevelColor lvlColor){
        this.levelRankObject = lvlRankManager.getLevelRank(this.levelRank);
        this.levelColor = lvlColor.getColorForLevel(this.level);
    }

    public void addXp(int xp){
        if(getNeededXp()-xp <= 0){
            if(this.level == MAX_LEVEL_PER_RANK){
                if(this.levelRank == MAX_RANK){
                    return;
                }
            }
        }
        this.xp += xp;
        this.checkXp();
    }

    public void setXp(int xp){
        this.xp = xp;
        checkXp();
    }

    public void setLevelRank(int levelRank) {
        this.levelRank = levelRank;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void checkXp(){
        if(getNeededXp() <= 0){
            nextLevel();
        }
    }

    public void checkLevelRank(){
        if(level >= MAX_LEVEL_PER_RANK){
            nextRank();
        }
    }

    public double getProgressPercentage(){
        return this.xp * 100 / getXpAt(level, levelRank);
    }

    private void nextLevel() {
        if(levelRank == MAX_RANK+1) return;
        PlayerNextLevelEvent nextLevelEvent = new PlayerNextLevelEvent(this.tempPlayer, this.level+1, level);
        Bukkit.getPluginManager().callEvent(nextLevelEvent);
        if(nextLevelEvent.isCancelled()) return;
        this.xp = xp-this.getNeededXpForLevel();
        this.level++;
        checkLevelRank();
        this.checkXp(); // IN CASE OF LOT XP GIVEN, SEVERAL LEVELS AT SAME TIME
    }

    public void nextRank(){
        if(levelRank == MAX_RANK+1) return;
        PlayerNextRankEvent nextRankEvent = new PlayerNextRankEvent(skyBlockApiInstance, this.tempPlayer, this.levelRank+1, levelRank);
        Bukkit.getPluginManager().callEvent(nextRankEvent);
        if(nextRankEvent.isCancelled()) return;
        this.level = level-MAX_LEVEL_PER_RANK;
        this.levelRank++;
    }

    public LevelRank getLevelRankObject() {
        return levelRankObject;
    }

    public String getLevelColor() {
        return levelColor;
    }

    public boolean isMaxRank(){
        return levelRank == MAX_RANK+1; // +1 for rank 30 (display)
    }

    public int getNeededXp(){
        return getXpAt(level, levelRank) - (int) this.xp;
    }

    public int getNeededLevelToNextRank(){
        return getMaxLevelPerRank() - this.level;
    }

    public double getXp() {
        return xp;
    }

    public int getLevel() {
        return level;
    }

    public int getLevelRank() {
        return levelRank;
    }

    public int getXpAt(int level, int levelRank) {
        // x: level, y: rank -> return needed xp for level x and rank y
        //5 + (((y + 5) ^ 1.6) * 0.1) * ((x + 1) + (y / 10)) * ((x + 1) * 0.5) * 0.1
        return (int) Math.ceil(5 + (Math.pow((levelRank + 5), 1.6) * 0.1) * ((level + 1) + (levelRank / 10.0)) * (((level + 1) * 0.5) * 0.1));
    }

    public int getNeededXpForLevel(){
        return getXpAt(level, levelRank);
    }

    public static int getMaxLevelPerRank() {
        return MAX_LEVEL_PER_RANK;
    }
}
