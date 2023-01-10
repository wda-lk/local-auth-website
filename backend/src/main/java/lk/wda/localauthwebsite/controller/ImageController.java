package lk.wda.localauthwebsite.controller;

import lk.wda.localauthwebsite.model.Image;
import lk.wda.localauthwebsite.repository.ImageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/images")
public class ImageController {
    private final ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Image> getAllImagesByLocalAuthorityId(
            @RequestParam(name = "local-auth-id") long id) {
        return imageRepository.findAllByLocalAuthorityId(id);
    }
}
