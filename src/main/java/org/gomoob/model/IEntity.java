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

import java.io.Serializable;

/**
 * Interface which represents a Business Entity.
 *
 * @author Baptiste GAILLARD (baptiste.gaillard@gomoob.com)
 * 
 * @param <IDT> the type of the technical identifier associated to this entity.
 */
public interface IEntity<IDT extends Serializable> {

    /**
     * Gets the value of an attribute of this entity by reflection.
     *
     * @param attributeName the name of the attribute for which one to get a value.
     *
     * @return the value of the attribute having a name equals to <code>$attributeName</code>.
     */
    public Object get(final String attributeName);

    /**
     * Gets the technical identifier of the entity. This is is most cases mapped to a primary key in database.
     *
     * @return the technical identifier of the entity.
     */
    public IDT getId();

    /**
     * Sets the value of an attribute of this entity by reflection.
     *
     * @param attributeName the name of the attribute for which one to set a value.
     * @param attributeValue the value of the attribute to set.
     */
    public void set(final String attributeName, final Object attributeValue);

    /**
     * Sets the technical identifier of the entity. This is is most cases mapped to a primary key in database.
     *
     * @param id the technical identifier of the entity.
     */
    public void setId(final IDT id);
}
