/**
 * The abstract House for the Game of Thrones Simulation
 *
 */
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public abstract class House {

    protected ImageIcon image;
    protected String imageFilename;
    private int age;
    private int health;
    private Point loc;
    private int maxAge;
    private String name;
    private String words;
    private Point direction;
    private double dx;
    private double dy;
    private boolean changeDirect;

    public House(String imageFilename, int health,
        Point loc, int maxAge, String name, String words) {
        this.imageFilename = imageFilename;
        this.image = new ImageIcon(this.imageFilename);
        this.age = 1;
        this.health = health * 10;
        this.loc = loc;
        this.maxAge = maxAge * 10;
        this.name = name;
        this.words = words;
        Random gen = new Random();
        this.direction = new Point(gen.nextInt(600) + 1, gen.nextInt(600) + 1);
        this.changeDirect = true;
    }
    public String getWords() {
        return this.words;
    }
    public int getMaxAge() {
        return this.maxAge;
    }
    public ImageIcon getImage() {
        return image;
    }
    public String getFilename() {
        return imageFilename;
    }
    public String getName() {
        return this.name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getHealth() {
        return this.health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void takeDamage(int dmg) {
        this.health -= dmg;
    }
    public Point getLoc() {
        return this.loc;
    }
    public void setLoc(int xPos, int yPos) {
        this.loc.setLocation(new Point(xPos, yPos));
    }
    public void setDirect(Point direction) {
        int dXPos = (int) direction.getX();
        int dYPos = (int) direction.getY();
        int xPos = (int) this.loc.getX();
        int yPos = (int) this.loc.getY();

        int diffX = dXPos - xPos;
        int diffY = dYPos - yPos;

        java.util.Date date = new java.util.Date();
        double mag = Math.sqrt((diffX * diffX) + (diffY * diffY));

        Random exGen = new Random();

        dx = diffX / mag;
        dy = diffY / mag;

        if (((int) (this.dx * 10)) <= 7 && ((int) (this.dy * 10)) <= 7
            && changeDirect) {
            changeDirect = false;

            if (exGen.nextInt() % 2 == 0) {
                dx = dx * (-1);
            }
            if (exGen.nextInt() % 2 == 0) {
                dy = dy * (-1);
            }

            Random gen = new Random(date.getTime() + exGen.nextInt());
            int genX = gen.nextInt(600) + 1;
            int genY = gen.nextInt(600) + 1;
            direction.setLocation(new Point(genX / ((gen.nextInt(2) + 1)),
                genY / ((gen.nextInt(2) + 1))));
            this.setDirect(this.direction);
        }
        changeDirect = true;
    }
    public void move() {
        age++;
        health--;
        Random exGen = new Random();
        java.util.Date date = new java.util.Date();
        Random gen = new Random(date.getTime() + exGen.nextInt());
        if (this.loc.getX() > 600) {
            int genX = gen.nextInt(601) + 1;
            int genY = gen.nextInt(601) + 1;
            direction.setLocation(new Point(genX / (gen.nextInt(2) + 1),
                genY / (gen.nextInt(2) + 1)));
        } else if (this.loc.getX() < 0) {
            int genX = gen.nextInt(601) + 1;
            int genY = gen.nextInt(601) + 1;
            direction.setLocation(new Point(genX / (gen.nextInt(2) + 1),
                genY / (gen.nextInt(2) + 1)));
        }
        if (this.loc.getY() > 600) {
            int genX = gen.nextInt(601) + 1;
            int genY = gen.nextInt(601) + 1;
            direction.setLocation(new Point(genX / (gen.nextInt(2) + 1),
                genY / (gen.nextInt(2) + 1)));
        } else if (this.loc.getY() < 0) {
            int genX = gen.nextInt(601) + 1;
            int genY = gen.nextInt(601) + 1;
            direction.setLocation(new Point(genX / (gen.nextInt(2) + 1),
                genY / (gen.nextInt(2) + 1)));
        }
        this.setDirect(this.direction);
        this.setLoc((int) (this.loc.getX() + (this.dx * 5)),
            (int) (this.loc.getY() + (this.dy * 5)));
    }
    public boolean collidesWithHouse(House otherHouse) {
        if (this == otherHouse) {
            return false;
        }
        Point otherLoc = otherHouse.getLoc();
        int diffX = (int) (otherLoc.getX() - loc.getX());
        int diffY = (int) (otherLoc.getY() - loc.getY());
        if (diffX >= 0 && diffX <= 90 && diffY >= 0 && diffY <= 60) {
            return true;
        }
        diffX = (int) (otherLoc.getX() - loc.getX());
        diffY = (int) (otherLoc.getY() - loc.getY() + 60);
        if (diffX >= 0 && diffX <= 90 && diffY >= 0 && diffY <= 60) {
            return true;
        }
        diffX = (int) (otherLoc.getX() - loc.getX() + 90);
        diffY = (int) (otherLoc.getY() - loc.getY() + 60);
        if (diffX >= 0 && diffX <= 90 && diffY >= 0 && diffY <= 60) {
            return true;
        }
        diffX = (int) (otherLoc.getX() - loc.getX() + 90);
        diffY = (int) (otherLoc.getY() - loc.getY());
        if (diffX >= 0 && diffX <= 90 && diffY >= 0 && diffY <= 60) {
            return true;
        }
        return false;
    }
    public boolean canReproduceWithHouse(House otherHouse) {
        return false;
    }
    public House reproduceWithHouse(House otherHouse) {
        return null;
    }
    public boolean isOld() {
        if (this.age > this.maxAge) {
            return true;
        }
        return false;
    }
    public void die() {
        this.health = 0;
        this.loc.setLocation(-1000, -1000);
    }
    public boolean isDead() {
        if (this.health <= 0) {
            return true;
        }
        return false;
    }
    public boolean canHarmHouse(House otherHouse) {
        return false;
    }
    public void harmHouse(House otherHouse) {
        if (this.canHarmHouse(otherHouse)) {
            otherHouse.takeDamage(10);
        }
    }
    /**
     * Should draw the House at its location.
     */
    protected void draw(Graphics g) {
        image.paintIcon(null, g, ((int) this.loc.getX()),
            ((int) this.loc.getY()));
    }
}
