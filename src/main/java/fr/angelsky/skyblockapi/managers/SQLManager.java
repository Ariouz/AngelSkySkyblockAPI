package fr.angelsky.skyblockapi.managers;

import fr.angelsky.angelskyapi.api.sql.accounts.SQLAccount;
import fr.angelsky.skyblockapi.api.SkyBlockApiInstance;
import fr.angelsky.skyblockapi.sql.SkyblockAccount;

public class SQLManager {

    private final SkyBlockApiInstance skyBlockApiInstance;

    private SkyblockAccount skyblockAccount;

    public SQLManager(SkyBlockApiInstance skyBlockApiInstance){
        this.skyBlockApiInstance = skyBlockApiInstance;
        init();
    }

    public void init(){
        this.skyblockAccount = new SkyblockAccount(skyBlockApiInstance, skyBlockApiInstance.getAngelSkyApiInstance());
    }

    public SkyblockAccount getSkyblockAccount() {
        return skyblockAccount;
    }
}
