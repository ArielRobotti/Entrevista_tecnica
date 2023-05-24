import java.io.*;
import java.io.BufferedReader;

public class Ejercicio6{
	public static void main(String[] args){
		String path = (args.length > 0) ? args[0]: "ejercicio6-input.txt";
		/*try {
			FileReader file = new FileReader(path);
			BufferedReader br = new BufferedReader(file);
			String line;
			while((line=br.readLine()) != null){
				if (line.contains("#")){
					System.out.print(line.charAt(line.length()-1)+". ");
				} 
				else{

				}
			}
		}		
		catch (IOException e){
			System.out.print("El archivo no existe");
		}*/
		Grafitti root = new Grafitti(1400,2000);
		System.out.println(root);
		root.insertGrafitti(200,1000);
		System.out.println(root.left);
		root.insertGrafitti(100,150);
		System.out.println(root.left.left);
		root.insertGrafitti(100,1500);
		System.out.println(root.left);
		System.out.println(Grafitti.qty);
	}
}

class Grafitti{
	static int qty = 0;
	int start;
	int end;
	Grafitti left = null;
	Grafitti rigth = null;
	public Grafitti(int start, int end){
		this.start = start;
		this.end = end;
		qty ++;
	}
	@Override
	public String toString(){
		return (this.start + " : "+ this.end);
	}
	public void insertGrafitti (int start, int end){

		if(start < this.start){
			if(end < this.start){
				if(this.left != null){
					this.left.insertGrafitti(start, end);
				}
				else{
					this.left = new Grafitti(start, end);
				}
			}
			else{
				if(end > this.end){
					this.start = start;
					this.end = end;
					this.left = null;
					return;
				}
				if(this.left != null){
					this.left.insertGrafitti(start, end);
				}
				else{
					this.start = start;
				}
			}
		}
			
	}
}