package AlgLin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

public class Matrice {

	private double coefficient[][];


	Matrice inverse() throws IllegalOperationException {
		if (getNbColonnes() != getNbLignes()) {
			throw new IllegalOperationException("Matrice NON CARREE");
		} else {

			Vecteur[] inverseVecteurs = new Vecteur[getNbColonnes()];
			Matrice inverse = new Matrice(getNbLignes(), getNbColonnes());

			// creation de la matrice identite
			/* 1000
			 * 0100
			 * 0010
			 * 0001
			 */
			Matrice identite = new Matrice(getNbLignes(), getNbColonnes());
			for (int i = 0; i < getNbLignes(); i++) {
				for (int j = 0; j < getNbColonnes(); j++) {
					if (i == j) {
						identite.replaceElement(i, j, 1);
					} else {
						identite.replaceElement(i, j, 0);
					} // else
				} // for
			} // for

			//System.out.println(identite);

			
			
			//Recuperation de chaque colonne de la matrice d'identit� comme Vecteur
			Vecteur[] iemColumIdentite = new Vecteur[getNbColonnes()];
			for (int i = 0; i < iemColumIdentite.length; i++) {
				iemColumIdentite[i]= new Vecteur(getNbColonnes());
			}
			System.out.println(iemColumIdentite[0]);

			for (int iemColumn = 0; iemColumn < getNbLignes(); iemColumn++) {
				for (int cmpColonne = 0; cmpColonne < getNbLignes(); cmpColonne++) {
					for (int cmpLignePourMatrice = 0; cmpLignePourMatrice < getNbLignes(); cmpLignePourMatrice++) {
						iemColumIdentite[iemColumn].replaceElement(cmpColonne, identite.getElement(cmpLignePourMatrice, cmpColonne));
					} // for
				} // for
			} // for

			
			
			Helder resultat = null;
			try {
				
				//Resolution pour chaque vecteur de les iemes Vecteurs
				int nbV = 0;
				while (nbV < getNbLignes()) {
					resultat = new Helder(this, iemColumIdentite[nbV]);
					inverseVecteurs[nbV] = resultat.Resolution();
					nbV++;
					resultat = null;
					} // while

			} catch (IrregularSysLinException e) {
				e.printStackTrace();
			}

			// construction de la nouvelle Matrice d'inverse
			for (int cmptVecteurTraite = 0; cmptVecteurTraite < getNbLignes(); cmptVecteurTraite++) {
				for (int nbLMatrice = 0; nbLMatrice < getNbLignes(); nbLMatrice++) {
					for (int nbCMatetVect = 0; nbCMatetVect < getNbLignes(); nbCMatetVect++) {
						inverse.replaceElement(nbLMatrice, nbCMatetVect, inverseVecteurs[cmptVecteurTraite].getElement(nbCMatetVect));
					} // for
				} // for
			} // for

			System.out.println("la solution trouver est :" + inverse);
			return inverse;

		}


	}

	
	public double norme_1() {
		double norme;
		double[] somme = new double[getNbColonnes()];
		for (int i = 0; i < somme.length; i++) {
			somme[i]=0.00000;
		}
		for (int i = 0; i < getNbLignes(); i++) {
			for (int j = 0; j < getNbColonnes(); j++) {
				somme[i]+=getElement(i, j);
			}
		}
		
		norme=somme[0];
		for (int i = 0; i < somme.length; i++) {
			norme= Math.max(norme, somme[i]);
		}
		
		return norme;
	}
	
	
	

