package net.buildUi.ExpenseService.deserializer;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.buildUi.ExpenseService.models.ExpenseDto;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

public class ExpenseDeserializer implements Deserializer<ExpenseDto> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public ExpenseDto deserialize(String s, byte[] bytes) {
        ExpenseDto expense = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
               expense = objectMapper.readValue(bytes , ExpenseDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return expense;
    }

    @Override
    public ExpenseDto deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public ExpenseDto deserialize(String topic, Headers headers, ByteBuffer data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
