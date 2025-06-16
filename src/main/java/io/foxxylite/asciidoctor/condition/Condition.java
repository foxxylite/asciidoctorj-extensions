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

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Enum of conditions.
 *
 * @author Dmitriy Schiller
 */
@SuppressWarnings("unchecked")
@RequiredArgsConstructor
enum Condition {

    /* null */
    NULL(Constants.NAME_NULL, false, (l, unused) -> Objects.isNull(l)),
    NOT_NULL(Constants.NAME_NOT_NULL, false, (l, unused) -> Objects.nonNull(l)),

    /* string */
    STRING_EQUAL(Constants.NAME_STRING_EQUAL, false, Object::equals),
    STRING_NOT_EQUAL(Constants.NAME_STRING_NOT_EQUAL, false, (l, r) -> !l.equals(r)),

    /* math */
    EQUAL(Constants.NAME_EQUAL, true, (l, r) -> l.compareTo(r) == 0),
    NOT_EQUAL(Constants.NAME_NOT_EQUAL, true, (l, r) -> l.compareTo(r) != 0),
    LESS_THAN(Constants.NAME_LESS_THAN, true, (l, r) -> l.compareTo(r) < 0),
    GREAT_THAN(Constants.NAME_GREAT_THAN, true, (l, r) -> l.compareTo(r) > 0),
    LESS_OR_EQUAL(Constants.NAME_LESS_OR_EQUAL, true, (l, r) -> l.compareTo(r) <=0),
    GREAT_OR_EQUAL(Constants.NAME_GREAT_OR_EQUAL, true, (l, r) -> l.compareTo(r) >= 0);

    private final String name;
    @Getter
    private final boolean isNumeric;
    private final BiPredicate<Comparable, Comparable> comparator;

    public <T extends Comparable<T>> boolean test(T left, T right) {
        return comparator.test(left, right);
    }

    public static Condition fromName(String name) {
        Objects.requireNonNull(name, "Condition name is null");

        for (Condition condition : Condition.values()) {
            if (condition.name.equals(name)) {
                return condition;
            }
        }
        throw new IllegalArgumentException("Unknown condition name '" + name + "'");
    }
}
