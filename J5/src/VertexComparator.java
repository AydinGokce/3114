import java.util.Comparator;

public class VertexComparator implements Comparator<Vertex>{

	@Override
	public int compare(Vertex vertex1, Vertex vertex2) {
		
		if(vertex1.getDistance() < vertex2.getDistance()) {
			return -1;
		}
		if(vertex1.getDistance() > vertex2.getDistance()) {
			return 1;
		}
		
		return 0;
	}
	
}
