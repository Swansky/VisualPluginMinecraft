package fr.swansky.visualpluginminecraft;

import fr.swansky.visualpluginminecraft.visual.commands.VisualCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class VisualPluginMinecraft extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        initCommands();
    }

    private void initCommands() {
        this.getCommand("visual").setExecutor(new VisualCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
