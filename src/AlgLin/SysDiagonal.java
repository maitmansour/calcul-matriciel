package AlgLin;

public class SysDiagonal extends SysLin{

	public SysDiagonal(Matrice matrice, Vecteur seconde) throws IrregularSysLinException {
		super(matrice, seconde);
	}




	@Override
	public Vecteur Resolution() {
		double solution[] = new double[getOrdre()];
		for (int i = 0; i < getOrdre(); i++) {
			 solution[i]=getMatriceSystem().getElement(i, i)*getSecondMembre().getElement(i, 0);

		}
		return new Vecteur(solution);
	}
	

	
	
	

	public static void main(String[] args) {
		Matrice matrice = new Matrice("//home//etudiant//am163898//Bureau//filename5.txt");
		Vecteur vecteur = new Vecteur("//home//etudiant//am163898//Bureau//filename4.txt");
		try {
			SysDiagonal Diagonal = new SysDiagonal(matrice,vecteur);
			Vecteur solution = Diagonal.Resolution();
			System.out.println(solution);
		} catch (IrregularSysLinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}


}
 