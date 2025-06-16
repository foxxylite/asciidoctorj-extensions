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

package io.foxxylite.asciidoctor.condition;

import org.asciidoctor.ast.ContentModel;
import org.asciidoctor.ast.StructuralNode;
import org.asciidoctor.extension.BlockProcessor;
import org.asciidoctor.extension.Contexts;
import org.asciidoctor.extension.Reader;

import java.util.Map;

import static org.asciidoctor.ast.ContentModel.SIMPLE;
import static org.asciidoctor.extension.Contexts.PASS;
import static org.asciidoctor.extension.Contexts.LISTING;

/**
 * Condition block processor.
 *
 * @author Dmitriy Schiller
 */
@NameNull
@NameEqual
@NameNotNull
@NameNotEqual
@NameLessThan
@NameGreatThan
@NameLessOrEqual
@NameGreatOrEqual
@NameStringEqual
@NameStringNotEqual
@Contexts({LISTING, PASS})
@ContentModel(SIMPLE)
public final class ConditionBlockProcessor extends BlockProcessor {

    private static final String LEFT_PAIR_KEY = "2";
    private static final String RIGHT_PAIR_KEY = "3";

    private final ConditionFactory condition = new ConditionFactory(name, this);

    public ConditionBlockProcessor(String name) {
        super(name);
    }

    @Override
    public Object process(StructuralNode parent, Reader reader, Map<String, Object> attributes) {
        ConditionParameters params = new ConditionParameters(name, LEFT_PAIR_KEY, RIGHT_PAIR_KEY, attributes);
        condition.appendBlock(parent, reader.lines(), params);
        return null;
    }
}
