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

/**
 * Interface used to manage getter and setter of a `defaultLanguageCode` attribute on an entity.
 *
 * @author Simon BAUDRY (simon.baudry@gomoob.com)
 */
public interface IDefaultLanguageCode {
    /**
     * Gets the language code which was used when the entity was created. In most cases this is the language which was
     * used by the user who created the entity.
     *
     * WARNING: The language code returned by this function is always expressed in uppercase even if you provided a
     *          language code to the entity using a lower case syntax. Internally translatable entities always store
     *          language codes in upper case.
     *
     * @return The default language code, the returned string is compliant with the ISO639-1 standard.
     */
    public String getDefaultLanguageCode();

    /**
     * Sets the language code which was used when the entity was created. In most cases this is the language which was
     * used by the user who created the entity.
     *
     * WARNING: The provided language code is not case sensitive, but, internally the language code which is used and
     *          stored is ALWAYS converted in uppercase.
     *
     * @param defaultLanguageCode The default language code to set, this string must be compliant with the
     *        ISO639-1 standard.
     */
    public void setDefaultLanguageCode(final String defaultLanguageCode);
}
