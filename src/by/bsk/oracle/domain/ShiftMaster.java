package by.bsk.oracle.domain;

import java.io.Serializable;

public class ShiftMaster implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idMaster;
	private String fio;
	private int idUnit;

	public int getIdMaster() {
		return idMaster;
	}

	public void setIdMaster(int idMaster) {
		this.idMaster = idMaster;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public int getIdUnit() {
		return idUnit;
	}

	public void setIdUnit(int idUnit) {
		this.idUnit = idUnit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fio == null) ? 0 : fio.hashCode());
		result = prime * result + idMaster;
		result = prime * result + idUnit;
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
		ShiftMaster other = (ShiftMaster) obj;
		if (fio == null) {
			if (other.fio != null)
				return false;
		} else if (!fio.equals(other.fio))
			return false;
		if (idMaster != other.idMaster)
			return false;
		if (idUnit != other.idUnit)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShiftMaster [idMaster=" + idMaster + ", fio=" + fio + ", idUnit=" + idUnit + "]";
	}

}
