/*
 * Copyright (c) 2025 Dmitriy Schiller
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.foxxylite.asciidoctor.condition;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class ConditionBlockMacroProcessorTest extends ConditionBaseTest {

    @DataProvider(name = "conditions")
    public ConditionAssertion[] createAssertions() {
        return new ConditionAssertion[] {
            /* ifnull */
            ConditionAssertion.of("ifnull::null_attr[then=\"is null\",else=\"is not null\"]", "is null"),
            ConditionAssertion.of("ifnull::not_null_attr[then=\"is null\",else=\"is not null\"]", "is not null"),

            /* ifnotnull */
            ConditionAssertion.of("ifnotnull::null_attr[then=\"is not null\",else=\"is null\"]", "is null"),
            ConditionAssertion.of("ifnotnull::not_null_attr[then=\"is not null\",else=\"is null\"]", "is not null"),

            /* ifstreq */
            ConditionAssertion.of("ifstreq::not_null_attr[some value,then=\"is equal\",else=\"is not equal\"]", "is equal"),
            ConditionAssertion.of("ifstreq::not_null_attr[some value 1,then=\"is equal\",else=\"is not equal\"]", "is not equal"),

            /* ifstrne */
            ConditionAssertion.of("ifstrne::not_null_attr[some value,then=\"is not equal\",else=\"is equal\"]", "is equal"),
            ConditionAssertion.of("ifstrne::not_null_attr[some value 1,then=\"is not equal\",else=\"is equal\"]", "is not equal"),

            /* ifeq */
            ConditionAssertion.of("ifeq::l_attr_15[{r_attr_15},then=\"is equal\",else=\"is not equal\"]", "is equal"),
            ConditionAssertion.of("ifeq::l_attr_15[{r_attr_16},then=\"is equal\",else=\"is not equal\"]", "is not equal"),

            /* ifne */
            ConditionAssertion.of("ifne::l_attr_15[{r_attr_15},then=\"is not equal\",else=\"is equal\"]", "is equal"),
            ConditionAssertion.of("ifne::l_attr_15[{r_attr_16},then=\"is not equal\",else=\"is equal\"]", "is not equal"),

            /* ifgt */
            ConditionAssertion.of("ifgt::l_attr_15[{r_attr_14},then=\"great than\",else=\"is not great than\"]", "great than"),
            ConditionAssertion.of("ifgt::l_attr_15[{r_attr_15},then=\"great than\",else=\"is not great than\"]", "is not great than"),
            ConditionAssertion.of("ifgt::l_attr_15[{r_attr_16},then=\"great than\",else=\"is not great than\"]", "is not great than"),

            /* ifge */
            ConditionAssertion.of("ifge::l_attr_15[{r_attr_14},then=\"great or equal\",else=\"is not great or equal\"]", "great or equal"),
            ConditionAssertion.of("ifge::l_attr_15[{r_attr_15},then=\"great or equal\",else=\"is not great or equal\"]", "great or equal"),
            ConditionAssertion.of("ifge::l_attr_15[{r_attr_16},then=\"great or equal\",else=\"is not great or equal\"]", "is not great or equal"),

            /* iflt */
            ConditionAssertion.of("iflt::l_attr_15[{r_attr_14},then=\"less than\",else=\"is not less than\"]", "is not less than"),
            ConditionAssertion.of("iflt::l_attr_15[{r_attr_15},then=\"less than\",else=\"is not less than\"]", "is not less than"),
            ConditionAssertion.of("iflt::l_attr_15[{r_attr_16},then=\"less than\",else=\"is not less than\"]", "less than"),

            /* ifle */
            ConditionAssertion.of("ifle::l_attr_15[{r_attr_14},then=\"less or equal\",else=\"is not less or equal\"]", "is not less or equal"),
            ConditionAssertion.of("ifle::l_attr_15[{r_attr_15},then=\"less or equal\",else=\"is not less or equal\"]", "less or equal"),
            ConditionAssertion.of("ifle::l_attr_15[{r_attr_16},then=\"less or equal\",else=\"is not less or equal\"]", "less or equal")
        };
    }
}
