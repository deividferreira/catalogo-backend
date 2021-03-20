package br.eti.deividferreira.catalogo.domain.entities.registration;

public enum OriginType {
	
	//TODO translated
	NATIONAL(1, "Nacional"), IMPORTED(2, "Importado");

	private Integer id;
	private String description;

	OriginType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getNameEnum() {
		return this.name();
	}
	
	@Override
	public String toString() {
		return this.description;
	}
}
