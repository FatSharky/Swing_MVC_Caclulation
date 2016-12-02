package by.bsk.oracle.domain;

import java.io.Serializable;

public class ProductCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idProcduct;
	private String name;
	private int idDivision;

	public int getIdProcduct() {
		return idProcduct;
	}

	public void setIdProcduct(int idProcduct) {
		this.idProcduct = idProcduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdDivision() {
		return idDivision;
	}

	public void setIdDivision(int idDivision) {
		this.idDivision = idDivision;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idDivision;
		result = prime * result + idProcduct;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductCategory other = (ProductCategory) obj;
		if (idDivision != other.idDivision)
			return false;
		if (idProcduct != other.idProcduct)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductCategory [idProcduct=" + idProcduct + ", name=" + name + ", idDivision=" + idDivision + "]";
	}

}
