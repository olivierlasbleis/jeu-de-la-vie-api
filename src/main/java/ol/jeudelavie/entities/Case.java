package ol.jeudelavie.entities;

public class Case {

	private IndexCase indexCase;
	private int value;
	
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Case() {
		super();
	}
	public IndexCase getIndexCase() {
		return indexCase;
	}
	public void setIndexCase(IndexCase indexCase) {
		this.indexCase = indexCase;
	}
	public Case(IndexCase indexCase, int value) {
		super();
		this.indexCase = indexCase;
		this.value = value;
	}
}
