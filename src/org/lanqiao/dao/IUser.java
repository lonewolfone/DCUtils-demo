package org.lanqiao.dao;

import org.lanqiao.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUser {
    //添加
    public  void  insert(User user) throws SQLException;
    //更新(修改)
    public void update (User user) throws SQLException;
    //删除
    public void delete(int id) throws SQLException;
    //根据ID查询
    public User getUserByID(int id) throws SQLException;
    //查询所有
    public List<User> getUserAll() throws SQLException;
}
