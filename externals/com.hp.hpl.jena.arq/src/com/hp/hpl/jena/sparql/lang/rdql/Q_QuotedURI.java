/*
 * (c) Copyright 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009 Hewlett-Packard Development Company, LP
 * [See end of file]
 */

/* Generated By:JJTree: Do not edit this line. Q_URI.java */

package com.hp.hpl.jena.sparql.lang.rdql;

import com.hp.hpl.jena.graph.Node ;
import com.hp.hpl.jena.query.Query ;



public class Q_QuotedURI extends Q_URI {
    // Also supports old-style "quoted qnames" (i.e. qnames inside <>)
    // Old RDQL used to always use quoted items for qnames and full URIs.  

    // The form actually coming from the parser.
    String seen = "" ;

    // This is set false until the Q_URI is transformed into absolute form
    // or it is known to be.

    boolean isAbsolute = false ;

    Q_QuotedURI(int id)
    {
        super(id);
    }

    Q_QuotedURI(RDQLParser p, int id)
    {
        super(p, id);
    }

    void set(String s)
    {
        seen = s ;
    }

    @Override
    public void jjtClose()
    {
        super._setURI(seen);
        super.jjtClose() ;
    }

    @Override
    public void postParse2(Query query)
    {
        if ( ! isAbsolute )
            absolute(query) ;
        super.postParse2(query) ;
    }

    static final String prefixOperator = ":" ;

    private void absolute(Query query)
    {
        if ( query == null )
        {
            // Only occurs during testing when we jump straight into the parser.
            isAbsolute = true ;
            return ;
        }
            
        int i = seen.indexOf(prefixOperator) ;
        if ( i < 0 )
        {
            isAbsolute = true ;
            return ;
        }

        String prefix = seen.substring(0,i) ;
        
        String full = query.getPrefix(prefix) ;

        if ( full == null )
        {
            isAbsolute = true ;
            super._setNode(Node.createURI(seen)) ;
            return ;
        }

        String remainder = seen.substring(i+prefixOperator.length()) ;
        super._setURI(full+remainder) ;
        super._setNode(Node.createURI(super.getURI()) ) ;    
        isAbsolute = true ;
    }
    
    public static Q_URI makeURI(String s)
    {
        Q_URI uri = new Q_URI(0) ;
        uri._setURI(s) ;
        return uri ;
    }


    // Override these to retain prefix (old style qnames in <> quotes)
    
    // But be aware of effects on URIs in expressions
    @Override
    public String asQuotedString()    { return "<"+seen+">" ; }
    @Override
    public String asUnquotedString()  { return seen ; }
    // Must return the expanded form
    @Override
    public String valueString()       { return super.getURI() ; }

    // Displyable form
    @Override
    public String toString() { return seen ; }
}

/*
 *  (c) Copyright 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009 Hewlett-Packard Development Company, LP
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
