/*
 * Copyright (c) 2016 Ryanair Ltd. All rights reserved.
 */

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class ListOrObjectDeserializer<T> extends JsonDeserializer<List<T>> {

    private final Class<T> clazz;

    public ListOrObjectDeserializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<T> deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final ObjectCodec objectCodec = jsonParser.getCodec();
        final JsonNode listOrObjectNode = objectCodec.readTree(jsonParser);
        final List<T> result = new ArrayList<T>();
        if (listOrObjectNode.isArray()) {
            for (JsonNode node : listOrObjectNode) {
                result.add(objectCodec.treeToValue(node, clazz));
            }
        } else {
            result.add(objectCodec.treeToValue(listOrObjectNode, clazz));
        }
        return result;
    }
}