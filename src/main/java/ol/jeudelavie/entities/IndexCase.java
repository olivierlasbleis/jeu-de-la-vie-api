package ol.jeudelavie.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IndexCase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int i;
	private int j;
	
	@Override
	public int hashCode() {
	    return (i * 31) ^ j;
	  }
	
	@Override
    public boolean equals(Object o) { 
		if (this.i == ((IndexCase)o).getI() && this.j == ((IndexCase)o).getJ()) {
			return true;
		}else {
			return false;
		}
	}

	public IndexCase(int i, int j) {
		this.i=i;
		this.j=j;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public IndexCase() {
		super();
	}

}
