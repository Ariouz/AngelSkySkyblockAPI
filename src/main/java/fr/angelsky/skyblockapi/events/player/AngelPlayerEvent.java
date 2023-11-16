package fr.angelsky.skyblockapi.events.player;

import fr.angelsky.skyblockapi.accounts.TempPlayer;
import fr.angelsky.skyblockapi.event.AngelEvent;

public class AngelPlayerEvent extends AngelEvent {

    private TempPlayer tempPlayer;

    public AngelPlayerEvent(TempPlayer tempPlayer) {
        this.tempPlayer = tempPlayer;
    }

    public TempPlayer getTempPlayer() {
        return this.tempPlayer;
    }


}

