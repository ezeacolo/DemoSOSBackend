package com.demosos.json;

import com.demosos.util.Util;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * Used to serialize Java.util.Date, which is not a common JSON
 * type, so we have to create a custom serialize method;.
 *
 * @author Loiane Groner
 *         http://loianegroner.com (English)
 *         http://loiane.com (Portuguese)
 */
@Component
public class JsonDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonparser,
                            DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {

        String date = jsonparser.getText();
        return Util.parseDate(date);
    }


}
