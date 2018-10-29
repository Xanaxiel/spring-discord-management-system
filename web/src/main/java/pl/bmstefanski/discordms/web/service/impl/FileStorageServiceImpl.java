package pl.bmstefanski.discordms.web.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.bmstefanski.discordms.web.exception.FileNotFoundException;
import pl.bmstefanski.discordms.web.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService {

  @Value("classpath:static/files/")
  private Path filePath;

  @Override
  public void initialize() {
    try {
      Files.createDirectories(filePath);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String storeFile(MultipartFile file) {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    Path path = filePath.resolve(fileName);

    try {
      Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
      return fileName;
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public Resource loadFileAsResource(String fileName) {
    try {
      Path filePath = this.filePath.resolve(fileName).normalize();
      Resource resource = new UrlResource(filePath.toUri());
      if(resource.exists()) {
        return resource;
      } else {
        throw new FileNotFoundException("File not found " + fileName);
      }
    } catch (MalformedURLException ex) {
      throw new FileNotFoundException("File not found " + fileName, ex);
    }
  }

  @Override
  public String getFileUrl(MultipartFile file) {
    return ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/files/")
        .path(this.storeFile(file))
        .toUriString();
  }

}
