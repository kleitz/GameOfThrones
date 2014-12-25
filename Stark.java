import java.awt.Point;
import java.util.Random;

public class Stark extends NorthHouse {
    private static int numStarks = 0;
    public static final int INIT_HEALTH = 10;
    public static final int MAXAGE = 45;
    public Stark(String imageFilename, Point loc) {
        super(imageFilename, INIT_HEALTH, loc, MAXAGE, "Stark",
            "Winter is Coming.");
        numStarks++;
    }
    @Override
    public boolean canReproduceWithHouse(House otherHouse) {
        if (otherHouse == null) {
            return false;
        }
        if (numStarks < 50) {
            Random gen = new Random();
            if (super.collidesWithHouse(otherHouse)) {
                if (otherHouse.getName() != null
                    && otherHouse.getName().equals("Tully")) {
                    if (Math.abs(gen.nextInt()) % 4 == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    @Override
    public House reproduceWithHouse(House otherHouse) {
        Random gen = new Random();
        int chance = gen.nextInt(100);
        if (chance >= 14) {
            Point point = new Point(0, 0);
            point.setLocation(super.getLoc());
            return new Stark("direwolf.png", point);
        }
        return null;
    }
    @Override
    public boolean canHarmHouse(House otherHouse) {
        if (otherHouse == null) {
            return false;
        }
        if (otherHouse.getName() != null
            && otherHouse.getName().equals("Lannister")) {
            Random gen = new Random();
            if (Math.abs(gen.nextInt()) % 100 <= 60) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void die() {
        numStarks--;
        super.die();
    }
    public static void reduceNum() {
        numStarks--;
    }
}
