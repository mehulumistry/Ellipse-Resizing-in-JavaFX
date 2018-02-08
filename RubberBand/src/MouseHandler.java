import javafx.scene.Node;
import javafx.scene.shape.Ellipse;

// its just drag and drop here
// Drag handlers are in selectionModel.


public class MouseHandler {

    private SelectionModel selectionModel;

    public MouseHandler( SelectionModel selectionModel) {
        this.selectionModel = selectionModel;
    }

    private class DragContext {
        double x;
        double y;
    }

    public void makeDraggable( final Ellipse node) {

        final DragContext dragDelta = new DragContext();


        node.setOnMouseClicked(event -> {

            if(event.getClickCount() == 2){

                System.out.println("Clicked");

            }


        });

        node.setOnMousePressed(mouseEvent -> {

            // TODO: add shift & ctrl check to add/remove nodes to selection
            selectionModel.clear();

            // add to selection model, create drag handles
            selectionModel.add(node);

            dragDelta.x = node.getTranslateX() - mouseEvent.getSceneX();
            dragDelta.y = node.getTranslateY() - mouseEvent.getSceneY();

            // consume event, so that scene won't get it (which clears selection)
            mouseEvent.consume();
        });

        node.setOnMouseDragged(mouseEvent -> {

            node.setTranslateX(mouseEvent.getSceneX() + dragDelta.x);
            node.setTranslateY(mouseEvent.getSceneY() + dragDelta.y);

        });

        node.setOnMouseReleased(mouseEvent -> {

            node.setTranslateX(mouseEvent.getSceneX() + dragDelta.x);
            node.setTranslateY(mouseEvent.getSceneY() + dragDelta.y);
           // fixPosition(node);

        });
    }

    private void fixPosition(Node node) {

        double x = node.getTranslateX();
        double y = node.getTranslateY();

//        node.setTranslateX(x);
//        node.setTranslateY(y);


       // node.relocate(node.getLayoutX() + x, node.getLayoutY() + y);

        node.setTranslateX(0);
        node.setTranslateY(0);

    }

}