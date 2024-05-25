package ru.ender.aitemss;

import org.bukkit.plugin.java.JavaPlugin;
import ru.ender.aitemss.Command.ItemsGive;

public final class Main extends JavaPlugin {

    public static Main getInstance;

    public Main() {
        getInstance = this;
    }

    @Override
    public void onEnable() {
        getCommand("itemgive").setExecutor(new ItemsGive());
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {

    }
}
