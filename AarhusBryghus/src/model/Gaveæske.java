package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Gaveæske implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Pris> indhold;

	public Gaveæske(List<Pris> produkter) {
		this.indhold = new ArrayList<>(produkter);
	}

	public ArrayList<Pris> getIndhold() {
		return new ArrayList<>(indhold);
	}

	@Override
	public String toString() {
		return "Gaveæske, ";
	}
}
