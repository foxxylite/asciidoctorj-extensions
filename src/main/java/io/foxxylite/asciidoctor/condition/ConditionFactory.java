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

import com.google.common.base.Strings;
import org.asciidoctor.ast.ContentNode;
import org.asciidoctor.ast.StructuralNode;
import org.asciidoctor.extension.Processor;
import io.foxxylite.asciidoctor.utils.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * A factory to create content by condition.
 *
 * @author Dmitriy Schiller
 */
final class ConditionFactory {

    private final Condition condition;
    private final Processor processor;

    public ConditionFactory(String name, Processor processor) {
        this.condition = Condition.fromName(name);
        this.processor = processor;
    }

    public void appendBlock(StructuralNode parent, List<String> thenContent, ConditionParameters params) {
        if (isConditionTruth(parent, params) && !thenContent.isEmpty()) {
            processor.parseContent(parent, thenContent);
        }
    }

    public Object createParagraphBlock(StructuralNode parent, ConditionParameters params) {
        String content = getContent(parent, params);
        return Strings.isNullOrEmpty(content) ? null : processor.createBlock(parent, "paragraph", content, params.getAttributes());
    }

    public Object createPhraseNodeQuoted(ContentNode parent, ConditionParameters params) {
        String content = getContent(parent, params);
        return Strings.isNullOrEmpty(content) ? null :
            processor.createPhraseNode(parent, "quoted", content, params.getAttributes());
    }

    private String getContent(ContentNode parent, ConditionParameters params) {
        return isConditionTruth(parent, params) ? params.getThenContent() : params.getElseContent();
    }

    private boolean isConditionTruth(ContentNode parent, ConditionParameters params) {
        boolean result;
        String leftPairKey = params.getLeftPairKey();
        String leftStr = StringUtils.valueOf(parent.getAttribute(leftPairKey, null, true)),
               rightStr = params.getRightPair();
        if (condition.isNumeric()) {
            BigDecimal leftNum = createBigDecimal(leftStr, "left"), rightNum = createBigDecimal(rightStr, "right");
            result = condition.test(leftNum, rightNum);
        } else {
            result = condition.test(leftStr, rightStr);
        }
        return result;
    }

    private static BigDecimal createBigDecimal(String value, String position) {
        String normalized = Objects.requireNonNull(value, position + " value is null")
            //If value is formatted then removing all whitespace, including non-breaking space (&nsbp;)
            //Example: 1 234 567 => 1234567
            .replaceAll("\\s+|\\u00A0", "");
        if (normalized.isEmpty()) {
            throw new NumberFormatException(position + "string is empty");
        }
        return new BigDecimal(normalized);
    }
}
