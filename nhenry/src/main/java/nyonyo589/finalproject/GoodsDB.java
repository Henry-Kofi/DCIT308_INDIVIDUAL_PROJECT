package nyonyo589.finalproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GoodsDB {
//    public static ObservableList<Goods> getAllRecords() throws ClassNotFoundException, SQLException{
//        DatabaseConnection connectNow = new DatabaseConnection();
//        Connection connectDB  = connectNow.getConnection();
//
//        String query = "select * from products_table";
//
//        try {
//            Statement statement = connectDB.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            ObservableList<Goods> goodsList =  getGoodsObjects(resultSet);
//
//            return goodsList;
//        }catch (SQLException e){
//            System.out.println("Error occurred while fetching records from Database" + e);
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//    private static ObservableList<Goods> getGoodsObjects(ResultSet resultSet) throws ClassNotFoundException,SQLException {
//
//        try {
//            ObservableList<Goods> goodslist = FXCollections.observableArrayList();
//            while(resultSet.next()){
//                Goods goods = new Goods();
//                goods.setId(resultSet.getInt("product_id"));
//                goods.setGoodName(resultSet.getString("product_name"));
//                goods.setCategory(resultSet.getString("product_category"));
//                goods.setSellingPrice(resultSet.getInt("selling_price"));
//                goods.setBuyingPrice(resultSet.getInt("buying_price"));
//                goods.setQuantity(resultSet.getInt("quantity"));
//                goods.setDate(resultSet.getString("date"));
//
//                goodslist.add(goods);
//            }
//            return goodslist;
//        }catch (SQLException e){
//            System.out.println("Error occurred while fetching records from Database" + e);
//            e.printStackTrace();
//            e.getMessage();
//            throw  e;
//        }
//
//    }
}
