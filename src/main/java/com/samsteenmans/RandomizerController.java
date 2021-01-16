package com.samsteenmans;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomizerController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField randomizerDoelTextField;

    @FXML
    private TextField randomizerBronTextfield;

    @FXML
    private TextField randomizerAantalNummersTextfield;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10;

    //Variabelen
    private String bronAbsolutePath;
    private String doelAbsolutePath;
    private int aantalNummers;


    public String getBronAbsolutePath() {
        return bronAbsolutePath;
    }

    public void setBronAbsolutePath(String bronAbsolutePath) {
        this.bronAbsolutePath = bronAbsolutePath;
    }

    public String getDoelAbsolutePath() {
        return doelAbsolutePath;
    }

    public void setDoelAbsolutePath(String doelAbsolutePath) {
        this.doelAbsolutePath = doelAbsolutePath;
    }

    public int getAantalNummers() {
        return aantalNummers;
    }

    public void setAantalNummers(int aantalNummers) {
        this.aantalNummers = aantalNummers;
    }


    @FXML
    void eventBronBrowseButton(ActionEvent event) {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a Folder");

        //Scene binnenhalen
        Stage stage = (Stage) anchorPane.getScene().getWindow();


        File selectedDirectory = directoryChooser.showDialog(stage);


        setBronAbsolutePath(selectedDirectory.getAbsolutePath());
        randomizerBronTextfield.setText(getBronAbsolutePath());
    }

    @FXML
    void eventDoelBrowseButton(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("choose a folder");
        Stage stage = (Stage) anchorPane.getScene().getWindow();


        File selectedDirectory = directoryChooser.showDialog(stage);

        setDoelAbsolutePath(selectedDirectory.getAbsolutePath());
        randomizerDoelTextField.setText(getDoelAbsolutePath());
    }

    public void eventStartRandomizerButton(ActionEvent actionEvent) {



            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR HOMO");
            //Variabelen
            int aantalNummers = Integer.parseInt(randomizerAantalNummersTextfield.getText());
            File directory = new File(getBronAbsolutePath());
            File[] filesBron = directory.listFiles();
            ArrayList<File> filesBronList = new ArrayList<>();
            Collections.addAll(filesBronList, filesBron);
            ArrayList<File> filesBronAfterRandomizeList = new ArrayList<>();

            //Thread gestart zodat dit op de achtegrond word gerund
            new Thread(() -> {

                //Checks
                if (randomizerAantalNummersTextfield.getText().isEmpty()) {
                    alert.setContentText("homo vul een aantal in #DEPCO");
                } else if (randomizerBronTextfield.getText().isEmpty()) {
                    alert.setContentText("VUL EEN BRON IN HOMO");
                } else if (randomizerDoelTextField.getText().isEmpty()) {
                    alert.setContentText("MEENT GIJ DA NU??? VUL EEN FUCKING DOEL IN");
                } else if (aantalNummers > filesBronList.size() - 1) {
                    alert.setContentText("Aantal groter dan u nummer,bent gij achterlijk ofzo Mongo");
                } else {
                    //Randomize the shit out of it

                    //Show progressBar and image
                    progressBar.setVisible(true);


                    //Make list with numbers to copy
                    for (int i = 0; i < aantalNummers; i++) {
                        Random random = new Random();
                        int randomNumber = random.nextInt(filesBronList.size());
                        System.out.println("Size list:" + filesBronList.size());
                        System.out.println("random nummer:" + randomNumber);
                        System.out.println("file:" + filesBronList.get(randomNumber).getName());
                        filesBronAfterRandomizeList.add(filesBronList.get(randomNumber));
                        filesBronList.remove(randomNumber);
                    }

                    //Copy songs
                    for (int i = 0; i < filesBronAfterRandomizeList.size(); i++) {
                        //Create a file
                        File file = filesBronAfterRandomizeList.get(i);

                        //Java Fx
                        double position = i + 1;
                        double positionFinal = position / filesBronAfterRandomizeList.size();

                        //Images laten zien
                        if(positionFinal >= 0.1){
                            imageView1.setVisible(true);
                        } if(positionFinal >= 0.2){
                            imageView2.setVisible(true);
                        } if(positionFinal >= 0.3){
                            imageView3.setVisible(true);
                        } if(positionFinal >= 0.4){
                            imageView4.setVisible(true);
                        } if(positionFinal >= 0.5){
                            imageView5.setVisible(true);
                        } if(positionFinal >= 0.6){
                            imageView6.setVisible(true);
                        } if(positionFinal >= 0.7){
                            imageView7.setVisible(true);
                        } if(positionFinal >= 0.8){
                            imageView8.setVisible(true);
                        } if(positionFinal >= 0.9){
                            imageView9.setVisible(true);
                        }

                        //Images laten verdwijnen
                        if(positionFinal >= 0.91){
                            imageView9.setVisible(false);
                        } if(positionFinal >= 0.92){
                            imageView8.setVisible(false);
                        } if(positionFinal >= 0.93){
                            imageView7.setVisible(false);
                        } if(positionFinal >= 0.94){
                            imageView6.setVisible(false);
                        } if(positionFinal >= 0.95){
                            imageView5.setVisible(false);
                        } if(positionFinal >= 0.96){
                            imageView4.setVisible(false);
                        } if(positionFinal >= 0.97){
                            imageView3.setVisible(false);
                        } if(positionFinal >= 0.98){
                            imageView2.setVisible(false);
                        } if(positionFinal >= 0.99){
                            imageView1.setVisible(false);
                        }

                        Platform.runLater(() -> {
                            progressBar.setProgress(positionFinal);
                        });

                        try {
                            FileUtils.copyFile(file.getAbsoluteFile(), new File(getDoelAbsolutePath() + "/" + file.getName()));
                            alert.setAlertType(Alert.AlertType.INFORMATION);
                            alert.setContentText("Het is gelukt mijn kleine hoerezoon");
                        } catch (IOException e) {
                            e.printStackTrace();
                            alert.setContentText("ERROR MTF");
                        }
                    }
                }

                //Renew Fx
                Platform.runLater(() -> {
                    //remove progressbar and images
                    progressBar.setVisible(false);
                    //Show alert
                    alert.show();
                });


                //Thread start
            }).start();

    }
}
