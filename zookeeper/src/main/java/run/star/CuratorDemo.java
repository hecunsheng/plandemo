package run.star;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;


/**
 * @author hecs
 * @date 2020-04-26 22:50
 */
public class CuratorDemo {

    public static final String CONNECT_STRING = "127.0.0.1:2181";

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_STRING)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
//        ExponentialBackoffRetry 衰减重试 重试时长递增
//        RetryOneTime 紧重试一次
//        RetryUntilElapsed 一直重试，直到规定的时间结束
//        RetryNTimes 最大重试次数
        curatorFramework.start();

        //CRUD
        createData(curatorFramework);
//        updateData(curatorFramework);
//        getData(curatorFramework);
        deleteData(curatorFramework);
    }

    private static void createData(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                //节点和值
                .forPath("/data/program", "test".getBytes());
    }

    private static void updateData(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.setData()
                //节点和值
                .forPath("/data/program", "up".getBytes());
    }

    private static void getData(CuratorFramework curatorFramework) throws Exception {
        byte[] bytes = curatorFramework.getData()
                //节点和值
                .forPath("/data");
        System.out.println(bytes.toString());
    }

    private static void deleteData(CuratorFramework curatorFramework) throws Exception {
        //获取存储状态 ->最新版本号
        Stat stat = new Stat();
        byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/data");
        System.out.println("=====" + new String(bytes));

        curatorFramework.delete()
                .deletingChildrenIfNeeded()
                //版本号删除 锁机制
                .withVersion(stat.getVersion())
                //节点和值
                .forPath("/data");
    }

}
