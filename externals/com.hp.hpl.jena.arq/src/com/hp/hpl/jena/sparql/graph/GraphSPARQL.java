/*
 * (c) Copyright 2005, 2006, 2007, 2008, 2009 Hewlett-Packard Development Company, LP
 * All rights reserved.
 * [See end of file]
 */

package com.hp.hpl.jena.sparql.graph;

import java.util.ArrayList ;
import java.util.List ;

import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

import com.hp.hpl.jena.graph.Capabilities ;
import com.hp.hpl.jena.graph.Graph ;
import com.hp.hpl.jena.graph.Node ;
import com.hp.hpl.jena.graph.Triple ;
import com.hp.hpl.jena.graph.TripleMatch ;
import com.hp.hpl.jena.graph.impl.AllCapabilities ;
import com.hp.hpl.jena.graph.impl.GraphBase ;
import com.hp.hpl.jena.sparql.algebra.Op ;
import com.hp.hpl.jena.sparql.algebra.op.OpBGP ;
import com.hp.hpl.jena.sparql.core.BasicPattern ;
import com.hp.hpl.jena.sparql.core.DatasetGraph ;
import com.hp.hpl.jena.sparql.core.DatasetGraphFactory ;
import com.hp.hpl.jena.sparql.core.Var ;
import com.hp.hpl.jena.sparql.engine.Plan ;
import com.hp.hpl.jena.sparql.engine.QueryEngineFactory ;
import com.hp.hpl.jena.sparql.engine.QueryIterator ;
import com.hp.hpl.jena.sparql.engine.binding.Binding ;
import com.hp.hpl.jena.sparql.engine.binding.BindingRoot ;
import com.hp.hpl.jena.util.iterator.ExtendedIterator ;
import com.hp.hpl.jena.util.iterator.WrappedIterator ;

/** This class provides the Jena Graph interface to a graph in a Dataset.
 *  It enables the full Jena API to be used with data only accessible
 *  via a Dataset. */

public class GraphSPARQL extends GraphBase implements Graph
{
    private static Logger log = LoggerFactory.getLogger(GraphSPARQL.class) ;

    private DatasetGraph dataset ;
    private QueryEngineFactory factory ;
    
    // Use the dataset graph uninterpretted.
    public GraphSPARQL(DatasetGraph dataset, QueryEngineFactory factory)
    {  
        this.dataset = dataset ;
        this.factory = factory ;
    }
    
    public GraphSPARQL(DatasetGraph dataset, String uri)
    {
        this(dataset, Node.createURI(uri)) ;
    }

    public GraphSPARQL(DatasetGraph dataset, Node gn)
    { 
        this.dataset = toDSG(dataset, gn) ;
        factory = null ;
    }

    
    public GraphSPARQL(Graph graph)
    { this(graph, null) ; }
    
    public GraphSPARQL(Graph graph, QueryEngineFactory factory)
    {
        this.dataset = DatasetGraphFactory.create(graph) ;
        this.factory = factory ;
    }
    
    private DatasetGraph toDSG(DatasetGraph dataset, Node graphNode)
    {
        Graph graph = ( graphNode == null ) ? dataset.getDefaultGraph() : dataset.getGraph(graphNode) ;
        DatasetGraph dsGraph = DatasetGraphFactory.create(graph) ;
        return dsGraph ;
    }

    
    /* We don't support value tests, hence handlesLiteralTyping is false */
    //@Override
    @Override
    public Capabilities getCapabilities()
    { 
    	if (capabilities == null)
            capabilities = new AllCapabilities()
        	  { @Override
                public boolean handlesLiteralTyping() { return false; } 
        	  };
        return capabilities;
    }
    
    public DatasetGraph getDataset() { return dataset ; } 

    //@Override
    @Override
    protected ExtendedIterator<Triple> graphBaseFind(TripleMatch m)
    {
        Node s = m.getMatchSubject() ;
        Var sVar = null ;
        if ( s == null )
        {
            sVar = Var.alloc("s") ;
            s = sVar ;
        }
        
        Node p = m.getMatchPredicate() ;
        Var pVar = null ;
        if ( p == null )
        {
            pVar = Var.alloc("p") ;
            p = pVar ;
        }
        
        Node o = m.getMatchObject() ;
        Var oVar = null ;
        if ( o == null )
        {
            oVar = Var.alloc("o") ;
            o = oVar ;
        }
        
        Triple triple = new Triple(s, p ,o) ;
        
        // Evaluate as an algebra expression
        BasicPattern pattern = new BasicPattern() ;
        pattern.add(triple) ;
        Op op = new OpBGP(pattern) ;
        Plan plan = factory.create(op, getDataset(), BindingRoot.create(), null) ;
        
        QueryIterator qIter = plan.iterator() ;
        //List<Triple> triples = new ArrayList<Triple>() ;
        List<Triple> triples = new ArrayList<Triple>() ;
        
        for (; qIter.hasNext() ; )
        {
            Binding b = qIter.nextBinding() ;
            Node sResult = s ;
            Node pResult = p ;
            Node oResult = o ;
            if ( sVar != null )
                sResult = b.get(sVar) ;
            if ( pVar != null )
                pResult = b.get(pVar) ;
            if ( oVar != null )
                oResult = b.get(oVar) ;
            Triple resultTriple = new Triple(sResult, pResult, oResult) ;
            if ( log.isDebugEnabled() )
                log.debug("  "+resultTriple) ;
            triples.add(resultTriple) ;
        }
        qIter.close() ;
        return WrappedIterator.createNoRemove(triples.iterator()) ;
    }

    
//    class GraphIterator extends TrackingTripleIterator
//    {
//        GraphIterator(Iterator iter) { super(iter) ; }
//        
//        @Override
//        public void remove()
//        { delete((Triple)current) ; }
//    }
//    
//    public StoreLoader getBulkLoader() { return store.getLoader() ; }
//    
//    @Override
//    public BulkUpdateHandler getBulkUpdateHandler()
//    {
//    	if (bulkHandler == null) bulkHandler = new UpdateHandlerSDB(this);
//    	return bulkHandler;
//    }
//    
//    @Override
//    public GraphEventManager getEventManager()
//    {
//    	if (gem == null) gem = new EventManagerSDB( this );
//        return gem;
//    }
//    
//    @Override
//    public void performAdd( Triple triple )
//    {
//    	if (inBulkUpdate == 0) store.getLoader().startBulkUpdate();
//        store.getLoader().addTriple(triple) ;
//        if (inBulkUpdate == 0) store.getLoader().finishBulkUpdate();
//    }
//    
//    @Override
//    public void performDelete( Triple triple ) 
//    {
//    	if (inBulkUpdate == 0) store.getLoader().startBulkUpdate();
//        store.getLoader().deleteTriple(triple) ;
//        if (inBulkUpdate == 0) store.getLoader().finishBulkUpdate();
//    }
}

/*
 * (c) Copyright 2005, 2006, 2007, 2008, 2009 Hewlett-Packard Development Company, LP
 * All rights reserved.
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