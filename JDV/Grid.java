import java.io.IOException;

public class Grid {
	
	
	private int mxDim = 0;
	private int myDim = 0;
	private Cell[][] mCells;
	
	public Grid(int pxDim, int pyDim) {
		this.mxDim = pxDim;
		this.myDim = pyDim;
		this.mCells = new Cell[this.mxDim][this.myDim];
		
		if(this.mxDim >0 && this.myDim > 0) {
			genGrid();
		}
	}
	
	public void init(){
		
		//shape1();
		shape3();
	}
	
	private void shape1() {
		this.mCells[15][5].setExist(true);
		this.mCells[16][5].setExist(true);
		this.mCells[17][5].setExist(true);
		this.mCells[18][5].setExist(true);
		this.mCells[19][5].setExist(true);
	}
	private void shape2() {
		this.mCells[15][5].setExist(true);
		this.mCells[16][5].setExist(true);
		
		this.mCells[14][6].setExist(true);
		this.mCells[16][6].setExist(true);		
		this.mCells[14][7].setExist(true);
		this.mCells[16][7].setExist(true);
		this.mCells[15][8].setExist(true);
		
	}
	
	private void shape3() {
		this.mCells[15][5].setExist(true);
		this.mCells[16][5].setExist(true);
		
		this.mCells[14][6].setExist(true);
		this.mCells[15][6].setExist(true);	
		
		this.mCells[13][7].setExist(true);
		this.mCells[14][7].setExist(true);
		
		
		
	}
	
	private void genGrid() {
		
		for(int y =0; y <this.myDim; y++) {
			for(int x =0; x <this.mxDim; x++) {										
				//System.out.println("x : "+x + "y:" + y);
				this.mCells[x][y] = new Cell(x, y);								
			}
		}
	}
	
	public void showGrid() {		
		//System.out.println("[");
		for(int y =0; y <this.myDim; y++) {
			System.out.println("");
			for(int x =0; x <this.mxDim; x++) {
				System.out.print(this.mCells[x][y]);
			}
		}
		//System.out.println("]");
	}
	
	public void setCellExist(int pxpos, int pypos, boolean exist) {
		this.mCells[pxpos][pypos].setExist(exist);
	}
	
	public static void main(String[] args) {
		
		// Create and initialize the grid
		Grid g = new Grid(50,20);
		
		// Set initial form
		g.init();
		
		g.showGrid();
		
	
		// Start the iteration 
		
		for(int iteration = 1; iteration < 100; iteration++ ) {
			
			g.compute();
			g.showGrid();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	// cellule survie si 2 ou 3 voisin
	// naissance si 3 voisines 
	
	
	public  void compute() {
		
		Cell[][] cells = new Cell[this.mxDim][this.myDim]; 
		
		for(int y =0; y <this.myDim; y++) {
			for(int x =0; x <this.mxDim; x++) {										
				
				int xmu = x-1 < 0 ? 0 :x-1;
				int ymu = y-1 < 0 ? 0 : y-1;
				int xpu = x+1 >= this.mxDim ? this.mxDim-1 : x+1;
				int ypu = y+1 >= this.myDim ? this.myDim-1 : y+1;
				
				int z = 0;
				if(x==5 && y==5) {
					z++;
				}
				int z2 = z;
				
				int countExistingcellAround = 0; 
				
				// Détermination du nombre de cellule viante autour
				for(int localy = ymu; localy <= ypu; localy++) {
					for(int localx = xmu; localx <= xpu; localx++) {
						if(this.mCells[localx][localy].Exist() && !(localy ==y && localx ==x))
							countExistingcellAround++;
					}
				}
				
				// Cas de naissance ou de mort de cellule

				// Naissance
				
				if(!this.mCells[x][y].Exist() && countExistingcellAround == 3) {
					cells[x][y] = new Cell(x, y, true);	
				}
				else if (this.mCells[x][y].Exist()){
					if(!(countExistingcellAround == 2 || countExistingcellAround ==3))	{
						//
						cells[x][y] = new Cell(x, y, false);
						//cells[x][y].setExist(false);
					}else
					{
						//System.out.print(this.mCells[x][y]);
						cells[x][y] = new Cell(x, y, true);
						//cells[x][y].setExist(true);
					}
					
				}else {cells[x][y] =new Cell(x, y, false); }
																
			}
			
		}
		this.mCells = cells;
				
	}
		
}
