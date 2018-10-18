package org.lanqiao.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.lanqiao.dao.IUser;
import org.lanqiao.entity.User;
import org.lanqiao.utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUser {

    //创建一个QueryRunner对象（由JDBCUtils工具类提供一个数据源）
    QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

    @Override
    //添加
    public void insert(User user) throws SQLException {
        String sql = "insert into stu(name,password) values(?,?)";
        //sql,结果集处理器，参数，执行完返回一个对象
        User user1 = queryRunner.insert(sql,new BeanHandler<>(User.class),user.getName(),user.getPassword());
    }

    @Override
    public void update(User user) throws SQLException {
        String sql = "update stu set name='李超',password='88888' where id = 1";
        queryRunner.update(sql);
    }

    @Override
    //删除
    public void delete(int id) throws SQLException {
        String sql = "delete from stu where id = ?";
        queryRunner.update(sql,id);
    }

    @Override
    //根据ID查询
    public User getUserByID(int id) throws SQLException {
        String sql = "select * from stu where id = ?";
        //结果集处理器，如何去处理？有一个结果集处理器：Interface ResultSetHandler<T>(他是一个接口，接口不能创建对象)，创建一个BeanHandler对象
        User user = queryRunner.query(sql,new BeanHandler<>(User.class),id);//类型：User.class
        return user;
    }

    @Override
    public List<User> getUserAll() {
        String sql = "select * from stu";
        List<User> list = new ArrayList<>();
        try {
           list = queryRunner.query(sql,new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
