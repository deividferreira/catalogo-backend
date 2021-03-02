package br.eti.deividferreira.legalpecas.infra.misc;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static org.apache.commons.lang3.RandomStringUtils.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordGenerator {

	public static String generate() {

        final String upperCaseLetters = random(4, 65, 90, true, true);
        final String lowerCaseLetters = random(4, 97, 122, true, true);
        final String numbers = randomNumeric(4);
        final String totalChars = randomAlphanumeric(4);

        final String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(totalChars);

        final List<Character> passwordChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

        Collections.shuffle(passwordChars);

        return passwordChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
