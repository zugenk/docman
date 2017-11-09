package com.app.shared;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SystemIndexGen  {
//	protected static final int maxLowCounter=9;
	protected static Map<String,long[]> IndexHandler=new HashMap<String,long[]>();
	
	public static synchronized String getIndex(String indexName,int len) {
		return getIndex(indexName,len-1, 1);
	}
    public static synchronized String getIndex(String indexName,int high, int low) {
    	String filename=indexName+".counter";
		long[] lst=(long[]) IndexHandler.get(indexName);
		long maxLow=1;
		for (int i = 0; i < low; i++) maxLow=maxLow*10;
		maxLow-=1;
		if (lst==null){
			lst=new long[]{0,-1,maxLow,0};
			IndexHandler.put(indexName, lst);
		}
		long ltxidCounter = lst[0];
		long htxidCounter = lst[1];
		long maxLowCounter=lst[2];
		long cdoy = lst[3];
		Calendar cdate = Calendar.getInstance();
    	if (ltxidCounter >= maxLowCounter) {
    		ltxidCounter = -1;
    		htxidCounter++; 
    		cdoy = cdate.get(Calendar.DAY_OF_YEAR);
    		File f = new File(filename);
    		FileChannel channel = null;
    		FileLock lock = null;
    		RandomAccessFile raf = null;
    		try {
    			raf = new RandomAccessFile(f, "rw");
	    		channel = raf.getChannel();
	    		lock = channel.lock();
	    		Calendar fdate = Calendar.getInstance();
	    		fdate.setTimeInMillis(f.lastModified());
	    		if (fdate.get(Calendar.DAY_OF_YEAR) != cdoy) {
	    			//RESETTING COUNTER for date changes
	    			htxidCounter = 0;
    				ltxidCounter=0;
    			} // ELSE INCREMENTING HI COUNTER and record to file 
	    		String htstr = String.valueOf(htxidCounter+1);
    			while (htstr.length()<high) htstr = "00000000000000000000" + htstr;
				htstr=htstr.substring(htstr.length()-high);

	    		raf.seek(0);
	    		raf.write(htstr.getBytes());
	    		raf.write('\n');
    		} catch (Exception ex) {
    			//slog.error(ex.getMessage(), ex);
    			throw new RuntimeException(ex.getMessage());
    		} finally {
    			try { lock.release(); } catch (Exception ex) {}
    			try { channel.close(); } catch (Exception ex) {}
    			try { raf.close(); } catch (Exception ex) {}
    		}
    	}

    	if (htxidCounter < 0 || cdoy != cdate.get(Calendar.DAY_OF_YEAR)) {
    		cdoy = cdate.get(Calendar.DAY_OF_YEAR);
    		File f = new File(filename);
    		FileChannel channel = null;
    		FileLock lock = null;
    		RandomAccessFile raf = null;
    		try {
    			raf = new RandomAccessFile(f, "rw");
	    		channel = raf.getChannel();
	    		lock = channel.lock();
	    		
	    		String htstr = "";
	    		Calendar fdate = Calendar.getInstance();
	    		fdate.setTimeInMillis(f.lastModified());
	    		if (fdate.get(Calendar.DAY_OF_YEAR) == cdoy) {
	    			//Reading last counter from file
	    			htstr = raf.readLine();
	    			if (htstr!=null) {
	    				while (htstr.length()<high) htstr = "00000000000000000000" + htstr;
	    				htstr=htstr.substring(htstr.length()-high);
		    			htxidCounter = Long.parseLong(htstr);
		    			ltxidCounter=-1;
	    			} else {
	    				htxidCounter =0;
	    				ltxidCounter=0;
	    			}
	    		} else {
	    			//RESETTING COUNTER for date changes
	    			ltxidCounter=0;
	    			htxidCounter=0;
	    		}
    			htstr = String.valueOf(htxidCounter+1);
				while (htstr.length()<high) htstr = "00000000000000000000" + htstr;
				htstr=htstr.substring(htstr.length()-high);
	    		raf.seek(0);
	    		raf.write(htstr.getBytes());
	    		raf.write('\n');
    		} catch (Exception ex) {
    			//slog.error(ex.getMessage(), ex);
    			throw new RuntimeException(ex.getMessage());
    		} finally {
    			try { lock.release(); } catch (Exception ex) {}
    			try { channel.close(); } catch (Exception ex) {}
    			try { raf.close(); } catch (Exception ex) {}
    		}
    	} 
    	ltxidCounter ++;
    	lst[0] = ltxidCounter;
    	lst[1] = htxidCounter;
    	lst[2] = maxLowCounter;
    	lst[3] = cdoy;
    	String str = "0000" + String.valueOf(ltxidCounter);
    	while (str.length()<low)str = "0000"+str;    	
    	str = "0000000000000000" + htxidCounter + str.substring(str.length()-low);
    	while (str.length()<(high+low))str = "0000000000000000"+str;
    	return str.substring(str.length()-(high+low));
	 }
    	
    
    
    public static void main(String[] args) {
    	List<String> indexList= new LinkedList<String>();
    	indexList.add("index");
    	indexList.add("stan");
    	indexList.add("serialize");
    	indexList.add("finexus");
    	indexList.add("pan");
    	boolean goOn=true;
		int i=0; int j=1;int k=0;
		while(goOn) {
			i++;
			try{
				String idxName=indexList.get(k);
				if (args.length>0)idxName=args[0];
				String txId=SystemIndexGen.getIndex(idxName,5,2);
//				System.out.println(txId);
				if (i>=95 || i<=5) System.out.println(idxName+" => "+txId);
				if (i>99) { i=0; j++;}
				if (j>3) { j=0; k++;}
				if (k>=indexList.size())goOn=false;			
//				Thread.sleep(10);
//				if (txId.indexOf("-")>0) {
//					System.out.println(txId);
//					goOn=false; 
//				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
    }	
}
