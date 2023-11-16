package fr.angelsky.skyblockapi.managers.level;

import fr.angelsky.angelskyapi.api.utils.file.ConfigUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LevelRankManager {

    private final HashMap<Integer, LevelRank> levelRanks = new HashMap<>();

    private static final String maxRankTag = ChatColor.translateAlternateColorCodes('&', "&f&l[&6&lDieu&f&l]");

    public void loadRanks(ConfigUtils rankConfig) {
        ConfigurationSection sec = rankConfig.getYamlConfiguration().getConfigurationSection("ranks");
        assert sec != null;
        for (String rankId : sec.getKeys(false)) {
            String rankDisplay = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(sec.getString(rankId + ".display")));
            List<String> passRewards = new ArrayList<>();
            Objects.requireNonNull(sec.getList(rankId + ".rewards")).forEach(reward -> passRewards.add(reward.toString()));

            LevelRank levelRank = new LevelRank(Integer.parseInt(rankId), rankDisplay, passRewards);
            this.levelRanks.put(levelRank.getId(), levelRank);
        }
        this.levelRanks.put(30, new LevelRank(30, getMaxRankTag(), new ArrayList<>())); // DIEU RANK
    }

    public LevelRank getLevelRank(int id){
        return this.levelRanks.get(id);
    }

    public HashMap<Integer, LevelRank> getLevelRanks() {
        return levelRanks;
    }

    public static String getMaxRankTag() {
        return maxRankTag;
    }
}
