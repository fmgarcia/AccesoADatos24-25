package com.fran.hibernateanotaciones3.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * EscribirId generated by hbm2java
 */
@Embeddable
public class EscribirId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String codautor;
	private int codlibro;

	public EscribirId() {
	}

	public EscribirId(String codautor, int codlibro) {
		this.codautor = codautor;
		this.codlibro = codlibro;
	}

	@Column(name = "codautor", nullable = false, length = 5)
	public String getCodautor() {
		return this.codautor;
	}

	public void setCodautor(String codautor) {
		this.codautor = codautor;
	}

	@Column(name = "codlibro", nullable = false)
	public int getCodlibro() {
		return this.codlibro;
	}

	public void setCodlibro(int codlibro) {
		this.codlibro = codlibro;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EscribirId))
			return false;
		EscribirId castOther = (EscribirId) other;

		return ((this.getCodautor() == castOther.getCodautor()) || (this.getCodautor() != null
				&& castOther.getCodautor() != null && this.getCodautor().equals(castOther.getCodautor())))
				&& (this.getCodlibro() == castOther.getCodlibro());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCodautor() == null ? 0 : this.getCodautor().hashCode());
		result = 37 * result + this.getCodlibro();
		return result;
	}

}