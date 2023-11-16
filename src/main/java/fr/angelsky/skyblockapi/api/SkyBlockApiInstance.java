package fr.angelsky.skyblockapi.api;

import fr.angelsky.angelskyapi.AngelskyApi;
import fr.angelsky.angelskyapi.api.AngelSkyApiInstance;
import fr.angelsky.skyblockapi.SkyblockApi;
import fr.angelsky.skyblockapi.managers.ManagerLoader;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class SkyBlockApiInstance {

    private final SkyblockApi skyblockApi;

    private AngelSkyApiInstance angelSkyApiInstance;
    private Economy economy;

    private ManagerLoader managerLoader;

    public SkyBlockApiInstance(SkyblockApi skyblockApi){
        this.skyblockApi = skyblockApi;
    }

    public void load() throws Exception {
        if(Bukkit.getPluginManager().getPlugin("AngelskyApi") != null){
            AngelskyApi angelskyApi = (AngelskyApi) Bukkit.getPluginManager().getPlugin("AngelskyApi");
            assert angelskyApi != null;
            this.angelSkyApiInstance = angelskyApi.getAngelSkyApiInstance();

            skyblockApi.getLogger().info("Instance API récupérée");
        }else{
            throw new Exception("L'API n'est pas détéctée !");
        }

        if (skyblockApi.getServer().getPluginManager().getPlugin("Vault") == null) {
            skyblockApi.getLogger().severe("Vault n'est pas trouvé");
            return;
        }
        RegisteredServiceProvider<Economy> rsp = skyblockApi.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            skyblockApi.getLogger().severe("Economy Vault n'est pas trouvé");
        }
        if (rsp != null) {
            this.economy = rsp.getProvider();
            skyblockApi.getLogger().info("Instance Economy Vault récupérée");
        }

        this.managerLoader = new ManagerLoader(this);
    }

    public void unload(){
        this.managerLoader.unload();
    }

    public SkyblockApi getSkyblockApi() {
        return skyblockApi;
    }

    public AngelSkyApiInstance getAngelSkyApiInstance() {
        return angelSkyApiInstance;
    }

    public ManagerLoader getManagerLoader() {
        return managerLoader;
    }

    public Economy getEconomy() {
        return economy;
    }
}
