package br.eti.deividferreira.catalogo.infra.jpa;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitForeignKeyNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.model.naming.ImplicitUniqueKeyNameSource;

public class CustomNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

	/**
	 * {@inheritDoc}
	 *
	 * @param source
	 * @return
	 */
	@Override
	public Identifier determineForeignKeyName(ImplicitForeignKeyNameSource source) {
		return this.toIdentifier("fk_" + source.getTableName().getCanonicalName() + "_"
				+ source.getReferencedTableName().getCanonicalName(), source.getBuildingContext());
	}

	/**
	 * {@inheritDoc}
	 *
	 * @implNote this is not working due to this bug in Hibernate implementation
	 *
	 * @see <a href="https://hibernate.atlassian.net/browse/HHH-11586">HHH-11586</>
	 *
	 * @param source
	 * @return
	 */
	@Override
	public Identifier determineUniqueKeyName(ImplicitUniqueKeyNameSource source) {
		return this.toIdentifier("uk_" + source.getTableName().getCanonicalName(), source.getBuildingContext());
	}

}
