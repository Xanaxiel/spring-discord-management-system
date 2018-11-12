package pl.bmstefanski.discordms.web.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

  void initialize();

  String storeFile(MultipartFile file);

  Resource loadFileAsResource(String fileName);

  String getFileUrl(MultipartFile file);

}
