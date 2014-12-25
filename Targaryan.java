import java.awt.Point;
import java.util.Random;

public class Targaryan extends House {
    private static int numTargaryans = 0;
    public static final int INIT_HEALTH = 100;
    public static final int MAXAGE = 2147483647;
    public Targaryan(String imageFilename, Point loc) {
        super(imageFilename, INIT_HEALTH, loc, MAXAGE, "Targaryan",
            "Fire and Blood");
        numTargaryans++;
    }
    @Override
    public boolean canReproduceWithHouse(House otherHouse) {
        if (otherHouse == null) {
            return false;
        }
        if (numTargaryans < 6) {
            Random gen = new Random();
            if (super.collidesWithHouse(otherHouse)) {
                if (otherHouse.getName() != null
                    && otherHouse.getName().equals("Targaryan")) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public House reproduceWithHouse(House otherHouse) {
        Random gen = new Random();
        int chance = gen.nextInt(100);
        if (chance > 30) {
            Point point = new Point(0, 0);
            point.setLocation(super.getLoc());
            return new Targaryan("dragon.png", point);
        }
        return null;
    }
    @Override
    public boolean canHarmHouse(House otherHouse) {
        if (otherHouse == null) {
            return false;
        }
        if (otherHouse.getName() != null
            && !(otherHouse.getName().equals("Baratheon"))) {
            Random gen = new Random();
            return true;
        }
        return false;
    }
    @Override
    public void die() {
        numTargaryans--;
        super.die();
    }
    @Override
    public boolean isOld() {
        return false;
    }
    @Override
    public void move() {
        int health = super.getHealth();
        super.move();
        super.setHealth(health);
    }
    public static void reduceNum() {
        numTargaryans--;
    }
}
