import java.awt.Point;

public class NorthHouse extends House {
    public NorthHouse(String imageFilename,
        int health, Point loc, int maxAge, String name, String words) {
        super(imageFilename, health, loc, maxAge, name, words);
    }
    @Override
    public void move() {
        super.move();
        if (super.getLoc().getY() < 300 && super.getHealth() < 150) {
            super.setHealth(super.getHealth() + 5);
        }
    }
}
