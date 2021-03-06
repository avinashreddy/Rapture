/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2011-2016 Incapture Technologies LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package plugin.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import rapture.common.PluginConfig;
import rapture.common.impl.jackson.JacksonUtil;

/**
 * Test loading a feature from raw string
 * 
 * @author amkimian
 * 
 */
public class PluginLoadingTest extends BaseModelTest {
    @Test
    public void testSimplePlugin() {
        String featureAsText = getResourceAsString(null, "/correct/feature.txt");
        PluginConfig feature = JacksonUtil.objectFromJson(featureAsText, PluginConfig.class);
        assertEquals(feature.getPlugin(), "bootstrap");
        assertEquals(1, feature.getVersion().getMajor());
        assertEquals(0, feature.getVersion().getMinor());
        assertEquals(0, feature.getVersion().getRelease());
        assertTrue(feature.getDepends().containsKey("featureX"));
        assertEquals(1, feature.getDepends().get("featureX").getMajor());
        assertEquals(0, feature.getDepends().get("featureX").getMinor());
        assertEquals(0, feature.getDepends().get("featureX").getRelease());
        assertEquals(feature.getDescription(), "A bootstrap feature");
    }
}
