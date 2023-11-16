package fr.angelsky.skyblockapi.managers;


import fr.angelsky.skyblockapi.api.SkyBlockApiInstance;
import fr.angelsky.skyblockapi.managers.level.LevelRankManager;

public class ManagerLoader {

    private final SkyBlockApiInstance skyBlockApiInstance;

    private SQLManager sqlManager;
    private LevelRankManager levelRankManager;

    public ManagerLoader(SkyBlockApiInstance skyBlockApiInstance){
        this.skyBlockApiInstance = skyBlockApiInstance;
        load();
    }

    public void load(){
        this.sqlManager = new SQLManager(skyBlockApiInstance);
        this.levelRankManager = new LevelRankManager();
    }

    public void unload(){

    }

    public LevelRankManager getLevelRankManager() {
        return levelRankManager;
    }

    public SQLManager getSqlManager() {
        return sqlManager;
    }
}
