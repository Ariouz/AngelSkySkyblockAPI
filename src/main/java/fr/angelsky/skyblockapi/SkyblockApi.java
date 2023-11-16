package fr.angelsky.skyblockapi;

import fr.angelsky.skyblockapi.api.SkyBlockApiInstance;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkyblockApi extends JavaPlugin {

    private SkyBlockApiInstance skyBlockApiInstance;

    @Override
    public void onEnable() {
        this.skyBlockApiInstance = new SkyBlockApiInstance(this);
        try {
            this.skyBlockApiInstance.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        this.skyBlockApiInstance.unload();
    }

    public SkyBlockApiInstance getSkyBlockApiInstance() {
        return skyBlockApiInstance;
    }
}
