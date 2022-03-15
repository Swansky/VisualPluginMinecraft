package fr.swansky.visualpluginminecraft.visual;

import org.bukkit.Location;

public class Plan3D extends Plan {
    private float gapZ = 0.1f;
    private int axisSizeZ = 5;

    public Plan3D(Location centerLocation) {
        super(centerLocation, 2);
        this.particleCount = 2;
        this.gapValue = 1;
        this.axisSizeX = axisSizeZ;
        this.axisSizeY = axisSizeZ;
    }


    @Override
    public void drawAxis() {
        drawAxisX();
        drawAxisY();
        drawAxisZ();
    }


    private void drawAxisX() {
        double blockX = centerLocation.getX();
        for (double x = blockX - axisSizeX; x < blockX + axisSizeX; x += gapX) {
            Location nextLocation = new Location(centerLocation.getWorld(), x, centerLocation.getY(), centerLocation.getZ());
            spawnParticleAt(nextLocation, axisDustOption);
        }
    }

    private void drawAxisY() {
        double blockY = centerLocation.getY();
        for (double y = blockY - axisSizeY; y < blockY + axisSizeY; y += gapY) {
            Location nextLocation = new Location(centerLocation.getWorld(), centerLocation.getX(), y, centerLocation.getZ());
            spawnParticleAt(nextLocation, axisDustOption);
        }
    }

    private void drawAxisZ() {
        double blockZ = centerLocation.getZ();
        for (double z = blockZ - axisSizeZ; z < blockZ + axisSizeZ; z += gapZ) {
            Location nextLocation = new Location(centerLocation.getWorld(), centerLocation.getX(), centerLocation.getY(), z);
            spawnParticleAt(nextLocation, axisDustOption);
        }
    }


    @Override
    public void drawValue() {
        if (function.getArgumentsNumber() == 1) {
            for (double x = -axisSizeX; x < axisSizeX; x += gapValue) {
                double y = function.calculate(x);
                Location nextLocation = new Location(centerLocation.getWorld(),
                        centerLocation.getX() + x,
                        centerLocation.getY() + y,
                        centerLocation.getZ());
                spawnParticleAt(nextLocation, valueDustOption);
            }
        } else {
            for (double x = -axisSizeX; x < axisSizeX; x += gapValue) {
                for (int z = -axisSizeZ; z < axisSizeZ; z += gapValue) {
                    double y = function.calculate(x, z);
                    Location nextLocation = new Location(centerLocation.getWorld(),
                            centerLocation.getX() + x,
                            centerLocation.getY() + y,
                            centerLocation.getZ() + z);
                    spawnParticleAt(nextLocation, valueDustOption);
                }
            }
        }

    }
}
