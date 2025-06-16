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

import org.asciidoctor.ast.Document;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * The block parameters.
 *
 * @author Dmitriy Schiller
 */
public abstract class BlockParameters extends DocumentParameters {

    public static final char KEY_DELIMITER = '-';

    private static final String GROUP_NAME = "group-name";

    private final Map<String, Object> attributes;

    public BlockParameters(Map<String, Object> attributes) {
        this.attributes = Collections.unmodifiableMap(attributes);
    }

    public BlockParameters(Map<String, Object> attributes, Document document) {
        super(document);
        this.attributes = Collections.unmodifiableMap(attributes);
    }

    public int getValue(String key, int defaultValue) {
        String value = getValue(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

    @Override
    public String getValue(String key) {
        return getValue(key, false);
    }

    protected String getValue(String key, boolean isRequired) {
        String value;
        if (attributes.containsKey(key)) {
            value = getValueAsString(attributes, key);
        } else {
            String blockName = getBlockName(), groupName = getGroupName();
            String shortName = blockName + KEY_DELIMITER + key;
            value = getValue((groupName == null) ? new String[] { shortName } :
                new String[] { groupName + KEY_DELIMITER + shortName, shortName }
            );
        }
        return isRequired ? Objects.requireNonNull(value, "Required attribute  '" + getBlockName() + '/' + key + "' is not present") : value;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    protected abstract String getBlockName();

    protected final String getGroupName() {
        return getValueAsString(attributes, GROUP_NAME);
    }
}
