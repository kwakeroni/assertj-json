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

import org.assertj.core.api.AbstractAssert;
import org.intellij.lang.annotations.Language;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * Assertion methods for JSON content.
 * <p>
 * To create a new instance of this class, invoke <code>{@link JSONAssertions#assertThatJSON(String)}</code>.
 *
 * @author Maarten Van Puymbroeck
 */
public class JSONAssert extends AbstractAssert<JSONAssert, String> {

    private boolean strictArrayOrdering = true;

    public JSONAssert(@Language("json") String actualJSON) {
        super(actualJSON, JSONAssert.class);
    }

    /**
     * Configures this assertion object to ignore the order of elements in JSON arrays when comparing JSON content.
     *
     * @return {@code this} assertion object
     */
    public JSONAssert ignoringArrayOrder() {
        this.strictArrayOrdering = false;
        return this;
    }

    /**
     * Verifies that the actual JSON content is strictly equal to the given content.
     * <p>
     * This means that both contents must contain the exact same fields and array contents must appear in the exact same order.
     * In JSONAssert terms this means that the {@link JSONCompareMode#STRICT STRICT} compare mode is used.
     * </p>
     *
     * @param expected the given JSON content to compare the actual content to.
     * @return {@code this} assertion object.
     * @throws AssertionError if the actual value is not equal to the given one.
     */
    public JSONAssert isEqualTo(@Language("json") String expected) {
        try {
            org.skyscreamer.jsonassert.JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT.withStrictOrdering(this.strictArrayOrdering));
        } catch (JSONException e) {
            AssertionError failure = failure(e.getMessage());
            failure.initCause(e);
            throw failure;
        }
        return this;
    }

    /**
     * Verifies that the actual JSON content contains at least the given content, allowing for additional fields.
     * <p>
     * This means that the actual content must contain at least the fields of the expected content.
     * In JSONAssert terms this means that the {@link JSONCompareMode#STRICT_ORDER STRICT_ORDER} compare mode is used.
     * </p>
     *
     * @param expected the given JSON content to compare the actual content to.
     * @return {@code this} assertion object.
     * @throws AssertionError if the actual value does not contain the given one.
     */
    public JSONAssert contains(@Language("json") String expected) {
        try {
            org.skyscreamer.jsonassert.JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT_ORDER.withStrictOrdering(this.strictArrayOrdering));
        } catch (JSONException e) {
            AssertionError failure = failure(e.getMessage());
            failure.initCause(e);
            throw failure;
        }
        return this;
    }
}
