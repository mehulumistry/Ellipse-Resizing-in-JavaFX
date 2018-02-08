import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    SelectionModel selectionModel;
    MouseHandler mouseHandler;

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();

        // object layer
        Group objectLayer = new Group();
        root.getChildren().add( objectLayer);

        // selection layer on top of object layer
        Group selectionLayer = new Group();
        root.getChildren().add( selectionLayer);

        selectionModel = new SelectionModel( selectionLayer);
        mouseHandler = new MouseHandler( selectionModel);



        Ellipse node = new Ellipse();


        node.setRadiusX(50);
        node.setRadiusY(50);
        node.relocate(300,100);
        node.setFill(Color.RED.deriveColor(1, 1, 1, 0.2));
        mouseHandler.makeDraggable(node);




        objectLayer.getChildren().addAll(node);

        Scene scene = new Scene( root, 1600, 900);

        // clear selection when user clicks outside of cell
        scene.setOnMousePressed(mouseEvent -> selectionModel.clear());

        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        primaryStage.setScene( scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}