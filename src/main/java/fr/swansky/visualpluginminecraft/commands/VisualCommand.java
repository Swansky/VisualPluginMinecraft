package fr.swansky.visualpluginminecraft.commands;

import fr.swansky.visualpluginminecraft.plans.Plan2D;
import fr.swansky.visualpluginminecraft.plans.Plan3D;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VisualCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            Location firstPointOfTable = player.getLocation();
            Plan3D plan2D = new Plan3D(firstPointOfTable);
            plan2D.initPlan();

        }
        return true;
    }

}
