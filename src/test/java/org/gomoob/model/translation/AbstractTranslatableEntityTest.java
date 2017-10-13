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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gomoob.model.ITranslation;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test case for the {@link AbstractTranslatableEntity} class.
 *
 * @author Simon BAUDRY (simon.baudry@gomoob.com)
 */
public class AbstractTranslatableEntityTest {

    /**
     * Test method for {@link AbstractTranslatableEntity} entity creation.
     */
    @Test
    public void testAbstractTranslatableEntity() {
        SampleTranslatableEntity sampleEntity = new SampleTranslatableEntity();

        /*
         * Test simple valid creation
         */
        Assert.assertNull(sampleEntity.getId());
        Assert.assertNull(sampleEntity.getDefaultLanguageCode());
        Assert.assertNull(sampleEntity.getTranslationLanguageCode());
        Assert.assertNull(sampleEntity.getTranslations());
        Assert.assertNull(sampleEntity.getTranslatableAttribute());
        Assert.assertNull(sampleEntity.getNotTranslatableAttribute());

        ITranslation frTranslation = new Translation();
        frTranslation.setLanguageCode("fr");
        frTranslation.setAttributeTranslation("translatableAttribute", "Londres");

        sampleEntity.setId(1);
        sampleEntity.setDefaultLanguageCode("en");
        sampleEntity.setTranslation(frTranslation);
        sampleEntity.setTranslatableAttribute("London");
        sampleEntity.setNotTranslatableAttribute(100);

        Assert.assertSame(1, sampleEntity.getId());
        Assert.assertSame("en", sampleEntity.getDefaultLanguageCode());
        Assert.assertNull(sampleEntity.getTranslationLanguageCode());
        Assert.assertTrue(sampleEntity.getTranslations().size() == 1);
        Assert.assertEquals(frTranslation, sampleEntity.getTranslation("fr"));
        Assert.assertSame("London", sampleEntity.getTranslatableAttribute());
        Assert.assertSame(100, sampleEntity.getNotTranslatableAttribute());

        /*
         * Test applyTranslation method
         */
        sampleEntity = new SampleTranslatableEntity();
        sampleEntity.setId(1);
        sampleEntity.setDefaultLanguageCode("en");
        sampleEntity.setTranslation(frTranslation);
        sampleEntity.setTranslatableAttribute("London");
        sampleEntity.setNotTranslatableAttribute(100);

        // Test with no translation set
        sampleEntity.setTranslations(null);
        try {
            sampleEntity.applyTranslation("fr");
            Assert.fail("An IllegalStateException should be thrown !");
        } catch(IllegalStateException e) {
            Assert.assertSame(
                "No translations associated to the entity !",
                e.getMessage()
            );
        }

        // Test applying an unknown translation
        frTranslation = new Translation();
        frTranslation.setLanguageCode("fr");
        frTranslation.setAttributeTranslation("translatableAttribute", "Londres");
        sampleEntity.setTranslation(frTranslation);
        try {
            sampleEntity.applyTranslation("es");
            Assert.fail("An IllegalStateException should be thrown !");
        } catch(IllegalStateException e) {
            Assert.assertEquals(
                "No translation with the language code 'es' is registered !",
                e.getMessage()
            );
        }

        // Test applying a translation entity which contains an attribute translation which is not of string type
        ITranslation nlTranslation = new Translation();
        nlTranslation.setLanguageCode("nl");
        nlTranslation.setAttributeTranslation("translatableAttribute", "Londen");
        nlTranslation.setAttributeTranslation("notTranslatableAttribute", "999");
        sampleEntity.setTranslation(nlTranslation);

        try {
            sampleEntity.applyTranslation("nl");
            Assert.fail("An IllegalStateException should be thrown !");
        } catch(IllegalStateException e) {
            Assert.assertEquals(
                "Translation attribute values must be of type 'String' !",
                e.getMessage()
            );
        }

        nlTranslation = new Translation();
        nlTranslation.setLanguageCode("nl");
        nlTranslation.setAttributeTranslation("translatableAttribute", "Londen");
        sampleEntity.setTranslation(nlTranslation);
        sampleEntity.setTranslatableAttribute("London");
        sampleEntity.setTranslationLanguageCode(null);

        // Test applying default language translation
        sampleEntity.applyTranslation("en");

        Assert.assertSame(1, sampleEntity.getId());
        Assert.assertSame("en", sampleEntity.getDefaultLanguageCode());
        Assert.assertNull(sampleEntity.getTranslationLanguageCode());
        Assert.assertTrue(sampleEntity.getTranslations().size() == 2);
        Assert.assertEquals(frTranslation, sampleEntity.getTranslation("fr"));
        Assert.assertEquals(nlTranslation, sampleEntity.getTranslation("nl"));
        Assert.assertSame("London", sampleEntity.getTranslatableAttribute());
        Assert.assertSame(100, sampleEntity.getNotTranslatableAttribute());

        // Test applying a language translation which is not the default language code
        sampleEntity.applyTranslation("fr");

        Assert.assertSame(1, sampleEntity.getId());
        Assert.assertSame("en", sampleEntity.getDefaultLanguageCode());
        Assert.assertSame("fr", sampleEntity.getTranslationLanguageCode());
        Assert.assertTrue(sampleEntity.getTranslations().size() == 2);
        Assert.assertEquals(
            "en",
            sampleEntity.getTranslation("en").getLanguageCode()
        );
        Assert.assertEquals(
            "London",
            sampleEntity.getTranslation("en").getAttributeTranslation("translatableAttribute")
        );
        Assert.assertEquals(nlTranslation, sampleEntity.getTranslation("nl"));
        Assert.assertSame("Londres", sampleEntity.getTranslatableAttribute());
        Assert.assertSame(100, sampleEntity.getNotTranslatableAttribute());

        /*
         * Test deleteTranslation method
         */
        sampleEntity = new SampleTranslatableEntity();
        sampleEntity.setId(1);
        sampleEntity.setDefaultLanguageCode("en");
        sampleEntity.setTranslation(frTranslation);
        sampleEntity.setTranslatableAttribute("London");
        sampleEntity.setNotTranslatableAttribute(100);
        sampleEntity.setTranslation(frTranslation);
        sampleEntity.setTranslation(nlTranslation);

        // Test with an unknown translation language code
        try {
            sampleEntity.deleteTranslation("es");
            Assert.fail("An IllegalStateException should be thrown !");
        } catch(IllegalStateException e) {
            Assert.assertEquals(
                "No translation with the language code 'es' is registered !",
                e.getMessage()
            );
        }

        // Test deleting an existing translation
        Assert.assertTrue(sampleEntity.getTranslations().size() == 2);
        Assert.assertEquals(nlTranslation, sampleEntity.getTranslation("nl"));

        sampleEntity.deleteTranslation("nl");

        Assert.assertTrue(sampleEntity.getTranslations().size() == 1);
        Assert.assertFalse(sampleEntity.getTranslations().containsKey("nl"));

        /*
         * Test deleteTranslations method
         */
        sampleEntity = new SampleTranslatableEntity();
        sampleEntity.setId(1);
        sampleEntity.setDefaultLanguageCode("en");
        sampleEntity.setTranslation(frTranslation);
        sampleEntity.setTranslatableAttribute("London");
        sampleEntity.setNotTranslatableAttribute(100);
        sampleEntity.setTranslation(frTranslation);
        sampleEntity.setTranslation(nlTranslation);

        // Test deleting all translations
        Assert.assertTrue(sampleEntity.getTranslations().size() == 2);
        Assert.assertEquals(frTranslation, sampleEntity.getTranslation("fr"));
        Assert.assertEquals(nlTranslation, sampleEntity.getTranslation("nl"));

        sampleEntity.deleteTranslations(null);

        Assert.assertSame(1, sampleEntity.getId());
        Assert.assertSame("en", sampleEntity.getDefaultLanguageCode());
        Assert.assertNull(sampleEntity.getTranslationLanguageCode());
        Assert.assertNull(sampleEntity.getTranslations());
        Assert.assertSame("London", sampleEntity.getTranslatableAttribute());
        Assert.assertSame(100, sampleEntity.getNotTranslatableAttribute());

        sampleEntity.setTranslation(frTranslation);
        sampleEntity.setTranslation(nlTranslation);

        // Test deleting a list of translations with an unknown translation languge code
        List<String> languageCodes = new ArrayList<String>();
        languageCodes.add("fr");
        languageCodes.add("es");

        try {
            sampleEntity.deleteTranslations(languageCodes);
            Assert.fail("An IllegalStateException should be thrown !");
        } catch(IllegalStateException e) {
            Assert.assertEquals(
                "No translation with the language code 'es' is registered !",
                e.getMessage()
            );
        }

        languageCodes.remove("es");

        // Test deleting a list of existing translations
        sampleEntity.deleteTranslations(languageCodes);

        Assert.assertSame(1, sampleEntity.getId());
        Assert.assertSame("en", sampleEntity.getDefaultLanguageCode());
        Assert.assertNull(sampleEntity.getTranslationLanguageCode());
        Assert.assertTrue(sampleEntity.getTranslations().size() == 1);
        Assert.assertEquals(nlTranslation, sampleEntity.getTranslation("nl"));
        Assert.assertSame("London", sampleEntity.getTranslatableAttribute());
        Assert.assertSame(100, sampleEntity.getNotTranslatableAttribute());

        /*
         * Test getTranslation method
         */
        sampleEntity = new SampleTranslatableEntity();
        sampleEntity.setId(1);
        sampleEntity.setDefaultLanguageCode("en");
        sampleEntity.setTranslation(frTranslation);
        sampleEntity.setTranslatableAttribute("London");
        sampleEntity.setNotTranslatableAttribute(100);

        // Test with an entity which does not have any translation
        try {
            sampleEntity.getTranslation("es");
            Assert.fail("An IllegalStateException should be thrown !");
        } catch(IllegalStateException e) {
            Assert.assertEquals(
                "No translation with the language code 'es' is registered !",
                e.getMessage()
            );
        }

        sampleEntity.setTranslation(frTranslation);
        sampleEntity.setTranslation(nlTranslation);

        // Test with an unkown translation language code
        try {
            sampleEntity.getTranslation("es");
            Assert.fail("An IllegalStateException should be thrown !");
        } catch(IllegalStateException e) {
            Assert.assertEquals(
                "No translation with the language code 'es' is registered !",
                e.getMessage()
            );
        }

        // Test with an existing translation language code
        Assert.assertEquals(frTranslation, sampleEntity.getTranslation("fr"));

        /*
         * Test setTranslation method
         */
        sampleEntity = new SampleTranslatableEntity();
        sampleEntity.setId(1);
        sampleEntity.setDefaultLanguageCode("en");
        sampleEntity.setTranslatableAttribute("London");
        sampleEntity.setNotTranslatableAttribute(100);
        sampleEntity.setTranslation(frTranslation);
        sampleEntity.setTranslation(nlTranslation);

        // Test with an entity already translated
        sampleEntity.applyTranslation("fr");
        Assert.assertEquals("fr", sampleEntity.getTranslationLanguageCode());

        try {
            sampleEntity.setTranslation(nlTranslation);
            Assert.fail("An IllegalStateException should be thrown !");
        } catch(IllegalStateException e) {
            Assert.assertEquals(
                "Cannot add a translation for an entity which is already using the 'one language mode' !",
                e.getMessage()
            );
        }

        // Test with an entity without translations
        sampleEntity.setTranslationLanguageCode(null);
        sampleEntity.setTranslatableAttribute("London");
        sampleEntity.setTranslations(null);

        sampleEntity.setTranslation(nlTranslation);

        Assert.assertTrue(sampleEntity.getTranslations().size() == 1);
        Assert.assertEquals(nlTranslation, sampleEntity.getTranslation("nl"));

        // Test with an entity with translations
        sampleEntity.setTranslation(frTranslation);

        Assert.assertTrue(sampleEntity.getTranslations().size() == 2);
        Assert.assertEquals(nlTranslation, sampleEntity.getTranslation("nl"));
        Assert.assertEquals(frTranslation, sampleEntity.getTranslation("fr"));
    }

