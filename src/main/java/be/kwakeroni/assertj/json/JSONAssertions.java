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

import org.assertj.core.util.CheckReturnValue;
import org.intellij.lang.annotations.Language;

/**
 * Entry point for assertion methods for JSON content.
 * Each method in this class is a static factory for a JSON assertion object.
 * <p>
 *     For example:
 * </p>
 * <pre><code class='java'>
 *     {@link JSONAssertions#assertThatJSON(String) assertThatJSON}(actualJSON).{@link JSONAssert#isEqualTo(String) isEqualTo}(expectedJSON);
 * </code></pre>
 *
 * @author Maarten Van Puymbroeck
 */
@CheckReturnValue
public class JSONAssertions {

    /**
     * Creates a new instance of <code>{@link JSONAssert}from a {@link String}</code>.
     *
     * @param actual the actual value.
     * @return the created assertion object.
     */
    public static JSONAssert assertThatJSON(@Language("json") String actual) {
        return new JSONAssert(actual);
    }

}
