package fr.swansky.visualpluginminecraft.plans;

import fr.swansky.visualpluginminecraft.VisualPluginMinecraft;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitTask;

public class Plan2D implements Plan {

    private float gapX = 0.1f;
    private float gapY = 0.1f;
    private Location mainBottomLeftLocation;
    private int axisSizeX = 20;
    private int axisSizeY = 20;
    private int particleCount = 3;
    private double gapValue = 0.01;
    private Particle.DustOptions axisDustOption = new Particle.DustOptions(Color.BLACK, 1);
    private Particle.DustOptions valueDustOption = new Particle.DustOptions(Color.RED, 1);
    private Particle particle = Particle.REDSTONE;
    private BukkitTask axisRefreshTask, valueRefreshTask;

    public Plan2D(Location mainBottomLeftLocation) {
        this.mainBottomLeftLocation = mainBottomLeftLocation;
    }

    @Override
    public void initPlan() {
        axisRefreshTask = Bukkit.getScheduler().runTaskTimerAsynchronously(VisualPluginMinecraft.getPlugin(VisualPluginMinecraft.class),
                this::drawAxis, 0, 20);
        valueRefreshTask = Bukkit.getScheduler().runTaskTimerAsynchronously(VisualPluginMinecraft.getPlugin(VisualPluginMinecraft.class),
                this::drawValue, 0, 20);
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
    }

    private void drawAxisX() {
        double blockX = mainBottomLeftLocation.getX();
        for (double x = blockX - axisSizeX; x < blockX + axisSizeX; x += gapX) {
            Location nextLocation = new Location(mainBottomLeftLocation.getWorld(), x, mainBottomLeftLocation.getY(), mainBottomLeftLocation.getZ());
            spawnParticleAt(nextLocation, axisDustOption);
        }
    }

    private void drawAxisY() {
        double blockY = mainBottomLeftLocation.getY();
        for (double y = blockY - axisSizeY; y < blockY + axisSizeY; y += gapY) {
            Location nextLocation = new Location(mainBottomLeftLocation.getWorld(), mainBottomLeftLocation.getX(), y, mainBottomLeftLocation.getZ());
            spawnParticleAt(nextLocation, axisDustOption);
        }
    }

    @Override
    public void drawValue() {
        for (double x = -axisSizeX; x < axisSizeX; x += gapValue) {
            double y = Math.pow(x,3);
            Location nextLocation = new Location(mainBottomLeftLocation.getWorld(),
                    mainBottomLeftLocation.getX() + x,
                    mainBottomLeftLocation.getY() + y,
                    mainBottomLeftLocation.getZ());
            spawnParticleAt(nextLocation, valueDustOption);
        }
    }

    private void spawnParticleAt(Location location, Particle.DustOptions options) {
        if(options !=null){
            mainBottomLeftLocation.getWorld().spawnParticle(particle, location, particleCount, options);
        }else
        {
            spawnParticleAt(location);
        }

    }

    private void spawnParticleAt(Location location) {
        mainBottomLeftLocation.getWorld().spawnParticle(particle, location, particleCount);
    }


    public void setXGap(float gap) {
        this.gapX = gap;
    }

    public void setYGap(float gap) {
        this.gapY = gap;
    }


    public Location getMainBottomLeftLocation() {
        return mainBottomLeftLocation;
    }

    public void setMainBottomLeftLocation(Location mainBottomLeftLocation) {
        this.mainBottomLeftLocation = mainBottomLeftLocation;
    }

    public float getGapX() {
        return gapX;
    }

    public void setGapX(float gapX) {
        this.gapX = gapX;
    }

    public float getGapY() {
        return gapY;
    }

    public void setGapY(float gapY) {
        this.gapY = gapY;
    }

    public int getAxisSizeX() {
        return axisSizeX;
    }

    public void setAxisSizeX(int axisSizeX) {
        this.axisSizeX = axisSizeX;
    }

    public int getAxisSizeY() {
        return axisSizeY;
    }

    public void setAxisSizeY(int axisSizeY) {
        this.axisSizeY = axisSizeY;
    }

    public int getParticleCount() {
        return particleCount;
    }

    public void setParticleCount(int particleCount) {
        this.particleCount = particleCount;
    }

    public double getGapValue() {
        return gapValue;
    }

    public void setGapValue(double gapValue) {
        this.gapValue = gapValue;
    }

    public Particle.DustOptions getAxisDustOption() {
        return axisDustOption;
    }

    public void setAxisDustOption(Particle.DustOptions axisDustOption) {
        this.axisDustOption = axisDustOption;
    }

    public Particle.DustOptions getValueDustOption() {
        return valueDustOption;
    }

    public void setValueDustOption(Particle.DustOptions valueDustOption) {
        this.valueDustOption = valueDustOption;
    }
}
