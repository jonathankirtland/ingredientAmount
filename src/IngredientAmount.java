package src;

import javafx.application.Application;
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


public class IngredientAmount extends Application
{
    static Stage window;
    static Scene ingredientInput, servingInput, resultScene, results;
    static int counter = 1;
    static int looper = 0;
    static Controller controller = new Controller();
    private static String resultsStr = "";

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;

        Controller controller = new Controller();

        // GUI Info
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon-1.png")));
        window.setTitle("Ingredient Ratio Calculator");

        setFirstScene();

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

        ArrayList<ChoiceBox<String>> arrUnit = new ArrayList<ChoiceBox<String>>();

        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<Double> amountList = new ArrayList<Double>();

        ArrayList<String> unitList = new ArrayList<>();

        // buttons
        Button nextButton = new Button("Next");
        Button addButton = new Button("Add");

        addButton.setId("addButton");
        nextButton.setId("nextButton");

        Label name = new Label("Name");
        Label amount = new Label("Amount");
        Label unitLabel = new Label("Unit");

        ArrayList<Label> arrNumbers = new ArrayList<Label>();

        Label currentServingLabel = new Label("Current Servings: ");
        Label desiredServingLabel = new Label("Desired Servings: ");

        TextField currentServing = new TextField();
        TextField desiredServing = new TextField();

        GridPane servingLayout = new GridPane();

        servingLayout.setConstraints(currentServingLabel, 2, 3);
        servingLayout.setConstraints(desiredServingLabel, 2, 4);
        GridPane.setConstraints(currentServing, 4, 3);
        GridPane.setConstraints(desiredServing, 4, 4);

        arrIngredientName.add(new TextField());
        arrIngredientAmount.add(new TextField());
        arrUnit.add(new ChoiceBox<>());
        arrUnit.get(0).getItems().addAll("Unit", "1/2 Cup", "1/3 Cup", "1/4 Cup", "Cup","Teaspoons", "Tablespoons");
        arrNumbers.add(new Label(1 + " "));
        ingredientLayout.add(arrNumbers.get(0), 1, 8);
        ingredientLayout.add(arrIngredientName.get(0), 2, 8);
        ingredientLayout.add(arrIngredientAmount.get(0), 3, 8);
        ingredientLayout.add(arrUnit.get(0), 4, 8);

        GridPane.setConstraints(name, 2, 7);
        GridPane.setConstraints(amount, 3, 7);
        GridPane.setConstraints(unitLabel, 4, 7);

        GridPane.setConstraints(arrNumbers.get(0), 1, 8);
        GridPane.setConstraints(arrIngredientName.get(0), 2, 8);
        GridPane.setConstraints(arrIngredientAmount.get(0), 3, 8);
        GridPane.setConstraints(arrUnit.get(0), 4, 8);

        GridPane.setConstraints(addButton, 3, 10);
        GridPane.setConstraints(nextButton, 4, 10);

        nextButton.addEventHandler(ActionEvent.ACTION, (e) -> {


            ArrayList<String> tempList = getChoice(arrUnit);

            for(; looper < arrIngredientName.size(); looper++)
            {
                int currentServingNum, desiredServingNum;

                nameList.add(looper, arrIngredientName.get(looper).getText());
                amountList.add(looper, Double.parseDouble(arrIngredientAmount.get(looper).getText()));
                unitList.add(looper, tempList.get(looper));
                currentServingNum = Integer.parseInt(currentServing.getText());
                desiredServingNum = Integer.parseInt(desiredServing.getText());

                controller.servingsGiven(currentServingNum);
                controller.servingsDesired(desiredServingNum);

                controller.initRecipe();

                if(unitList.get(looper).equals("Unit"))
                {
                    controller.createIng(nameList.get(looper), amountList.get(looper), null);
                }
                else if(unitList.get(looper).equals("Cup"))
                {
                    controller.createIng(nameList.get(looper), amountList.get(looper), Type.CUP);
                }
                else if(unitList.get(looper).equals("Teaspoon"))
                {
                    controller.createIng(nameList.get(looper), amountList.get(looper), Type.TEASPOON);
                }
                else if(unitList.get(looper).equals("Tablespoon"))
                {
                    controller.createIng(nameList.get(looper), amountList.get(looper), Type.TABLESPOON);
                }
                else if(unitList.get(looper).equals("1/3 Cup"))
                {
                    controller.createIng(nameList.get(looper), amountList.get(looper), Type.THIRDCUP);
                }
                else if(unitList.get(looper).equals("1/4 Cup"))
                {
                    controller.createIng(nameList.get(looper), amountList.get(looper), Type.FOURTHCUP);
                }
                else if(unitList.get(looper).equals("1/2 Cup"))
                {
                    controller.createIng(nameList.get(looper), amountList.get(looper), Type.HALFCUP);
                }
                else
                {
                    controller.createIng(null, null, null);
                }

                this.resultsStr += controller.nextClicked();

                GridPane resultLayout = new GridPane();

                Label resultSceneLabel = new Label("Results:");

                GridPane.setConstraints(resultLayout, 3, 2);

                Label resultsLabel = new Label(this.resultsStr);

                GridPane.setConstraints(resultsLabel, 3, 3);

                resultLayout.getChildren().addAll(resultSceneLabel, resultsLabel);
                resultScene = new Scene(resultLayout, 400, 600);
                resultScene.getStylesheets().add(getClass().getResource("ingredientAmount.css").toExternalForm());
            }
        });

        nextButton.addEventHandler(ActionEvent.ACTION, (e) -> window.setScene(resultScene));

        addButton.setOnAction(e -> {
            arrIngredientName.add(new TextField());
            arrIngredientAmount.add(new TextField());
            arrUnit.add(new ChoiceBox<>());
            arrUnit.get(counter).getItems().addAll("Unit", "1/2 Cup", "1/3 Cup", "1/4 Cup", "Cup", "Teaspoons", "Tablespoons");
            arrNumbers.add(new Label(counter + 1 + " "));
            ingredientLayout.add(arrNumbers.get(counter), 1, counter + 8);
            ingredientLayout.add(arrIngredientName.get(counter), 2, counter + 8);
            ingredientLayout.add(arrIngredientAmount.get(counter), 3, counter + 8);
            ingredientLayout.add(arrUnit.get(counter), 4, counter + 8);
            GridPane.setConstraints(nextButton, 4, counter + 10);
            GridPane.setConstraints(addButton, 3, counter + 10);
            counter++;
        });

        ingredientLayout.getChildren().addAll(nextButton, addButton, name, amount, unitLabel, currentServing, currentServingLabel, desiredServing, desiredServingLabel);
        ingredientInput = new Scene(ingredientLayout, 400, 600);
        ingredientInput.getStylesheets().add(getClass().getResource("ingredientAmount.css").toExternalForm());
    }



    private ArrayList<String> getChoice(ArrayList<ChoiceBox<String>> arrUnit)
    {
        ArrayList<String> unitListConvert = new ArrayList<>();

        for (int i = 0; i < arrUnit.size(); i++)
        {
            unitListConvert.add(i, arrUnit.get(i).getValue());
        }
        return unitListConvert;
    }
}
