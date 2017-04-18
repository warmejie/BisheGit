package cn.wj.utils;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 商城自定义响应结构
 */
public class BiSheResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;
    
    private List<?> rows;

    public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public static BiSheResult build(Integer status, String msg, Object data) {
        return new BiSheResult(status, msg, data);
    }

    public static BiSheResult ok(Object data) {
        return new BiSheResult(data);
    }

    public static BiSheResult ok() {
        return new BiSheResult(null);
    }

    public BiSheResult() {
      
    }

    public static BiSheResult build(Integer status, String msg) {
        return new BiSheResult(status, msg, null);
    }

    public BiSheResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public BiSheResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为TaotaoResult对象
     * 
     * @param jsonData json数据
     * @param clazz TaotaoResult中的object类型
     * @return
     */
    public static BiSheResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, BiSheResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static BiSheResult format(String json) {
        try {
            return MAPPER.readValue(json, BiSheResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static BiSheResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