	public double[][] getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double[][] coefficient) {
		this.coefficient = coefficient;
	}

	public Matrice(int nblignes, int nbcolonnes) {
	//	System.out.println("CONSTRUCTEUR : public Matrice( " + nblignes + ", " + nbcolonnes + ")");
		coefficient = new double[nblignes][nbcolonnes];
	}

	public Matrice(double[][] TmpTable) {
		int lignes = TmpTable.length;
		int colonnes = TmpTable[0].length;
	//	System.out.println("CONSTRUCTEUR : public Matrice( Table[" + lignes + "][" + colonnes + "] ) ");
		coefficient = new double[lignes][colonnes];
		for (int i = 0; i < TmpTable.length; i++) {
			for (int j = 0; j < TmpTable[i].length; j++) {
				System.out.println(TmpTable[i][j]);

				coefficient[i][j] = TmpTable[i][j];
			}
		}
	}

	Matrice(String nomFichier) {
		//System.out.println("CONSTRUCTEUR : public Matrice( " + nomFichier + " ) ");
		try {
			Scanner sc = new Scanner(new File(nomFichier));

			sc.useLocale(Locale.US);
			int lignes = sc.nextInt();
			int colonnes = sc.nextInt();
			System.out.println(lignes);
			coefficient = new double[lignes][colonnes];
			for (int i = 0; i < lignes; i++)
				for (int j = 0; j < colonnes; j++)
					coefficient[i][j] = sc.nextDouble();
		} catch (FileNotFoundException e) {
			System.out.println("fichier inexistant");
		}
	}
	/*
	 * public Matrice(String FILENAME) {
	 * System.out.println("CONSTRUCTEUR : public Matrice( Fichier ) "); try
	 * (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
	 * 
	 * String sCurrentLine; int ligneFile=0; int ligneMatrice=0;
	 * 
	 * int colonneMatrice=0; int nbLignes = 0,nbColonnes = 0; String
	 * dataMatrice=""; while ((sCurrentLine = br.readLine()) != null) { if
	 * (ligneFile==0) { nbLignes= Integer.parseInt(sCurrentLine); ligneFile++; }
	 * else if (ligneFile==1) { nbColonnes= Integer.parseInt(sCurrentLine);
	 * ligneFile++; } else{ if (ligneFile==2) { coefficient= new
	 * double[nbLignes][nbColonnes]; }
	 * 
	 * dataMatrice+=sCurrentLine; } }
	 * 
	 * String[] lines = dataMatrice.split("0"); int nbL=0; int nbC=0; for (int i
	 * = 0; i < nbLignes*nbColonnes; i++) {
	 * 
	 * for (int j = 0; j < nbLignes; j++) { for (int j2 = 0; j2 < nbColonnes;
	 * j2++) { coefficient[j][j2]=Double.parseDouble(lines[i]); i++; }
	 * 
	 * } }
	 * 
	 * 
	 * } catch (IOException e) { System.out.println("FICHIER ERRONNE"); }
	 * 
	 * }
	 * 
	 */

	void copy(Matrice tmp) {

		int lignes = tmp.getNbLignes();
		int colonnes = tmp.getNbColonnes();
		this.coefficient = new double[lignes][colonnes];

		for (int nbL = 0; nbL < getNbLignes(); nbL++) {
			for (int nbC = 0; nbC < getNbColonnes(); nbC++)

				replaceElement(nbL, nbC, tmp.getElement(nbL, nbC));
		}

		/*
		 * if(this!=tmp){ coefficient = null; coefficient = new double
		 * [tmp.getNbLignes()][tmp.getNbColonnes()]; for (int i = 0; i <
		 * tmp.getNbLignes(); i++) { for (int j = 0; j < tmp.getNbColonnes();
		 * j++) { coefficient[i][j]=tmp.coefficient[i][j];
		 * System.out.println(tmp.coefficient[i][j]); } }
		 * 
		 * }
		 */
	}

	void NbElements() {
		System.out.println("Nombre de lignes :" + getNbLignes());
		System.out.println("Nombre de colonnes :" + getNbColonnes());

	}

	double getElement(int nbL, int nbC) {
		return coefficient[nbL][nbC];
	}

	void replaceElement(int nbL, int nbC, double element) {
		coefficient[nbL][nbC] = element;
	}

	@Override
	public String toString() {
		DecimalFormat format = new DecimalFormat("   .00");

		String result = "[";

		for (int i = 0; i < getNbLignes(); i++) {
			for (int j = 0; j < getNbColonnes(); j++) {

				result += format.format(coefficient[i][j]) + ",";

			}
			result += "\n";
		}

		result = result.substring(0, result.length() - 1);
		result += " ]";
		return result;
	}

	void produitScalaire(double scalaire) {
		for (int i = 0; i < getNbLignes(); i++) {
			for (int j = 0; j < getNbColonnes(); j++) {

				coefficient[i][j] *= scalaire;

			}
		}
	}

	public static Matrice additionMatrices(Matrice m1, Matrice m2) {
		Matrice resultat = new Matrice(m1.getNbLignes(), m1.getNbColonnes());
		resultat.copy(m1);
		for (int i = 0; i < m1.getNbLignes(); i++) {
			for (int j = 0; j < m1.getNbColonnes(); j++) {

				resultat.coefficient[i][j] += m2.coefficient[i][j];

			}
		}
		return resultat;
	}

	public int getNbLignes() {
		return getCoefficient().length;
	}

	public int getNbColonnes() {
		return getCoefficient()[0].length;
	}

	public static Matrice multiplicationMatrices(Matrice m1, Matrice m2) throws Exception {
		if (m1.getNbLignes() == m2.getNbLignes()) {
			if (m1.getNbColonnes() == m2.getNbColonnes()) {
				Matrice resultat = new Matrice(m1.getNbLignes(), m1.getNbColonnes());
				// Initialisation de tableau
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						resultat.coefficient[i][j] = 0.00000;
					}
				}

				// calcul
				for (int i = 0; i < m1.getNbLignes(); i++) {
					for (int j = 0; j < m1.getNbColonnes(); j++) {
						for (int k = 0; k < m1.getNbColonnes(); k++) {

							resultat.coefficient[i][j] += m1.coefficient[i][k] * m2.coefficient[k][j];
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

	public static void showmenu() {
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
		System.out.println("9.Inverse de matrice");
		System.out.println("10.Norme 1");
		System.out.println("8.exit");
	}

	public static void select() {
		int nb, x, val, pos, ch, yes = 0;
		String str;
		Matrice m1 = null;
		Matrice m2 = null;
		Scanner sc = new Scanner(System.in);
		try {
			while (yes == 0) {
				showmenu();

				System.out.println("\n \n \n \n");
				System.out.print("Entrer votre choix :");
				ch = sc.nextInt();
				switch (ch) {
				case 0:
					m1 = new Matrice("matrice1.txt");
					m2 = new Matrice("matrice1.txt");

					break;
				case 1:
					System.out.println(m1.toString());
					System.out.println("\n \n");
					System.out.println(m2.toString());
					break;
				case 2:

					// Nombre element d'une matrice
					System.out.println("Nombre elements M1");
					m1.NbElements();

					break;
				case 3:

					// Produit Scalaire avec une matrice
					System.out.println("Produit Scalaire m1*5.2");
					m1.produitScalaire(5.2);
					System.out.println(m1.toString());

					break;
				case 4:

					// R�cup�rer l'element situe a M1[2][2]
					System.out.println("R�cup�rer l'element situe a M1[2][2]= " + m1.getElement(2, 2));

					break;

				case 5:

					// Remplacer l'element situe a M1[2][2]
					m1.replaceElement(2, 2, 5.3);
					break;
				case 6:
					// Addition deux matrices
					Matrice m3 = Matrice.additionMatrices(m1, m2);
					System.out.println("Addition deux Matrices");
					System.out.println(m3.toString());
					break;
				case 7:

					// Multiplication deux matrices
					System.out.println("Multiplication deux Matrices");
					Matrice m4 = Matrice.multiplicationMatrices(m1, m2);
					System.out.println(m4.toString());
					break;
				case 10:

					System.out.println("Norme 1");
					System.out.println(m1.norme_1());
					break;
				case 8:
					System.exit(0);
				case 9:
					System.out.println(m1);
					try {
						System.out.println(m1.inverse());
					} catch (IllegalOperationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				default:
					System.out.println("Invalid choice!");

				}

				System.out.print("Continue? Press 0 to continue:");
				yes = sc.nextInt();

			}

		} catch (Exception e) {
			System.exit(100);
		}

	}

}
