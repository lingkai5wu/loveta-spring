package com.github.lingkai5wu.loveta.config;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Jackson 配置类
 *
 * @author lingkai5wu
 * @since 2024-04-03
 */
@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer());
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer());

            builder.serializerByType(LocalDate.class, new LocalDateSerializer());
            builder.deserializerByType(LocalDate.class, new LocalDateDeserializer());

            builder.serializerByType(LocalTime.class, new LocalTimeSerializer());
            builder.deserializerByType(LocalTime.class, new LocalTimeDeserializer());
        };
    }

    public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

        @Override
        public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            long epochMilli = LocalDateTimeUtil.toEpochMilli(localDateTime);
            jsonGenerator.writeNumber(epochMilli);
        }
    }

    public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

        @Override
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            long valueAsLong = jsonParser.getValueAsLong();
            return LocalDateTimeUtil.of(valueAsLong);
        }
    }

    public static class LocalDateSerializer extends JsonSerializer<LocalDate> {

        @Override
        public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            long epochMilli = LocalDateTimeUtil.toEpochMilli(localDate);
            jsonGenerator.writeNumber(epochMilli);
        }
    }

    public static class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

        @Override
        public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            long valueAsLong = jsonParser.getValueAsLong();
            LocalDateTime localDateTime = LocalDateTimeUtil.of(valueAsLong);
            return localDateTime.toLocalDate();
        }
    }

    public static class LocalTimeSerializer extends JsonSerializer<LocalTime> {

        @Override
        public void serialize(LocalTime localTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            long epochMilli = LocalDateTimeUtil.toEpochMilli(localTime);
            jsonGenerator.writeNumber(epochMilli);
        }
    }

    public static class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {

        @Override
        public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            long valueAsLong = jsonParser.getValueAsLong();
            LocalDateTime localDateTime = LocalDateTimeUtil.of(valueAsLong);
            return localDateTime.toLocalTime();
        }
    }
}
