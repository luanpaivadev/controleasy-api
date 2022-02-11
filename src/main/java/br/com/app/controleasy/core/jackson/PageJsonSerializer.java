package br.com.app.controleasy.core.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {

    @Override
    public void serialize(Page<?> page, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        gen.writeStartObject();
        gen.writeObjectField("content", page.getContent());
        gen.writeObjectField("size", page.getSize());
        gen.writeObjectField("totalElements", page.getTotalElements());
        gen.writeObjectField("totalPages", page.getTotalPages());
        gen.writeObjectField("number", page.getNumber());
        gen.writeEndObject();

    }

}
