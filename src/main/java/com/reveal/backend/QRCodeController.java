package com.reveal.backend;


import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userData", new UserData());
        return "index";
    }

    @PostMapping("/generate")
    public String generateQRCode(UserData userData, Model model) throws Exception {
        String qrCodeText = userData.toString();
        byte[] qrCodeImage = qrCodeService.generateQRCode(qrCodeText, 250, 250);

        String qrCodeBase64 = Base64.getEncoder().encodeToString(qrCodeImage);

        model.addAttribute("qrCodeImage", qrCodeBase64);

        return "index";
    }

    @PostMapping("/scan")
    public String scanQRCode(@RequestParam("file") MultipartFile file, Model model) {
        try {
            String decodedText = qrCodeService.scanQRCode(file);
            model.addAttribute("decodedText", decodedText);

            model.addAttribute("userData", new UserData());
        } catch (IOException e) {
            model.addAttribute("error", "Failed to read the uploaded file.");
        } catch (NotFoundException e) {
            model.addAttribute("error", "No QR code found in the image.");
        } catch (ChecksumException | FormatException e) {
            model.addAttribute("error", "QR code could not be decoded.");
        }

        return "index";
    }

}