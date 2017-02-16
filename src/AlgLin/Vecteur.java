package AlgLin;

import java.util.Arrays;

public class Vecteur extends Matrice{

	public Vecteur(int nblignes) {
		super(nblignes,1);
	}

	public Vecteur(double [] tmpTable) {
		this(tmpTable.length);
		for (int i = 0; i < tmpTable.length; i++) {
			replaceElement(i, 0, tmpTable[i]);
		}
		
	}
	

	public Vecteur(String FILENAME) {
		super(FILENAME);
	}
	
	public int renvoieTaille(){
		return getCoefficient().length;
	}
	
	double getElement(int emplacement) {
		return super.getElement(emplacement, 0);
	}
	
	void replaceElement(int emplacement, double element) {
		super.replaceElement(emplacement, 0, element);
	}
	
	public static double produitScalaire(Vecteur v1, Vecteur v2) throws Exception {
		double somme = 0.00000;
		if (v1.renvoieTaille()==v2.renvoieTaille()) {
			for (int i = 0; i < v1.getCoefficient().length; i++) {
				somme+=v1.getElement(i)*v2.getElement(i);

			}
			return somme;
			
		}
		throw new Exception("PRODUIT NON EFFECTUE - TAILLE INCOMPATIBLE");
	}
	
	public static void main(String[] args) {
		Vecteur v1 = new Vecteur("//home//etudiant//am163898//Bureau//filename4.txt");
		//System.out.println(v1.toString()); 
		double[] tmp = {9,8,7,6,5,4,3,2,1};
		Vecteur v2 = new Vecteur(tmp);
		System.out.println(v2.toString()); 
		System.out.println("taille de vecteur : "+v2.renvoieTaille()); 
		System.out.println("Element situe a 2 : "+v2.getElement(2)); 
		v2.replaceElement(2, 5);
		System.out.println("Element situe a 2 apres remplacement: "+v2.getElement(2)); 
		System.out.println("\n \n Affichage de vecteur : \n"+v2.toString()); 
		try {
			System.out.println("\n \n Produit scalaire des deux vecteurs v1*v2 :"+produitScalaire(v1, v2));
		} catch (Exception e) {
			e.printStackTrace();
		} 

		
		
	}


	

}
