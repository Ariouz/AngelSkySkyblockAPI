package fr.angelsky.skyblockapi.managers.level;

import fr.angelsky.angelskyapi.api.utils.file.ConfigUtils;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LevelColor {

    private final ConfigUtils levelConfig;

    private final HashMap<Integer, String> colors = new HashMap<>();

    public LevelColor(ConfigUtils levelConfig){
        this.levelConfig = levelConfig;
    }

    public void loadColors(){
        List<String> levels = new ArrayList<>(Objects.requireNonNull(levelConfig.getYamlConfiguration().getConfigurationSection("colors")).getKeys(false));

        for(String level : levels){
            String levelColor = ChatColor.translateAlternateColorCodes('&', levelConfig.getString("colors."+level));
            colors.put(Integer.parseInt(level), levelColor);
        }
    }

    public String getColorForLevel(int level){
        return colors.get(level/10);
    }

    public HashMap<Integer, String> getColors() {
        return colors;
    }
}
