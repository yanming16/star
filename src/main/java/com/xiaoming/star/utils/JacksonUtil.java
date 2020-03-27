/*
 * JacksonUtil.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.xiaoming.star.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author liangyi
 */
public final class JacksonUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JacksonUtil.class);

    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private JacksonUtil() {
    }

    public static byte[] encode2Bytes(final Object obj) {
        try {
            return objectMapper.writeValueAsBytes(obj);
        } catch (final JsonGenerationException e) {
            LOG.error("encode JsonGenerationException", e);
        } catch (final IOException e) {
            LOG.error("encode IOException", e);
        }
        return null;
    }

    public static String encode2String(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (final JsonGenerationException e) {
            LOG.error("encode JsonGenerationException", e);
        } catch (final IOException e) {
            LOG.error("encode IOException", e);
        }
        return null;
    }

    /**
     * 将json string反序列化成对象
     *
     * @param json
     * @param valueType
     * @return
     */
    public static <T> T decodeFromString(final String json, final Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (final IOException e) {
            LOG.error("decode error!", e);
        }
        return null;
    }

    /**
     * 将json array反序列化为对象
     *
     * @param json
     * @param typeReference
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T decodeFromString(final String json, final TypeReference<T> typeReference) {
        try {
            return (T) objectMapper.readValue(json, typeReference);
        } catch (final IOException e) {
            LOG.error("decode error!", e);
        }
        return null;
    }
}
