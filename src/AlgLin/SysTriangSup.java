package AlgLin;

public class SysTriangSup extends SysLin {

	public SysTriangSup(Matrice matrice, Vecteur seconde) throws IrregularSysLinException {
		super(matrice, seconde);
	}

	@Override
	public Vecteur Resolution() throws IrregularSysLinException {
		Vecteur solution = new Vecteur(getOrdre());
		double somme;

		for (int i = getOrdre() - 1; i >= 0; i--) {
			somme = secondMembre.getElement(i);

			for (int j = i + 1; j < getOrdre(); j++)

				somme -= matriceSystem.getElement(i, j) * solution.getElement(j);

			double D = matriceSystem.getElement(i, i);
			if (D == 0)
				throw new IrregularSysLinException("diagnonal est null");

			solution.replaceElement(i, somme / matriceSystem.getElement(i, i));
		}

		return solution;
	}

	public static void main(String[] arg) {
		double Matrice[][] = { { 1.0, 1.0 }, { 0.0, 1.0 } };
		double Vecteur[] = { 1.0, 0.0 };

		Matrice matrice = new Matrice(Matrice);
		Vecteur vecteur = new Vecteur(Vecteur);
		Vecteur resultat = null;

		SysTriangInf m;
		try {
			m = new SysTriangInf(matrice, vecteur);
			resultat = m.Resolution();
		} catch (IrregularSysLinException e) {
			e.printStackTrace();
		}

		System.out.println("la solution est :" + resultat);

	}

}
