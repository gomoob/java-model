/**
 * Copyright 2017 SARL GOMOOB. All rights reserved.
 */

package org.gomoob.model;

import org.json.JSONObject;

/**
 * Interface which represents a state.
 *
 * @author GOMOOB SARL (contact@gomoob.com)
 */
public interface IState extends IEntityWithCreationDate<String> {

    /**
     * Gets the message, in most cases this message is empty. The message is used to describe the state or to describe
     * the reason why this state has been created.
     *
     * @return the message.
     */
    public String getMessage();

    /**
     * Gets the generic metadata attached to this state, this is an array which can contain any keys or values. This is
     * useful to make the state usable in every application.
     *
     * @return the JSON Object of metadata.
     */
    public JSONObject getMetadata();

    /**
     * Gets the name of the state.
     *
     * @return the name of the state.
     */
    public String getName();

    /**
     * Sets the message, in most cases this message is empty. The message is used to describe the state or to describe
     * the reason why this state has been created.
     *
     * @param message the message.
     */
    public void setMessage(final String message);

    /**
     * Sets the generic metadata attached to this state, this is an array which can contain any keys or values. This is
     * useful to make the state usable in every application.
     *
     * @param metadata the JSON Object of metadata.
     */
    public void setMetadata(final JSONObject metadata);

    /**
     * Sets the name of the state.
     *
     * @param name the name of the state.
     */
    public void setName(final String name);
}
