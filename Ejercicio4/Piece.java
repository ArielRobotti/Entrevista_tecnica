import java.util.ArrayList;

public abstract class Piece{
	protected Color color;
	protected Position position;
	protected ArrayList<ArrayList<Position>> attackingLines;
	Piece(Color color, Position position){
		this.color = color;
		this.position = position;
	}
	protected ArrayList<ArrayList<Position>> getLinesAttack(Position position, int[][] directions){
		ArrayList<ArrayList<Position>> lines = new ArrayList<ArrayList<Position>>(directions.length);
		for(int i=0; i<directions.length; i++){
			ArrayList<Position> line = new ArrayList<Position>();
			int x = position.horiz + directions[i][0];
			int y = position.vert + directions[i][1];
			while((x<=7) & (y<=7) & (x>=0) & (y>=0)){
				line.add(new Position(x,y));
				x += directions[i][0];
				y += directions[i][1];
			}
			if(line.size() != 0) {
				lines.add(line);
			}					
		}
		return lines;
	}	
}
class Position{
	int vert;
	int horiz;
	int id;
	Position(int horiz, int vert){
		this.vert = vert;
		this.horiz = horiz;
		this.id = vert*8 + horiz;
	}
/*	@Override
	public String toString(){
		return(horiz+":"+vert);
	}	*/
}
enum Color{
	WHITE(1),BLACK(-1);
	public int moveTo;
	private Color(int moveTo){
		this.moveTo = moveTo;
	}

}
class PieceFactory{
	public static Piece makePiece(char kind, Color color, Position position){
		return switch (kind){
			case 'D' -> new Queen(color, position);
			case 'P' -> new Pawn(color, position);
			case 'A' -> new Bishop(color, position);
			case 'T' -> new Tower(color, position);
			case 'C' -> new Horse(color, position);
			case 'R' -> new King(color, position);
			default -> null;
		};
	}
}
class King extends Piece{
	King(Color color, Position position){
		super(color, position);
		this.attackingLines = new ArrayList<ArrayList<Position>>();
	}
}
class Queen extends Piece{
	Queen(Color color, Position position){
		super(color, position);
		int [][] directions = {{1,1},{1,-1},{-1,1},{-1,-1},{0,1},{0,-1},{1,0},{-1,0}};
		this.attackingLines = new ArrayList<ArrayList<Position>>();
		this.attackingLines = getLinesAttack(position, directions);
	}
}
class Tower extends Piece{
	Tower(Color color, Position position){	
		super(color, position);
		int [][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
		this.attackingLines = new ArrayList<ArrayList<Position>>();
		this.attackingLines = getLinesAttack(position, directions);
	}
}
class Bishop extends Piece{
	Bishop(Color color, Position position){	
		super(color, position);
		int [][] directions = {{1,1},{1,-1},{-1,1},{-1,-1}};
		this.attackingLines = new ArrayList<ArrayList<Position>>();
		this.attackingLines = getLinesAttack(position,directions);
	}
}
class Horse extends Piece{
	Horse(Color color, Position position){
		super(color, position);
		this.attackingLines = new ArrayList<ArrayList<Position>>();
		ArrayList<Position> positionsAttack = new ArrayList<Position>();
		int pos [][] = {{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};
		for(int i=0; i<8; i++){
			Position attack = new Position(position.horiz+pos [i][0],position.vert+pos [i][1]);
			if(attack.vert<=7 & attack.horiz<=7 & attack.vert>=0 & attack.horiz>=0){
				positionsAttack.add(attack);
			}
		}
		this.attackingLines.add(positionsAttack);
	}
}
class Pawn extends Piece{
	Pawn(Color color, Position position){
		super(color, position);
		this.attackingLines = new ArrayList<ArrayList<Position>>();
		this.attackingLines.add(new ArrayList<Position>(1));	
		if(position.vert < 7) {
			this.attackingLines.get(0).add(new Position(position.horiz+color.moveTo, position.vert+1));
		}
		if(position.vert > 0) {
			this.attackingLines.get(0).add(new Position(position.horiz+color.moveTo, position.vert-1));
		}
	}
}