    /**
     * Test method for {@link AbstractTranslatableEntityWithCreationDate} entity creation.
     */
    @Test
    public void testAbstractTranslatableEntityWithCreationDate() {
        SampleTranslatableEntityWithCreationDate sampleEntity = new SampleTranslatableEntityWithCreationDate();

        /*
         * Test simple valid creation
         */
        Assert.assertNull(sampleEntity.getId());
        Assert.assertNull(sampleEntity.getDefaultLanguageCode());
        Assert.assertNull(sampleEntity.getTranslationLanguageCode());
        Assert.assertNull(sampleEntity.getTranslations());
        Assert.assertNull(sampleEntity.getTranslatableAttribute());
        Assert.assertNull(sampleEntity.getNotTranslatableAttribute());
        Assert.assertNull(sampleEntity.getCreationDate());

        ITranslation frTranslation = new Translation();
        frTranslation.setLanguageCode("fr");
        frTranslation.setAttributeTranslation("translatableAttribute", "Londres");

        sampleEntity.setId(1);
        sampleEntity.setDefaultLanguageCode("en");
        sampleEntity.setTranslation(frTranslation);
        sampleEntity.setTranslatableAttribute("London");
        sampleEntity.setNotTranslatableAttribute(100);
        Date creationDate = new Date();
        sampleEntity.setCreationDate(creationDate);

        Assert.assertSame(1, sampleEntity.getId());
        Assert.assertSame("en", sampleEntity.getDefaultLanguageCode());
        Assert.assertNull(sampleEntity.getTranslationLanguageCode());
        Assert.assertTrue(sampleEntity.getTranslations().size() == 1);
        Assert.assertEquals(frTranslation, sampleEntity.getTranslation("fr"));
        Assert.assertSame("London", sampleEntity.getTranslatableAttribute());
        Assert.assertSame(100, sampleEntity.getNotTranslatableAttribute());
        Assert.assertEquals(creationDate, sampleEntity.getCreationDate());
    }

