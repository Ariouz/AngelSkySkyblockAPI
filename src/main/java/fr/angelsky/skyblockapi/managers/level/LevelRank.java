package fr.angelsky.skyblockapi.managers.level;


import java.util.List;

public class LevelRank {

    private final int id;
    private final String display;
    private final List<String> rewards;

    public LevelRank(int id, String display, List<String> rewards) {
        this.id = id;
        this.display = display;
        this.rewards = rewards;
    }

    public int getId() {
        return id;
    }

    public String getDisplay() {
        return display;
    }

    public List<String> getRewards() {
        return rewards;
    }
}
