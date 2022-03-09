package com.quaxantis.assertj.json.it.java8;

import com.quaxantis.assertj.json.JSONAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JSONAssertJava8Test {

    @Test
    void testCreateJSONAssert() {
        assertThat(System.getProperty("java.version")).startsWith("1.8.");

        String json = "{'test':123}".replace('\'', '"');
        JSONAssertJava8 stub = new JSONAssertJava8(json);
        JSONAssert jsonAssert = stub.createAssert();
        jsonAssert.isEqualTo(json);
    }

}
