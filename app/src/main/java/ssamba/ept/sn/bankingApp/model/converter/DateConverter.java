package ssamba.ept.sn.bankingApp.model.converter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Gson serialiser/deserialiser for converting Joda {@link Date} objects.
 */
public class DateConverter implements JsonDeserializer<Date> {
    private final  SimpleDateFormat sdfDateFormatter ;

   // @Inject
    public  DateConverter (){
       this.sdfDateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.getAsString() == null || json.getAsString().isEmpty()) {
            return null;
        }

        try {
            return sdfDateFormatter.parse(json.getAsString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
