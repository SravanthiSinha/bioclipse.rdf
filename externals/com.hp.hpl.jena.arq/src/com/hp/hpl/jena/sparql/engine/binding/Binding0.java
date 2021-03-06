/*
 * (c) Copyright 2004, 2005, 2006, 2007, 2008, 2009 Hewlett-Packard Development Company, LP
 * [See end of file]
 */

package com.hp.hpl.jena.sparql.engine.binding;

import java.util.Iterator ;

import org.openjena.atlas.iterator.Iter ;

import com.hp.hpl.jena.graph.Node ;
import com.hp.hpl.jena.sparql.core.Var ;

/** Special purpose binding for nothing. Surprisingly useful.
 */

public class Binding0 extends BindingBase
{
    /* package */ Binding0() { super(null) ; }
    /* package */ Binding0(Binding parent) { super(parent) ; }

    @Override
    protected void add1(Var var, Node node)
    {
        throw new UnsupportedOperationException("Binding0.add1") ;
    }
    
    /** Iterate over all the names of variables.
     */
    @Override
    public Iterator<Var> vars1() { return Iter.nullIterator() ; }

    @Override
    protected int size1() { return 0 ; }
    
    @Override
    protected boolean isEmpty1() { return true ; }
    
    @Override
    public boolean contains1(Var var) { return false ; }
    
    @Override
    public Node get1(Var var) { return null ; }

    @Override
    protected void checkAdd1(Var var, Node node) { }
}

/*
 *  (c) Copyright 2004, 2005, 2006, 2007, 2008, 2009 Hewlett-Packard Development Company, LP
 *  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
