/**
 * 
 * This object acts as the coordinates of the rectangle object.
 * It is useful for testing intersection, printing and checking equality.
 * 
 * 
 * PID: aydingokce
 * @author Aydin Gokce
 * @version 8 September 2022
 *
 */
public class Coordinates {

    private int x;
    private int y;
    private int w;
    private int h;

    private final int maxHeight = 1024;
    private final int maxWidth = 1024;
    private final int minX = 0;
    private final int minY = 0;

    /**
     * Coordinates constructor. Use this if you are creating a regular rectangle (i.e. not a search region or evaluation).
     * 
     * @param x starting x coordinate
     * @param y starting y coordinate
     * @param w width of rectangle
     * @param h height of rectangle
     */
    public Coordinates(int x, int y, int w, int h) {

        if (x + w > this.maxHeight || y + h > this.maxHeight) {
            throw new IllegalArgumentException("Rectangle out of bounds!");
        }
        if (x < this.minX || y < this.minY) {
            throw new IllegalArgumentException(
                "X or Y is below minimum valid value!");
        }
        if (w <= 0 || h <= 0) {
            throw new IllegalArgumentException(
                "W and H must be positive nonzero!");
        }

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * Coordinates constructor. Use this if you are not creating a regular rectangle (i.e. a search region or evaluation).
     * 
     * @param x starting x coordinate
     * @param y starting y coordinate
     * @param w width of rectangle
     * @param h height of rectangle
     */
    public Coordinates(int x, int y, int w, int h, String mode) {
        if (mode.equals("regionSearch")) {
            if (w <= 0 || h <= 0) {
                throw new IllegalArgumentException(
                    "W and H must be positive nonzero!");
            }
        }
        else if (mode.equals("override")) {

        }
        else {
            throw new IllegalArgumentException("Invalid mode: " + mode);
        }

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * Determine whether another object is equal to this Coordinates object
     * 
     * @param o object being tested for equality
     * @return true iff object and this have the same coordinates, width and height
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o.getClass().equals(this.getClass()))) {
            return false;
        }
        Coordinates coordsified = (Coordinates)o;
        return coordsified.x == this.x && coordsified.y == this.y
            && coordsified.h == this.h && coordsified.w == this.w;
    }

    /**
     * determine whether another Coordinates object intersects this one
     * @param c other coordinate object being tested for intersection
     * @return true iff there is nonzero overlap on both axes
     */
    public boolean intersects(Coordinates c) {
        if ((this.y >= c.y + c.h) || (this.y + this.h <= c.y)) {
            return false;
        }
        return (this.x >= c.x + c.w) || (this.x + this.w <= c.x);
    }

    /**
     * returns string representation of the object
     * @return comma separated coordinates and dimensions
     */
    public String toString() {
        return this.x + ", " + this.y + ", " + this.w + ", " + this.h;
    }
}
