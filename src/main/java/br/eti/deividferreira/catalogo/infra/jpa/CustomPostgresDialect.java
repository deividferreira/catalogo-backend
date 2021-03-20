package br.eti.deividferreira.catalogo.infra.jpa;

import org.hibernate.dialect.PostgreSQL10Dialect;

public class CustomPostgresDialect extends PostgreSQL10Dialect {
	
	public CustomPostgresDialect() {
        super.registerFunction("FILTER", new PostgresFilterFunction());
    }

}
