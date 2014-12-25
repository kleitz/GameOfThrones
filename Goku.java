import java.awt.Point;

public class Goku extends House {
    public static final int INIT_HEALTH = 1000;
    public static final int MAXAGE = 100;
    public Goku(String imageFilename, Point loc) {
        super(imageFilename, INIT_HEALTH, loc, MAXAGE, "Goku",
            "Everyone lend me your energy!");
    }
    @Override
    public boolean isDead() {
        return false;
    }
    @Override
    public boolean canHarmHouse(House otherHouse) {
        if (otherHouse == null) {
            return false;
        }
        return true;
    }
    @Override
    public void harmHouse(House otherHouse) {
        if (this.canHarmHouse(otherHouse)) {
            otherHouse.takeDamage(100);
        }
    }
    @Override
    public boolean isOld() {
        return false;
    }
}
