package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.util.ArrayList;


public class Main<TemplateType> extends Application
{
    static Stage window;
    static Scene ingredientInput, servingInput, results;
    static int counter = 1;
    static int looper = 0;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;

        // GUI Info
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon-1.png")));
        window.setTitle("Ingredient Ratio Calculator");

        setFirstScene();
        setSecondScene();

        window.setScene(ingredientInput);
        window.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }

    public void setFirstScene()
    {
        GridPane ingredientLayout = new GridPane();

        ArrayList<TextField> arrIngredientName = new ArrayList<TextField>();
        ArrayList<TextField> arrIngredientAmount = new ArrayList<TextField>();

        ArrayList<ChoiceBox<TemplateType>> arrUnit = new ArrayList<ChoiceBox<TemplateType>>();

        //ChoiceBox<String> arrUnit = new ChoiceBox<String>();

        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<Double> amountList = new ArrayList<Double>();

        ArrayList<TemplateType> unitList = new ArrayList<>();

        // buttons
        Button nextButton = new Button("Next");
        Button addButton = new Button("Add");

        addButton.setId("addButton");
        nextButton.setId("nextButton");

        Label name = new Label("Name");
        Label amount = new Label("Amount");
        Label unitLabel = new Label("Unit");

        ArrayList<Label> arrNumbers = new ArrayList<Label>();

        arrIngredientName.add(new TextField());
        arrIngredientAmount.add(new TextField());
        arrUnit.add(new ChoiceBox<>());
        arrUnit.get(0).getItems().addAll((TemplateType) "Unit", (TemplateType) "Cups", (TemplateType) "Teaspoons", (TemplateType) "Tablespoons");
        arrNumbers.add(new Label(1 + " "));
        ingredientLayout.add(arrNumbers.get(0), 1, 3);
        ingredientLayout.add(arrIngredientName.get(0), 2, 3);
        ingredientLayout.add(arrIngredientAmount.get(0), 3, 3);
        ingredientLayout.add(arrUnit.get(0), 4, 3);

        GridPane.setConstraints(name, 2, 2);
        GridPane.setConstraints(amount, 3, 2);
        GridPane.setConstraints(unitLabel, 4, 2);

        GridPane.setConstraints(arrNumbers.get(0), 1, 3);
        GridPane.setConstraints(arrIngredientName.get(0), 2, 3);
        GridPane.setConstraints(arrIngredientAmount.get(0), 3, 3);
        GridPane.setConstraints(arrUnit.get(0), 4, 3);

        GridPane.setConstraints(addButton, 2, 1);
        GridPane.setConstraints(nextButton, 3, 1);

        nextButton.addEventHandler(ActionEvent.ACTION, (e) -> window.setScene(servingInput));
        nextButton.addEventHandler(ActionEvent.ACTION, (e) -> {

            ArrayList<TemplateType> tempList = getChoice(arrUnit);

            for(; looper < arrIngredientName.size(); looper++)
            {
                nameList.add(looper, arrIngredientName.get(looper).getText());
                amountList.add(looper, Double.parseDouble(arrIngredientAmount.get(looper).getText()));
                unitList.add(looper, tempList.get(looper));

                //createIng(nameList.get(looper, amountList.get(looper), unitList.get(looper)));
            }
        });


        addButton.setOnAction(e -> {
            arrIngredientName.add(new TextField());
            arrIngredientAmount.add(new TextField());
            arrUnit.add(new ChoiceBox<>());
            arrUnit.get(counter).getItems().addAll((TemplateType) "Unit", (TemplateType) "Cups", (TemplateType) "Teaspoons", (TemplateType) "Tablespoons");
            arrNumbers.add(new Label(counter+1 + " "));
            ingredientLayout.add(arrNumbers.get(counter), 1, counter+3);
            ingredientLayout.add(arrIngredientName.get(counter), 2, counter+3);
            ingredientLayout.add(arrIngredientAmount.get(counter), 3, counter+3);
            ingredientLayout.add(arrUnit.get(counter), 4, counter+3);
            counter++;
        });

        ingredientLayout.getChildren().addAll(nextButton, addButton, name, amount, unitLabel);
        ingredientInput = new Scene(ingredientLayout, 400, 500);
        ingredientInput.getStylesheets().add(getClass().getResource("ingredientAmount.css").toExternalForm());
    }

    public void setSecondScene()
    {

        Label currentServingLabel = new Label("Current Servings: ");
        Label desiredServingLabel = new Label("Desired Servings: ");

        TextField currentServing = new TextField();
        TextField desiredServing = new TextField();

        Button nextButton = new Button("Next");
        nextButton.setOnAction(e -> window.setScene(results));

        GridPane servingLayout = new GridPane();

        servingLayout.setConstraints(currentServingLabel, 2, 3);
        servingLayout.setConstraints(desiredServingLabel, 2, 5);
        GridPane.setConstraints(currentServing, 4, 3);
        GridPane.setConstraints(desiredServing, 4, 5);
        GridPane.setConstraints(nextButton, 3, 1);

        //Label secondLabel = new Label("Servings");
        servingLayout.getChildren().addAll(nextButton, currentServing,currentServingLabel,desiredServing,desiredServingLabel);
        servingInput = new Scene(servingLayout, 400, 500);
        servingInput.getStylesheets().add(getClass().getResource("ingredientAmount.css").toExternalForm());
    }

    private ArrayList<TemplateType> getChoice(ArrayList<ChoiceBox<TemplateType>> arrUnit)
    {
        ArrayList<TemplateType> unitListConvert = new ArrayList<>();

        for (int i = 0; i < arrUnit.size(); i++)
        {
            unitListConvert.add(i, arrUnit.get(i).getValue());
        }
        return unitListConvert;
    }
}
