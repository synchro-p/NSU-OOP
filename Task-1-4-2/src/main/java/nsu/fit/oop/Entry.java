package nsu.fit.oop;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class Entry {
    @JsonSerialize(using = StringSerializer.class)
    @JsonDeserialize(using = StringDeserializer.class)
    private final String name;
    @JsonSerialize(using = StringSerializer.class)
    @JsonDeserialize(using = StringDeserializer.class)
    private final String content;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private final LocalDateTime creationDateTime;

    Entry() {
        this("","");
    }

    Entry(String n, String c) {
        name = n;
        content = c;
        creationDateTime = LocalDateTime.now();
    }

    String getName() {
        return this.name;
    }

    String getContent() {
        return this.content;
    }

    LocalDateTime getCreationDateTime() {
        return this.creationDateTime;
    }
}
