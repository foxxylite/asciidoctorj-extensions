/*
 * Copyright (c) 2024 Dmitriy Schiller
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

package io.foxxylite.asciidoctor;

import com.google.auto.service.AutoService;
import io.foxxylite.asciidoctor.condition.ConditionBlockMacroProcessor;
import io.foxxylite.asciidoctor.condition.ConditionBlockProcessor;
import io.foxxylite.asciidoctor.condition.ConditionInlineMacroProcessor;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.jruby.extension.spi.ExtensionRegistry;

/**
 * AsciidoctorJ extensions entrypoint.
 *
 * @author Dmitriy Schiller
 */
@AutoService(ExtensionRegistry.class)
public final class ExtensionsRegistry implements ExtensionRegistry {

    @Override
    public void register(Asciidoctor asciidoctor) {
        asciidoctor.javaExtensionRegistry()
            /* Inline macro */
            .inlineMacro(ConditionInlineMacroProcessor.class)

            /* Blocks macro */
            .blockMacro(ConditionBlockMacroProcessor.class)

            /* Blocks */
            .block(ConditionBlockProcessor.class);
    }
}
