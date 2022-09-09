import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MinHeap {
	private PriorityQueue<Vertex> heap;
	
	MinHeap(int numVertices) {
		heap = new PriorityQueue<Vertex>(numVertices, new VertexComparator());
	}
	
	public void add(Vertex v) {
		heap.add(v);
	}
	
	public Vertex poll() {
		return heap.poll();
	}
	
	public String toString() {
		return Arrays.toString(heap.toArray());
	}
	
	public void remove(Vertex v) {
		heap.remove(v);
	}
	
	
	public int size() {
		return heap.size();
	}
}
