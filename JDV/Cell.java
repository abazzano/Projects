
public class Cell {
	private boolean mbExist = false;
	private int mxPos = 0;
	private int myPos = 0;
	
	public Cell(int pxPos, int pyPos) {
		this.mxPos = pxPos;
		this.myPos = pyPos;
		this.mbExist = false;
	}

	public Cell(int pxPos, int pyPos, boolean pExist) {
		this( pxPos,  pyPos);
		this.mbExist = pExist;
	}
	
	public void setExist(boolean pExist) {
		this.mbExist = pExist;
	}
	
	public String toString() {
		//return "[" + this.mxPos + " ; "+ this.myPos + "; Exist" + this.mbExist + "]";
		return this.mbExist ? "O" : " ";
	}
	
	public boolean Exist() {
		return this.mbExist;
	}
}
