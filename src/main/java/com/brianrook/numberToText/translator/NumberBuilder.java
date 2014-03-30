package com.brianrook.numberToText.translator;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brianrook.numberToText.chunker.NumberChunker;

@Component
public class NumberBuilder {
	@Autowired
	NumberTranslator numTrans;

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

	public String processSingle(String num) {
		Integer singleVal = Integer.parseInt(StringUtils.substring(num, 2));
		StringBuffer value = new StringBuffer();
		if (0 != singleVal) {
			value.append(numTrans.getName(singleVal));
		}
		return value.toString();
	}

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

	public String processNumber(String num) {
		StringBuffer returnText = new StringBuffer();
		List<String> chunkList = NumberChunker.getChunks(num);
		// start creating text from chunks
		for (int i = chunkList.size() - 1; i >= 0; i--) {
			String thisChunk = chunkList.get(i);
			if (StringUtils.isNotEmpty(returnText.toString())) {
				// apply space if we are appending
				returnText.append(StringUtils.SPACE);
			}
			returnText.append(processChunk(thisChunk));
			if (i > 0) {
				returnText.append(StringUtils.SPACE);
				returnText.append(numTrans.getPositionName(i));
			}
		}
		// got in some text, which should be a number, but we didn't get a text
		// representation. This should be Zero.
		if (StringUtils.isNotEmpty(num) && StringUtils.isEmpty(returnText)) {
			returnText.append(numTrans.getName(0));
		}
		return returnText.toString();
	}

	// from
	// http://stackoverflow.com/questions/14014158/double-to-fraction-in-java
	public String processDecimal(String num) {
		StringBuffer returnStr = new StringBuffer();
		if (StringUtils.isNotEmpty(num)) {
			BigDecimal convertNum = new BigDecimal(num);
			convertNum = convertNum.setScale(2, BigDecimal.ROUND_HALF_UP);
			int denominator = (int) Math.pow(10, convertNum.scale());
			convertNum = convertNum.movePointRight(convertNum.scale());
			int numerator = convertNum.toBigInteger().intValue();

			returnStr.append(StringUtils.leftPad(Integer.toString(numerator), 2,
					"0"));
			returnStr.append("/");
			returnStr.append(denominator);
		}
		return returnStr.toString();

	}

}
