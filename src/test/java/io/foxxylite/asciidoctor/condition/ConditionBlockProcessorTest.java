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

import org.assertj.core.api.AbstractAssert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class ConditionBlockProcessorTest extends ConditionBaseTest {

    @DataProvider(name = "conditions")
    public ConditionAssertion[] createAssertions() {
        return new ConditionAssertion[] {
            /* ifnull */
            ConditionAssertion.of("[ifnull,null_attr]\n----\nis null\n----", "is null"),
            ConditionAssertion.of("[ifnull,not_null_attr]\n----\nis null\n----", AbstractAssert::isNull),

            ConditionAssertion.of("[ifnull,null_attr]\n++++\nis null\n++++", "is null"),
            ConditionAssertion.of("[ifnull,not_null_attr]\n++++\nis null\n++++", AbstractAssert::isNull),

            /* ifnotnull */
            ConditionAssertion.of("[ifnotnull,null_attr]\n----\nis not null\n----", AbstractAssert::isNull),
            ConditionAssertion.of("[ifnotnull,not_null_attr]\n----\nis not null\n----", "is not null"),

            ConditionAssertion.of("[ifnotnull,null_attr]\n++++\nis not null\n++++", AbstractAssert::isNull),
            ConditionAssertion.of("[ifnotnull,not_null_attr]\n++++\nis not null\n++++", "is not null"),

            /* ifstreq */
            ConditionAssertion.of("[ifstreq,not_null_attr,some value]\n----\nis equal\n----", "is equal"),
            ConditionAssertion.of("[ifstreq,not_null_attr,some value 1]\n----\nis equal\n----", AbstractAssert::isNull),

            ConditionAssertion.of("[ifstreq,not_null_attr,some value]\n++++\nis equal\n++++", "is equal"),
            ConditionAssertion.of("[ifstreq,not_null_attr,some value 1]\n++++\nis equal\n++++", AbstractAssert::isNull),

            /* ifstrne */
            ConditionAssertion.of("[ifstrne,not_null_attr,some value]\n----\nis not equal\n----", AbstractAssert::isNull),
            ConditionAssertion.of("[ifstrne,not_null_attr,some value 1]\n----\nis not equal\n----", "is not equal"),

            ConditionAssertion.of("[ifstrne,not_null_attr,some value]\n++++\nis not equal\n++++", AbstractAssert::isNull),
            ConditionAssertion.of("[ifstrne,not_null_attr,some value 1]\n++++\nis not equal\n++++", "is not equal"),

            /* ifeq */
            ConditionAssertion.of("[ifeq,l_attr_15,{r_attr_15}]\n----\nis equal\n----", "is equal"),
            ConditionAssertion.of("[ifeq,l_attr_15,{r_attr_16}]\n----\nis equal\n----", AbstractAssert::isNull),

            ConditionAssertion.of("[ifeq,l_attr_15,{r_attr_15}]\n++++\nis equal\n++++", "is equal"),
            ConditionAssertion.of("[ifeq,l_attr_15,{r_attr_16}]\n++++\nis equal\n++++", AbstractAssert::isNull),

            /* ifne */
            ConditionAssertion.of("[ifne,l_attr_15,{r_attr_15}]\n----\nis not equal\n----", AbstractAssert::isNull),
            ConditionAssertion.of("[ifne,l_attr_15,{r_attr_16}]\n----\nis not equal\n----", "is not equal"),

            ConditionAssertion.of("[ifne,l_attr_15,{r_attr_15}]\n++++\nis not equal\n++++", AbstractAssert::isNull),
            ConditionAssertion.of("[ifne,l_attr_15,{r_attr_16}]\n++++\nis not equal\n++++", "is not equal"),

            /* ifgt */
            ConditionAssertion.of("[ifgt,l_attr_15,{r_attr_14}]\n----\ngreat than\n----", "great than"),
            ConditionAssertion.of("[ifgt,l_attr_15,{r_attr_15}]\n----\ngreat than\n----", AbstractAssert::isNull),
            ConditionAssertion.of("[ifgt,l_attr_15,{r_attr_16}]\n----\ngreat than\n----", AbstractAssert::isNull),

            ConditionAssertion.of("[ifgt,l_attr_15,{r_attr_14}]\n++++\ngreat than\n++++", "great than"),
            ConditionAssertion.of("[ifgt,l_attr_15,{r_attr_15}]\n++++\ngreat than\n++++", AbstractAssert::isNull),
            ConditionAssertion.of("[ifgt,l_attr_15,{r_attr_16}]\n++++\ngreat than\n++++", AbstractAssert::isNull),

            /* ifge */
            ConditionAssertion.of("[ifge,l_attr_15,{r_attr_14}]\n----\ngreat or equal\n----", "great or equal"),
            ConditionAssertion.of("[ifge,l_attr_15,{r_attr_15}]\n----\ngreat or equal\n----", "great or equal"),
            ConditionAssertion.of("[ifge,l_attr_15,{r_attr_16}]\n----\ngreat or equal\n----", AbstractAssert::isNull),

            ConditionAssertion.of("[ifge,l_attr_15,{r_attr_14}]\n++++\ngreat or equal\n++++", "great or equal"),
            ConditionAssertion.of("[ifge,l_attr_15,{r_attr_15}]\n++++\ngreat or equal\n++++", "great or equal"),
            ConditionAssertion.of("[ifge,l_attr_15,{r_attr_16}]\n++++\ngreat or equal\n++++", AbstractAssert::isNull),

            /* iflt */
            ConditionAssertion.of("[iflt,l_attr_15,{r_attr_14}]\n----\nless than\n----", AbstractAssert::isNull),
            ConditionAssertion.of("[iflt,l_attr_15,{r_attr_15}]\n----\nless than\n----", AbstractAssert::isNull),
            ConditionAssertion.of("[iflt,l_attr_15,{r_attr_16}]\n----\nless than\n----", "less than"),

            ConditionAssertion.of("[iflt,l_attr_15,{r_attr_14}]\n++++\nless than\n++++", AbstractAssert::isNull),
            ConditionAssertion.of("[iflt,l_attr_15,{r_attr_15}]\n++++\nless than\n++++", AbstractAssert::isNull),
            ConditionAssertion.of("[iflt,l_attr_15,{r_attr_16}]\n++++\nless than\n++++", "less than"),

            /* ifle */
            ConditionAssertion.of("[ifle,l_attr_15,{r_attr_14}]\n----\nless or equal\n----", AbstractAssert::isNull),
            ConditionAssertion.of("[ifle,l_attr_15,{r_attr_15}]\n----\nless or equal\n----", "less or equal"),
            ConditionAssertion.of("[ifle,l_attr_15,{r_attr_16}]\n----\nless or equal\n----", "less or equal"),

            ConditionAssertion.of("[ifle,l_attr_15,{r_attr_14}]\n++++\nless or equal\n++++", AbstractAssert::isNull),
            ConditionAssertion.of("[ifle,l_attr_15,{r_attr_15}]\n++++\nless or equal\n++++", "less or equal"),
            ConditionAssertion.of("[ifle,l_attr_15,{r_attr_16}]\n++++\nless or equal\n++++", "less or equal")
        };
    }
}
