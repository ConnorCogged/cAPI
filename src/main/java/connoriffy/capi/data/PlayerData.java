package connoriffy.capi.data;

import connoriffy.capi.annotations.OnShutdown;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

import static connoriffy.capi.cAPI.instance;

public class PlayerData extends DataHandler {
    public HashMap<String, Object> playerData = new HashMap<>();

    public PlayerData(Player p) {
        super(instance, new File(instance.getDataFolder() + File.separator + "userdata"), p.getUniqueId().toString(), true, false);
    }

    protected void unloadData() {
        config.getKeys(false).forEach(key -> playerData.put(key, config.get(key)));
    }

    public Object fetchWithFallback(String key, Object fallback) {
        if(getConfig().isSet(key)) return getConfig().get(key);

        getConfig().set(key, fallback);
        return fallback;
    }

    public void writeData(String key, Object data, boolean save) {
        if (playerData.containsKey(key))
            playerData.replace(key, data);

        playerData.put(key, data);
        if(save) getConfig().set(key, data);
    }

    @OnShutdown
    private void _save() {
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object fetchData(String key) {
        if(playerData.containsKey(key)) return playerData.get(key);
        return null;
    }
}
