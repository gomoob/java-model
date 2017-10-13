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

import java.util.HashMap;
import java.util.Map;

import org.gomoob.model.ITranslation;

/**
 * Class which define translations for multiple attributes.
 *
 * @author Simon BAUDRY (simon.baudry@gomoob.com)
 */
public class Translation implements ITranslation {

    /**
     * All the attribute translations which have been registered.
     */
    private Map<String, String> attributeTranslations = new HashMap<String, String>();

    /**
     * The language code which define the language associated to the translations.
     *
     * <p>NOTE: The language code is always stored using an uppercase syntax.</p>
     */
    private String languageCode;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAttributeTranslation(final String attributeName) {
        // The attribute translations must contains the attribute name
        if (!this.attributeTranslations.containsKey(attributeName)) {
            throw new IllegalStateException(
                "No attribute named '" + attributeName + "' has been found in the attribute translations !"
            );
        }

        return this.attributeTranslations.get(attributeName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getAttributeTranslations() {
        return this.attributeTranslations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLanguageCode() {
        return this.languageCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAttributeTranslation(final String attributeName, final String attributeValue) {
        this.attributeTranslations.put(attributeName, attributeValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLanguageCode(final String languageCode) {
        this.languageCode = languageCode;
    }
}
