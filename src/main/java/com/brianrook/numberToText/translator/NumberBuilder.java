package com.brianrook.numberToText.translator;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brianrook.numberToText.chunker.NumberChunker;
import com.brianrook.numberToText.exception.InvalidNumberException;

/**
 * Builds the text representation of the number.
 * 
 * @author B.Rook
 * 
 */
@Component
public class NumberBuilder {
	@Autowired
	NumberTranslator numTrans;

	/**
	 * Builds the text representation of a number from 000-999 (known as a
	 * chunk)
	 * 
	 * @param num
	 *            the chunk to process
	 * @return the text representation of the chunk
	 */
	public String processHundred(String num) {
		String firstPosition = StringUtils.substring(num, 0, 1);
		Integer firstVal = Integer.parseInt(firstPosition);
		StringBuffer value = new StringBuffer();
		if (0 != firstVal) {
			value.append(numTrans.getName(firstVal));
			value.append(StringUtils.SPACE);
			value.append(numTrans.getPositionName(0));
		}
		return value.toString();
	}

	/**
	 * Creates the text representation of the ten/single position
	 * 
	 * @param num
	 *            the chunk to process
	 * @return a text representation of the ten/single position
	 */
	public String processTens(String num) {
		String secondPosition = StringUtils.substring(num, 1, 2);
		StringBuffer value = new StringBuffer();
		// test for teens
		if (1 == Integer.parseInt(secondPosition)) {
			value.append(numTrans.getName(Integer.parseInt(StringUtils
					.substring(num, 1))));
		} else if (0 == Integer.parseInt(secondPosition)) {
			// no second position, just get the single digit
			value.append(processSingle(num));

		} else {
			// build the tens
			String tens = secondPosition.concat("0");
			value.append(numTrans.getName(Integer.parseInt(tens)));
			String single = processSingle(num);
			if (StringUtils.isNotEmpty(single)) {
				value.append("-");
				value.append(single);
			}

		}
		return value.toString();
	}

	/**
	 * creates a text reprentation of the singles position
	 * 
	 * @param num
	 *            the chunk to process
	 * @return a text representation of the single position
	 */
	public String processSingle(String num) {
		Integer singleVal = Integer.parseInt(StringUtils.substring(num, 2));
		StringBuffer value = new StringBuffer();
		if (0 != singleVal) {
			value.append(numTrans.getName(singleVal));
		}
		return value.toString();
	}

	/**
	 * Process a chunk evaluating the hundreds and tens/single position
	 * 
	 * @param num
	 *            the chunk
	 * @return text representation of the chunk
	 */
	public String processChunk(String num) {
		StringBuffer chunkStr = new StringBuffer(processHundred(num));
		String tens = processTens(num);
		if (StringUtils.isNotEmpty(tens)) {
			if (StringUtils.isNotEmpty(chunkStr.toString())) {
				// apply space if we are appending
				chunkStr.append(StringUtils.SPACE);
			}
			chunkStr.append(tens);
		}
		return chunkStr.toString();
	}

	/**
	 * Create a text representation of a number. Creates chunks which are
	 * processed and adds positional information
	 * 
	 * @param num
	 *            the string to process
	 * @return a text representation of the string
	 * @throws InvalidNumberException
	 */
	public String processNumber(String num) throws InvalidNumberException {
		StringBuffer returnText = new StringBuffer();
		List<String> chunkList = NumberChunker.getChunks(num);
		// start creating text from chunks
		for (int i = chunkList.size() - 1; i >= 0; i--) {
			String thisChunk = chunkList.get(i);

			String text = processChunk(thisChunk);
			if (StringUtils.isNotEmpty(text)) {
				if (StringUtils.isNotEmpty(returnText.toString())) {
					// apply space if we are appending
					returnText.append(StringUtils.SPACE);
				}
				returnText.append(processChunk(thisChunk));
				if (i > 0) {
					returnText.append(StringUtils.SPACE);
					String posName = numTrans.getPositionName(i);
					if (StringUtils.isEmpty(posName)) {
						throw new InvalidNumberException(
								"Position name not found.  Number may be too big to process.");
					}
					returnText.append(posName);
				}
			}

		}
		// got in some text, which should be a number, but we didn't get a text
		// representation. This should be Zero.
		if (StringUtils.isNotEmpty(num) && StringUtils.isEmpty(returnText)) {
			returnText.append(numTrans.getName(0));
		}
		return returnText.toString();
	}

	/**
	 * Process decimal position of the number. Converts to fractions of /100.
	 * Rounds smaller than 100s down
	 * 
	 * @param num
	 *            the decimal portion of the number (.XXX)
	 * @return a text representation of the decimal portion
	 */
	// from
	// http://stackoverflow.com/questions/14014158/double-to-fraction-in-java
	public String processDecimal(String num) {
		StringBuffer returnStr = new StringBuffer();
		if (StringUtils.isNotEmpty(num)) {
			BigDecimal convertNum = new BigDecimal(num);
			convertNum = convertNum.setScale(2, BigDecimal.ROUND_DOWN);
			int denominator = (int) Math.pow(10, convertNum.scale());
			convertNum = convertNum.movePointRight(convertNum.scale());
			int numerator = convertNum.toBigInteger().intValue();

			returnStr.append(StringUtils.leftPad(Integer.toString(numerator),
					2, "0"));
			returnStr.append("/");
			returnStr.append(denominator);
		}
		return returnStr.toString();

	}

}
