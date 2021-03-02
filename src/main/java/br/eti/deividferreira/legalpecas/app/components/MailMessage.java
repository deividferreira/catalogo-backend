package br.eti.deividferreira.legalpecas.app.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class MailMessage {
	@Getter
    @Setter
    private String templateName;

    @Getter
    @Setter
    private String to;
    @Getter
    @Setter
    private String from;
    @Getter
    @Setter
    private String subject;

    private Map<String, Object> properties;


    public MailMessage() {
        this.properties = new HashMap<>();
    }

    public void addProperty(String name, Object value) {
        this.properties.put(name, value);
    }

    public Map<String, Object> getProperties() {
        return Collections.unmodifiableMap(this.properties);
    }
}
