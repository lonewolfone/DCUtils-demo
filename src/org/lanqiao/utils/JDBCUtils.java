package org.lanqiao.utils;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    private static  String driverClassName;
    private static String url;
    private static  String username ;
    private static String password ;
    private static int maxActive;
    private static int initSize;
    private static DruidAbstractDataSource druid= null;
    static {
        //读取文件，得到一个输入流
        //首先通过当前类获取自己的Class对象，在获取类加载器，得到输入流
        InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        //创建Properties对象(属性对象)
        Properties properties = new Properties();
        try {
            //将属性流加载到属性对象中
            properties.load(inputStream);
            //获取其中内容
            druid = new DruidDataSource();//创建一个数据源
            //设置连接属性
            driverClassName = properties.getProperty("driverClassName");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            initSize = Integer.valueOf(properties.getProperty("initialSize"));
            maxActive = Integer.valueOf(properties.getProperty("maxActive"));
            druid.setDriverClassName(driverClassName);
            druid.setUrl(url);
            druid.setUsername(username);
            druid.setPassword(password);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return druid;
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = druid.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void releaseSource(ResultSet resultSet, Statement statement, Connection connection){
        try {
            if (resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (connection != null){
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
