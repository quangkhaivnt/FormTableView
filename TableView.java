/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;
import demo.entity.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 *
 * @author admin
 */
public class TableView extends Application{

    @Override
     @SuppressWarnings("Convert2Lambda")
    public void start(Stage primaryStage) throws Exception {
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

        tableView.getColumns().addAll(columnImage, columnName, columnPrice, columnAction);

        ObservableList<Product> list = FXCollections.observableArrayList();
        list.add(new Product("Bánh mỳ trứng", "", "Ruby_logo_64x64.png", 10000));
        list.add(new Product("Mỳ tôm chanh", "", "apple.png", 10000));
        list.add(new Product("Bò húc", "", "twitter-bird.png", 12000));
        list.add(new Product("Cà phê", "", "Ruby_logo_64x64.png", 20000));
        list.add(new Product("Highland Cà phê", "", "windows_64x64.png", 15000));
        tableView.getItems().addAll(list);

        Scene scene = new Scene(tableView, 650, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
