package br.eti.deividferreira.legalpecas.infra.jpa;

import org.hibernate.dialect.PostgreSQL95Dialect;

public class CustomPostgresDialect extends PostgreSQL95Dialect {
	
	public CustomPostgresDialect() {
        super.registerFunction("FILTER", new PostgresFilterFunction());
    }

}
