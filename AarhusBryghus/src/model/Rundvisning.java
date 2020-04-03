package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Rundvisning implements Serializable {
	private static final long serialVersionUID = 1L;
	private LocalDateTime dagTid;

	public Rundvisning(LocalDateTime tidspunkt) {
		this.dagTid = tidspunkt;

	}

	public LocalDateTime getDagTid() {
		return dagTid;
	}

}
