package com.ociweb.jfast.stream;

import static org.junit.Assert.assertEquals;

import com.ociweb.jfast.primitive.PrimitiveReader;
import com.ociweb.jfast.primitive.PrimitiveWriter;
import com.ociweb.jfast.primitive.adapter.FASTInputByteArray;
import com.ociweb.jfast.primitive.adapter.FASTOutputByteArray;
import static org.junit.Assert.*;

public abstract class BaseStreamingTest {

	
	private final float PCT_LIMIT = 40; //if avg is 40 pct above min then fail
	private final float MAX_X_LIMIT = 4f;//if max is 4x larger than avg then fail
	
	
	protected long emptyLoop(int iterations, int fields, int fieldsPerGroup) {
		
		long start = System.nanoTime();
		int i = iterations;
		int g = fieldsPerGroup;
		
		//open new group
		boolean isGroupOpen = false;
		while (--i>=0) {
			int f = fields;
					
			while (--f>=0) {
				
				//write field data here
				
				if (--g<0) {
					
					//close group
					
					g = fieldsPerGroup;
					if (f>0 || i>0) {
						
						//open new group
						
					}				
				}				
			}			
		}
		if (isGroupOpen) {
			//close group
		}
		return System.nanoTime() - start;
	}

	protected int[] buildTokens(int count, int[] types, int[] operators) {
		int[] lookup = new int[count];
		int typeIdx = types.length-1;
		int opsIdx = operators.length-1;
		while (--count>=0) {
			//high bit set
			//  7 bit type (must match method)
			//  4 bit operation (must match method)
			// 20 bit instance (MUST be lowest for easy mask and frequent use)
			
			lookup[count] = 0x80000000 |  
					       (types[typeIdx]<<24) |
					       (operators[opsIdx]<<20) |
					       count;
					
			//find next pattern to be used, rotating over them all.
			
			if (--typeIdx<0) {
				if (--opsIdx<0) {
					opsIdx = operators.length-1;
				}
				typeIdx = types.length-1;
			}
		}
		return lookup;
		
	}

	protected void printResults(int sampleSize, long maxOverhead, long totalOverhead, long minOverhead, long maxDuration, long totalDuration,
			long minDuration, long byteCount, String label) {
		
				System.out.println("---------"+label);
				float avgOverhead = totalOverhead/(float)sampleSize;
				//System.out.println("Overhead Min:"+minOverhead+" Max:"+maxOverhead+" Avg:"+avgOverhead);
				float avgDuration = totalDuration/(float)sampleSize;
				//System.out.println("Duration Min:"+minDuration+" Max:"+maxDuration+" Avg:"+avgDuration);
								
				float perByteMin = (minDuration-minOverhead)/(float)byteCount;
				float perByteAvg = (avgDuration-avgOverhead)/(float)byteCount;
				float perByteMax = (maxDuration-maxOverhead)/(float)byteCount;
				float pctAvgVsMin = 100f*((perByteAvg/perByteMin)-1);
				String msg = "PerByte  Min:"+perByteMin+"ns Avg:"+perByteAvg+"ns  <"+pctAvgVsMin+" pct>   Max:"+perByteMax+"ns";
				System.out.println(msg);

				assertTrue("Avg is too large vs min:"+pctAvgVsMin+" "+msg,pctAvgVsMin<PCT_LIMIT);
				assertTrue("Max is too large vs avg: "+msg,perByteMax < (MAX_X_LIMIT*perByteAvg));
								
			}

	protected int buildGroupToken(int maxPMapBytes, int repeat) {
		
		return 	0x80000000 |
				maxPMapBytes<<20 |
	            (repeat&0xFFFFF);
		
	}

	protected int groupManagementRead(int fieldsPerGroup, FASTStaticReader fr, int i, int g, int groupToken, int f) {
		if (--g<0) {
			//close group
			fr.closeGroup();
			
			g = fieldsPerGroup;
			if (f>0 || i>0) {
	
				//open new group
				fr.openGroup(groupToken);
				
			}				
		}
		return g;
	}

	protected int groupManagementWrite(int fieldsPerGroup, FASTStaticWriter fw, int i, int g, int groupToken, int f) {
		if (--g<0) {
			//close group
			fw.closeGroup();
			
			g = fieldsPerGroup;
			if (f>0 || i>0) {
	
				//open new group
				fw.openGroup(groupToken);
				
			}				
		}
		return g;
	}

	protected abstract long timeReadLoop(int fields, int fieldsPerGroup, int maxMPapBytes, int operationIters, int[] tokenLookup,
									FASTStaticReader fr);

