package AlgLin;

public class SysDiagonal extends SysLin {

	public SysDiagonal(Matrice matrice, Vecteur seconde) throws IrregularSysLinException {
		super(matrice, seconde);
	}

	@Override
	public Vecteur Resolution() throws IrregularSysLinException {

		Vecteur solution = new Vecteur(getOrdre());
		for (int i = 0; i < getOrdre(); i++) {
			if (matriceSystem.getElement(i, i) == 0)
				throw new IrregularSysLinException("SOLUTION INNEXISTANTE");

			if (matriceSystem.getElement(i, i) == 0)
				throw new IrregularSysLinException("SOLUTIONS INFINIES");
			System.out.println("test : " + secondMembre.getElement(i) / matriceSystem.getElement(i, i));
			solution.replaceElement(i, secondMembre.getElement(i) / matriceSystem.getElement(i, i));
		}

		// System.out.println("test");
		return solution;
	}

	public static void main(String[] args) {

		double Vect[] = { 2.0, 2.0 };

		double Mat[][] = { { 2.0, 0.0 }, { 0.0, 2.0 } };

		Matrice M = new Matrice(Mat);
		Vecteur V = new Vecteur(Vect);

		Vecteur resultat = null;

		SysDiagonal SD = null;
		try {
			SD = new SysDiagonal(M, V);

		} catch (IrregularSysLinException e) {
			e.printStackTrace();
		}

		try {
			resultat = SD.Resolution();
		} catch (IrregularSysLinException e) {
			e.printStackTrace();
		}

		System.out.println("la solution est : \n" + resultat);

	}

}
