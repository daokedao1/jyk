package com.xoa.global.jsondate;

import com.xoa.util.DateFormat;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * Created by eagle on 2017/5/25.
 */
public class DateSerializerHMS extends JsonSerializer<Date> {
    @Override
    public void serialize(Date o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

            try{
               String date= DateFormat.getFormatByUse("HH:mm:ss",o);
                jsonGenerator.writeString(date);
            }catch (Exception e){
                e.printStackTrace();
            }


    }
}