	protected void performanceReadTest(int fields, int fieldsPerGroup, int maxMPapBytes, int operationIters, int warmup, int sampleSize,
			String label, int streamByteSize, int maxGroupCount, int[] tokenLookup,
			 long byteCount, byte[] writtenData) {
				
				long maxOverhead;
				long totalOverhead;
				long minOverhead;
				long maxDuration;
				long totalDuration;
				long minDuration;
				maxOverhead = Long.MIN_VALUE;
				totalOverhead = 0;
				minOverhead = Long.MAX_VALUE;
				
				maxDuration = Long.MIN_VALUE;
				totalDuration = 0;
				minDuration = Long.MAX_VALUE;
				
				FASTInputByteArray input = new FASTInputByteArray(writtenData);
				
				PrimitiveReader pr = new PrimitiveReader(streamByteSize*10, input, maxGroupCount*10);
				
				assertEquals(0,pr.totalRead());
				try {
					int w = warmup+sampleSize;
					while (--w>=0) {

						input.reset();
						pr.reset();
						
						FASTStaticReader fr = new FASTStaticReader(pr, fields, tokenLookup);
						
			
						//compute overhead
						long overhead = emptyLoop(operationIters, fields, fieldsPerGroup);
						
						//run test, note that timer does not cross virtual call boundary			
						long duration = timeReadLoop(fields, fieldsPerGroup, maxMPapBytes, operationIters,
														tokenLookup,
														fr);
					
						if (w<sampleSize) {
							if (0==totalDuration) {
								System.out.println("finished warmup...");
							}
							
							maxOverhead = Math.max(overhead, maxOverhead);
							totalOverhead += overhead;
							minOverhead = Math.min(overhead, minOverhead);
							
							maxDuration = Math.max(duration, maxDuration);
							totalDuration += duration;
							minDuration = Math.min(duration, minDuration);
							
						}	
						
					}
					
					
					printResults(sampleSize, maxOverhead, totalOverhead, minOverhead, 
			                maxDuration, totalDuration, minDuration,
			                byteCount, label);
				} finally {
					System.out.println("finished test after:"+pr.totalRead()+" bytes");
				}
			}

	protected abstract long timeWriteLoop(int fields, 
			int fieldsPerGroup, int maxMPapBytes, int operationIters, int[] tokenLookup,
			FASTStaticWriter fw);

	protected long performanceWriteTest(int fields, int fieldsPerGroup, int maxMPapBytes, int operationIters, int warmup,
			int sampleSize, String writeLabel, int streamByteSize, int maxGroupCount, int[] tokenLookup, 
			byte[] writeBuffer
			) {
				
		
		    long byteCount=0;
		
				long maxOverhead = Long.MIN_VALUE;
				long totalOverhead = 0;
				long minOverhead = Long.MAX_VALUE;
				
				long maxDuration = Long.MIN_VALUE;
				long totalDuration = 0;
				long minDuration = Long.MAX_VALUE;
		
		
				FASTOutputByteArray output = new FASTOutputByteArray(writeBuffer);
				
				PrimitiveWriter pw = new PrimitiveWriter(streamByteSize, output, maxGroupCount, false);
				try {
					
					int w = warmup+sampleSize;
					while (--w>=0) {
					
						output.reset();
						pw.reset();
						
						FASTStaticWriter fw = new FASTStaticWriter(pw, fields, tokenLookup);
						
						//compute overhead
						long overhead = emptyLoop(operationIters, fields, fieldsPerGroup);
						
						//run test			
						long duration = timeWriteLoop(fields, fieldsPerGroup, maxMPapBytes, 
								                       operationIters, tokenLookup,
								                       fw);
						
						if (w<sampleSize) {
							if (0==totalDuration) {
								byteCount = pw.totalWritten();
								System.out.println("finished warmup...  wrote:"+byteCount);
							}
							
							maxOverhead = Math.max(overhead, maxOverhead);
							totalOverhead += overhead;
							minOverhead = Math.min(overhead, minOverhead);
							
							maxDuration = Math.max(duration, maxDuration);
							totalDuration += duration;
							minDuration = Math.min(duration, minDuration);
							
						}			
					}
					
			
					printResults(sampleSize, maxOverhead, totalOverhead, minOverhead, 
							                 maxDuration, totalDuration, minDuration,
							                 byteCount, writeLabel);
				} finally {
					System.out.println("finished test after:"+pw.totalWritten()+" bytes");
				}
				return byteCount;
			}

}