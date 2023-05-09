///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
// */
//package GUI;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.fxml.Initializable;
//import com.google.zxing.BinaryBitmap;
//import com.google.zxing.MultiFormatReader;
//import com.google.zxing.Reader;
//import com.google.zxing.Result;
//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//import com.google.zxing.common.HybridBinarizer;
//import javafx.fxml.FXML;
//import javafx.scene.image.ImageView;
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//import javafx.embed.swing.SwingFXUtils;
//import javafx.fxml.FXML;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//
///**
// * FXML Controller class
// *
// * @author rihem
// */
//public class TestController  {
//
//    /**
//     * Initializes the controller class.
//     */
//
//
//
//    @FXML
//    private ImageView qrCodeImageView;
//
//    @FXML
//    public void initialize() {
//        String text = "http://www.example.com/page1";
//        generateQRCode(text);
//        qrCodeImageView.setOnMouseClicked(event -> {
//            String result = readQRCode();
//            if (result != null) {
//                try {
//                    URI uri = new URI(result);
//                    String path = uri.getPath();
//                    if (path != null) {
//                        switch (path) {
//                            case "/page1":
//                                // Déplacer l'utilisateur vers l'interface de la page 1
//                                break;
//                            case "/page2":
//                                // Déplacer l'utilisateur vers l'interface de la page 2
//                                break;
//                            // Ajoutez des cas supplémentaires pour chaque interface que vous souhaitez afficher
//                        }
//                    }
//                } catch (URISyntaxException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//     public void generateQRCode(String text) {
//        int width = 200;
//        int height = 200;
//        String format = "png";
//        Map<EncodeHintType, Object> hintMap = new HashMap<>();
//        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//        hintMap.put(EncodeHintType.MARGIN, 1);
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        BitMatrix bitMatrix;
//        try {
//            bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hintMap);
//        } catch (WriterException e) {
//            e.printStackTrace();
//            return;
//        }
//        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try {
//            ImageIO.write(bufferedImage, format, baos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Image qrCodeImage = new Image(baos.toByteArray());
//        qrCodeImageView.setImage(qrCodeImage);
//    }
//
//    public String readQRCode() {
//        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(qrCodeImageView.getImage(), null);
//        Reader reader = new MultiFormatReader();
//        Result result;
//        try {
//            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
//            result = reader.decode(binaryBitmap);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return result.getText();
//    }
//
//   
//    
//}
