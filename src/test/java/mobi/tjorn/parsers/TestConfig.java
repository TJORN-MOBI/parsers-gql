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

package mobi.tjorn.parsers;

import java.io.File;

/**
 *
 */
public final class TestConfig {
    public final static File ROOT_DIR = new File("src/test/input");

    public static String getPath(Class<?> cls) {
        return cls.getName().replace('.', '/');
    }
}
