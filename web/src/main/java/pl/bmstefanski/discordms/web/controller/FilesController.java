package pl.bmstefanski.discordms.web.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bmstefanski.discordms.web.service.FileStorageService;

@RequestMapping("/files/")
@Controller
public class FilesController {

  private static final Logger LOGGER = LoggerFactory.getLogger(FilesController.class);
  private final FileStorageService fileStorageService;

  @Autowired
  public FilesController(FileStorageService fileStorageService) {
    this.fileStorageService = fileStorageService;
  }

  @GetMapping("{file:.+}/download")
  public ResponseEntity<Resource> download(@PathVariable String file, HttpServletRequest request) {
    Resource resource = this.fileStorageService.loadFileAsResource(file);

    String contentType = null;
    try {
      contentType = request.getServletContext()
          .getMimeType(resource.getFile().getAbsolutePath());
    } catch (IOException e) {
      LOGGER.info("Could not determine file type.");
    }

    if (contentType == null) {
      contentType = "application/octet-stream";
    }

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }

}
