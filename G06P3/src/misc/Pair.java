package misc;

public class Pair {

	private int x;
	private int y;
	
	public Pair(int y, int x){
		this.x = x;
		this.y = y;
	}
	
	public Pair(Pair p) {
		this.x = p.get_x();
		this.y = p.get_y();
	}
	
	public int get_x() {
		return x;
	}
	
	public int get_y() {
		return y;
	}
	
	public void set_x(int x) {
		this.x = x;
	}
	
	public void set_y(int y) {
		this.y = y;
	}
	
	public boolean equals(Pair other) {
		return this.x == other.get_x() && this.y == other.get_y();
	}
}
