import java.awt.Point;
import java.util.Random;

public class Baratheon extends SouthHouse {
    private static int numBaratheons = 0;
    public static final int INIT_HEALTH = 12;
    public static final int MAXAGE = 25;
    public Baratheon(String imageFilename, Point loc) {
        super(imageFilename, INIT_HEALTH, loc, MAXAGE, "Baratheon",
            "Ours is the fury");
        numBaratheons++;
    }
    @Override
    public boolean canReproduceWithHouse(House otherHouse) {
        if (otherHouse == null) {
            return false;
        }
        if (numBaratheons < 16) {
            Random gen = new Random();
            if (super.collidesWithHouse(otherHouse)) {
                if (otherHouse.getName() != null
                    && otherHouse.getName().equals("Lannister")) {
                    if (Math.abs(gen.nextInt()) % 2 == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public House reproduceWithHouse(House otherHouse) {
        Random gen = new Random();
        int chance = gen.nextInt(100);
        if (chance >= 15) {
            Point point = new Point(0, 0);
            point.setLocation(super.getLoc());
            return new Baratheon("stag.png", point);
        }
        return null;
    }
    @Override
    public boolean canHarmHouse(House otherHouse) {
        if (otherHouse == null) {
            return false;
        }
        if (otherHouse.getName() != null
            && otherHouse.getName().equals("Targaryan")) {
            return true;
        }
        return false;
    }
    @Override
    public void die() {
        numBaratheons--;
        super.die();
    }
    public static void reduceNum() {
        numBaratheons--;
    }
}
