package AlgLin;

public class SysTriangInf extends SysLin {

	public SysTriangInf(Matrice matrice, Vecteur seconde) throws IrregularSysLinException {
		super(matrice, seconde);
	}

	@Override
	public Vecteur Resolution() throws IrregularSysLinException {

		Vecteur solution = new Vecteur(getOrdre());

		double somme;
		for (int nbL = 0; nbL < getOrdre(); nbL++) {
			somme = secondMembre.getElement(nbL);

			for (int nbC = 0; nbC <= nbL; nbC++)
				somme -= matriceSystem.getElement(nbL, nbC) * solution.getElement(nbC);

			if (matriceSystem.getElement(nbL, nbL) == 0)
				throw new IrregularSysLinException(" diagonal est null");

			solution.replaceElement(nbL, somme / matriceSystem.getElement(nbL, nbL));

		}

		return solution;
	}

	public static void main(String[] args) {
		double M[][] = { { 1.0, 0.0 }, { 1.0, 1.0 } };
		double V[] = { 1.0, 2.0 };

		Matrice mat = new Matrice(M);
		Vecteur vect = new Vecteur(V);
		Vecteur resultat = null;

		SysTriangInf m;
		try {
			m = new SysTriangInf(mat, vect);
			resultat = m.Resolution();

		} catch (IrregularSysLinException e) {
			e.printStackTrace();
		}

		System.out.println("la solution est :" + resultat);

	}

}
