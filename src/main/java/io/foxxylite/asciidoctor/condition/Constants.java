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

/**
 * @author Dmitriy Schiller
 */
interface Constants {

    /* null */
    String NAME_NULL = "ifnull";
    String NAME_NOT_NULL = "ifnotnull";

    /* string */
    String NAME_STRING_EQUAL = "ifstreq";
    String NAME_STRING_NOT_EQUAL = "ifstrne";

    /* math */
    String NAME_EQUAL = "ifeq";
    String NAME_NOT_EQUAL = "ifne";
    String NAME_GREAT_THAN = "ifgt";
    String NAME_GREAT_OR_EQUAL = "ifge";
    String NAME_LESS_THAN = "iflt";
    String NAME_LESS_OR_EQUAL = "ifle";
}
