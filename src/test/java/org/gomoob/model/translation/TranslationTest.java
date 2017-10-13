/**
 * BSD 3-Clause License
 *
 * Copyright (c) 2017, GOMOOB All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 * disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.gomoob.model.translation;

import org.gomoob.model.ITranslation;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test case for the {@link Translation} class.
 *
 * @author Simon BAUDRY (simon.baudry@gomoob.com)
 */
public class TranslationTest {

    /**
     * Test method for {@link Translation} entity creation.
     */
    @Test
    public void testTranslationEntityCreation() {
        ITranslation translation = new Translation();
        translation.setLanguageCode("fr");
        translation.setAttributeTranslation("city", "Londres");
        translation.setAttributeTranslation("language", "Anglais");
        translation.setAttributeTranslation("continent", "Europe");

        try {
            translation.getAttributeTranslation("unknownProperty");
            Assert.fail("An IllegalStateException should be thrown !");
        } catch (IllegalStateException e) {
            Assert.assertEquals(
                "No attribute named 'unknownProperty' has been found in the attribute translations !",
                e.getMessage()
            );
        }

        Assert.assertSame("fr", translation.getLanguageCode());
        Assert.assertTrue(translation.getAttributeTranslations().size() == 3);
        Assert.assertSame("Londres", translation.getAttributeTranslation("city"));
        Assert.assertSame("Anglais", translation.getAttributeTranslation("language"));
        Assert.assertSame("Europe", translation.getAttributeTranslation("continent"));
    }
}
