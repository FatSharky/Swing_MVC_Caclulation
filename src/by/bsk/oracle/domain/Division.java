package by.bsk.oracle.domain;

import java.io.Serializable;

/**
 * �����-�������� {@code Division} ��������� �������� ���� � ������ ���
 * �������������� � ���� �������.
 * 
 * @author Vladislav
 *
 */
public class Division implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * � ���� {@code idDivision} ���������� ����������� �� id �����
	 */
	private int idDivision;
	/**
	 * � ���� {@code name} ���������� ���������� �� �������� �����
	 */
	private String name;
	/**
	 * � ���� {@code unn} ���������� ���������� �� ���������� ��������������
	 * �����
	 */
	private String uNN;

	public int getIdDivision() {
		return idDivision;
	}

	public void setIdDivision(int idDivision) {
		this.idDivision = idDivision;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUNN() {
		return uNN;
	}

	public void setUNN(String uNN) {
		this.uNN = uNN;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uNN == null) ? 0 : uNN.hashCode());
		result = prime * result + idDivision;
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
		Division other = (Division) obj;
		if (uNN == null) {
			if (other.uNN != null)
				return false;
		} else if (!uNN.equals(other.uNN))
			return false;
		if (idDivision != other.idDivision)
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
		return "Divison [idDivision=" + idDivision + ", name=" + name + ", UNN=" + uNN + "]";
	}

}
