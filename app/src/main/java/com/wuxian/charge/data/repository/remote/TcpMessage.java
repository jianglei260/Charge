package com.wuxian.charge.data.repository.remote;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by jianglei on 2017/2/28.
 */

public class TcpMessage {
    private String tcpMsgContent;
    private OpType type;
    private HashMap<String, Object> contentMap = new HashMap<>();
    public static String KEY_WORDS_BRANCH_ID = "branch_id";
    public static String KEY_WORDS_BATTERY_ID = "battery_id";
    public static String KEY_WORDS_STATUS = "status";
    public static String KEY_WORDS_BATTERY_ELEC = "batelec";
    public static String KEY_WORDS_MSG = "message";
    public static HashMap<Integer, String> STATUS_MAP = new HashMap<>();

    static {
        STATUS_MAP.put(1, "借出成功");
        STATUS_MAP.put(2, "借出失败（无可用机器）");
        STATUS_MAP.put(3, "借出失败（其他原因）");
        STATUS_MAP.put(4, "归还成功");
        STATUS_MAP.put(5, "归还失败");
        STATUS_MAP.put(6, "充电完毕");
        STATUS_MAP.put(7, "充电宝损坏");
        STATUS_MAP.put(8, "没有充电宝");
        STATUS_MAP.put(9, "指纹录入成功");
        STATUS_MAP.put(10, "指纹录入校验成功");
        STATUS_MAP.put(11, "指纹校验成功");
    }

    public enum OpType {
        TYPE_BORROW_STATUS("borrowStatus"), TYPE_RETURN_STATUS("returnStatus");
        private String value;

        OpType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public class Builder {
        public Builder setType(OpType type) {
            TcpMessage.this.type = type;
            return this;
        }

        public Builder addParam(String key, Object value) {
            contentMap.put(key, value);
            return this;
        }

        public TcpMessage build() {
            JSONObject object = new JSONObject();
            try {
                object.put("type", "say");
                contentMap.put("op", TcpMessage.this.type.getValue());
                contentMap.put("role", "branch");
                JSONObject contentObject = new JSONObject(contentMap);
                object.put("content", contentObject);
                tcpMsgContent = object.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return TcpMessage.this;
        }
    }

    public String getTcpMsgContent() {
        return tcpMsgContent;
    }

}
