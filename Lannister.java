import java.awt.Point;
import java.util.Random;

public class Lannister extends SouthHouse {
    private static int numLannisters = 0;
    public static final int INIT_HEALTH = 15;
    public static final int MAXAGE = 20;
    public Lannister(String imageFilename, Point loc) {
        super(imageFilename, INIT_HEALTH, loc, MAXAGE, "Lannister",
            "Hear me roar!");
        numLannisters++;
    }
    @Override
    public boolean canReproduceWithHouse(House otherHouse) {
        if (otherHouse == null) {
            return false;
        }
        if (numLannisters < 16) {
            Random gen = new Random();
            if (super.collidesWithHouse(otherHouse)) {
                if (otherHouse.getName() != null
                    && (otherHouse.getName().equals("Lannister")
                    || otherHouse.getName().equals("Baratheon"))) {
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
        if (chance > 10) {
            Point point = new Point(0, 0);
            point.setLocation(super.getLoc());
            return new Lannister("lion.png", point);
        }
        return null;
    }
    @Override
    public boolean canHarmHouse(House otherHouse) {
        if (otherHouse == null) {
            return false;
        }
        if (otherHouse.getName() != null) {
            Random gen = new Random();
            if (otherHouse.getName().equals("Stark")) {
                if (Math.abs(gen.nextInt()) % 100 <= 60) {
                    return true;
                }
            }
            if (otherHouse.getName().equals("Tully")) {
                if (Math.abs(gen.nextInt()) % 100 <= 80) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public void die() {
        numLannisters--;
        super.die();
    }
    public static void reduceNum() {
        numLannisters--;
    }
}
