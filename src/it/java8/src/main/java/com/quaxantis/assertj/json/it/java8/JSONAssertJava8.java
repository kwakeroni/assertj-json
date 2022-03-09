package com.quaxantis.assertj.json.it.java8;

import com.quaxantis.assertj.json.JSONAssert;
import com.quaxantis.assertj.json.JSONAssertions;

final class JSONAssertJava8 {

    final String actualJSON;

    JSONAssertJava8(String actualJSON) {
        this.actualJSON = actualJSON;
    }

    JSONAssert createAssert() {
        return JSONAssertions.assertThatJSON(this.actualJSON);
    }
}
