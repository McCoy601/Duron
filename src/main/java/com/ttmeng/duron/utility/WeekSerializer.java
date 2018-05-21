package com.ttmeng.duron.utility;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class WeekSerializer extends JsonSerializer<Integer> {

	@Override
	public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		int week=value;
		String weekChs;
		switch (week) {
		case 1:
			weekChs="星期一";
			break;
		case 2:
			weekChs="星期二";
			break;
		case 3:
			weekChs="星期三";
			break;
		case 4:
			weekChs="星期四";
			break;
		case 5:
			weekChs="星期五";
			break;
		case 6:
			weekChs="星期六";
			break;
		default:
			weekChs="星期日";
			break;
		}
		
		gen.writeString(weekChs);
		
	}

}
