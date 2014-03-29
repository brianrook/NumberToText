package com.brianrook.numberToText.chunker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class NumberChunker {

	public static List<String> getChunks(String num) {
		char[] numArray = num.toCharArray();
		List<String> returnList = new ArrayList<String>();

		List<Integer> chunkIndexes = findChunkIndex(numArray.length);
		returnList = getChunksFromIndex(chunkIndexes, num);
		return returnList;
	}

	public static List<Integer> findChunkIndex(final int len) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < len; i++) {
			if (i % 3 == 0) {
				list.add(i);
			}
		}
		Arrays.sort(list.toArray());
		return list;
	}

	public static List<String> getChunksFromIndex(List<Integer> indexes,
			String num) {
		int numLength = num.length();
		List<String> returnChunks = new ArrayList<String>();
		for (int i = 0; i < indexes.size(); i++) {
			int rightSide = indexes.get(i);
			int leftSide;
			if (i + 1 < indexes.size()) {
				leftSide = indexes.get(i + 1);
			} else {
				leftSide = numLength;
			}

			String chunk = StringUtils.substring(num, numLength - leftSide,
					numLength - rightSide);
			if (3 != chunk.length()) {
				// left pad with 0
				chunk = StringUtils.leftPad(chunk, 3, "0");
			}
			returnChunks.add(chunk);
		}
		return returnChunks;
	}

}
