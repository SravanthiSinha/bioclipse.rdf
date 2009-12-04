/*******************************************************************************
 * Copyright (c) 2009  Egon Willighagen <egonw@users.sf.net>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contact: http://www.bioclipse.net/
 ******************************************************************************/
package net.bioclipse.pellet.tests;

import net.bioclipse.pellet.business.IPelletManager;
import net.bioclipse.rdf.business.IRDFStore;

import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractPelletManagerPluginTest {

    protected static IPelletManager pellet;

    @Test public void testCreateInMemoryStore() {
        IRDFStore store = pellet.createInMemoryStore();
        Assert.assertNotNull(store);
    }

    @Test public void testCreateStore() {
        IRDFStore store = pellet.createStore("/Virtual/test.store");
        Assert.assertNotNull(store);
    }

    @Test public void testIsRDFType() {
        Assert.fail("Test not implemented");
    }

    @Test public void testAllAbout() {
        Assert.fail("Test not implemented");
    }

    @Test public void testReason() {
        Assert.fail("Test not implemented");
    }

    @Test public void testValidate() throws Exception {
        IRDFStore store = pellet.createInMemoryStore();
        pellet.validate(store);
        Assert.assertNotNull(store);
    }

}
