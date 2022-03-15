package fr.swansky.visualpluginminecraft.visual.commands;

import fr.swansky.visualpluginminecraft.visual.Plan;
import fr.swansky.visualpluginminecraft.visual.Plan2D;
import fr.swansky.visualpluginminecraft.visual.Plan3D;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VisualCommand implements CommandExecutor {

    private Plan plan;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length > 1) {
                String planType = args[0];
                StringBuilder formulaBuilder = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    formulaBuilder.append(args[i]);
                }
                String formula = formulaBuilder.toString();
                Location firstPointOfTable = player.getLocation();
                if (planType.equalsIgnoreCase("2D")) {
                    if (plan == null) {
                        plan = new Plan2D(firstPointOfTable);
                        plan.initPlan();
                    }
                } else if (planType.equalsIgnoreCase("3D")) {
                    if (plan == null) {
                        plan = new Plan3D(firstPointOfTable);
                        plan.initPlan();
                    }
                } else {
                    player.sendMessage("Invalid Plan type. 2D or 3D accept");
                    return true;
                }
                try {
                    plan.defineFormula(formula);
                } catch (Exception e) {
                    player.sendMessage("Invalid function: " + e.getMessage());
                }
            } else {
                player.sendMessage("visual <plan type> <expression>");
            }


        }
        return true;
    }

}
