package br.eti.deividferreira.catalogo.infra.misc;

import java.util.Collection;
import java.util.Objects;

import org.springframework.http.ResponseEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RestPreconditions {

	public static <T> ResponseEntity<T> checkFound(final T object) {
		if (Objects.isNull(object)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(object);
	}

	public static <T extends Collection<T>> ResponseEntity<T> checkFound(final T object) {
		if (!Objects.isNull(object) && !object.isEmpty()) {
			return ResponseEntity.ok(object);
		}
		return ResponseEntity.noContent().build();
	}

}
