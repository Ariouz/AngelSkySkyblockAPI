package fr.angelsky.skyblockapi.events.player;

import fr.angelsky.skyblockapi.accounts.TempPlayer;
import fr.angelsky.skyblockapi.api.SkyBlockApiInstance;
import fr.angelsky.skyblockapi.managers.level.LevelRank;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class PlayerNextRankEvent extends AngelPlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final SkyBlockApiInstance skyblockApiInstance;

    private boolean isCancelled;

    private final LevelRank oldLevelRank;
    private final LevelRank newLevelRank;

    public PlayerNextRankEvent(SkyBlockApiInstance skyBlockApiInstance, TempPlayer tempPlayer, int newRankInt, int oldRankInt) {
        super(tempPlayer);
        this.skyblockApiInstance = skyBlockApiInstance;
        this.oldLevelRank = skyBlockApiInstance.getManagerLoader().getLevelRankManager().getLevelRank(oldRankInt);
        this.newLevelRank = skyBlockApiInstance.getManagerLoader().getLevelRankManager().getLevelRank(newRankInt);
        ;
    }

    public LevelRank getOldLevelRank() {
        return oldLevelRank;
    }

    public LevelRank getNewLevelRank() {
        return newLevelRank;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean arg0) {
        this.isCancelled = arg0;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }


}
