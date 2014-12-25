import java.awt.Point;
import java.util.Random;

public class Tully extends NorthHouse {
    private static int numTullys = 0;
    public static final int INIT_HEALTH = 10;
    public static final int MAXAGE = 50;
    public Tully(String imageFilename, Point loc) {
        super(imageFilename, INIT_HEALTH, loc, MAXAGE, "Tully",
            "Family, Duty, Honor.");
        numTullys++;
    }
    @Override
    public boolean canReproduceWithHouse(House otherHouse) {
        if (otherHouse == null) {
            return false;
        }
        if (numTullys < 50) {
            Random gen = new Random();
            if (otherHouse.getName() != null
                && otherHouse.getName().equals("Stark")) {
                return true;
            }
        }
        return false;
    }
    @Override
    public House reproduceWithHouse(House otherHouse) {
        Random gen = new Random();
        int chance = gen.nextInt(100);
        if (chance >= 15) {
            Point point = new Point(0, 0);
            point.setLocation(super.getLoc());
            return new Tully("trout.png", point);
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
            if (Math.abs(gen.nextInt()) % 100 <= 20) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void die() {
        numTullys--;
        super.die();
    }
    public static void reduceNum() {
        numTullys--;
    }
}
