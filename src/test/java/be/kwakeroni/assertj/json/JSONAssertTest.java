/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2022 Maarten Van Puymbroeck.
 */
package be.kwakeroni.assertj.json;

import org.intellij.lang.annotations.Language;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Asserts that JSON content")
class JSONAssertTest {

    @Language("json")
    private static final String CONTENT = "{\"firstName\": \"Santa\", \"lastName\":  \"Claus\", \"age\": 1751, \"aliases\":  [\"Saint Nicholas\", \"Kris Kringle\"]}";
    @Language("json")
    private static final String CONTENT_PARTIAL = "{\"firstName\": \"Santa\", \"lastName\":  \"Claus\"}";
    @Language("json")
    private static final String CONTENT_EQUIVALENT = "{\n" +
            "\"lastName\": \"Claus\",       \n" +
            "\"age\"     :   1751,\n" +
            "\"firstName\":       \"Santa\",\n" +
            "\"aliases\": [\"Saint Nicholas\", \"Kris Kringle\"]\n" +
            "}";
    @Language("json")
    private static final String CONTENT_SORTED_ARRAY = "{\n" +
            "\"lastName\": \"Claus\",       \n" +
            "\"age\"     :   1751,\n" +
            "\"firstName\":       \"Santa\",\n" +
            "\"aliases\": [\"Kris Kringle\", \"Saint Nicholas\"]\n" +
            "}";
    @Language("json")
    private static final String CONTENT_PARTIAL_SORTED_ARRAY = "{\"aliases\": [\"Kris Kringle\", \"Saint Nicholas\"]}";

    @Test
    @DisplayName("can be compared using a static convenience method")
    void testAssertEqualsJSON() {
        JSONAssertions.assertThatJSON(CONTENT).isEqualTo(CONTENT);
    }

    @Nested
    @DisplayName("using a strict match (isEqualTo)")
    class StrictlyEqualTest {

        @Test
        @DisplayName("matches equivalent content")
        void testMatchEqual() {
            new JSONAssert(CONTENT).isEqualTo(CONTENT_EQUIVALENT);
        }

        @Test
        @DisplayName("does not match partial content")
        void testNotMatchPartial() {
            assertThatThrownBy(() -> new JSONAssert(CONTENT).isEqualTo(CONTENT_PARTIAL))
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("Unexpected")
                    .hasMessageContaining("age");
        }

        @Test
        @DisplayName("does not match content with reordered arrays")
        void testNotMatchReordered() {
            assertThatThrownBy(() -> new JSONAssert(CONTENT).isEqualTo(CONTENT_SORTED_ARRAY))
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("aliases[0]")
                    .hasMessageContaining("aliases[1]");
        }
    }

    @Nested
    @DisplayName("using an extensible match (contains)")
    class ExtensibleEqualTest {

        @Test
        @DisplayName("matches equivalent content")
        void testMatchEqual() {
            new JSONAssert(CONTENT).contains(CONTENT_EQUIVALENT);
        }

        @Test
        @DisplayName("matches partial content")
        void testMatchPartial() {
            new JSONAssert(CONTENT).contains(CONTENT_PARTIAL);
        }

        @Test
        @DisplayName("does not match partial content with reordered arrays")
        void testNotMatchReordered() {
            assertThatThrownBy(() -> new JSONAssert(CONTENT).isEqualTo(CONTENT_PARTIAL_SORTED_ARRAY))
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("aliases[0]")
                    .hasMessageContaining("aliases[1]");
        }
    }

    @Nested
    @DisplayName("ignoring array order")
    class IgnoringArrayOrderTest {

        @Test
        @DisplayName("matches strict content with reordered arrays (isEqualTo)")
        void testMatchStrictReordered() {
            new JSONAssert(CONTENT)
                    .ignoringArrayOrder()
                    .isEqualTo(CONTENT_SORTED_ARRAY);
        }

        @Test
        @DisplayName("matches partial content with reordered arrays (contains)")
        void testMatchPartialReordered() {
            new JSONAssert(CONTENT)
                    .ignoringArrayOrder()
                    .contains(CONTENT_PARTIAL_SORTED_ARRAY);
        }
    }

    @Nested
    @DisplayName("cannot be compared if it is invalid")
    @SuppressWarnings("JsonStandardCompliance")
    class InvalidJSONTest {

        @Test
        @DisplayName("as actual content")
        void testInvalidActual() {
            assertThatThrownBy(() -> new JSONAssert("bla=bla").isEqualTo(CONTENT))
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("Unparsable")
                    .hasMessageContaining("bla=bla")
                    .hasCauseInstanceOf(JSONException.class);
        }

        @Test
        @DisplayName("as expected strict content")
        void testInvalidExpectedStrict() {
            assertThatThrownBy(() -> new JSONAssert(CONTENT).isEqualTo("bla=bla"))
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("Unparsable")
                    .hasMessageContaining("bla=bla")
                    .hasCauseInstanceOf(JSONException.class);
        }

        @Test
        @DisplayName("as expected partial content")
        void testInvalidExpectedPartial() {
            assertThatThrownBy(() -> new JSONAssert(CONTENT).contains("bla=bla"))
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("Unparsable")
                    .hasMessageContaining("bla=bla")
                    .hasCauseInstanceOf(JSONException.class);
        }
    }
}