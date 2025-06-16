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

package io.foxxylite.asciidoctor.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Static utility methods pertaining to {@code String} instances.
 *
 * @author Dmitriy Schiller
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

    /**
     * Returns the string representation of the Object argument or {@code null} if the argument is null.
     *
     * @param obj an {@code Object}
     * @return the value of {@code obj.toString()} or {@code null} if the argument is {@code null}
     */
    public static String valueOf(Object obj) {
        return (obj == null) ? null : obj.toString();
    }
}
