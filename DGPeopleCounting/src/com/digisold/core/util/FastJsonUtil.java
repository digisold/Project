package com.digisold.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonUtil {

	public static String toJSONString(Object obj) {
		return JSON.toJSONString(obj, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.MapSortField);
	}

	public static Object parseJSONObject(String jsonStr, Class<?> cls) {
		return JSON.parseObject(jsonStr, cls, Feature.OrderedField);
	}

}
