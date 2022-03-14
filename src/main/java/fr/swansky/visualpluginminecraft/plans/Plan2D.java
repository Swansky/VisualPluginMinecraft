package fr.swansky.visualpluginminecraft.plans;

import org.bukkit.Location;

public class Plan2D extends Plan {


    public Plan2D(Location mainBottomLeftLocation) {
        super(mainBottomLeftLocation);
    }


    @Override
    public void drawAxis() {
        drawAxisX();
        drawAxisY();
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

    @Override
    public void drawValue() {
        for (double x = -axisSizeX; x < axisSizeX; x += gapValue) {
            double y = Math.pow(x, 3);
            Location nextLocation = new Location(centerLocation.getWorld(),
                    centerLocation.getX() + x,
                    centerLocation.getY() + y,
                    centerLocation.getZ());
            spawnParticleAt(nextLocation, valueDustOption);
        }
    }
}
