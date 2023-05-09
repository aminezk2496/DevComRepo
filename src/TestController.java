
import java.sql.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class TestController {
    
    @FXML
    private FlowPane productPane;
    
    private Connection connection;
    
    public void initialize() {
        // Load data from the database
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/products_db", "username", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                String imageUrl = resultSet.getString("image_url");
                Image image = new Image(imageUrl);
                
                // Create the product pane for each product
                VBox productBox = new VBox();
                productBox.setPrefWidth(250);
                productBox.setSpacing(10);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
                Label nameLabel = new Label(name);
                Label priceLabel = new Label("$" + price);
                Label descriptionLabel = new Label(description);
                productBox.getChildren().addAll(imageView, nameLabel, priceLabel, descriptionLabel);
                productPane.getChildren().add(productBox);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addToCart() {
        // Handle the user clicking on the Add to Cart button
    }
    
    public void close() {
        // Close the database connection
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}