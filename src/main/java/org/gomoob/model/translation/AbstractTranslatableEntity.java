/**
 * BSD 3-Clause License
 *
 * Copyright (c) 2017, GOMOOB All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 *
 * * Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.gomoob.model.translation;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.gomoob.model.AbstractEntity;
import org.gomoob.model.ITranslatableEntity;
import org.gomoob.model.ITranslation;

/**
 * Abstract class to be extended by Translatable Business entities.
 *
 * @author Simon BAUDRY (simon.baudry@gomoob.com)
 *
 * @param <IDT> the type of the technical identifier associated to this entity.
 */
public abstract class AbstractTranslatableEntity<IDT extends Serializable>
    extends AbstractEntity<IDT> implements ITranslatableEntity<IDT> {

  /**
   * The language which was used when the entity was created. In most cases this is the language
   * which was used by the user who created the entity.
   * <p>
   * This field is a string which must be compliant with the ISO639-1 standard.
   * This language code is ALWAYS stored using an upper case syntax.
   * </p>
   *
   * @see http://fr.wikipedia.org/wiki/Liste_des_codes_ISO_639-1
   */
  private String defaultLanguageCode;

  /**
   * The language code associated to translations which have been applied to the entity. The
   * translation language code is only set if the entity is translated (that's to say its fields are
   * translated in a different language than the default language code).
   *
   * <p>
   * This field is a string which must be compliant with the ISO639-1 standard.
   * This language code is ALWAYS stored using an upper case syntax.
   * </p>
   *
   * @see http://fr.wikipedia.org/wiki/Liste_des_codes_ISO_639-1
   */
  private String translationLanguageCode;

  /**
   * Multiple translations in multiple languages for this translatable entity. This attribute is
   * used when you have to display multiple translations in multiple languages on a GUI, in most
   * cases thoses GUIs are dedicated to persons responsible of the translations (i.e the
   * translators). When you use multiple translations in multiple languages attached to an entity
   * the metadata attached to the entity stays in the default language specified by the default
   * language code. This attribute should not contain translations for the default language.
   * <p>
   * If you have to display an entity in only one language then do not use this attribute and prefer
   * the use of the `getTranslationLanguageCode()` and `setTranslationLanguageCode()` functions and
   * translate the fields of the entity directly.
   * </p>
   */
  private Map<String, ITranslation> translations;

  /**
   * {@inheritDoc}
   */
  @Override
  public void applyTranslation(final String languageCode) {
    // Apply the translations of the entity attributes in the targeted language code
    // if the actual translation language code is not already the targeted language code
    String previousTranslationLanguageCode = this.getDefaultLanguageCode();
    if (this.getTranslationLanguageCode() != null) {
      previousTranslationLanguageCode = this.getTranslationLanguageCode();
    }

    if (previousTranslationLanguageCode != languageCode) {
      // Checks that translations are embedded
      if (this.translations == null) {
        throw new IllegalStateException("No translations associated to the entity !");
      }

      // Checks that the target translation language code exists
      if (!this.translations.containsKey(languageCode)) {
        throw new IllegalStateException(
            "No translation with the language code '" + languageCode + "' is registered !");
      }

      ITranslation translationToApply = this.translations.get(languageCode);
      ITranslation previousTranslation = new Translation();
      previousTranslation.setLanguageCode(previousTranslationLanguageCode);

      for (Map.Entry<String, String> entry : translationToApply.getAttributeTranslations()
          .entrySet()) {
        String attributeName = entry.getKey();

        // Checks that the attribute to translate is of String Type
        Field field = null;
        try {
          field = this.getClass().getDeclaredField(attributeName);
        } catch (NoSuchFieldException | SecurityException e) {
          throw new RuntimeException("Fail to get value of property '" + attributeName + "' !", e);
        }

        if (!field.getType().equals(String.class)) {
          throw new IllegalStateException(
              "Translation attribute values must be of type 'String' !");
        }

        // Sets the previous translation attribute with the entity attribute value
        previousTranslation.setAttributeTranslation(attributeName,
            (String) this.get(attributeName));

        // Set the entity attribute value with the translation attribute to apply
        String attributeValue = entry.getValue();
        this.set(attributeName, attributeValue);
      }

      // Sets the new translations and translation language code
      this.setTranslationLanguageCode(null);
      this.deleteTranslation(languageCode);
      this.setTranslation(previousTranslation);
      if (languageCode != this.getDefaultLanguageCode()) {
        this.setTranslationLanguageCode(languageCode);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteTranslation(final String languageCode) {
    if (this.translations != null) {
      if (!this.translations.containsKey(languageCode)) {
        throw new IllegalStateException(
            "No translation with the language code '" + languageCode + "' is registered !");
      }

      this.translations.remove(languageCode);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteTranslations(final List<String> languageCodes) {
    // If the parameter is null we delete all the translations
    if (languageCodes == null) {
      this.translations = null;
    // Otherwise we only delete the provided translations
    } else {
      // First we check that all the provided language codes are associated to registered
      // translation
      Iterator<String> it = languageCodes.iterator();
      while (it.hasNext()) {
        String languageCode = it.next();

        if (!this.translations.containsKey(languageCode)) {
          throw new IllegalStateException(
              "No translation with the language code '" + languageCode + "' is registered !");
        }
      }

      // Then we delete the translations
      it = languageCodes.iterator();
      while (it.hasNext()) {
        String languageCode = it.next();
        this.translations.remove(languageCode);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDefaultLanguageCode() {
    return this.defaultLanguageCode;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITranslation getTranslation(final String languageCode) {
    if (this.translations == null || !this.translations.containsKey(languageCode)) {
      throw new IllegalStateException(
          "No translation with the language code '" + languageCode + "' is registered !");
    }

    return this.translations.get(languageCode);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTranslationLanguageCode() {
    return this.translationLanguageCode;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<String, ITranslation> getTranslations() {
    return this.translations;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Boolean isTranslated() {
    return this.translationLanguageCode != null
        || (this.translations != null && !this.translations.isEmpty());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setDefaultLanguageCode(final String defaultLanguageCode) {
    this.defaultLanguageCode = defaultLanguageCode;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setTranslationLanguageCode(final String translationLanguageCode) {
    this.translationLanguageCode = translationLanguageCode;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setTranslation(final ITranslation translation) {
    // You cannot add translation for a translatable entity which is translated using a "one
    // language mode"
    if (this.translationLanguageCode != null) {
      throw new IllegalStateException(
          "Cannot add a translation for an entity which is already using the 'one language "
          + "mode' !");
    }

    if (this.translations == null) {
      this.translations = new HashMap<String, ITranslation>();
    }

    this.translations.put(translation.getLanguageCode(), translation);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setTranslations(final Map<String, ITranslation> translations) {
    this.translations = translations;
  }
}
