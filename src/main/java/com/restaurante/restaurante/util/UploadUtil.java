package com.restaurante.restaurante.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadUtil {

    @Value("${upload.image.directory}")
    private String pastaUploadImagem;

    public boolean fazerUploadImagem(MultipartFile imagem) {
        boolean sucessoUpload = false;
        if (!imagem.isEmpty()) {
            String nomeArquivo = imagem.getOriginalFilename();
            try {
                // Criando diretório para armazenar o arquivo
                File dir = new File(pastaUploadImagem);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Criando o arquivo no diretório
                File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);
                try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                    stream.write(imagem.getBytes());
                    sucessoUpload = true;
                } catch (IOException e) {
                    System.out.println("Você falhou! " + e.getMessage());
                }
            } catch (Exception e) {
                System.out.println("Você falhou! " + e.getMessage());
            }
        } else {
            System.out.println("Você falhou!");
        }
        return sucessoUpload;
    }
}
