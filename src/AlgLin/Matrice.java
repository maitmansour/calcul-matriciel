package AlgLin;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Matrice {
	
	//Les attributs a definir
	private double coefficient[][];
	
	public double[][] getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(double[][] coefficient) {
		this.coefficient = coefficient;
	}
	public Matrice(int nblignes, int nbcolonnes) {
		System.out.println("CONSTRUCTEUR : public Matrice( "+ nblignes+", "+nbcolonnes+")");
		coefficient= new double[nblignes][nbcolonnes];
	}	
	public Matrice(double TmpTable[][]) {
		System.out.println("CONSTRUCTEUR : public Matrice( Table ) ");
		for (int i = 0; i < TmpTable.length; i++) {
			for (int j = 0; j < TmpTable[i].length; j++) {
				coefficient[i][i]=TmpTable[i][j];
			}
		}
	}
	public Matrice(String FILENAME) {
		System.out.println("CONSTRUCTEUR : public Matrice( Fichier ) ");
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			String sCurrentLine;
			int ligneFile=0;
			int ligneMatrice=0;
			
			int colonneMatrice=0;
			int nbLignes = 0,nbColonnes = 0;
			String dataMatrice="";
			while ((sCurrentLine = br.readLine()) != null) {
				if (ligneFile==0) {
					nbLignes= Integer.parseInt(sCurrentLine); 
					ligneFile++;
				}	
				else if (ligneFile==1) {
					nbColonnes= Integer.parseInt(sCurrentLine);
					ligneFile++;
				}
				else{
					if (ligneFile==2) {
						coefficient= new double[nbLignes][nbColonnes];
					}
					
					dataMatrice+=sCurrentLine+"-";
				}
			}
			
			String[] lines = dataMatrice.split("-");
			int nbL=0;
			int nbC=0;
			for (int i = 0; i < nbLignes*nbColonnes; i++) {
				
				for (int j = 0; j < nbLignes; j++) {
					for (int j2 = 0; j2 < nbColonnes; j2++) {
						coefficient[j][j2]=Double.parseDouble(lines[i]);
						i++;
					}
					
				}
			}


		} catch (IOException e) {
			System.out.println("FICHIER ERRONNE"); 
		}

	}
	
	
	
	void copy(Matrice tmp){
		if(this!=tmp){
			coefficient = null;
			coefficient = new double [tmp.coefficient.length][tmp.coefficient[0].length];
			for (int i = 0; i < tmp.coefficient.length; i++) {
				for (int j = 0; j < tmp.coefficient[0].length; j++) {
					coefficient[i][j]=tmp.coefficient[i][j];
				}
			}
			
		}
	}
	
	
	void NbElements(){
		System.out.println("Nombre de lignes :"+coefficient.length);
		System.out.println("Nombre de colonnes :"+coefficient[0].length);
		
	}
	
	double getElement(int nbL, int nbC){
		return coefficient[nbL][nbC];
	}
	
	void replaceElement(int nbL, int nbC, double element) {
		coefficient[nbL][nbC]=element;
	}
	
	@Override
	public String toString() {
		DecimalFormat format = new DecimalFormat("   .00");

		String result="[";

		for (int i = 0; i < coefficient.length; i++) {
			for (int j = 0; j < coefficient[0].length; j++) {
				
				result+=format.format(coefficient[i][j])+",";

			}
			result+="\n";
		}

		result=result.substring(0, result.length()-1);
		result+=" ]";
		return result;
	}
	
	void produitScalaire(double scalaire){
		for (int i = 0; i < coefficient.length; i++) {
			for (int j = 0; j < coefficient[0].length; j++) {
				
				coefficient[i][j]*=scalaire;

			}
		}
	}
	
	public static Matrice additionMatrices(Matrice m1, Matrice m2){
		Matrice resultat = new Matrice(m1.coefficient.length,m1.coefficient[0].length );
		resultat.copy(m1);
		for (int i = 0; i < m1.coefficient.length; i++) {
			for (int j = 0; j < m1.coefficient[0].length; j++) {
				
				resultat.coefficient[i][j]+=m2.coefficient[i][j];

			}
		}
		return resultat;
	}
	
	public static Matrice multiplicationMatrices(Matrice m1, Matrice m2) throws Exception{
		if (m1.getCoefficient().length==m2.getCoefficient().length){
			if (m1.getCoefficient()[0].length==m2.getCoefficient()[0].length){
				Matrice resultat = new Matrice(m1.coefficient.length,m1.coefficient[0].length );
			    // Initialisation de tableau
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							resultat.coefficient[i][j] = 0.00000;
						}
					}
					
			    //calcul 
					for (int i = 0; i < m1.coefficient.length; i++) {
						for (int j = 0; j < m1.coefficient[0].length; j++) {
							for (int k = 0; k < m1.coefficient[0].length; k++) {

								resultat.coefficient[i][j]+=m1.coefficient[i][k]*m2.coefficient[k][j];
							}
						}
					} 
					return resultat;
			}

		}
		
		throw new Exception("MULTUPLICATION IMPOSSIBLE - MATRICES NON EGAUX");
		
		
	}
	
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
		System.out.println("4.Recuperer l'element situe a M1[2][2]");
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
					m2 = new Matrice("//home//etudiant//am163898//Bureau//filename22.txt");

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
