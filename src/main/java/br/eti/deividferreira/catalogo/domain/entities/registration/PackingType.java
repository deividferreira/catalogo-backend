package br.eti.deividferreira.catalogo.domain.entities.registration;

public enum PackingType {
	
	PC(1, "Pacote"), CJ(2, "Conjunto"), JG(3, "Jogo");
	
	private Integer id;
	private String description;

	PackingType(Integer id, String description) {
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
