package br.eti.deividferreira.catalogo.domain.entities.registration;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.eti.deividferreira.catalogo.domain.entities.PersistentEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Table(name = "parts")
@EqualsAndHashCode(callSuper = true)
public class Parts extends PersistentEntity {

	@Getter
	@Setter
	@Column(name = "code", nullable = true, length = 50)
	private String code;
	@Getter
	@Setter
	@Column(name = "manufacturer_code", nullable = false, length = 50)
	private String manufacturerCode;
	@Getter
	@Setter
	@Column(name = "manufacturer_name", nullable = false, length = 255)
	private String manufacturerName;	
	@Getter
	@Setter
	@Column(name = "description", nullable = false, length = 500)
	private String description;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "packing", nullable = false, length = 2)
	private PackingType packing;
    @Getter
    @Setter
    @Column(name = "packing_qty", nullable = false)
	private Integer packingQty;
    @Getter
    @Setter
    @Column(name = "ncm", nullable = false)
	private String ncm;
    @Getter
    @Setter
    @Column(name = "ean", nullable = false)
	private String ean;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "origin", nullable = false)
	private OriginType origin;
    @Getter
    @Setter
    @Column(name = "cost_price", nullable = false)
	private BigDecimal costPrice;
    @Getter
    @Setter
    @Column(name = "sale_price", nullable = false)
	private BigDecimal salePrice;
    @Getter
    @Setter
    @Column(name = "qty_stock")
	private Integer qtyStock;
    @Getter
    @Setter
    @Column(name = "active", nullable = false)
	private boolean active;
    @Getter
    @Setter
    @Column(name = "net_weight")
	private BigDecimal netWeight;
    @Getter
    @Setter
    @Column(name = "brand")
	private String brand;
    @Getter
    @Setter
    @Column(name = "photo")
	private String photo;
	
    @Getter
    @Setter
    @Column(name = "comments")
	private String comments;
    
    public Parts() {
    	this.active = true;
    }
	
	
}
