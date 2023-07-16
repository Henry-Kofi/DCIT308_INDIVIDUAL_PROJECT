package nyonyo589.finalproject;

import com.mysql.jdbc.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddGoodsController implements Initializable {
    @FXML
    private ChoiceBox<String> categoryChoiceBox;
    @FXML
    private Button addButton;
    @FXML
    private TextField goodnameTextField;
    @FXML
    private TextField sellingpriceTextField;
    @FXML
    private TextField buyingpriceTextField;
    @FXML
    private TextField quantityTextField;
    @FXML
    private Label messageLabel;
    @FXML
    private TableView<Goods> addgoodsTableView;
    @FXML
    private TableColumn<Goods, Integer> idTableColumn;
    @FXML
    private TableColumn<Goods,String> goodnameTableColumn;
    @FXML
    private TableColumn<Goods,String> categoryTableColumn;
    @FXML
    private TableColumn<Goods,Integer> sellingpriceTableColumn;
    @FXML
    private TableColumn<Goods, Integer> buyingpriceTableColumn;
    @FXML
    private TableColumn<Goods,Integer> quantityTableColumn;
    @FXML
    private TableColumn<Goods, Date> dateTableColumn;

    int totalGoods = 0;
    ObservableList<Goods> goodsObservableList = FXCollections.observableArrayList();

    String[] categoryChoices = {"","Beverages","Bread/Bakery","Canned/Jarred Goods","Dairy Products"
            ,"Dry/Baking Goods","Frozen Products","Meat","Farm Produce","Home Cleaners",
            "Paper Goods","Home Care"};

    public void addButtonOnAction(ActionEvent event){
        String category = categoryChoiceBox.getValue();
        String name = goodnameTextField.getText();
        int sprice = Integer.parseInt(sellingpriceTextField.getText());
        int bprice = Integer.parseInt(buyingpriceTextField.getText());
        int qty =Integer.parseInt(quantityTextField.getText());
        Date date = new Date(System.currentTimeMillis());
        System.out.println(category + name + sprice + bprice + qty);
        Connection conn = null;
        Statement statement = null;
        try{
            try {
                Class.forName("com.mysql.jdbc.Driver");

            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
                e.getMessage();
            }
            conn =(Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/demo_db","root","hunberry143");
            System.out.println("Connected to database");
            statement = (Statement) conn.createStatement();
            String query1 = "insert products_table (product_name,product_category,selling_price,buying_price,quantity,date) values ("
                    +"'" + goodnameTextField.getText() + "'" + ","+ "'"+ categoryChoiceBox.getValue().toString() + "'" + ","+Integer.parseInt(sellingpriceTextField.getText()) +","
                    +Integer.parseInt(buyingpriceTextField.getText()) +","+Integer.parseInt(quantityTextField.getText())+ "," + "curtime())";
            statement.executeUpdate(query1);
            goodsObservableList.add(new Goods(totalGoods+1,name,category,sprice,bprice,qty, date));
            idTableColumn.setCellValueFactory(new PropertyValueFactory<>("productid"));
            goodnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("productname"));
            categoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("categoryname"));
            sellingpriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("sellingprice"));
            buyingpriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("buyingprice"));
            quantityTableColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

            addgoodsTableView.setItems(goodsObservableList);
            totalGoods++;
            categoryChoiceBox.setValue("");
            goodnameTextField.setText("");
            sellingpriceTextField.setText(null);
            buyingpriceTextField.setText(null);
            quantityTextField.setText(null);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        }
        messageLabel.setText("Product Added Successfully");
        messageLabel.setTextFill(Color.GREEN);
    }

    public void retrieveButtonOnAction(ActionEvent event){
        Connection conn = null;
        Statement statement = null;
        try{
            try {
                Class.forName("com.mysql.jdbc.Driver");

            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
                e.getMessage();
            }
            conn =(Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/demo_db","root","hunberry143");
            System.out.println("Connected to database");
            statement = (Statement) conn.createStatement();
            String query1 = "select * from products_table";
            statement.executeUpdate(query1);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        }

    }


    public void categoryChoiceBoxOnAction(ActionEvent event){
        String option = categoryChoiceBox.getSelectionModel().getSelectedItem();
        System.out.println(option);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryChoiceBox.getItems().addAll(categoryChoices);
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String productQuery = "select product_id,product_name,product_category,selling_price,buying_price,quantity,date from products_table";
        try{

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(productQuery);

            while (queryOutput.next()){
                totalGoods++;
                Integer queryProductID = queryOutput.getInt("product_id");
                String queryProductName = queryOutput.getString("product_name");
                String queryProductCategory = queryOutput.getString("product_category");
                Integer queryProductSellingPrice = queryOutput.getInt("selling_price");
                Integer queryProductBuyingPrice = queryOutput.getInt("buying_price");
                Integer queryProductQuantity = queryOutput.getInt("quantity");
                Date queryProductDate = queryOutput.getDate("date");
                goodsObservableList.add(new Goods(queryProductID,queryProductName,queryProductCategory,queryProductSellingPrice,queryProductBuyingPrice,queryProductQuantity,queryProductDate));
            }
            idTableColumn.setCellValueFactory(new PropertyValueFactory<>("productid"));
            goodnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("productname"));
            categoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("categoryname"));
            sellingpriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("sellingprice"));
            buyingpriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("buyingprice"));
            quantityTableColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

            addgoodsTableView.setItems(goodsObservableList);

        }catch (SQLException e){
            Logger.getLogger(Goods.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }
    }

    @FXML
    private   void initialize() {

    }

    private void populateTable(ObservableList<Goods> goodslist) {
        addgoodsTableView.setItems(goodslist);
    }


}
class Goodsdata{
    String productName = null;
    String categoryName = null;
    double sellingPrice = 0;
    int quantity = 0;
    double buyingPrice = 0;
    Date date = null;
    int id = 0;
    private ArrayList<Goodsdata> getData(ResultSet rs) throws Exception{
        ArrayList<Goodsdata> alldata = new ArrayList<>();
        try {

            Goodsdata goods;
            while(rs.next()){
                //for(int i = 1; i<= c; i++){
                goods = new Goodsdata();
                goods.buyingPrice = rs.getInt("buying_price");
                goods.sellingPrice = rs.getInt("selling_price");
                goods.categoryName = rs.getString("category_name");
                goods.productName = rs.getString("product_name");
                goods.quantity = rs.getInt("quantity");
                goods.id = rs.getInt("product_id");
                goods.date = rs.getDate("date");
                alldata.add(goods);
                //}
            }

        }catch (Exception e){
            System.out.println("No no no Error");

        }
        return alldata;
    }
}
