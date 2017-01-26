package AlgLin;
import AlgLin.*;
import java.util.Scanner;

public class Testes {

	public static void main(String[] args) {
		select();
	}
	
	public static void showmenu(){
		System.out.println("===========================================");
		System.out.println("Menu des testes  possibles sur les matrices");
		System.out.println("===========================================");
		System.out.println("0.Creation des matrices ");
		System.out.println("1.Affihage des matrices");
		System.out.println("2.Afficher Nombre d'element d'une matrice");
		System.out.println("3.Produit Scalaire avec une matrice (ex. 5.2)");
		System.out.println("4.R�cup�rer l'element situe a M1[2][2]");
		System.out.println("5.Remplacer l'element situe a M1[2][2] par 5.3");
		System.out.println("6.Addition deux matrices");
		System.out.println("7.Multiplication deux matrices");
		System.out.println("8.exit");		
	}
	


	public static void select(){
		int nb,x,val, pos, ch,yes=0;
		String str;
		Matrice m1 = null;
		Matrice m2 = null;
		Scanner sc=new Scanner(System.in);
		try{
			while(yes==0){
				showmenu();

				System.out.println("\n \n \n \n");
				System.out.print("Entrer votre choix :");
				ch=sc.nextInt();
				switch(ch){
					case 0:
					m1 = new Matrice("//home//etudiant//am163898//Bureau//filename2.txt");
					m2 = new Matrice("//home//etudiant//am163898//Bureau//filename3.txt");

					break;
					case 1:
					System.out.println(m1.toString()); 
					System.out.println("\n \n"); 
					System.out.println(m2.toString()); 
					break;
					case 2:
					
	//Nombre element d'une matrice
					System.out.println("Nombre elements M1"); 
					m1.NbElements();

					break;
					case 3:
					
	//Produit Scalaire avec une matrice
					System.out.println("Produit Scalaire m1*5.2"); 
					m1.produitScalaire(5.2);
					System.out.println(m1.toString()); 
					
					break;
					case 4:
					
    //R�cup�rer l'element situe a M1[2][2]
					System.out.println("R�cup�rer l'element situe a M1[2][2]= "+m1.getElement(2, 2)); 

					break;

					case 5 :

	//Remplacer l'element situe a M1[2][2]
					m1.replaceElement(2, 2,5.3); 
					break;
					case 6:
	//Addition deux matrices
					Matrice m3 = Matrice.additionMatrices(m1, m2);
					System.out.println("Addition deux Matrices"); 
					System.out.println(m3.toString()); 
					break;
					case 7:

	//Multiplication deux matrices
					System.out.println("Multiplication deux Matrices"); 
					Matrice m4 = Matrice.multiplicationMatrices(m1, m2);
					System.out.println(m4.toString()); 
					break;
					case 8:System.exit(0);
					default: System.out.println("Invalid choice!");

				}


				System.out.print("Continue? Press 0 to continue:");
				yes=sc.nextInt();

			}

		}catch(Exception e){System.exit(100);}

	}




}
