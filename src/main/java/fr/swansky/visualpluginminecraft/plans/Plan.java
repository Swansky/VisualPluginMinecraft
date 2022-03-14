package fr.swansky.visualpluginminecraft.plans;

import fr.swansky.visualpluginminecraft.VisualPluginMinecraft;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitTask;

public abstract class Plan {

    protected float gapX = 0.1f;
    protected float gapY = 0.1f;
    protected Location centerLocation;
    protected int axisSizeX = 20;
    protected int axisSizeY = 20;
    protected int particleCount = 3;
    protected double gapValue = 0.01;
    protected Particle.DustOptions axisDustOption = new Particle.DustOptions(Color.BLACK, 1);
    protected Particle.DustOptions valueDustOption = new Particle.DustOptions(Color.RED, 1);
    protected Particle particle = Particle.REDSTONE;
    protected BukkitTask axisRefreshTask, valueRefreshTask;

    public Plan(Location centerLocation) {
        this.centerLocation = centerLocation;
    }

    public void initPlan() {
        axisRefreshTask = Bukkit.getScheduler().runTaskTimerAsynchronously(VisualPluginMinecraft.getPlugin(VisualPluginMinecraft.class),
                this::drawAxis, 0, 20);
        valueRefreshTask = Bukkit.getScheduler().runTaskTimerAsynchronously(VisualPluginMinecraft.getPlugin(VisualPluginMinecraft.class),
                this::drawValue, 0, 20);
    }

    public void deletePlan() {
        axisRefreshTask.cancel();
        valueRefreshTask.cancel();
    }

    protected abstract void drawAxis();

    protected abstract void drawValue();

    public void defineFormula(String formula) {

    }


    protected void spawnParticleAt(Location location, Particle.DustOptions options) {
        if(options !=null){
            centerLocation.getWorld().spawnParticle(particle, location, particleCount, options);
        }else
        {
            spawnParticleAt(location);
        }

    }

    protected void spawnParticleAt(Location location) {
        centerLocation.getWorld().spawnParticle(particle, location, particleCount);
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

    public Location getCenterLocation() {
        return centerLocation;
    }

    public void setCenterLocation(Location centerLocation) {
        this.centerLocation = centerLocation;
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

    public Particle getParticle() {
        return particle;
    }

    public void setParticle(Particle particle) {
        this.particle = particle;
    }
}
