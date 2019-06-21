package run.star.plan.serializable;

import com.alibaba.fastjson.JSON;

/**
 * @author hecs
 * @date 2019-06-21 15:33
 */
public class FastJsonSerialize implements ISerializer {
    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONString(obj).getBytes();
    }

    @Override
    public <b> b reSerialize(byte[] data, Class<b> clazz) {
        return (b) JSON.parseObject(new String(data), clazz);
    }
}
