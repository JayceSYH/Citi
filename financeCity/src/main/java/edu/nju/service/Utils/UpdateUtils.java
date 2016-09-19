package edu.nju.service.Utils;

import edu.nju.service.CategoryAndProduct.Category;
import edu.nju.service.CategoryAndProduct.ProductCategoryManager;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.*;

/**
 * Created by Sun YuHao on 2016/9/7.
 */
@Component
public class UpdateUtils {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://123.206.202.103:3306/citi?useUnicode=true&characterEncoding=UTF-8";
    private static final String username = "user";
    private static final String password = "root";

    //update fund
    static public void updateNameToId_Fund() {
        Connection connection = getConn();

        String sql_select = "SELECT * FROM product_fund";
        String sql_insert = "INSERT INTO name_to_id(id, name) values(?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_select);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Byte type = resultSet.getByte("category");
                if (resultSet.wasNull()) {
                    type = null;
                }
                Category category = ProductCategoryManager.getFundCategory(type);
                Integer id = resultSet.getInt("id");
                int pid = ProductCategoryManager.generateProductID(id, category.getCategoryName());

                System.out.print(resultSet.getString("name"));
                PreparedStatement prep = connection.prepareStatement(sql_insert);
                prep.setInt(1, pid);
                prep.setString(2, resultSet.getString("name"));

                prep.executeUpdate();

            }
            connection.close();
        }
        catch (SQLException s) {
            s.printStackTrace();
        }
    }


    static public void updateNameToId_Bank() {
        Connection connection = getConn();

        String sql_select = "SELECT * FROM product_bank";
        String sql_insert = "INSERT INTO name_to_id(id, name) values(?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_select);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = ProductCategoryManager.getCategoryByName(ProductCategoryManager.categoryBank);
                Integer id = resultSet.getInt("id");
                int pid = ProductCategoryManager.generateProductID(id, category.getCategoryName());

                System.out.print(resultSet.getString("name"));
                PreparedStatement prep = connection.prepareStatement(sql_insert);
                prep.setInt(1, pid);
                prep.setString(2, resultSet.getString("name"));

                prep.executeUpdate();

            }
            connection.close();
        }
        catch (SQLException s) {
            s.printStackTrace();
        }
    }

    static public void updateNameToId_Bond() {
        Connection connection = getConn();

        String sql_select = "SELECT * FROM product_bond";
        String sql_insert = "INSERT INTO name_to_id(id, name) values(?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_select);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = ProductCategoryManager.getCategoryByName(ProductCategoryManager.categoryBond);
                Integer id = resultSet.getInt("id");
                int pid = ProductCategoryManager.generateProductID(id, category.getCategoryName());

                System.out.println(pid);
                PreparedStatement prep = connection.prepareStatement(sql_insert);
                prep.setInt(1, pid);
                prep.setString(2, resultSet.getString("name"));

                prep.executeUpdate();

            }
            connection.close();
        }
        catch (SQLException s) {
            s.printStackTrace();
        }
    }

    static public void updateFundDaily() {
        Connection connection = getConn();

        String sql_select = "SELECT * FROM product_fund";
        String sql_insert = "UPDATE fund_daily_history SET fund_id = ? WHERE product_code = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_select);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Byte type = resultSet.getByte("category");
                if (resultSet.wasNull()) {
                    type = null;
                }
                Category category = ProductCategoryManager.getFundCategory(type);
                Integer id = resultSet.getInt("id");
                String product_code = resultSet.getString("product_code");
                int pid = ProductCategoryManager.generateProductID(id, category.getCategoryName());

                //System.out.print(resultSet.getString("name"));
                PreparedStatement prep = connection.prepareStatement(sql_insert);
                prep.setInt(1, pid);
                prep.setInt(2, Integer.valueOf(product_code));

                prep.executeUpdate();

            }
            connection.close();
        }
        catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public static void updateIfNav_Bank() {
        Connection connection = getConn();

        String sql_select = "SELECT * FROM product_bank";
        String sql_insert = "UPDATE product_bank SET if_NAV_type = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_select);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                BigDecimal nav = resultSet.getBigDecimal("nav");

                System.out.println(resultSet.getString("name"));
                if (nav != null) {
                    PreparedStatement prep = connection.prepareStatement(sql_insert);
                    prep.setInt(2, id);
                    prep.setByte(1, (byte)1);

                    prep.executeUpdate();
                }
            }
            connection.close();
        }
        catch (SQLException s) {
            s.printStackTrace();
        }
    }

    static public void cleanTradHistory() {
        Connection connection = getConn();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM citi.trade_history");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("DELETE FROM citi.unpaid_item");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("DELETE FROM citi.invest_status");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("DELETE FROM citi.invested_products");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("DELETE FROM citi.investment_portfolio");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("DELETE FROM citi.investment_portfolio");
            preparedStatement.executeUpdate();

            connection.close();
        }
        catch (SQLException s) {
            s.printStackTrace();
        }
    }

    static private Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return conn;
    }

    public static void test() {
        Connection connection = getConn();
        try {
            PreparedStatement p = connection.
                    prepareStatement("SELECT pbk.*, pbd.*  FROM citi.product_bank pbk, citi.product_bond pbd INNER JOIN citi.name_to_id n ON pbk.id=n.id WHERE n.name LIKE '%%'");
            p.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    static public void main(String[] args) {
    }
}
