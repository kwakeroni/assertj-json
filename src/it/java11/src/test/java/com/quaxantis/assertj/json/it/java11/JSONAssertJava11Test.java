package com.quaxantis.assertj.json.it.java11;

import com.quaxantis.assertj.json.JSONAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JSONAssertJava11Test {

    @Test
    void testCreateJSONAssert() {
        assertThat(Runtime.version().feature()).isGreaterThanOrEqualTo(9);
        assertThat(JSONAssert.class.getModule().getName()).isEqualTo("com.quaxantis.assertj.json");

        String json = "{'test':123}".replace('\'', '"');
        JSONAssertJava11 stub = new JSONAssertJava11(json);
        JSONAssert jsonAssert = stub.createAssert();
        jsonAssert.isEqualTo(json);
    }

}
