import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

class Ejercicio3{	
	public static void main(String[] args){
		String path = (args.length > 0) ? args[0]: "ejercicio3-input.txt";
		try {
			FileReader file = new FileReader(path);
			BufferedReader br = new BufferedReader(file);
			String line;
			while((line=br.readLine()) != null){
				if (line.contains("#")) {
					System.out.print(line.charAt(line.length()-1)+". ");
				}
				else {
					System.out.println(isMirrorable(line));
				}
			}
		}		
		catch (IOException e){
			System.out.print("El archivo no existe");
		}	
	}
	static String isMirrorable(String line){
	 	for(int c=0; c<2;c++){
	 		int left = Character.getNumericValue(line.charAt(c));
	 		int rigth = Character.getNumericValue(line.charAt(line.length()-1-c));
	 		if((left+rigth != 0) && (left*rigth != 10)){
	 			return("No se ve igual");
	 		} 	
	 	}
	 	return("Se ve igual");
	 }
}