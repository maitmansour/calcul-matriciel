package AlgLin;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class Matrice {
	
	//Les attributs à définir
	private double coefficient[][];
	
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
	// A terminer plus tard
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
					
				//	System.out.println(sCurrentLine);

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
			e.printStackTrace();
		}

	}
	
	
	
	void copy(Matrice tmp){
		if(this!=tmp){
			coefficient=null;
			coefficient = new double [tmp.coefficient.length][tmp.coefficient[0].length];
			for (int i = 0; i < tmp.coefficient.length; i++) {
				for (int j = 0; j < tmp.coefficient[i].length; j++) {
					coefficient[i][j]=tmp.coefficient[i][i];
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
        DecimalFormat format = new DecimalFormat("#.00");

		String result="[";

		for (int i = 0; i < coefficient.length; i++) {
			for (int j = 0; j < coefficient[0].length; j++) {
				
				result+=" "+format.format(coefficient[i][j])+"";

			}
			result+="\n";
		}

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
	
	
	/*
	 *   public static Matrice multiplicationMatrices(Matrice m1, Matrice m2){
    Matrice resultat = new Matrice(m1.coefficient.length,m1.coefficient[0].length );
    resultat.copy(m1);
    for (int i = 0; i < m1.coefficient.length; i++) {
      for (int j = 0; j < m1.coefficient[0].length; j++) {
       for (int k = 0; k < m1.coefficient[0].length; k++) {

resultat.coefficient[i][j]+=m1.coefficient[i][k]*m2.coefficient[k][j];
  }
    }
       } 
   return resultat;
  }

	 */
	
	public static Matrice multiplicationMatrices(Matrice m1, Matrice m2){
		Matrice resultat = new Matrice(m1.coefficient.length,m1.coefficient[0].length );
		double somme =0;
		for (int i = 0; i < m1.coefficient.length; i++) {
			for (int j = 0; j < m1.coefficient[0].length; j++) {
				
				somme+= m1.coefficient[i][j]*m2.coefficient[i][j];
				if (!(j+1 < m1.coefficient[0].length)) {
					resultat.coefficient[i][j]=somme;
				}

			}
			System.out.println(somme);
			somme=0;
		}
		return resultat;
	}
	
	
	
	
	public static void main(String[] args) {
		Matrice m1 = new Matrice("C:\\Users\\NUMIDEA\\Desktop\\filename2.txt");
		Matrice m2 = new Matrice("C:\\Users\\NUMIDEA\\Desktop\\filename3.txt");
		Matrice m3 = additionMatrices(m1, m2);
		Matrice m4 = multiplicationMatrices(m1, m2);
		System.out.println(m4.toString()); 
	}
	
	

}
