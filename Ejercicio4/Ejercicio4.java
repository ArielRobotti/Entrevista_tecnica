import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ejercicio4{
	public static void main(String[] args){
		String path = (args.length > 0) ? args[0] : "ejercicio4-input.txt";	
		try{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String numOfCase = br.readLine();
			while(numOfCase != null){
				ArrayList<Piece> pieces = new ArrayList<Piece>(32);
				System.out.print(numOfCase.charAt(numOfCase.length()-1) + ". ");
				for (int i=0; i<8; i++){
					int j = 0;
					for(char pieza : br.readLine().toCharArray()){
						if(pieza != '0'){
							pieces.add(PieceFactory.makePiece(pieza,Color.WHITE,new Position(i,j)));
						}
						j++;
	        		}
				}
				for (int i=0; i<8; i++){
					int j = 0;
	        		for(char pieza : br.readLine().toCharArray()){
						if(pieza != '0'){
							pieces.add(PieceFactory.makePiece(pieza,Color.BLACK,new Position(i,j)));
						}
						j++;
	        		}
	        	}
				numOfCase = br.readLine();
				if(isCheck(pieces)){
					System.out.println("Hay jaque");
				}
				else{
					System.out.println("No hay jaque");
				}					
			}
		}
		catch (IOException e){

			System.out.print("El archivo no existe");
		}	
	}
	private static boolean isCheck(ArrayList<Piece> pieces){
		boolean brokenLine = false;
		for(Piece striker: pieces){
			for(ArrayList<Position> line : striker.attackingLines){
				brokenLine = false;
				for(Position pos : line){
					for(Piece attacked: pieces){
						if (attacked.position.id == pos.id){
							brokenLine = true;
							if ((attacked.color != striker.color)&(attacked.attackingLines.size() == 0)){
								return true;		
								}
							break;	
						}
					}
					if(brokenLine) {
						break;
					}
				}
			}	
		}
		return false;
	}
}