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

import io.foxxylite.asciidoctor.BlockParameters;

import java.util.Map;
import java.util.function.Supplier;

/**
 * The condition parameters.
 *
 * @author Dmitriy Schiller
 */
final class ConditionParameters extends BlockParameters {

    private static final String RIGHT_PAIR_KEY = "1";

    private static final String THEN = "then";
    private static final String ELSE = "else";

    private final String blockName;
    private final Supplier<String> leftPairKeyFactory;
    private final String rightPairKey;

    public ConditionParameters(String blockName, Supplier<String> leftPairKeyFactory, Map<String, Object> attributes) {
        this(blockName, leftPairKeyFactory, RIGHT_PAIR_KEY, attributes);
    }

    public ConditionParameters(String blockName, String leftPairKey, String rightPairKey, Map<String, Object> attributes) {
        this(blockName, () -> BlockParameters.getValueAsString(attributes, leftPairKey), rightPairKey, attributes);
    }

    private ConditionParameters(String blockName, Supplier<String> leftPairKeyFactory, String rightPairKey, Map<String, Object> attributes) {
        super(attributes);
        this.blockName = blockName;
        this.leftPairKeyFactory = leftPairKeyFactory;
        this.rightPairKey = rightPairKey;
    }

    public String getLeftPairKey() {
        return leftPairKeyFactory.get();
    }

    public String getRightPair() {
        return getValue(rightPairKey);
    }

    public String getThenContent() {
        return getValue(THEN, true);
    }

    public String getElseContent() {
        return getValue(ELSE);
    }

    @Override
    protected String getBlockName() {
        return blockName;
    }
}
