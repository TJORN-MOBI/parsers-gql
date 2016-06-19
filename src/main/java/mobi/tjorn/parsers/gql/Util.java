/*
 * Copyright 2016 TJORN LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mobi.tjorn.parsers.gql;

/**
 * Contains some internal utility methods.
 *
 * @author yuri
 * @since 6/6/16.
 */
final class Util {
    /**
     * Matches {@link Token} against {@code names}.
     *
     * @param t {@link Token} to match.
     * @param names Names to match against.
     * @throws ParseException Thrown when matching fails.
     */
    public static void matchNames(Token t, String...names) throws ParseException {
        final String s = t.image;
        boolean matched = false;
        for (final String name : names) {
            if (name.equalsIgnoreCase(s)) {
                matched = true;
                break;
            }
        }
        if (! matched) {
            final StringBuilder sb = new StringBuilder();
            sb.append("Encountered <NAME> \"").append(s).append("\". Was expecting <NAME>(s): ");
            int idx = -1;
            for (final String name : names) {
                if (++idx > 0) {
                    sb.append(", ");
                }
                sb.append("\"").append(name).append("\"");
            }
            throw new ParseException(sb.toString());
        }
    }

    /**
     * Checks if {@link Token} is {@code null} or not.
     *
     * @param t A {@link Token}
     * @return {@code true} if {@link Token} is not null, {@code false} otherwise.
     */
    public static boolean is(Token t) {
        return t != null;
    }

    /**
     * Matches {@link Token} against a {@link String}.
     *
     * @param t {@link Token} to match.
     * @param s A {@link String} to match against.
     * @return {@code true} if matches, {@link false} otherwise.
     */
    public static boolean matches(Token t, String s) {
        return t.image.equalsIgnoreCase(s);
    }
}
