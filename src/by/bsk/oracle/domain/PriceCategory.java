package by.bsk.oracle.domain;

import java.io.Serializable;

/**
 * �����-�������� {@code PriceCategory} ��������� �������� ���� � ������ ���
 * �������������� � ���� �������.
 * 
 * @author Vladislav
 *
 */
public class PriceCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * � ���� {@code idPrice} ���������� ����������� �� id ������� ���������
	 */
	private int idPrice;
	/**
	 * � ���� {@code name} ���������� ����������� �� �������� ������� ���������
	 */
	private String name;
	/**
	 * � ���� {@code idDivision} ���������� ����������� �� id �����
	 */
	private int idDivision;

	public int getIdPrice() {
		return idPrice;
	}

	public void setIdPrice(int idPrice) {
		this.idPrice = idPrice;
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
		result = prime * result + idPrice;
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
		PriceCategory other = (PriceCategory) obj;
		if (idDivision != other.idDivision)
			return false;
		if (idPrice != other.idPrice)
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
		return "PriceCategory [idPrice=" + idPrice + ", name=" + name + ", idDivision=" + idDivision + "]";
	}

}
