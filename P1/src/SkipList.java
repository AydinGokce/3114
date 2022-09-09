/**
 * My SkipList Implementation
 * 
 * Some parts have been copied, then modified from the course OpenDSA resources and the provided code snippets
 * 
 * PID: AydinGokce
 * @author Aydin Gokce
 * @version 8 September 2022
 */
import java.lang.reflect.Array;
import java.util.Random;
import student.TestableRandom;

public class SkipList<K extends Comparable<K>, E> {
    /**
     * SkipNode class
     * 
     * @author aydin
     *
     * @param <K> key
     * @param <E> value
     */
    // ---------------- SKIPNODE CLASS ---------------- //
    // COPIED FROM OPENDSA
    private class SkipNode<K extends Comparable<K>, E> {
        private KVPair<K, E> rec;
        private SkipNode<K, E>[] forward;

        public E element() {
            return rec.value();
        }


        public K key() {
            return rec.key();
        }


        public KVPair<K, E> record() {
            return rec;
        }


        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, E> kvPair, int level) {
            this.rec = kvPair;
            forward = new SkipNode[level + 1];
            for (int i = 0; i < level; i++)
                forward[i] = null;
        }


        @SuppressWarnings("unchecked")
        public SkipNode(K key, E value, int level) {
            this.rec = new KVPair<K, E>(key, value);
            forward = new SkipNode[level + 1];
            for (int i = 0; i < level; i++)
                forward[i] = null;
        }


