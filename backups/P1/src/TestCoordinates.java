
public class TestCoordinates extends student.TestCase {
    
    Coordinates c;
    public void setUp() {
        c = new Coordinates(0, 0, 512, 512);
    }
    
    public void testEquals() {
        assertFalse(c.equals(null));
        assertFalse(c.equals("hi"));
        assertFalse(c.equals(new Coordinates(0, 0, 512, 1)));
        assertFalse(c.equals(new Coordinates(0, 0, 1, 512)));
        assertFalse(c.equals(new Coordinates(0, 1, 512, 512)));
        assertFalse(c.equals(new Coordinates(1, 0, 1, 512)));
        
        assertTrue(c.equals(new Coordinates(0, 0, 512, 512)));
    }
    
    public void testToString() {
        assertEquals("0, 0, 512, 512", c.toString());
    }
    
    public void testIntersects() {
        c = new Coordinates(512/2, 512/2, 512, 512);
        
        Coordinates cBottomLeft = new Coordinates(0, 0, 512, 512);
        Coordinates cBottomRight = new Coordinates(512, 0, 512, 512);
        Coordinates cTopLeft = new Coordinates(0, 512, 512, 512);
        Coordinates cTopRight = new Coordinates(512, 512, 512, 512);
        
        Coordinates cTop= new Coordinates(512/2, 512, 512, 512);
        Coordinates cBottom = new Coordinates(512/2, 0, 512, 512);
        Coordinates cLeft = new Coordinates(0, 512/2, 512, 512);
        Coordinates cRight = new Coordinates(512, 512/2, 512, 512);
        
        Coordinates cOverlap = new Coordinates(512/2, 512/2, 512, 512);
        
        assertTrue(c.intersects(cBottomLeft));
        assertTrue(c.intersects(cBottomRight));
        assertTrue(c.intersects(cTopLeft));
        assertTrue(c.intersects(cTopRight));
        
        assertTrue(c.intersects(cTop));
        assertTrue(c.intersects(cBottom));
        assertTrue(c.intersects(cLeft));
        assertTrue(c.intersects(cRight));
        
        assertTrue(c.intersects(cOverlap));
        
        assertTrue(cBottomLeft.intersects(c));
        assertTrue(cBottomRight.intersects(c));
        assertTrue(cTopLeft.intersects(c));
        assertTrue(cTopRight.intersects(c));
        
        assertTrue(cTop.intersects(c));
        assertTrue(cBottom.intersects(c));
        assertTrue(cLeft.intersects(c));
        assertTrue(cRight.intersects(c));
        
        assertTrue(cOverlap.intersects(c));
        
        
        Coordinates cBorderX = new Coordinates(0, 512, 512/2, 1);
        Coordinates cBorderY = new Coordinates(512, 0, 1, 512/2);
        Coordinates cCorner = new Coordinates(0, 0, 512/2, 512/2);
        
        assertFalse(c.intersects(cBorderX));
        assertFalse(c.intersects(cBorderY));
        assertFalse(c.intersects(cCorner));
        
        assertFalse(cBorderX.intersects(c));
        assertFalse(cBorderY.intersects(c));
        assertFalse(cCorner.intersects(c));
        
        
    }
}
