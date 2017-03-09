package AlgLin;

public class Helder extends SysLin {

	public Helder(Matrice matrice, Vecteur seconde) throws IrregularSysLinException {
		super(matrice, seconde);
	}

	@Override
	public Vecteur Resolution() throws IrregularSysLinException {
		factorLDR();
		return ResolutionPartielle();
	}

	// factorisation LDR
	public void factorLDR() throws IrregularSysLinException {

		int nbL, nbC, cmp;

		double somme1, somme2, Diagonal;
		for (nbL = 0; nbL < matriceSystem.getNbLignes(); nbL++) {

			for (nbC = 0; nbC < nbL - 1; nbC++) {

				somme1 = matriceSystem.getElement(nbL, nbC);
				for (cmp = 0; cmp < nbC - 1; cmp++) {

					somme1 -= matriceSystem.getElement(nbL, cmp) * matriceSystem.getElement(cmp, cmp)
							* matriceSystem.getElement(cmp, nbC);

				}
				somme1 = (1 / matriceSystem.getElement(nbC, nbC)) * somme1;
				matriceSystem.replaceElement(nbL, nbC, somme1);
			}
			Diagonal = matriceSystem.getElement(nbL, nbL);

			for (cmp = 0; cmp < nbL - 1; cmp++)
				Diagonal -= matriceSystem.getElement(nbL, cmp) * matriceSystem.getElement(cmp, cmp)
						* matriceSystem.getElement(cmp, nbC);

			matriceSystem.replaceElement(nbL, nbL, Diagonal);

			for (nbC = nbL + 1; nbC < matriceSystem.getNbColonnes(); nbC++) {
				somme2 = matriceSystem.getElement(nbL, nbC);
				for (cmp = 0; cmp < nbL; cmp++) {
					somme2 -= matriceSystem.getElement(nbL, cmp) * matriceSystem.getElement(cmp, cmp)
							* matriceSystem.getElement(cmp, nbC);
				}

				somme2 = (1 / matriceSystem.getElement(nbL, nbL)) * somme2;
				matriceSystem.replaceElement(nbL, nbC, somme2);

			}
		}

	}

	// la méthode Resolutionpartielle

	public Vecteur ResolutionPartielle() throws IrregularSysLinException {
		Vecteur a1, a2, resultat;
		SysTriangInfUnite L = new SysTriangInfUnite(matriceSystem, secondMembre);

		a1 = L.Resolution();

		SysDiagonal D = new SysDiagonal(matriceSystem, a1);

		a2 = D.Resolution();

		SysTriangSupUnite R = new SysTriangSupUnite(matriceSystem, a2);

		resultat = R.Resolution();

		return resultat;
	}

	public static void main(String[] args) {
		double Matrice[][] = { { 0.0, 1, 25 }, { 3.0, 0, 025 } };
		double Vecteur[] = { 3.0, 2.0 };

		Matrice matrice = new Matrice(Matrice);
		Vecteur vecteur = new Vecteur(Vecteur);
		Vecteur result = null;

		Helder res;
		//System.out.println("test 1 ");
		try {
		//	System.out.println("test 2");
			res = new Helder(matrice, vecteur);
		//	System.out.println("test 3");
			result = res.Resolution();
		//	System.out.println("test 4");
		} catch (IrregularSysLinException e) {
			e.printStackTrace();
		}

		System.out.println("la solution trouver est :" + result);

	}

}
