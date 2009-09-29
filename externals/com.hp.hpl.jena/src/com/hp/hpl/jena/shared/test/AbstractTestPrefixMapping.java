/*
  (c) Copyright 2003, 2004, 2005, 2006, 2007, 2008, 2009 Hewlett-Packard Development Company, LP
  [See end of file]
  $Id: AbstractTestPrefixMapping.java,v 1.31 2009/01/16 18:24:40 andy_seaborne Exp $
*/

package com.hp.hpl.jena.shared.test;

import com.hp.hpl.jena.shared.*;
import com.hp.hpl.jena.graph.test.*;

import java.util.*;

/**
    Test prefix mappings - subclass this test and override getMapping() to
    deliver the prefixMapping to be tested.
    
    @author kers
*/

public abstract class AbstractTestPrefixMapping extends GraphTestBase
    {
    public AbstractTestPrefixMapping( String name )
         { super( name ); }

    /**
        Subclasses implement to return a new, empty prefixMapping of their
        preferred kind.
    */
    abstract protected PrefixMapping getMapping();
        
    static final String crispURI = "http://crisp.nosuch.net/";
    static final String ropeURI = "scheme:rope/string#";
    static final String butterURI = "ftp://ftp.nowhere.at.all/cream#";
        
    /**
        The empty prefix is specifically allowed [for the default namespace].
    */
    public void testEmptyPrefix()
        {
        PrefixMapping pm = getMapping();
        pm.setNsPrefix( "", crispURI );    
        assertEquals( crispURI, pm.getNsPrefixURI( "" ) );
        }

    static final String [] badNames =
        {
        "<hello>",
        "foo:bar",
        "with a space",
        "-argument"
        };
    
    /**
        Test that various illegal names are trapped.
    */    
    public void testCheckNames()
        {
        PrefixMapping ns = getMapping();
        for (int i = 0; i < badNames.length; i += 1)
            {
            String bad = badNames[i];
            try 
                { 
                ns.setNsPrefix( bad, crispURI ); 
                fail( "'" + bad + "' is an illegal prefix and should be trapped" ); 
                }
            catch (PrefixMapping.IllegalPrefixException e) { pass(); }
            }
        }
    
    public void testNullURITrapped()
        {
        try
            {
            getMapping().setNsPrefix( "xy", null );
            fail( "shouild trap null URI in setNsPrefix" );
            }
        catch (NullPointerException e)
            { pass(); }
        }
                 
    /**
        test that a PrefixMapping maps names to URIs. The names and URIs are
        all fully distinct - overlapping names/uris are dealt with in other tests.
    */
    public void testPrefixMappingMapping()
        {
        String toast = "ftp://ftp.nowhere.not/";
        assertDiffer( "crisp and toast must differ", crispURI, toast );
    /* */
        PrefixMapping ns = getMapping();
        assertEquals( "crisp should be unset", null, ns.getNsPrefixURI( "crisp" ) );
        assertEquals( "toast should be unset", null, ns.getNsPrefixURI( "toast" ) );
        assertEquals( "butter should be unset", null, ns.getNsPrefixURI( "butter" ) );
    /* */
        ns.setNsPrefix( "crisp", crispURI );
        assertEquals( "crisp should be set", crispURI, ns.getNsPrefixURI( "crisp" ) );
        assertEquals( "toast should still be unset", null, ns.getNsPrefixURI( "toast" ) );
        assertEquals( "butter should still be unset", null, ns.getNsPrefixURI( "butter" ) );
    /* */
        ns.setNsPrefix( "toast", toast );
        assertEquals( "crisp should be set", crispURI, ns.getNsPrefixURI( "crisp" ) );
        assertEquals( "toast should be set", toast, ns.getNsPrefixURI( "toast" ) );
        assertEquals( "butter should still be unset", null, ns.getNsPrefixURI( "butter" ) );
        } 
        
    /**
        Test that we can run the prefix mapping in reverse - from URIs to prefixes.
        uriB is a prefix of uriA to try and ensure that the ordering of the map doesn't matter.
    */
    public void testReversePrefixMapping()
        {
        PrefixMapping ns = getMapping();
        String uriA = "http://jena.hpl.hp.com/A#", uriB = "http://jena.hpl.hp.com/";
        String uriC = "http://jena.hpl.hp.com/Csharp/";
        String prefixA = "aa", prefixB = "bb";
        ns.setNsPrefix( prefixA, uriA ).setNsPrefix( prefixB, uriB );
        assertEquals( null, ns.getNsURIPrefix( uriC) );
        assertEquals( prefixA, ns.getNsURIPrefix( uriA ) );
        assertEquals( prefixB, ns.getNsURIPrefix( uriB ) );
        }
    
    /**
       test that we can extract a proper Map from a PrefixMapping
    */
    public void testPrefixMappingMap()
        {
        PrefixMapping ns = getCrispyRope();
        Map<String, String> map = ns.getNsPrefixMap();
        assertEquals( "map should have two elements", 2, map.size() );
        assertEquals( crispURI, map.get( "crisp" ) );
        assertEquals( "scheme:rope/string#", map.get( "rope" ) );
        }
    
    /**
       test that the Map returned by getNsPrefixMap does not alias (parts of)
       the secret internal map of the PrefixMapping
    */
    public void testPrefixMappingSecret()
        {
        PrefixMapping ns = getCrispyRope();
        Map<String, String> map = ns.getNsPrefixMap();
    /* */
        map.put( "crisp", "with/onions" );
        map.put( "sandwich", "with/cheese" );
        assertEquals( crispURI, ns.getNsPrefixURI( "crisp" ) );
        assertEquals( ropeURI, ns.getNsPrefixURI( "rope" ) );
        assertEquals( null, ns.getNsPrefixURI( "sandwich" ) );
        }
        
    private PrefixMapping getCrispyRope()
        {
        PrefixMapping ns = getMapping();
        ns.setNsPrefix( "crisp", crispURI);
        ns.setNsPrefix( "rope", ropeURI );        
        return ns;
        }
    
    /**
       these are strings that should not change when they are prefix-expanded
       with crisp and rope as legal prefixes.
   */
   static final String [] dontChange = 
       { 
       "",
       "http://www.somedomain.something/whatever#",
       "crispy:cabbage",
       "cris:isOnInfiniteEarths",
       "rop:tangled/web",
       "roped:abseiling"
       };
    
    /**
       these are the required mappings which the test cases below should
       satisfy: an array of 2-arrays, where element 0 is the string to expand
       and element 1 is the string it should expand to. 
   */
   static final String [][] expansions =
       {
           { "crisp:pathPart", crispURI + "pathPart" },
           { "rope:partPath", ropeURI + "partPath" },
           { "crisp:path:part", crispURI + "path:part" },
       };
       
   public void testExpandPrefix()
       {
       PrefixMapping ns = getMapping();
       ns.setNsPrefix( "crisp", crispURI );
       ns.setNsPrefix( "rope", ropeURI );
   /* */
       for (int i = 0; i < dontChange.length; i += 1)
           assertEquals
               ( 
               "should be unchanged", 
               dontChange[i], 
               ns.expandPrefix( dontChange[i] ) 
               );    
   /* */
       for (int i = 0; i < expansions.length; i += 1)
           assertEquals
               ( 
               "should expand correctly", 
               expansions[i][1], 
               ns.expandPrefix( expansions[i][0] ) 
               );
       }
    
    public void testUseEasyPrefix()
       {
       testUseEasyPrefix( "prefix mapping impl", getMapping() );
       testShortForm( "prefix mapping impl", getMapping() );
       }
    
    public static void testUseEasyPrefix( String title, PrefixMapping ns )
        {
        testShortForm( title, ns );
        }
            
    public static void testShortForm( String title, PrefixMapping ns )
        {
        ns.setNsPrefix( "crisp", crispURI );
        ns.setNsPrefix( "butter", butterURI );
        assertEquals( title, "", ns.shortForm( "" ) );
        assertEquals( title, ropeURI, ns.shortForm( ropeURI ) );
        assertEquals( title, "crisp:tail", ns.shortForm( crispURI + "tail" ) );
        assertEquals( title, "butter:here:we:are", ns.shortForm( butterURI + "here:we:are" ) );
        }
    
    public void testEasyQName()
        {
        PrefixMapping ns = getMapping();
        String alphaURI = "http://seasonal.song/preamble/";
        ns.setNsPrefix( "alpha", alphaURI );
        assertEquals( "alpha:rowboat", ns.qnameFor( alphaURI + "rowboat" ) );
        }
    
    public void testNoQNameNoPrefix()
        {
        PrefixMapping ns = getMapping();
        String alphaURI = "http://seasonal.song/preamble/";
        ns.setNsPrefix( "alpha", alphaURI );
        assertEquals( null, ns.qnameFor( "eg:rowboat" ) );
        }
    
    public void testNoQNameBadLocal()
        {
        PrefixMapping ns = getMapping();
        String alphaURI = "http://seasonal.song/preamble/";
        ns.setNsPrefix( "alpha", alphaURI );
        assertEquals( null, ns.qnameFor( alphaURI + "12345" ) );
        }
    
    /**
        The tests implied by the email where Chris suggested adding qnameFor;
        shortForm generates illegal qnames but qnameFor does not.
    */
    public void testQnameFromEmail()
        {
    	String uri = "http://some.long.uri/for/a/namespace#";
        PrefixMapping ns = getMapping();
    	ns.setNsPrefix( "x", uri );
    	assertEquals( null, ns.qnameFor( uri ) );
        assertEquals( null, ns.qnameFor( uri + "non/fiction" ) );
        }

        
    /**
        test that we can add the maplets from another PrefixMapping without
        losing our own.
    */
    public void testAddOtherPrefixMapping()
        {
        PrefixMapping a = getMapping();
        PrefixMapping b = getMapping();
        assertFalse( "must have two diffferent maps", a == b );
        a.setNsPrefix( "crisp", crispURI );
        a.setNsPrefix( "rope", ropeURI );
        b.setNsPrefix( "butter", butterURI );
        assertEquals( null, b.getNsPrefixURI( "crisp") );
        assertEquals( null, b.getNsPrefixURI( "rope") );
        b.setNsPrefixes( a );
        checkContainsMapping( b );
        }
        
    private void checkContainsMapping( PrefixMapping b )
        {
        assertEquals( crispURI, b.getNsPrefixURI( "crisp") );
        assertEquals( ropeURI, b.getNsPrefixURI( "rope") );
        assertEquals( butterURI, b.getNsPrefixURI( "butter") );
        }
        
    /**
        as for testAddOtherPrefixMapping, except that it's a plain Map
        we're adding.
    */
    public void testAddMap()
        {
        PrefixMapping b = getMapping();
        Map<String, String> map = new HashMap<String, String>();
        map.put( "crisp", crispURI );
        map.put( "rope", ropeURI );
        b.setNsPrefix( "butter", butterURI );
        b.setNsPrefixes( map );
        checkContainsMapping( b );
        }
    
    public void testAddDefaultMap()
        {
        PrefixMapping pm = getMapping();
        PrefixMapping root = PrefixMapping.Factory.create();
        pm.setNsPrefix( "a", "aPrefix:" );
        pm.setNsPrefix( "b", "bPrefix:" );
        root.setNsPrefix( "a", "pootle:" );
        root.setNsPrefix( "z", "bPrefix:" );
        root.setNsPrefix( "c", "cootle:" );
        assertSame( pm, pm.withDefaultMappings( root ) );
        assertEquals( "aPrefix:", pm.getNsPrefixURI( "a" ) );
        assertEquals( null, pm.getNsPrefixURI( "z" ) );
        assertEquals( "bPrefix:", pm.getNsPrefixURI( "b" ) );
        assertEquals( "cootle:", pm.getNsPrefixURI( "c" ) );
        }
    
    
    public void testSecondPrefixRetainsExistingMap()
        {
        PrefixMapping A = getMapping();
        A.setNsPrefix( "a", crispURI );
        A.setNsPrefix( "b", crispURI );
        assertEquals( crispURI, A.getNsPrefixURI( "a" ) );
        assertEquals( crispURI, A.getNsPrefixURI( "b" ) );
        }
    
    public void testSecondPrefixReplacesReverseMap()
        {
        PrefixMapping A = getMapping();
        A.setNsPrefix( "a", crispURI );
        A.setNsPrefix( "b", crispURI );
        assertEquals( "b", A.getNsURIPrefix( crispURI ) );
        }
    
    public void testSecondPrefixDeletedUncoversPreviousMap()
        {
        PrefixMapping A = getMapping();
        A.setNsPrefix( "x", crispURI );
        A.setNsPrefix( "y", crispURI );
        A.removeNsPrefix( "y" );
        assertEquals( "x", A.getNsURIPrefix( crispURI ) );
        }
        
    /**
        Test that the empty prefix does not wipe an existing prefix for the same URI.
    */    
    public void testEmptyDoesNotWipeURI()
        {
        PrefixMapping pm = getMapping();
        pm.setNsPrefix( "frodo", ropeURI );
        pm.setNsPrefix( "", ropeURI );
        assertEquals( ropeURI, pm.getNsPrefixURI( "frodo" ) );    
        }   
                
    /**
        Test that adding a new prefix mapping for U does not throw away a default 
        mapping for U.
    */
    public void testSameURIKeepsDefault()
        {
        PrefixMapping A = getMapping();
        A.setNsPrefix( "", crispURI );
        A.setNsPrefix( "crisp", crispURI );
        assertEquals( crispURI, A.getNsPrefixURI( "" ) );
        }
        
    public void testReturnsSelf()
        {
        PrefixMapping A = getMapping();
        assertSame( A, A.setNsPrefix( "crisp", crispURI ) );
        assertSame( A, A.setNsPrefixes( A ) );
        assertSame( A, A.setNsPrefixes( new HashMap<String, String>() ) );
        assertSame( A, A.removeNsPrefix( "rhubarb" ) );
        }
    
    public void testRemovePrefix()
        {
        String hURI = "http://test.remove.prefixes/prefix#";
        String bURI = "http://other.test.remove.prefixes/prefix#";
        PrefixMapping A = getMapping();
        A.setNsPrefix( "hr", hURI );
        A.setNsPrefix( "br", bURI );
        A.removeNsPrefix( "hr" );
        assertEquals( null, A.getNsPrefixURI( "hr" ) );
        assertEquals( bURI, A.getNsPrefixURI( "br" ) );
        }
    
    public void testEquality()
        {
        testEquals( "" );
        testEquals( "", "x=a", false );
        testEquals( "x=a", "", false );
        testEquals( "x=a" );
        testEquals( "x=a y=b", "y=b x=a", true );
        testEquals( "x=a x=b", "x=b x=a", false );
        }
    
    protected void testEquals( String S )
        { testEquals( S, S, true ); }
    
    protected void testEquals( String S, String T, boolean expected )
        {
        testEqualsBase( S, T, expected );
        testEqualsBase( T, S, expected );
        }
    
    public void testEqualsBase( String S, String T, boolean expected )
        {
        testEquals( S, T, expected, getMapping(), getMapping() );
        testEquals( S, T, expected, PrefixMapping.Factory.create(), getMapping() );
        }

    protected void testEquals( String S, String T, boolean expected, PrefixMapping A, PrefixMapping B )
        {
        fill( A, S );
        fill( B, T );
        String title = "usual: '" + S + "', testing: '" + T + "', should be " + (expected ? "equal" : "different");
        assertEquals( title, expected, A.samePrefixMappingAs( B ) );
        assertEquals( title, expected, B.samePrefixMappingAs( A ) );
        }
    
    protected void fill( PrefixMapping pm, String settings )
        {
        List<String> L = listOfStrings( settings );
        for (int i = 0; i < L.size(); i += 1)
            {
            String setting = L.get(i);
            int eq = setting.indexOf( '=' );
            pm.setNsPrefix( setting.substring( 0, eq ), setting.substring( eq + 1 ) );
            }
        }
    
    public void testAllowNastyNamespace()
        { // we now allow namespaces to end with non-punctuational characters
        getMapping().setNsPrefix( "abc", "def" ); 
        }
        
    public void testLock()
        {
        PrefixMapping A = getMapping();
        assertSame( A, A.lock() );
    /* */    
        try { A.setNsPrefix( "crisp", crispURI ); fail( "mapping should be frozen" ); }
        catch (PrefixMapping.JenaLockedException e) { pass(); }
    /* */    
        try { A.setNsPrefixes( A ); fail( "mapping should be frozen" ); }
        catch (PrefixMapping.JenaLockedException e) { pass(); }
    /* */
        try { A.setNsPrefixes( new HashMap<String, String>() ); fail( "mapping should be frozen" ); }
        catch (PrefixMapping.JenaLockedException e) { pass(); }
    /* */
        try { A.removeNsPrefix( "toast" ); fail( "mapping should be frozen" ); }
        catch (PrefixMapping.JenaLockedException e) { pass(); }
        }
    }

/*
    (c) Copyright 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009 Hewlett-Packard Development Company, LP
    All rights reserved.

    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions
    are met:

    1. Redistributions of source code must retain the above copyright
       notice, this list of conditions and the following disclaimer.

    2. Redistributions in binary form must reproduce the above copyright
       notice, this list of conditions and the following disclaimer in the
       documentation and/or other materials provided with the distribution.

    3. The name of the author may not be used to endorse or promote products
       derived from this software without specific prior written permission.

    THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
    IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
    OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
    IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
    INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
    NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
    DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
    THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
    (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
    THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/