        public String toString() {
            if (this.rec == null) {
                return null;
            }
            return this.rec.toString();
        }
    }
    // ------------------------------------------------ //

    private SkipNode<K, E> head;
    private Random rnd;
    private int level;
    private int size;

    /**
     * constructor for empty skiplist. Creates a head skipNode and zero size. 
     */
    public SkipList() {
        this.rnd = new TestableRandom();
        this.head = new SkipNode<K, E>(null, 0);
        this.level = -1;
        this.size = 0;
    }


    /**
     * Picks random level for a skipNode. This has been copied from the provided code snippets.
     * @return new level for skipNode
     */
    public int randomLevel() {
        int lev;
        for (lev = 0; rnd.nextBoolean(); lev++); // advance level
        return lev;
    }

    /**
     * Inserts a Key-Value pair into the skipNode. This has been copied then modified from the provided code snippets.
     * @param it object to be inserted
     * @return true iff the insertion was successful
     */
    @SuppressWarnings("unchecked") // Generic array allocation
    public boolean insert(KVPair<K, E> it) {
        if (it == null) {
            return false;
        }
        int newLevel = randomLevel();
        Comparable<K> k = it.key();
        if (level < newLevel)
            adjustHead(newLevel);
        
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, level + 1);
        SkipNode<K, E> x = head; // Start at header node
        for (int i = level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null) && (k.compareTo((K)(x.forward[i])
                .key()) > 0))
                x = x.forward[i];
            update[i] = x; // Track end at level i
        }
        x = new SkipNode<K, E>(it, newLevel);
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x; // Who y points to
        }
        size++; // Increment dictionary size
        return true;

    }

    /**
     * remove the first object with the specified key
     * @param key the key being searched for through the skipList
     * @return the first encountered KVPair with the specified key
     */
    public KVPair<K, E> removeByKey(Comparable<K> key) {
        SkipNode<K, E> x = head;
        KVPair<K, E> item = null;
        boolean found = false;
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, level + 1);
        for (int i = level; i >= 0; i--) {
            x = head;
            while ((x.forward[i] != null) && (key.compareTo((K)x.forward[i]
                .key()) != 0)) {
                x = x.forward[i];
            }
            update[i] = x;
            if (x.forward[i] != null && (key.compareTo((K)x.forward[i]
                .key()) == 0)) {
                found = true;
                item = x.forward[i].record();
            }
        }

        if (!found) {
            return item;
        }

        for (int i = 0; i <= level; i++) {
            if (update[i].forward[i] != null) {
                update[i].forward[i] = update[i].forward[i].forward[i];
            }
        }
        size--;
        return item;

    }


    /**
     * Returns a list of all the KVPairs with the specified key.
     * This code has been copied then modified from OpenDSA 15.01
     * @param key the key being checked against
     * @return an array of KVPairs with matching keys
     */
    public KVPair<K, E>[] search(Comparable<K> key) {

        KVPair<K, E>[] arr = (KVPair<K, E>[])Array.newInstance(KVPair.class,
            this.size);
        int arrIdx = 0;

        SkipNode<K, E> x = head; // Dummy header node
        for (int i = level; i >= 0; i--) // For each level...
            while ((x.forward[i] != null) && (x.forward[i].key().compareTo(
                (K)key) < 0)) { // go forward
                x = x.forward[i]; // Go one last step
            }
        x = x.forward[0]; // Move to actual record, if it exists
        
        while ((x != null) && (x.key().compareTo((K)key) == 0)) {
            arr[arrIdx] = x.record();
            // return x.record(); // Got it
            x = x.forward[0];
            arrIdx++;
        }
        return arr;
        // else return null; // Its not there

    }

    /**
     * Prints the contents of the SkipList
     */
    public void dump() {
        System.out.println("SkipList dump:");
        SkipNode<K, E> x = head;
        while (x != null) {
            System.out.println("Node has depth " + x.forward.length
                + ", Value (" + (x == head ? "null" : x.toString()) + ")");
            x = x.forward[0];
        }
        System.out.println("SkipList size is: " + this.size);
    }

    /**
     * returns the size of the SkipList
     * @return the number of elements in the list, excluding the head
     */
    public int size() {
        return this.size;
    }


    /**
     * Resizes the head node to a new level
     * @param newLevel the level to set the head to
     */
    private void adjustHead(int newLevel) {
        SkipNode<K, E> temp = head;
        head = new SkipNode<K, E>(null, newLevel);
        for (int i = 0; i <= level; i++)
            head.forward[i] = temp.forward[i];
        level = newLevel;
    }

    // START: rectangle-specific functions --------
    
    /**
     * Removes the first KVPair with the matching coordinates
     * @param coordsUncasted coordinates of type E to be checked against
     * @return the KVPair that was removed
     */
    public KVPair<K, E> removeByCoordinates(E coordsUncasted) {
        if (!(coordsUncasted instanceof Coordinates)) {
            throw new IllegalArgumentException(
                "Argument must be of type coordinates!");
        }
        KVPair<K, E> item = null;
        Coordinates coords = (Coordinates)coordsUncasted;

        SkipNode<K, E> x = head;
        boolean found = false;
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, level + 1);
        for (int i = level; i >= 0; i--) {
            while ((x.forward[i] != null) && !(coords.equals((E)x.forward[i]
                .element()))) {
                x = x.forward[i];
            }
            update[i] = x;
            if (x.forward[i] != null) {
                found = true;
                item = x.forward[i].record();
            }
        }

        if (!found) {
            return item;
        }

        for (int i = 0; i < level; i++) {
            if (update[i].forward[i] != null) {
                update[i].forward[i] = update[i].forward[i].forward[i];
            }
        }
        size--;
        return item;

    }


    /**
     * Compiles all intersecting pairs of rectangles into a representative string
     * 
     * @return a string representation of all intersecting pairs of rectangles
     */
    public String intersection() {
        SkipNode<K, E> a = head.forward[0];
        SkipNode<K, E> b;

        String results = "";

        while (a != null) {
            b = head.forward[0];

            while (b != null) {

                if (((Coordinates)a.element()).intersects((Coordinates)b
                    .element()) && (a != b)) {
                    results += "(" + a.toString() + " | " + b.toString() + ")";
                    if(b.forward[0] == null) {
                        results += "\n";
                    }
                }

                b = b.forward[0];
            }

            a = a.forward[0];
        }
        return results;
    }


    /**
     * search all rectangles that intersect a given region
     * @param coordsUncasted region of type E to check against for intersection
     * @return a string representation of all rectangles that intersect with the given bounds
     */
    public String regionSearch(E coordsUncasted) {
        if (!(coordsUncasted instanceof Coordinates)) {
            throw new IllegalArgumentException(
                "Error: This method is specific to rectangles!");
        }

        Coordinates coords = (Coordinates)coordsUncasted;

        String result = "";

        SkipNode<K, E> x = head.forward[0];
        while (x != null) {
            if (coords.intersects((Coordinates)x.element())) {
                result += "(" + x.record().toString() + ")";
                if (x.forward[0] != null) {
                    result += "\n";
                }
            }
            x = x.forward[0];
        }
        return result;
    }
    // END: rectangle-specific functions --------
}
