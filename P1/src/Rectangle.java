
/**
 * Class used to represent rectangles.
 * 
 * PID: AydinGokce
 * @author Aydin Gokce
 * @version 8 September 2022
 *
 */
public class Rectangle extends KVPair<String, Coordinates> {
    
    /**
     * Constructor
     * 
     * @param name name, or key of rectangle
     * @param dims dimensions (Coordinates object) of rectangle
     */
    public Rectangle(String name, Coordinates dims) {
        super(name, dims);
    }    
    
    /**
     * checks for equality to another Rectangle object
     * @param o object being checked for equality
     * @return true iff name and Coordiantes are equal
     */
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        else if(!(o instanceof Rectangle)) {
            return false;
        }
        else {
            return this.theKey == ((Rectangle)o).key() && this.theVal.equals(((Rectangle)o).value());
        }
    }
        
}
