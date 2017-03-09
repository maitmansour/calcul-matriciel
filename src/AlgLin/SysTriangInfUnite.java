package AlgLin;

public class SysTriangInfUnite extends SysLin {

	public SysTriangInfUnite(Matrice matrice, Vecteur seconde) throws IrregularSysLinException {
		super(matrice, seconde);

	}
	// la methode de resolution qui retourne une exception en cas d'une matrice

	@Override
	public Vecteur Resolution() throws IrregularSysLinException {

		Vecteur solution = new Vecteur(getOrdre());

		double somme;
		for (int nbL = 0; nbL < getOrdre(); nbL++) {
			somme = secondMembre.getElement(nbL);

			for (int nbC = 0; nbC <= nbL; nbC++)

				somme -= matriceSystem.getElement(nbL, nbC) * solution.getElement(nbC);
			if (matriceSystem.getElement(nbL, nbL) == 0)
				throw new IrregularSysLinException("diagonal null");

			solution.replaceElement(nbL, somme);

		}

		return solution;

	}

	public static void main(String[] arg) {
		double Matrice[][] = { { 1.0, 0.0 }, { 2.0, 1.0 } };

		double Vecteur[] = { 1.0, 1.0 };

		Matrice matrice = new Matrice(Matrice);
		Vecteur vecteur = new Vecteur(Vecteur);
		Vecteur result = null;

		SysTriangInfUnite infUnite;
		try {
			infUnite = new SysTriangInfUnite(matrice, vecteur);
			result = infUnite.Resolution();
		} catch (IrregularSysLinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("la solution trouver est :" + result);

	}
}
