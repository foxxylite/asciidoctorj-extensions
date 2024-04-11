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

import io.foxxylite.asciidoctor.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.asciidoctor.ast.Document;

import java.util.Collections;
import java.util.Map;

/**
 * This is class describes access to the document parameters.
 * The document parameters.
 *
 * @author Dmitriy Schiller
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DocumentParameters {

    private final Map<String, Object> attributes;

    protected DocumentParameters() {
        this(Collections.emptyMap());
    }

    public DocumentParameters(Document document) {
        this(document.getAttributes());
    }

    public String getValue(String key) {
        return getValueAsString(attributes, key);
    }

    protected final String getValue(String[] keys) {
        String value = null;
        for (String key : keys) {
            value = getValueAsString(attributes, key);
            if (value != null) {
                break;
            }
        }
        return value;
    }

    protected static String getValueAsString(Map<String, Object> attributes, String key) {
        return StringUtils.valueOf(attributes.get(key));
    }
}
