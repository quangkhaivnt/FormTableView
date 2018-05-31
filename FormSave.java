/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import demo.entity.Product;
import demo.model.ProductModel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author admin
 */
public class FormSave extends Application{
    private Scene scene1;
    private Scene scene2;
    private Stage window;
    ProductModel model = new ProductModel();

    @Override
    @SuppressWarnings("Convert2Lambda")
    public void start(Stage stage) throws Exception {
        window = stage;
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(20, 20, 20, 20));
        gridpane.setHgap(20);
        gridpane.setVgap(20);
        gridpane.setAlignment(Pos.CENTER);

        // Tạo ra các components của Form.
        Label lblName = new Label("Name");
        Label lblImage = new Label("Image");
        Label lblPrice = new Label("Price");
        Label lblMessage = new Label();
        lblMessage.setTextFill(Color.web("red"));
        Label lblSuccessfully = new Label();
        lblSuccessfully.setTextFill(Color.web("green"));
        Label lblMessageError = new Label();
        lblMessageError.setTextFill(Color.web("red"));
        TextField txtName = new TextField();
        txtName.setPromptText("Name");
        TextField txtImage = new TextField();
        txtImage.setPromptText("Image");
        TextField txtPrice = new TextField();
        txtPrice.setPromptText("Price");
        //Table view
        javafx.scene.control.TableView<Product> tableView = new javafx.scene.control.TableView<>();

        TableColumn<Product, String> columnName = new TableColumn<>("Tên sản phẩm");
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnName.setMinWidth(200);

        TableColumn<Product, String> columnImage = new TableColumn<>("Ảnh sản phẩm");
        columnImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        columnImage.setMinWidth(200);

        TableColumn<Product, Integer> columnPrice = new TableColumn<>("Giá (VND)");
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnPrice.setMinWidth(100);

        TableColumn<Product, String> columnAction = new TableColumn<>("Thao tác");
        columnAction.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnAction.setMinWidth(150);

        columnImage.setCellFactory(
                new Callback<TableColumn<Product, String>, TableCell<Product, String>>() {
            @Override
            public TableCell<Product, String> call(TableColumn<Product, String> param) {

                return new TableCell<Product, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        if (item != null) {
                            System.out.println(param.isVisible());
                            System.out.println(item);
                            HBox box = new HBox();
                            box.setSpacing(10);
                            VBox vbox = new VBox();
                            vbox.getChildren().add(new Label("Tên sản phẩm"));
                            vbox.getChildren().add(new Label("Mô tả"));

                            ImageView imageview = new ImageView();
                            imageview.setFitHeight(50);
                            imageview.setFitWidth(50);
                            imageview.setImage(new Image("demo/resource/" + item));

                            box.getChildren().addAll(imageview, vbox);
                            //SETTING ALL THE GRAPHICS COMPONENT FOR CELL
                            setGraphic(box);
                        }
                    }
                };
            }

        }
        );
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableView.getColumns().addAll(columnImage, columnName, columnPrice, columnAction);

        ObservableList<Product> list = FXCollections.observableArrayList();
        Product product1 = new Product();
        model.show(product1);
        tableView.getItems().addAll(list);

        
        //
        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction((event) -> {
            String name = txtName.getText();
            String image = txtImage.getText();
            String price = txtPrice.getText();
            Product product = new Product(name, image, Integer.parseInt(price));
//            if(txtName == null || txtName.getLength() == 0){
//                lblMessage.setText("Thông tin thiếu.Nhập thông tin username");
//                return;
//            }else{
//                lblMessage.setText("");
//            }
//             if(txtImage == null || txtImage.getLength() == 0){
//                lblMessage.setText("Thông tin thiếu.Nhập thông tin password");
//                return;
//            }else{
//                lblMessage.setText("");
//            }
//            if(txtPrice == null || txtPrice.getLength() == 0){
//                lblMessage.setText("Thông tin thiếu.Nhập thông tin email");
//                return;
//            }else{
//                lblMessage.setText("");
//            }
//            model.save(product);
            if(model.save(product)){
                lblSuccessfully.setText("Nhập thành công!");
                return;
            }else{
                lblMessageError.setText("Nhập không thành công thử lại!");
            }
            window.setScene(scene2);
            
        });
//        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle (ActionEvent event){
//                
//            }
//        });
        Button btnReset = new Button("Reset");
        btnReset.setOnAction((event) -> {
            txtName.clear();
            txtImage.clear();
            txtPrice.clear();
        });
        btnSubmit.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        btnReset.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        txtName.setStyle("-fx-font: normal bold 20px 'serif' ");
        txtImage.setStyle("-fx-font: normal bold 20px 'serif' ");
        txtPrice.setStyle("-fx-font: normal bold 20px 'serif' ");
        // Đưa các components vào layout.
        gridpane.add(lblName, 0, 1, 1, 1);
        gridpane.add(lblImage, 0, 2, 1, 1);
        gridpane.add(lblPrice, 0, 3, 1, 1);
//        gridpane.add(lblMessage, 1, 0, 1, 1);
//        gridpane.add(lblSuccessfully, 1, 0, 1, 1);
//        gridpane.add(lblMessageError, 2, 1, 1, 1);
        gridpane.add(txtName, 1, 1, 1, 1);
        gridpane.add(txtImage, 1, 2, 1, 1);
        gridpane.add(txtPrice, 1, 3, 1, 1);
       
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().addAll(btnSubmit, btnReset);
        
        gridpane.add(hbox, 1, 5, 1, 1);        
        
        // Ném group vào scene.
        scene1 = new Scene(gridpane, 500, 300);
        scene2 = new Scene(tableView, 650, 500);
        // Đưa scene vào stage.
        stage.setScene(scene1);
        stage.setTitle("Hello FX");
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
