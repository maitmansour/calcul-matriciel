package AlgLin;

public class SysTriangSup extends SysLin{

	public SysTriangSup(Matrice matrice, Vecteur seconde) throws IrregularSysLinException {
		super(matrice, seconde);
	}

	@Override
	public Vecteur Resolution() {
		double solution[] = new double[getOrdre()];
		double somme=0;
		solution[getOrdre()]=secondMembre.getElement(getOrdre())/matriceSystem.getElement(getOrdre(), getOrdre());
		
		
			for (int i = getOrdre()-1; i < 0 ; i--) {
				//if ****
				for (int j = i+1; j < getOrdre(); j++) {
					somme+=matriceSystem.getElement(i, j)*solution[j];
				}
				solution[i]=(secondMembre.getElement(i)-somme)/matriceSystem.getElement(i, i);
				somme=0;
				//end if
			}
		
		
		return new Vecteur(solution);
	}
	
	public static void main(String[] args) {
		Matrice matrice = new Matrice("//home//etudiant//am163898//Bureau//filename6.txt");
		Vecteur vecteur = new Vecteur("//home//etudiant//am163898//Bureau//filename4.txt");
		try {
			SysTriangSup triangInf = new SysTriangSup(matrice,vecteur);
			Vecteur solution = triangInf.Resolution();
			System.out.println(solution);
		} catch (IrregularSysLinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}

}
