package run.star;

import com.google.common.collect.Lists;
import org.apache.curator.framework.AuthInfo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;


/**
 * @author hecs
 * @date 2020-04-26 22:50
 */
public class AclDemo {

    public static final String CONNECT_STRING = "127.0.0.1:2181";

    public static void main(String[] args) throws Exception {
        //授权会话
        ArrayList<AuthInfo> authInfos = Lists.newArrayList();
        authInfos.add(new AuthInfo("digest", "admin:admin".getBytes()));

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_STRING)
                .sessionTimeoutMs(5000)
                .authorization(authInfos)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        curatorFramework.start();


        List<ACL> digest = Lists.newArrayList();
//        ip方式    new Id("ip", "127.0.0.1")
//        world方式 new Id("world", "anyone")
//        内置权限 Ids
        ACL acl = new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE,
                new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin")));
        digest.add(acl);
        curatorFramework.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(digest)
                //节点和值
                .forPath("/auth/program", "test".getBytes());
        //已存在节点设置授权
        curatorFramework.setACL().withACL(digest).forPath("/data");
    }

}
