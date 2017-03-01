package AlgLin;

import javax.print.attribute.ResolutionSyntax;

public abstract class SysLin {
	private int ordre;
	protected Matrice matriceSystem;
	protected Vecteur secondMembre;

	public SysLin(Matrice matrice, Vecteur seconde) throws IrregularSysLinException {
		if ((matrice.getNbLignes() == seconde.getNbLignes()) && (matrice.getNbColonnes() == matrice.getNbColonnes())) {
			matriceSystem = new Matrice(matrice.getNbLignes(), matrice.getNbColonnes());
			matriceSystem.copy(matrice);
			secondMembre = new Vecteur(seconde.getNbLignes());
			secondMembre.copy(seconde);
			setOrdre(seconde.getNbLignes());
			// System.out.println(matriceSystem);
		} else {
			throw new IrregularSysLinException("IMCOMPATIBLES (MATRICE ET VECTEUR)");
		}
	}

	abstract public Vecteur Resolution() throws IrregularSysLinException;

	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

	public Matrice getMatriceSystem() {
		return matriceSystem;
	}

	public void setMatriceSystem(Matrice matriceSystem) {
		this.matriceSystem = matriceSystem;
	}

	public Matrice getSecondMembre() {
		return secondMembre;
	}

	public void setSecondMembre(Vecteur secondMembre) {
		this.secondMembre = secondMembre;
	}

	public static void main(String[] args) {

		// Construction de deux matrices
		Matrice m1 = new Matrice("matrice3.txt");
		Vecteur v1 = new Vecteur("vecteur3.txt");

	}

}
