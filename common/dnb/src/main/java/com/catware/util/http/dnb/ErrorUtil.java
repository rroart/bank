package com.catware.util.http.dnb;

import java.util.ArrayList;
import java.util.List;

import com.catware.model.TppMessageGeneric;
import com.catware.util.http.MyResponse;
import com.catware.util.json.JsonUtil;

public class ErrorUtil {

	public static MyResponse getError(int code, com.catware.model.Error error) {
		List<com.catware.service.model.TppMessageGeneric> msgList = new ArrayList<>();
		for (TppMessageGeneric msg : error.getTppMessages()) {
			msgList.add(new com.catware.service.model.TppMessageGeneric(msg.getCategory(), msg.getText()));
		}
		String json = JsonUtil.convert(new com.catware.service.model.Error(msgList));
		return new MyResponse(code, json);
	}

	public static MyResponse getError(MyResponse response) {
		com.catware.model.Error error = JsonUtil.convert(response.getBody(), com.catware.model.Error.class);
		return getError(response.getCode(), error);
	}

	public static MyResponse getError(int code, String text) {
		List<com.catware.service.model.TppMessageGeneric> msgList = new ArrayList<>();
		msgList.add(new com.catware.service.model.TppMessageGeneric("ERROR", text));
		String json = JsonUtil.convert(new com.catware.service.model.Error(msgList));
		return new MyResponse(code, json);
	}

}
