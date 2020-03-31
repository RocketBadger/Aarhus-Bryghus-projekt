package model;

public interface Rabat {

	/**
	 * 
	 * @param pris - Original pris på produktet.
	 * @param antal - Hvor mange 
	 * @return Retunerer den samlede rabat for x antal varer
	 */
	public double calcRabat (Double pris, int antal);	
}
