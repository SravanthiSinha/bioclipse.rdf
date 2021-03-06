/*
 * (c) Copyright 2007, 2008, 2009 Hewlett-Packard Development Company, LP
 * All rights reserved.
 * [See end of file]
 */

package com.hp.hpl.jena.tdb.base.block;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.WeakHashMap ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Caching block manager - this is an weak ref cache */
public class BlockMgrWeakRefCache extends BlockMgrSync
{

    // UNTESTED
    private static Logger log = LoggerFactory.getLogger(BlockMgrWeakRefCache.class) ;
    // Read cache
    WeakHashMap<Integer, ByteBuffer> readCache = null ;

    // Delayed dirty writes.
    WeakHashMap<Integer, ByteBuffer> writeCache = null ;
    
    public static boolean globalLogging = false ;           // Also enable the logging level. 
    private boolean logging = false ;                       // Also enable the logging level. 
    private String indexName ; 
    // ---- stats
    long cacheHits = 0 ;
    long cacheMisses = 0 ;
    long cacheWriteHits = 0 ;
    
    public BlockMgrWeakRefCache(String indexName, int readSlots, int writeSlots, final BlockMgr blockMgr)
    {
        super(blockMgr) ;
        this.indexName = String.format("%-12s", indexName) ;
        
        // Caches are related so we can't use a Getter for cache management.
        readCache = new WeakHashMap<Integer, ByteBuffer>(readSlots, 0.75f) ;
        
        if ( writeSlots > 0 )
            writeCache = new WeakHashMap<Integer, ByteBuffer>(writeSlots) ;
    }
    
    // Write out when flushed.
    private void expelEntry(Integer id)
    {
        ByteBuffer bb = writeCache.get(id) ;
        if ( bb == null )
        {
            log.error("Write cache: "+id+" expeling entry that isn't there") ;
            return ;
        }
        log("Drop (write cache): %d", id) ;
        super.put(id, bb) ;
        writeCache.remove(id) ;
    }

    // Pool?
//    @Override
//    public ByteBuffer allocateBuffer(int id)
//    {
//        super.allocateBuffer(id) ;
//    }
    
    @Override
    synchronized
    public ByteBuffer get(int id)
    {
        // A ByteBuffer may be in the read cache or the write cache - 
        // it can be just in the write cache because the read cache is finite.
        ByteBuffer bb = readCache.get(id) ;
        if ( bb != null )
        {
            cacheHits++ ;
            log("Hit(r) : %d", id) ;
            return bb ;
        }
        if ( writeCache != null )
        {
            // Might still be in the dirty blocks.
            bb = writeCache.get(id) ;
            if ( bb != null )
            {
                cacheWriteHits++ ;
                log("Hit(w) : %d",id) ;
                return bb ;
            }
        }
        
        cacheMisses++ ;
        log("Miss  : %d", id) ;
        bb = super.get(id) ;
        readCache.put(id, bb) ;
        return bb ;
    }

    @Override
    synchronized
    public void put(int id, ByteBuffer block)
    {
        log("Put   : %d", id) ;
        
        if ( writeCache != null )
            writeCache.put(id, block) ;
        else
            super.put(id, block) ;

        // ????
        readCache.put(id, block) ;
    }
    
    @Override
    synchronized
    public void freeBlock(int id)
    {
        log("Free  : %d", id) ;
        readCache.remove(id) ;
        if ( writeCache != null )
            writeCache.remove(id) ;
        super.freeBlock(id) ;
    }
    
//    @Override
//    public void finishUpdate()
//    { 
//        sync() ; 
//    }

    @Override
    synchronized
    public void sync()
    {
        if ( true )
        {
            String x = "" ;
            if ( indexName != null )
                x = indexName+" : ";
            log("%sH=%d, M=%d, W=%d", x, cacheHits, cacheMisses, cacheWriteHits) ;
        }
        
        if ( writeCache != null )
            log("sync (%d blocks)", writeCache.size()) ;
        else
            log("sync") ;
        boolean somethingWritten = syncFlush() ;
        // Sync the wrapped object
        if ( somethingWritten ) 
        {
            log("sync underlying BlockMgr") ;
            super.sync() ;
        }
        else
            log("Empty sync") ;
        
    }
    
    private void log(String fmt, Object... args)
    { 
        if ( ! logging && ! globalLogging ) return ;
        String msg = String.format(fmt, args) ;
        if ( indexName != null )
             msg = indexName+" : "+msg ;
        log.debug(msg) ;
    }
    
    @Override
    synchronized
    public void close()
    {
        if ( writeCache != null )
            log("close ("+writeCache.size()+" blocks)") ;
        syncFlush() ;
        super.close() ;
    }

    private boolean syncFlush()
    {
        boolean didSync = false ;
        if ( writeCache != null )
        {
            log("Flush (write cache)") ;
            
            long N = writeCache.size() ;
            Integer[] ids = new Integer[(int)N] ;

            Iterator<Integer> iter = writeCache.keySet().iterator() ;
            if ( iter.hasNext() )
                didSync = true ;

            for ( ; iter.hasNext() ; )
            {
                Integer id = iter.next();
                expelEntry(id) ;
            }
        }
        return didSync ;
    }
}

/*
 * (c) Copyright 2007, 2008, 2009 Hewlett-Packard Development Company, LP
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