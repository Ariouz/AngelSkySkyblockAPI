package fr.angelsky.skyblockapi.events.player;

import fr.angelsky.skyblockapi.accounts.TempPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class PlayerNextLevelEvent extends AngelPlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled;

    private final int oldLevel;
    private final int newLevel;

    public PlayerNextLevelEvent(TempPlayer tempPlayer, int newLevel, int oldLevel) {
        super(tempPlayer);
        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
    }

    public int getOldLevel() {
        return oldLevel;
    }

    public int getNewLevel() {
        return this.newLevel;
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
