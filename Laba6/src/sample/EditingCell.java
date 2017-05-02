package sample;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import laba2.FoodResidus;

class EditingCell extends TableCell<FoodResidus, String> {

    private TextField textField;

    public EditingCell() {
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText((String) getItem());
        setGraphic(null);
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            System.out.println("fer");
            setTextFill(Color.RED);
            if (isEditing()) {
                System.out.println("ger");
                if (textField != null) {;
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(null);
            } else {
                System.out.println(getString());

                setGraphic(null);
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.focusedProperty()
                .addListener(
                        (ObservableValue<? extends Boolean> arg0, Boolean arg1,
                         Boolean arg2) -> {
                            if (!arg2) {
                                commitEdit(textField.getText());
                            }
                        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
