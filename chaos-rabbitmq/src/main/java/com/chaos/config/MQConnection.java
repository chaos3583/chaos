package com.chaos.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 * @Description 包装的mq连接  
 * @author wangchunfeng
 * @date 2018年1月5日  
 *
 */
public class MQConnection {
    private static final Logger log = LogManager.getLogger(MQConnection.class);
    private String addresses;
    private String host;
    private int port;
    private String username;
    private String password;
    private String virtualHost;
    private int connectionTimeout;
    private boolean isUsed;
    private Connection conn;
    //唯一标识，用于equals方法
    private long onlyNum;
    
    public Channel createChannel() throws IOException{
        if(this.conn==null){
            return null;
        }
        return conn.createChannel();
    }
    
    public MQConnection(String addresses, String host, int port, String virtualHost, int connectionTimeout,
            String username, String password,long onlyNum) {
        this.addresses = addresses;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.virtualHost = virtualHost;
        this.connectionTimeout = connectionTimeout;
        //唯一标识，用于equals方法
        this.onlyNum = onlyNum;
        this.isUsed = false;
        // 创建数据库连接
        this.createConnection();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (onlyNum ^ (onlyNum >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MQConnection other = (MQConnection) obj;
        if (onlyNum != other.onlyNum)
            return false;
        return true;
    }

    private void createConnection() {
        List<Address> list = new ArrayList<Address>();
        if (!StringUtils.isBlank(addresses)) {
            String[] addressArray = addresses.split(",");
            for (String address : addressArray) {
                Address addr = new Address(address, port);
                list.add(addr);
            }
        }
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        factory.setConnectionTimeout(connectionTimeout);
        try {
            if (list.size() > 0) {
                conn = factory.newConnection(list);
            } else {
                conn = factory.newConnection();
            }
        } catch (IOException | TimeoutException e) {
            log.error("创建mq连接出现错误", e);
        }

    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public long getOnlyNum() {
        return onlyNum;
    }

    public void setOnlyNum(long onlyNum) {
        this.onlyNum = onlyNum;
    }

    
}