    /**
     * Test method for {@link AbstractTranslatableEntityWithCreationDateAndUpdateDate} entity creation.
     */
    @Test
    public void testAbstractTranslatableEntityWithCreationDateAndUpdateDate() {
        SampleTranslatableEntityWithCreationDateAndUpdateDate sampleEntity =
            new SampleTranslatableEntityWithCreationDateAndUpdateDate();

        /*
         * Test simple valid creation
         */
        Assert.assertNull(sampleEntity.getId());
        Assert.assertNull(sampleEntity.getDefaultLanguageCode());
        Assert.assertNull(sampleEntity.getTranslationLanguageCode());
        Assert.assertNull(sampleEntity.getTranslations());
        Assert.assertNull(sampleEntity.getTranslatableAttribute());
        Assert.assertNull(sampleEntity.getNotTranslatableAttribute());
        Assert.assertNull(sampleEntity.getCreationDate());
        Assert.assertNull(sampleEntity.getUpdateDate());

        ITranslation frTranslation = new Translation();
        frTranslation.setLanguageCode("fr");
        frTranslation.setAttributeTranslation("translatableAttribute", "Londres");

        sampleEntity.setId(1);
        sampleEntity.setDefaultLanguageCode("en");
        sampleEntity.setTranslation(frTranslation);
        sampleEntity.setTranslatableAttribute("London");
        sampleEntity.setNotTranslatableAttribute(100);
        Date creationDate = new Date();
        sampleEntity.setCreationDate(creationDate);
        Date updateDate = new Date();
        sampleEntity.setUpdateDate(updateDate);

        Assert.assertSame(1, sampleEntity.getId());
        Assert.assertSame("en", sampleEntity.getDefaultLanguageCode());
        Assert.assertNull(sampleEntity.getTranslationLanguageCode());
        Assert.assertTrue(sampleEntity.getTranslations().size() == 1);
        Assert.assertEquals(frTranslation, sampleEntity.getTranslation("fr"));
        Assert.assertSame("London", sampleEntity.getTranslatableAttribute());
        Assert.assertSame(100, sampleEntity.getNotTranslatableAttribute());
        Assert.assertEquals(creationDate, sampleEntity.getCreationDate());
        Assert.assertEquals(updateDate, sampleEntity.getUpdateDate());
    }
}
