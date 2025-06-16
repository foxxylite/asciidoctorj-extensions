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

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Attributes;
import org.asciidoctor.Options;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

abstract class ConditionBaseTest {

    private final Asciidoctor asciidoctor = Asciidoctor.Factory.create();
    private final Options options = Options.builder()
        .docType("inline")
        .attributes(
            Attributes.builder()
                .attribute("not_null_attr", "some value")
                .attribute("l_attr_15", "15")
                .attribute("r_attr_14", "14")
                .attribute("r_attr_15", "15")
                .attribute("r_attr_16", "16")
                .build()
        )
        .build();

    @Test(dataProvider = "conditions")
    public void verifyCondition(ConditionAssertion data) {
        String actual = asciidoctor.convert(data.getContent(), options);
        data.getStringAssert().accept(assertThat(actual));
    }
}
