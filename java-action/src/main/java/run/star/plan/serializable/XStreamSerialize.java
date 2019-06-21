package run.star.plan.serializable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author hecs
 * @date 2019-06-21 14:49
 */
public class XStreamSerialize implements ISerializer {
    XStream xStream = new XStream(new DomDriver());

    @Override
    public byte[] serialize(Object obj) {
        return xStream.toXML(obj).getBytes();
    }

    @Override
    public <b> b reSerialize(byte[] data, Class<b> clazz) {
        return (b) xStream.fromXML(new String(data));
    }
}
