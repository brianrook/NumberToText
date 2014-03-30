package com.brianrook.numberToText.chunker;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class NumberChunkerTest {

	@Test
	public void testNumberChunker() {
		String num = "1";
		List chunkList = NumberChunker.getChunks(num);
		assertEquals(1, chunkList.size());
		assertEquals("001", chunkList.get(0));
	}

	@Test
	public void testNumberChunker1() {
		String num = "10";
		List chunkList = NumberChunker.getChunks(num);
		assertEquals(1, chunkList.size());
		assertEquals("010", chunkList.get(0));
	}

	@Test
	public void testNumberChunker2() {
		String num = "100";
		List chunkList = NumberChunker.getChunks(num);
		assertEquals(1, chunkList.size());
		assertEquals("100", chunkList.get(0));
	}

	@Test
	public void testNumberChunker3() {
		String num = "1000";
		List chunkList = NumberChunker.getChunks(num);
		assertEquals(2, chunkList.size());
		assertEquals("000", chunkList.get(0));
		assertEquals("001", chunkList.get(1));
	}
	@Test
	public void testNumberChunker4() {
		String num = "1100010";
		List chunkList = NumberChunker.getChunks(num);
		assertEquals(3, chunkList.size());
		assertEquals("010", chunkList.get(0));
		assertEquals("100", chunkList.get(1));
		assertEquals("001", chunkList.get(2));
	}

	@Test
	public void testFindChunkIndex() {
		String num = "1";
		List<Integer> indexList = NumberChunker.findChunkIndex(num.length());
		assertEquals(1, indexList.size());
		assertEquals(0, (int) indexList.get(0));
	}

	@Test
	public void testFindChunkIndex1() {
		String num = "10";
		List<Integer> indexList = NumberChunker.findChunkIndex(num.length());
		assertEquals(1, indexList.size());
		assertEquals(0, (int) indexList.get(0));
	}

	@Test
	public void testFindChunkIndex2() {
		String num = "100";
		List<Integer> indexList = NumberChunker.findChunkIndex(num.length());
		assertEquals(1, indexList.size());
		assertEquals(0, (int) indexList.get(0));
	}

	@Test
	public void testFindChunkIndex3() {
		String num = "1000";
		List<Integer> indexList = NumberChunker.findChunkIndex(num.length());
		assertEquals(2, indexList.size());
		assertEquals(0, (int) indexList.get(0));
		assertEquals(3, (int) indexList.get(1));
	}

	@Test
	public void testFindChunkIndex4() {
		String num = "1000000";
		List<Integer> indexList = NumberChunker.findChunkIndex(num.length());
		assertEquals(3, indexList.size());
		assertEquals(0, (int) indexList.get(0));
		assertEquals(3, (int) indexList.get(1));
		assertEquals(6, (int) indexList.get(2));
	}

	@Test
	public void testGetChunkFromIndex() {
		String num = "1";
		List<Integer> chunkIndex = new ArrayList<Integer>();
		chunkIndex.add(0);
		List<String> chunkList = NumberChunker.getChunksFromIndex(chunkIndex,
				num);
		assertEquals(1, chunkList.size());
		assertEquals("001", chunkList.get(0));
	}

	@Test
	public void testGetChunkFromIndex1() {
		String num = "10";
		List<Integer> chunkIndex = new ArrayList<Integer>();
		chunkIndex.add(0);
		List<String> chunkList = NumberChunker.getChunksFromIndex(chunkIndex,
				num);
		assertEquals(1, chunkList.size());
		assertEquals("010", chunkList.get(0));
	}

	@Test
	public void testGetChunkFromIndex2() {
		String num = "100";
		List<Integer> chunkIndex = new ArrayList<Integer>();
		chunkIndex.add(0);
		List<String> chunkList = NumberChunker.getChunksFromIndex(chunkIndex,
				num);
		assertEquals(1, chunkList.size());
		assertEquals("100", chunkList.get(0));
	}

	@Test
	public void testGetChunkFromIndex3() {
		String num = "1000";
		List<Integer> chunkIndex = new ArrayList<Integer>();
		chunkIndex.add(0);
		chunkIndex.add(3);
		List<String> chunkList = NumberChunker.getChunksFromIndex(chunkIndex,
				num);
		assertEquals(2, chunkList.size());
		assertEquals("000", chunkList.get(0));
		assertEquals("001", chunkList.get(1));
	}

	@Test
	public void testGetChunkFromIndex4() {
		String num = "1000000";
		List<Integer> chunkIndex = new ArrayList<Integer>();
		chunkIndex.add(0);
		chunkIndex.add(3);
		chunkIndex.add(6);
		List<String> chunkList = NumberChunker.getChunksFromIndex(chunkIndex,
				num);
		assertEquals(3, chunkList.size());
		assertEquals("000", chunkList.get(0));
		assertEquals("000", chunkList.get(1));
		assertEquals("001", chunkList.get(2));
	}
}
