package br.eti.deividferreira.catalogo.infra.jpa;

import org.hibernate.dialect.PostgreSQL95Dialect;

public class CustomPostgresDialect extends PostgreSQL95Dialect {
	
	public CustomPostgresDialect() {
        super.registerFunction("FILTER", new PostgresFilterFunction());
    }

}
