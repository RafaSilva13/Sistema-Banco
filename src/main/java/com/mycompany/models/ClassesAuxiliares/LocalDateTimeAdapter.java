package com.mycompany.models.ClassesAuxiliares;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.lang.reflect.Type;
import com.google.gson.*;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(FORMATTER));
    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return LocalDateTime.parse(json.getAsString(), FORMATTER);
    }
}