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
import java.lang.reflect.Field;

/**
 * Abstract class to be extended by Java entities mapped to a database.
 *
 * @author Baptiste GAILLARD (baptiste.gaillard@gomoob.com)
 *
 * @param <IDT> the type of the technical identifier associated to this entity.
 */
public abstract class AbstractEntity<IDT extends Serializable> implements IEntity<IDT> {

    /**
     * Technical identifier of the entity. This is is most cases mapped to a primary key in database.
     */
    protected IDT id = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get(final String attributeName) {
        String errorMessage = "Fail to get value of property '" + attributeName + "' !";
        Field field = null;

        try {
            field = this.getClass().getDeclaredField(attributeName);
        } catch (NoSuchFieldException nsfex) {
            throw new RuntimeException(errorMessage, nsfex);
        }

        // Backup the property accessibility
        boolean accessible = field.isAccessible();

        field.setAccessible(true);
        Object attributeValue = null;
        try {
            attributeValue = field.get(this);
        } catch (IllegalArgumentException iaex) {
            throw new RuntimeException(errorMessage, iaex);
        } catch (IllegalAccessException iaccex) {
            throw new RuntimeException(errorMessage, iaccex);
        }

        // Restore the property accessibility
        field.setAccessible(accessible);

        return attributeValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDT getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(final String attributeName, Object attributeValue) {
        String errorMessage = "Fail to set value of property '" + attributeName + "' !";
        Field field = null;

        try {
            field = this.getClass().getDeclaredField(attributeName);
        } catch (NoSuchFieldException nsfex) {
            throw new RuntimeException(errorMessage, nsfex);
        }

        // Backup the property accessibility
        boolean accessible = field.isAccessible();

        field.setAccessible(true);
        try {
            field.set(this, attributeValue);
        } catch (IllegalArgumentException iaex) {
            throw new RuntimeException(errorMessage, iaex);
        } catch (IllegalAccessException iaccex) {
            throw new RuntimeException(errorMessage, iaccex);
        }

        // Restore the property accessibility
        field.setAccessible(accessible);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(IDT id) {
        this.id = id;
    }
}
