package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Rundvisning implements Serializable {
	private static final long serialVersionUID = 1L;
	private LocalDate dag;
	private LocalTime tid;

	public Rundvisning(LocalDate dato, LocalTime tidspunkt) {
		this.dag = dato;
		this.tid = tidspunkt;
	}

	public LocalDate getDag() {
		return dag;
	}

	public LocalTime getTid() {
		return tid;
	}

	@Override
	public String toString() {
		return "Rundvisning, " + dag.toString() + ", klk " + tid.toString();
	}
}
