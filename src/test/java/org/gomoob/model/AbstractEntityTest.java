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

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test case for the {@link AbstractEntity} class.
 *
 * @author Simon BAUDRY (simon.baudry@gomoob.com)
 */
public class AbstractEntityTest {

    /**
     * Test method for {@link AbstractEntity} creation.
     */
    @Test
    public void testAbstractEntity() {
        SampleEntity sampleEntity = new SampleEntity();

        Assert.assertNull(sampleEntity.getId());
        Assert.assertNull(sampleEntity.getA());

        sampleEntity.setId(1);
        sampleEntity.setA("A_VALUE");

        Assert.assertSame(1, sampleEntity.getId());
        Assert.assertSame("A_VALUE", sampleEntity.getA());

        sampleEntity.set("a", "NEW_A_VALUE");
        Assert.assertSame("NEW_A_VALUE", sampleEntity.get("a"));
    }

    /**
     * Test method for {@link AbstractEntityWithCreationDate} creation.
     */
    @Test
    public void testAbstractEntityWithCreationDate() {
        SampleEntityWithCreationDate sampleEntity = new SampleEntityWithCreationDate();

        Assert.assertNull(sampleEntity.getId());
        Assert.assertNull(sampleEntity.getCreationDate());
        Assert.assertNull(sampleEntity.getA());

        Date currentDate = new Date();
        sampleEntity.setId(1);
        sampleEntity.setCreationDate(currentDate);
        sampleEntity.setA("A_VALUE");

        Assert.assertSame(1, sampleEntity.getId());
        Assert.assertEquals(currentDate, sampleEntity.getCreationDate());
        Assert.assertSame("A_VALUE", sampleEntity.getA());
    }

    /**
     * Test method for {@link AbstractEntityWithCreationDateAndUpdateDate} creation.
     */
    @Test
    public void testAbstractEntityWithCreationDateAndUpdateDate() {
        SampleEntityWithCreationDateAndUpdateDate sampleEntity = new SampleEntityWithCreationDateAndUpdateDate();

        Assert.assertNull(sampleEntity.getId());
        Assert.assertNull(sampleEntity.getCreationDate());
        Assert.assertNull(sampleEntity.getUpdateDate());
        Assert.assertNull(sampleEntity.getA());

        Date creationDate = new Date();
        Date updateDate = new Date();
        sampleEntity.setId(1);
        sampleEntity.setCreationDate(creationDate);
        sampleEntity.setUpdateDate(updateDate);
        sampleEntity.setA("A_VALUE");

        Assert.assertSame(1, sampleEntity.getId());
        Assert.assertEquals(creationDate, sampleEntity.getCreationDate());
        Assert.assertEquals(updateDate, sampleEntity.getUpdateDate());
        Assert.assertSame("A_VALUE", sampleEntity.getA());
    }
}
