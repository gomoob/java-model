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

package org.gomoob.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Interface which represents a Translatable Business Entity.
 *
 * @author Simon BAUDRY (simon.baudry@gomoob.com)
 *
 * @param <IDT> the type of the technical identifier associated to this entity.
 */
public interface ITranslatableEntity<IDT extends Serializable> extends IEntity<IDT> {
  /**
   * Apply an existing translation on this translatable entity.
   *
   * @param languageCode The code of the targeted language translation.
   *
   * @throws IllegalStateException If no translation with the language code `languageCode` has been
   *           found.
   */
  public void applyTranslation(final String languageCode);

  /**
   * Deletes an existing translation from this translatable entity.
   *
   * <p>
   * WARNING: The provided language code is not case sensitive, but, internally the language code
   * which is used and stored is ALWAYS converted in uppercase.
   * </p>
   *
   * @param languageCode The code of the language for which one to delete a translation.
   *
   * @throws IllegalStateException If no translation with the language code `languageCode` has not
   *           been registered.
   */
  public void deleteTranslation(final String languageCode);

  /**
   * Function used to delete one or multiple existing translations from the entity.
   *
   * <p>
   * WARNING: The provided language codes are not case sensitive, but, internally the language codes
   * which are used and stored are ALWAYS converted in uppercase.
   * </p>
   *
   * @param languageCodes An array with language codes associated to translations to be deleted, if
   *          this parameter is `null` then all the translations are deleted.
   *
   * @throws IllegalStateException if one (or more) translation with one of the provided language
   *           has not been registered. If this exception is thrown then the translatable entity is
   *           not modified.
   */
  public void deleteTranslations(final List<String> languageCodes);

  /**
   * Gets the language code which was used when the entity was created. In most cases this is the
   * language which was used by the user who created the entity.
   *
   * <p>
   * WARNING: The language code returned by this function is always expressed in uppercase even if
   * you provided a language code to the entity using a lower case syntax. Internally translatable
   * entities always store language codes in upper case.
   * </p>
   *
   * @return The default language code, the returned string is compliant with the ISO639-1 standard.
   */
  public String getDefaultLanguageCode();

  /**
   * Gets a translation (corresponding to a specified language code) for the entity. This function
   * picks on of the translation returned by the `getTranslations()` function.
   *
   * <p>
   * WARNING: The provided language code is not case sensitive, but, internally the language code
   * which is used and stored is ALWAYS converted in uppercase.
   * </p>
   *
   * @param languageCode The language code for which one to get a translation.
   *
   * @return The found translation.
   *
   * @throws IllegalStateException if no translation have been found for the specified language
   *           code.
   */
  public ITranslation getTranslation(final String languageCode);

  /**
   * Gets the language code associated to translations which have been applied to the entity. The
   * translation language code is only set if the entity is translated (that's to say its fields are
   * translated in a different language than the default language code).
   *
   * <p>
   * The translation language code should only be used when you have to translate an entity in one
   * language to display informations on a GUI (also in one language). If you have to create a GUI
   * for persons responsible of the translations (i.e the translators) then you'll need to attach
   * all the available translations in all languages to you entities. In this cas you should used
   * the `getTranslations()` and `setTranslations()` functions to manage a list of all the
   * translations in all languages attached to the entity.
   *
   * WARNING: The provided language code is not case sensitive, but, internally the language code
   * which is used and stored is ALWAYS converted in uppercase.
   * </p>
   *
   * @return The translation language code, the returned string is compliant with ISO639-1 standard.
   */
  public String getTranslationLanguageCode();

  /**
   * Gets multiple translations in multiple languages for this translatable entity. This function is
   * used when you have to display multiple translations in multiple languages on a GUI, in most
   * cases thoses GUIs are dedicated to persons responsible of the translations (i.e the
   * translators). When you use multiple translations in multiple languages attached to an entity
   * the metadata attached to the entity stays in the default language specified by the default
   * language code. The returned multiple translations should not contain translations for the
   * default language.
   *
   * <p>
   * If you have to display an entity in only one language then do not use the `getTranslations()`
   * and `setTranslations()` function but prefer the `getTranslationLanguageCode()` and
   * `setTranslationLanguageCode()` functions which are used to manage only one translation (i.e in
   * only one language), that's to say to translate the entity in one target language only.
   * </p>
   *
   * @return The translations in multiple languages attached to this translatable entity.
   */
  public Map<String, ITranslation> getTranslations();

  /**
   * Function used to indicate if the entity has been translated of if its fields are in its default
   * language. An entity is translated when its `translationLanguageCode` is not `null` or if its
   * associated to at least one translation (i.e the `getTranslations()` function returns a not
   * empty array. This function is convenient when, after having trying to translate an entity you
   * want to know if translations for this entity have been found. If no translations have been
   * found the entity should stay intact and should not be modified.
   *
   * @return `true` if the entity is translated, `false` otherwise.
   */
  public Boolean isTranslated();

  /**
   * Sets the language code which was used when the entity was created. In most cases this is the
   * language which was used by the user who created the entity.
   *
   * <p>
   * WARNING: The provided language code is not case sensitive, but, internally the language code
   * which is used and stored is ALWAYS converted in uppercase.
   * </p>
   *
   * @param defaultLanguageCode The default language code to set, this string must be compliant with
   *          the ISO639-1 standard.
   */
  public void setDefaultLanguageCode(final String defaultLanguageCode);

  /**
   * Sets the language code associated to the translations which have been applied to the entity.
   * The translation language code must only be set if the entity is translated (that's to say its
   * fields have been translated in a different language than the default language code).
   *
   * <p>
   * The translation language code should only be used when you have to translate an entity in one
   * language to display informations on a GUI (also in one language). If you have to create a GUI
   * for persons responsible of the translations (i.e the translators) then you'll need to attach
   * all the available translations in all languages to you entities. In this cas you should used
   * the `getTranslations()` and `setTranslations()` functions to manage a list of all the
   * translations in all languages attached to the entity.
   *
   * WARNING: The provided language code is not case sensitive, but, internally the language code
   * which is used and stored is ALWAYS converted in uppercase.
   * </p>
   *
   * @param translationLanguageCode The translation language code to set, this string must be
   *          compliant with the ISO639-1 standard.
   */
  public void setTranslationLanguageCode(final String translationLanguageCode);

  /**
   * Adds a new translation or update an existing one for the entity.
   *
   * @param translation The new translation to add or an existing translation to update.
   *
   * @throws IllegalStateException if the entity is already translated using a the "one language
   *           mode".
   */
  public void setTranslation(final ITranslation translation);

  /**
   * Sets multiple translations in multiple languages for this translatable entity. This function is
   * used when you have to display multiple translations in multiple languages on a GUI, in most
   * cases thoses GUIs are dedicated to persons responsible of the translations (i.e the
   * translators). When you use multiple translations in multiple languages attached to an entity
   * the metadata attached to the entity stays in the default language specified by the default
   * language code. The returned multiple translations should not contain translations for the
   * default language.
   *
   * <p>
   * If you have to display an entity in only one language then do not use the `getTranslations()`
   * and `setTranslations()` function but prefer the `getTranslationLanguageCode()` and
   * `setTranslationLanguageCode()` functions which are used to manage only one translation (i.e in
   * only one language), that's to say to translate the entity in one target language only.
   * </p>
   *
   * @param translations The translations to set.
   */
  public void setTranslations(final Map<String, ITranslation> translations);
}
