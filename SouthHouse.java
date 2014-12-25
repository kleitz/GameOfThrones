import java.awt.Point;

public class SouthHouse extends House {
    private int speed;
    public SouthHouse(String imageFilename,
        int health, Point loc, int maxAge, String name, String words) {
        super(imageFilename, health, loc, maxAge, name, words);
        this.speed = 1;
    }
    @Override
    public void move() {
        //moves the house at least once
        super.move();
        //moves twice more as long as the house started in the south and
        //remains in the south.
        for (int i = 1; i < this.speed && super.getLoc().getY() > 300; i++) {
            super.move();
        }
        //adjusts the speed based on whether it ends in the south
        if (super.getLoc().getY() > 300) {
            this.speed = 3;
        } else {
            this.speed = 1;
        }
    }
}
