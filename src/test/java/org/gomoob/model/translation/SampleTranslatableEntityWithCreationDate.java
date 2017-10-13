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

import org.gomoob.model.ITranslatableEntityWithCreationDate;

/**
 * Sample entity used to test the `AbstractEntity` class.
 *
 * @author Simon BAUDRY (simon.baudry@gomoob.com)
 */
public class SampleTranslatableEntityWithCreationDate
    extends AbstractTranslatableEntityWithCreationDate<Integer>
    implements ITranslatableEntityWithCreationDate<Integer> {
  /**
   * A sample attribute to get / set.
   */
  private Integer notTranslatableAttribute;

  /**
   * A sample attribute to get / set.
   */
  private String translatableAttribute;

  /**
   * Gets the value of the notTranslatableAttribute attribute.
   *
   * @return The value of the notTranslatableAttribute attribute.
   */
  public Integer getNotTranslatableAttribute() {
    return this.notTranslatableAttribute;
  }

  /**
   * Gets the value of the translatableAttribute attribute.
   *
   * @return The value of the translatableAttribute attribute.
   */
  public String getTranslatableAttribute() {
    return this.translatableAttribute;
  }

  /**
   * Sets the value of the notTranslatableAttribute attribute.
   *
   * @param notTranslatableAttribute The value of the notTranslatableAttribute attribute to set.
   */
  public void setNotTranslatableAttribute(final Integer notTranslatableAttribute) {
    this.notTranslatableAttribute = notTranslatableAttribute;
  }

  /**
   * Sets the value of the translatableAttribute attribute.
   *
   * @param translatableAttribute The value of the translatableAttribute attribute to set.
   */
  public void setTranslatableAttribute(final String translatableAttribute) {
    this.translatableAttribute = translatableAttribute;
  }
}
