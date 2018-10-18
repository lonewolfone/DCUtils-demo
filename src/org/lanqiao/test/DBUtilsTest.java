package org.lanqiao.test;

import org.junit.Test;
import org.lanqiao.dao.IUser;
import org.lanqiao.dao.impl.UserDaoImpl;
import org.lanqiao.entity.User;
import org.lanqiao.utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtilsTest {
    public static void main(String[] args){
        System.out.println(JDBCUtils.getDataSource());
    }

    @Test
    //添加
    public void insertTest() throws SQLException {
        IUser iUser = new UserDaoImpl();
        User user = new User();
        user.setName("火女");
        user.setPassword("2222");
    }

    @Test
    //删除
    public void deleteTest() throws SQLException {
        IUser iUser = new UserDaoImpl();
        iUser.delete(13);
    }

    @Test
    //根据ID查询
    public void getUserByIdTest() throws SQLException {
        IUser iUser = new UserDaoImpl();
        User user = iUser.getUserByID(1);
        System.out.println(user);
    }

    @Test
    //更新
    public void updateTest() throws SQLException {
        IUser iUser = new UserDaoImpl();
        User user = new User();
        iUser.update(user);
    }

    @Test
    //查询所有
    public void getUserAllTest() throws SQLException {
        IUser iUser = new UserDaoImpl();
        List<User> list = iUser.getUserAll();
        for (User str:list){
            System.out.println(str);
        }
    }
}
