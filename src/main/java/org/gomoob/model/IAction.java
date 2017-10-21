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

import org.json.JSONObject;

/**
 * Interface which represents an action.
 *
 * @author GOMOOB SARL (contact@gomoob.com)
 */
public interface IAction extends IEntityWithCreationDate<String> {

    /**
     * Gets the generic metadata attached to this action, this is an array which can contain any keys or values. This is
     * useful to make the action usable in every application.
     *
     * @return a JSON object with arbitrary data.
     */
    public JSONObject getMetadata();

    /**
     * Gets the name of the action.
     *
     * @return The name of the action.
     */
    public String getName();

    /**
     * Sets the generic metadata attached to this action, this is an array which can contain any keys or values. This is
     * useful to make the action usable in every application.
     *
     * @param metadata An array with arbitrary metadata.
     */
    public void setMetadata(final JSONObject metadata);

    /**
     * Sets the name of the action.
     *
     * @param name The name of the action.
     */
    public void setName(final String name);
}
