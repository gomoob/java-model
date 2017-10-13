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
package org.gomoob.model;

import java.util.Map;

/**
 * Interface which represents an Translation Entity.
 *
 * @author Simon BAUDRY (simon.baudry@gomoob.com)
 */
public interface ITranslation {
    /**
     * Gets the translation associated to an attribute.
     *
     * @param attributeName The name of the attribute for which one to get translations.
     *
     * @return The found translation.
     *
     * @throws IllegalStateException If no attribute translation corresponds to the specified name.
     */
    public String getAttributeTranslation(final String attributeName);

    /**
     * Gets all the attribute translations which have been registered.
     *
     * @return All the attribute translations which have been registered.
     */
    public Map<String, String> getAttributeTranslations();

    /**
     * Gets the language code which define the language associated to the translations. This language code defines in
     * which language the attributes are set in this  object.
     *
     * WARNING: The language code returned by this function is always expressed in uppercase even if you set it using
     *          a lower case syntax. The translation class always stores language codes using an upper case syntax.
     *
     * @return The language code which define the language associated to the translations. This string is
     *                compliant with the ISO639-1 language.
     */
    public String getLanguageCode();

    /**
     * Add a translated attribute or update an existing one.
     *
     * @param attributeName  The name of the translated attribute.
     * @param attributeValue The value of the translated attribute, the language associated to this value is
     *                               defined by the `getLanguageCode()` and `setLanguageCode()` functions.
     */
    public void setAttributeTranslation(final String attributeName, final String attributeValue);

    /**
     * Sets the language code which define the language associated to the translations. This language code defines in
     * which language the attributes are set in this  object.
     *
     * WARNING: The provided language code is not case sensitive, but, internally the language code which is used and
     *          stored is ALWAYS converted in uppercase.
     *
     * @param languageCode The language code to set, this string must be compliant with the ISO639-1 standard.
     */
    public void setLanguageCode(final String languageCode);
}
