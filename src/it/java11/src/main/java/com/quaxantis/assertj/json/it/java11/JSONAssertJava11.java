package com.quaxantis.assertj.json.it.java11;

import com.quaxantis.assertj.json.JSONAssert;
import com.quaxantis.assertj.json.JSONAssertions;

final class JSONAssertJava11 {

    final String actualJSON;

    JSONAssertJava11(String actualJSON) {
        this.actualJSON = actualJSON;
    }

    JSONAssert createAssert() {
        return JSONAssertions.assertThatJSON(this.actualJSON);
    }
}
