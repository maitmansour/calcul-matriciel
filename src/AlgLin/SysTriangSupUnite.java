package AlgLin;

public class SysTriangSupUnite extends SysLin {

	public SysTriangSupUnite(Matrice matrice, Vecteur seconde) throws IrregularSysLinException {
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

			double termDiagonal = matriceSystem.getElement(i, i);

			if (termDiagonal == 0)
				throw new IrregularSysLinException(" Diagnonal est null");
			solution.replaceElement(i, somme);
		}
		return solution;

	}

	public static void main(String[] arg) {
		double Matrice[][] = { {1.0,2.0},{0.0,1.0} };

		double Vecteur[] = { 1.0, 1.0 };

		Matrice matrice = new Matrice(Matrice);
		Vecteur vecteur = new Vecteur(Vecteur);
		Vecteur result = null;

		SysTriangSupUnite infUnite;
		try {
			infUnite = new SysTriangSupUnite(matrice, vecteur);
			result = infUnite.Resolution();
		} catch (IrregularSysLinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("la solution trouver est :" + result);

	}
}
