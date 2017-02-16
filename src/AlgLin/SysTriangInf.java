package AlgLin;

public class SysTriangInf extends SysLin{

	public SysTriangInf(Matrice matrice, Vecteur seconde) throws IrregularSysLinException {
		super(matrice, seconde);
	}

	@Override
	public Vecteur Resolution() {
		double solution[] = new double[getOrdre()];
		
		
		return new Vecteur(solution);
	}
	
	public static void main(String[] args) {
		Matrice matrice = new Matrice("//home//etudiant//am163898//Bureau//filename6.txt");
		Vecteur vecteur = new Vecteur("//home//etudiant//am163898//Bureau//filename4.txt");
		try {
			SysTriangInf triangInf = new SysTriangInf(matrice,vecteur);
			Vecteur solution = triangInf.Resolution();
			System.out.println(solution);
		} catch (IrregularSysLinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}

}
