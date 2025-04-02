package org.example.homework;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class StringToListDeserializer extends JsonDeserializer<List<String>> {
    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        String tagsString = p.getValueAsString().trim(); // "natura peisaj"
        return Arrays.asList(tagsString.split("\\s+"));  // → ["natura", "peisaj"]
    }
}
