package com.ruoyi.framework.handlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@MappedTypes({Object.class, List.class, Map.class})
@MappedJdbcTypes({JdbcType.VARCHAR, JdbcType.NULL})
public class ListTypeHandler extends JacksonTypeHandler {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public ListTypeHandler(Class<?> type) {
        super(type);
    }

    @Override
    protected Object parse(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<Object>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


