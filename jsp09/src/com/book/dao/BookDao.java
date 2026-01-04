package com.book.dao;

import com.book.model.Book;
import com.book.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    // ================= 考试时只改这里 =================
    // 1. 修改表名和字段
    private static final String TABLE_NAME = "tb_book";

    // 2. 准备好 SQL 模板 (考试时甚至不用动结构，只改字段名)
    private static final String SQL_FIND_ALL = "SELECT * FROM " + TABLE_NAME;
    private static final String SQL_FIND_BY_KEYWORD = "SELECT * FROM " + TABLE_NAME + " WHERE name LIKE ? OR isbn LIKE ?";
    private static final String SQL_INSERT = "INSERT INTO " + TABLE_NAME + " (isbn, name, price) VALUES (?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String SQL_GET_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE " + TABLE_NAME + " SET isbn = ?, name = ?, price = ? WHERE id = ?";
    // =================================================

    // 封装一个从 ResultSet 获取对象的方法，避免重复代码
    // 考试时：修改这里的 set 方法
    private Book mapToModel(ResultSet rs) throws Exception {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setIsbn(rs.getString("isbn"));
        book.setName(rs.getString("name"));
        book.setPrice(rs.getFloat("price"));
        return book;
    }

    // 1. 查询列表 (包含搜索)
    public List<Book> findList(String keyword) {
        List<Book> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            if (keyword != null && !keyword.trim().isEmpty()) {
                ps = conn.prepareStatement(SQL_FIND_BY_KEYWORD);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
            } else {
                ps = conn.prepareStatement(SQL_FIND_ALL);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapToModel(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, ps, rs);
        }
        return list;
    }

    // 2. 添加
    public boolean add(Book book) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            // 考试时：修改这里的参数顺序
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getName());
            ps.setFloat(3, book.getPrice());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, ps);
        }
        return false;
    }

    // 3. 删除
    public boolean delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, ps);
        }
        return false;
    }

    // 4. 根据ID查询 (用于修改回显)
    public Book getById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(SQL_GET_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return mapToModel(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, ps, rs);
        }
        return null;
    }

    // 5. 更新
    public boolean update(Book book) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            // 考试时：修改这里的参数顺序
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getName());
            ps.setFloat(3, book.getPrice());
            ps.setInt(4, book.getId()); // ID 通常是最后一个
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, ps);
        }
        return false;
    }
}