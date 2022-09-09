import java.util.ArrayList;

public class Vertex {
	private int distance;
	private ArrayList<Edge> successors;
	private ArrayList<Vertex> pathToSource;
	private int id;
	
	Vertex(int id) {
		//-1 distance corresponds to INF
		this.distance = Integer.MAX_VALUE;
		successors = new ArrayList<Edge>();
		pathToSource = new ArrayList<Vertex>();
		this.id = id;
	}
	
	public int getDistance() {
		return distance;
	}
	public void setDistance(int newDistance) {
		distance = newDistance;
	}
	
	public void addSuccessor(Edge edge) {
		successors.add(edge);
	}
	
	public int getID() {
		return this.id;
	}
	
	public ArrayList<Edge> getSuccessors() {
		return this.successors;
	}
	
	public String toString() {
		return "(" + this.id + ": " + this.distance + ")";
	}
	
	public boolean Equals(Object o) {
		if(o == null) {
			return false;
		}
		else if(!o.getClass().equals(this.getClass())) {
			return false;
		}
		else {
			return ((Vertex)o).id == this.id;
		}
	}
}