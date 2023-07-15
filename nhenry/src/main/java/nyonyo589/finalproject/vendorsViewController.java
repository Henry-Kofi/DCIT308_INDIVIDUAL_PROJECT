package nyonyo589.finalproject;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class vendorsViewController {
    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    String[] categoryChoices = {"","Beverages","Bread/Bakery","Canned/Jarred Goods","Dairy Products"
            ,"Dry/Baking Goods","Frozen Products","Meat","Farm Produce","Home Cleaners",
            "Paper Goods","Home Care"};

    @FXML
    private void initialize(){
        categoryChoiceBox.getItems().addAll(categoryChoices);
    }
}
