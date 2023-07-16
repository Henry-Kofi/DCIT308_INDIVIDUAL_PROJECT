package nyonyo589.finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import  javafx.stage.Stage;
import  javafx.event.ActionEvent;

//import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;

class Userdata{
    String firstname = null;
    String lastname = null;
    String username = null;
    int id = 0;
}

public class LoginController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordTextfield;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
//        File brandingFile = new File("images/signupbg.png");
//        Image brandingImage = new Image(brandingFile.toURI().toString());
//        brandingImageView.setImage(brandingImage);
//
//        File lockFile = new File("images/elitlogo.jpg");
//        Image lockImage = new Image(lockFile.toURI().toString());
//        lockImageView.setImage(lockImage);
    }

    public void loginButtonOnAction(ActionEvent event) throws IOException {

        if (usernameTextField.getText().isBlank() == false && enterPasswordTextfield.getText().isBlank() == false) {
//            validateLogin();
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB  = connectNow.getConnection();

            String verifyLogin = "SELECT * FROM user_account WHERE username =  '"
                    + usernameTextField.getText() + "'AND password = '"
                    + enterPasswordTextfield.getText() + "'";
            try{
                Statement statement = connectDB.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyLogin);
                ArrayList<Userdata> users = this.getData(queryResult);
                int len = users.size();
                System.out.println(len);
                if(len==1){
                    System.out.println(" kbis");
                    //if (queryResult.getInt(1) == 1){
                        System.out.println(" kbis");
                        loginMessageLabel.setText("Login successful!!!");
                        System.out.println("Login successful");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome.fxml"));
                        root = loader.load();
                        WelcomeController welcomeController = loader.getController();


                        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
//                    }else {
//                        loginMessageLabel.setText("Invalid login, try again.");
//                    }
                }else {
                    loginMessageLabel.setText("Invalid login, try again.");
                }
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }

        } else if (usernameTextField.getText().isBlank() == true && enterPasswordTextfield.getText().isBlank() == true) {
            loginMessageLabel.setText("Please enter username and password");
        }
        else{
            if (usernameTextField.getText().isBlank() == true){
                loginMessageLabel.setText("Please enter username");
            } else if (enterPasswordTextfield.getText().isBlank() == true) {
                loginMessageLabel.setText("Please enter password");
            }
        }
    }
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private ArrayList<Userdata> getData(ResultSet rs) throws Exception{
        ArrayList<Userdata> alldata = new ArrayList<>();
        try {

            Userdata user;
            while(rs.next()){
                System.out.println("No no 4");
                //for(int i = 1; i<= c; i++){
                    user = new Userdata();
                    user.firstname = rs.getString("firstname");
                    user.lastname = rs.getString("lastname");
                    user.username = rs.getString("username");
                    user.id = rs.getInt("account_id");
                    alldata.add(user);
                //}
            }

        }catch (Exception e){
            System.out.println("No no no Error");

        }
        return alldata;
    }



}