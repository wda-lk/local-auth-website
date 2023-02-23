package lk.wda.localauthwebsite.controller;

import lk.wda.localauthwebsite.exception.APIException;
import lk.wda.localauthwebsite.exception.GoogleSheetConfigException;
import lk.wda.localauthwebsite.exception.MalformedImageURL;
import lk.wda.localauthwebsite.exception.NoResourceFoundException;
import lk.wda.localauthwebsite.model.Image;
import lk.wda.localauthwebsite.repository.ImageRepository;
import lk.wda.localauthwebsite.service.ImageService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/images")
public class ImageController {
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    public ImageController(ImageService imageService, ImageRepository imageRepository) {
        this.imageService = imageService;
        this.imageRepository = imageRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Image> getAllImagesByLocalAuthorityId(
            @RequestParam(name = "local-auth-id") long id) {
        return imageRepository.findAllByLocalAuthorityId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ExceptionHandler(APIException.class)
    public List<Image> createImages() throws GoogleSheetConfigException,
                                             NoResourceFoundException,
                                             MalformedImageURL {
        return imageService.createImages();
    }
}
