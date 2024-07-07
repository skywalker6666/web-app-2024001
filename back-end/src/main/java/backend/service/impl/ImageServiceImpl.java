package backend.service.impl;

import backend.entity.EmployeeImage;
import backend.entity.FileData;
import backend.repository.FileDataRepository;
import backend.repository.ImageRepository;
import backend.service.ImageService;
import backend.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

/**
 * @author alan9
 **/
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private FileDataRepository fileDataRepository;
    private final String FOLDER_PATH="D:/";
    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        EmployeeImage imageData = imageRepository.save(EmployeeImage.builder()
                .imageName(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
    }


    @Override
    public byte[] downloadImage(String fileName) {
        Optional<EmployeeImage> dbImageData = imageRepository.findByImageName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        FileData fileData=fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}
