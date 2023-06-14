package connoriffy.capi;

import org.bukkit.plugin.java.JavaPlugin;

public final class cAPI extends JavaPlugin {

    public static JavaPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}
