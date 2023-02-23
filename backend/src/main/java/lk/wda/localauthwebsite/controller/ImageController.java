package lk.wda.localauthwebsite.controller;

import java.util.List;

import lk.wda.localauthwebsite.exception.APIException;
import lk.wda.localauthwebsite.exception.GoogleSheetConfigException;
import lk.wda.localauthwebsite.exception.MalformedImageURL;
import lk.wda.localauthwebsite.exception.NoResourceFoundException;
import lk.wda.localauthwebsite.model.Image;
import lk.wda.localauthwebsite.service.ImageService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
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
