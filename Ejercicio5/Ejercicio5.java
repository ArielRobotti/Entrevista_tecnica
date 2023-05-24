import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Ejercicio5{
	public static void main(String[] args){
		String path = (args.length > 0) ? args[0]: "ejercicio5-input.txt";
		try {
			FileReader file = new FileReader(path);
			BufferedReader br = new BufferedReader(file);
			String line;
			while((line=br.readLine()) != null){
				if (line.contains("#")){
					System.out.print(line.charAt(line.length()-1)+". ");
				} 
				else{
					System.out.println(with_3_consecutive_curtains(Integer.parseInt(line)));
				}
			}
		}		
		catch (IOException e){
			System.out.print("El archivo no existe");
		}	
	}
	static long with_3_consecutive_curtains(int n){
		return (long) (Math.pow(2,(n-3)) * (1+ (n-3)/2.0)-(n-4)/3);
	}
}
