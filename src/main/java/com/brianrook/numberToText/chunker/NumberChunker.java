package com.brianrook.numberToText.chunker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Converts the number into chunks that can be used to create the text message.
 * 
 * @author B.Rook
 * 
 */
public class NumberChunker {

	/**
	 * Convert a string into a series of 3 digit chunks
	 * 
	 * @param num
	 *            the string of the number
	 * @return a list of chunks
	 */
	public static List<String> getChunks(String num) {
		char[] numArray = num.toCharArray();
		List<String> returnList = new ArrayList<String>();

		List<Integer> chunkIndexes = findChunkIndex(numArray.length);
		returnList = getChunksFromIndex(chunkIndexes, num);
		return returnList;
	}

	/**
	 * Determine where in the string the chunks reside.
	 * 
	 * @param len
	 *            the length of the string
	 * @return a list of chunk start/end locations
	 */
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

	/**
	 * Use the indexes to create the list of chunks
	 * 
	 * @param indexes
	 *            the start/end locations of the chunks
	 * @param num
	 *            the string of the number
	 * @return a list of chunks
	 */
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
