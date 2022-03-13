package fr.swansky.visualpluginminecraft.plans;

import fr.swansky.visualpluginminecraft.VisualPluginMinecraft;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitTask;

public class Plan3D implements Plan {

    private float gapX = 0.1f;
    private float gapY = 0.1f;
    private float gapZ = 0.1f;
    private Location centerZeroLocation;
    private int axisSizeX = 5;
    private int axisSizeY = 5;
    private int axisSizeZ = 5;
    private int particleCount = 1;
    private double gapValue = 1;
    private Particle.DustOptions axisDustOption = new Particle.DustOptions(Color.BLACK, 1);
    private Particle.DustOptions valueDustOption = new Particle.DustOptions(Color.RED, 4);
    private Particle particle = Particle.REDSTONE;
    private BukkitTask axisRefreshTask, valueRefreshTask;

    public Plan3D(Location centerZeroLocation) {
        this.centerZeroLocation = centerZeroLocation;
    }

    @Override
    public void initPlan() {
        axisRefreshTask = Bukkit.getScheduler().runTaskTimerAsynchronously(VisualPluginMinecraft.getPlugin(VisualPluginMinecraft.class),
                this::drawAxis, 0, 20);
        valueRefreshTask = Bukkit.getScheduler().runTaskTimerAsynchronously(VisualPluginMinecraft.getPlugin(VisualPluginMinecraft.class), this::drawValue, 0, 20);

    }

    @Override
    public void deletePlan() {
        axisRefreshTask.cancel();
        valueRefreshTask.cancel();
    }

    @Override
    public void drawAxis() {
        drawAxisX();
        drawAxisY();
        drawAxisZ();
    }


    private void drawAxisX() {
        double blockX = centerZeroLocation.getX();
        for (double x = blockX - axisSizeX; x < blockX + axisSizeX; x += gapX) {
            Location nextLocation = new Location(centerZeroLocation.getWorld(), x, centerZeroLocation.getY(), centerZeroLocation.getZ());
            spawnParticleAt(nextLocation, axisDustOption);
        }
    }

    private void drawAxisY() {
        double blockY = centerZeroLocation.getY();
        for (double y = blockY - axisSizeY; y < blockY + axisSizeY; y += gapY) {
            Location nextLocation = new Location(centerZeroLocation.getWorld(), centerZeroLocation.getX(), y, centerZeroLocation.getZ());
            spawnParticleAt(nextLocation, axisDustOption);
        }
    }

    private void drawAxisZ() {
        double blockZ = centerZeroLocation.getZ();
        for (double z = blockZ - axisSizeZ; z < blockZ + axisSizeZ; z += gapZ) {
            Location nextLocation = new Location(centerZeroLocation.getWorld(), centerZeroLocation.getX(), centerZeroLocation.getY(), z);
            spawnParticleAt(nextLocation, axisDustOption);
        }
    }


    @Override
    public void drawValue() {
        for (double x = -axisSizeX; x < axisSizeX; x += gapValue) {
            for (int z = -axisSizeZ; z < axisSizeZ; z += gapValue) {
                double y = x * z;
                Location nextLocation = new Location(centerZeroLocation.getWorld(),
                        centerZeroLocation.getX() + x,
                        centerZeroLocation.getY() + y,
                        centerZeroLocation.getZ() + z);
                spawnParticleAt(nextLocation, valueDustOption);
            }
        }
    }


    private void spawnParticleAt(Location location, Particle.DustOptions options) {
        if (options != null) {
            centerZeroLocation.getWorld().spawnParticle(particle, location, particleCount, options);
        } else {
            spawnParticleAt(location);
        }

    }

    private void spawnParticleAt(Location location) {
        centerZeroLocation.getWorld().spawnParticle(particle, location, particleCount);
    }
}
