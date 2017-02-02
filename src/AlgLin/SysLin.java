package AlgLin;

import javax.print.attribute.ResolutionSyntax;

public abstract class SysLin {
	private int ordre;
	protected Matrice matriceSystem;
	protected Vecteur secondMembre;
	
	
	public SysLin(Matrice matrice, Matrice seconde) throws IrregularSysLinException {
		if ((matrice.getNbLignes()==seconde.getNbLignes())&&(matrice.getNbColonnes()==seconde.getNbColonnes())&&(matrice.getNbColonnes()==matrice.getNbColonnes())) {
			matriceSystem.copy(matrice);
			secondMembre.copy(seconde);
			
		} else {
			throw new IrregularSysLinException();
		}
	}
	
	abstract public Vecteur Resolution();
	
	
	
	
	
	
	
	
	
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
		
		//Construction de deux matrices
		Matrice m1 = new Matrice("//home//etudiant//am163898//Bureau//filename2.txt");
		Vecteur v1 = new Vecteur("//home//etudiant//am163898//Bureau//filename22.txt");
		
		
	}

}
