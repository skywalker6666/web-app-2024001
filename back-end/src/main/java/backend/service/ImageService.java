package backend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author alan9
 **/
public interface ImageService {
     String uploadImage(MultipartFile file) throws IOException;
     byte[] downloadImage(String fileName);
     String uploadImageToFileSystem(MultipartFile file) throws IOException;
     byte[] downloadImageFromFileSystem(String fileName) throws IOException;
}
