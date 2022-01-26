package com.example.captiongenerator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HelloController {
    public TextField minText;
    public TextField maxText;
    public Button selectFileButton;
    @FXML
    private Label welcomeText;
    File file;
    @FXML
    protected void selectFileButtonAction(ActionEvent event){
        Window window = ((Node) (event.getSource())).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(window);
        welcomeText.setText(file.getAbsolutePath());
        event.consume();

    }

    @FXML
    protected void onHelloButtonClick() throws IOException {


        welcomeText.setText("Başlıklar kaydedildi. basliklar.txt!");
        String fileName = file.getAbsolutePath();

        Document doc = Jsoup.parse(new File(fileName), "utf-8");

        //array
        ArrayList<String> baslikdizisi = new ArrayList<String>();
        ArrayList<String> cikarilandizisi = new ArrayList<String>();

        //başlıklar
        int max=Integer.valueOf(maxText.getText());
        int min=Integer.valueOf(minText.getText());
        int sum=0;
        ArrayList<Integer> maxHitOnly = new ArrayList<>();
        ArrayList<String> maxHitName = new ArrayList<>();
        while(min<=max){
            if(doc.getElementById("row-"+min)==null){
                min++;
                continue;
            }
            else{
                Element secondLevelChildElement = doc.getElementById("row-"+min);
                System.out.println(secondLevelChildElement.text());
                String arr[] = secondLevelChildElement.text().split(" ", 3);
                String arrGoruntulenme[] = secondLevelChildElement.text().split(" ");
                maxHitOnly.add(Integer.valueOf(arrGoruntulenme[arrGoruntulenme.length-4]));
                maxHitName.add(secondLevelChildElement.text());
                System.out.println(sum);
                sum+=Integer.valueOf(arrGoruntulenme[arrGoruntulenme.length-4]);

                if(arr[1].equalsIgnoreCase("GÜNDEM")||arr[1].equalsIgnoreCase("YAŞAM")||arr[1].equalsIgnoreCase("TEKNOLOJİ")||arr[1].equalsIgnoreCase("MAGAZİN")||arr[1].equalsIgnoreCase("EĞİTİM")||arr[1].equalsIgnoreCase("EKONOMİ")||arr[1].equalsIgnoreCase("DÜNYA")||arr[1].equalsIgnoreCase("KÜLTÜR...")||arr[1].equalsIgnoreCase("SİYASET")||arr[1].equalsIgnoreCase("SAĞLIK")||arr[1].equalsIgnoreCase("BİLİM...")){
                    min++;
                    cikarilandizisi.add(secondLevelChildElement.text().replaceFirst("(?:^|(?:[.!?]\\s))(\\w+)","").replaceFirst(" ","").replaceAll(" [^ ]+$", "").replaceAll(" [^ ]+$", "").replaceAll(" [^ ]+$", "").replaceAll(" [^ ]+$", "").replaceAll("i","İ").toUpperCase(Locale.ROOT));
                }
                else{
                    baslikdizisi.add(secondLevelChildElement.text().replaceFirst("(?:^|(?:[.!?]\\s))(\\w+)","").replaceFirst(" ","").replaceAll(" [^ ]+$", "").replaceAll(" [^ ]+$", "").replaceAll(" [^ ]+$", "").replaceAll(" [^ ]+$", "").replaceAll("i","İ").toUpperCase(Locale.ROOT));
                    min++;
                }

            }

        }
        Collections.sort(baslikdizisi);
        int toplamhaber = cikarilandizisi.size()+baslikdizisi.size();
        int maxHitValue;
        baslikdizisi.add("Toplam "+ toplamhaber+" haber var. ");
        baslikdizisi.add("Toplam görüntülenme sayısı: "+sum);
        //1.haber
        maxHitValue = Collections.max(maxHitOnly);
        baslikdizisi.add("1: "+maxHitValue);
        Integer maxHitIndex = maxHitOnly.indexOf(maxHitValue);
        baslikdizisi.add(maxHitName.get(maxHitIndex).replaceFirst("(?:^|(?:[.!?]\\s))(\\w+)",""));
        maxHitOnly.remove(new Integer(Collections.max(maxHitOnly)));
        maxHitName.remove(new String(maxHitName.get(maxHitIndex)));
        //2.haber
        maxHitValue = Collections.max(maxHitOnly);
        baslikdizisi.add("2 : "+maxHitValue);
        Integer secondMaxHitIndex = maxHitOnly.indexOf(maxHitValue);
        baslikdizisi.add(maxHitName.get(secondMaxHitIndex).replaceFirst("(?:^|(?:[.!?]\\s))(\\w+)",""));
        maxHitOnly.remove((Collections.max(maxHitOnly)));
        maxHitName.remove(new String(maxHitName.get(secondMaxHitIndex)));

        //3.haber
        maxHitValue = Collections.max(maxHitOnly);
        baslikdizisi.add("3 : "+maxHitValue);
        Integer thirdMaxHitIndex = maxHitOnly.indexOf(maxHitValue);
        baslikdizisi.add(maxHitName.get(thirdMaxHitIndex).replaceFirst("(?:^|(?:[.!?]\\s))(\\w+)",""));
        maxHitOnly.remove(new Integer(Collections.max(maxHitOnly)));
        maxHitName.remove(new String(maxHitName.get(thirdMaxHitIndex)));

        //4.haber
        maxHitValue = Collections.max(maxHitOnly);
        baslikdizisi.add("4 : "+maxHitValue);
        Integer forthMaxHitIndex = maxHitOnly.indexOf(maxHitValue);
        baslikdizisi.add(maxHitName.get(forthMaxHitIndex).replaceFirst("(?:^|(?:[.!?]\\s))(\\w+)",""));
        maxHitOnly.remove(new Integer(Collections.max(maxHitOnly)));
        maxHitName.remove(new String(maxHitName.get(forthMaxHitIndex)));

        //3.haber
        maxHitValue = Collections.max(maxHitOnly);
        baslikdizisi.add("5 : "+maxHitValue);
        Integer fifthMaxIndex = maxHitOnly.indexOf(maxHitValue);
        baslikdizisi.add(maxHitName.get(fifthMaxIndex).replaceFirst("(?:^|(?:[.!?]\\s))(\\w+)",""));
        maxHitOnly.remove(new Integer(Collections.max(maxHitOnly)));
        maxHitName.remove(new String(maxHitName.get(fifthMaxIndex)));





        baslikdizisi.add("xxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxxxxxxxxxxxxxxxxx");
        baslikdizisi.add("xxxxxxxxxxxxxxxxxxxxxx LİSTEDEN ÇIKARILAN BAŞLIKLAR xxxxxxxxxxxxxxxxxxxxx");
        baslikdizisi.add("xxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxxxxxxxxxxxxxxxxx");
        cikarilandizisi.forEach((n)->baslikdizisi.add(n));
        baslikdizisi.forEach((n)-> System.out.println(n));
        Path out = Paths.get("basliklar.txt");
        Files.write(out,baslikdizisi, Charset.defaultCharset());
    }
}