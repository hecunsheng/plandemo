package run.star.plan.serializable;

/**
 * @author hecs
 * @date 2019-06-21 14:00
 */
public interface ISerializer {

    byte[] serialize(Object obj);

    <b> b reSerialize(byte[] data, Class<b> clazz);
}
