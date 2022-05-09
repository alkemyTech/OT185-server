package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.Slide;
import com.alkemy.ong.domain.repository.SlideRepository;
import com.alkemy.ong.domain.usecase.SlideService;
import com.alkemy.ong.ports.output.s3.AmazonS3Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

    private final SlideRepository slideJpaRepository;

    private final AmazonS3Client amazonS3Client;

    @Override
    @Transactional
    public void deleteById(Long id) {
        slideJpaRepository.findById(id).ifPresent(slideJpaRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public Slide getById(Long id) {
        return slideJpaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    @Transactional
    public Long create(Slide slide) {

        slide.setOrder(checkAndSetOrder(slide.getOrder()));

        slide.setImageUrl(decodeBase64ToMultipart(slide));
        return slideJpaRepository.save(slide).getId();
    }

    private Integer checkAndSetOrder(Integer order) {
        List<Slide> slides = findAll();
        if (order == null) {
            Integer lastOrder = slides.get(slides.size() - 1).getOrder();
            if (lastOrder != null) {
                order = lastOrder + 1;
            } else {
                order = 1;
            }
        }
        for (Slide slide : slides) {
            if (slide.getOrder() == order) {
                throw new NotFoundException("The number indicated already exists.");
            }
        }
        return order;
    }

    private String decodeBase64ToMultipart(Slide slide) {
        String dataType;
        String fileName = "slide";
        if (slide.getImageUrl().indexOf("data:image/png;") != -1) {
            dataType = "image/png";
            slide.setImageUrl(slide.getImageUrl().replace("data:image/png;base64,", ""));
            fileName += ".png";
        } else {
            dataType = "image/jpeg";
            slide.setImageUrl(slide.getImageUrl().replace("data:image/jpeg;base64,", ""));
            fileName += ".jpeg";
        }
        try {
            byte[] imageByte = Base64.getDecoder().decode(slide.getImageUrl());
            FileItem fileItem = new DiskFileItem(new String(imageByte), dataType, true, fileName, DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, new java.io.File(System.getProperty("java.io.tmpdir")));
            InputStream input = new ByteArrayInputStream(imageByte);
            OutputStream os = fileItem.getOutputStream();
            int ret = input.read();
            while (ret != -1) {
                os.write(ret);
                ret = input.read();
            }
            os.flush();
            MultipartFile image = new CommonsMultipartFile(fileItem);
            return amazonS3Client.uploadFile(image);
        } catch (IOException e) {
            log.error("error converting file", e);
            throw new RuntimeException(e);
        }
    }

    public List<Slide> findAll() {
        List<Slide> slides = (List<Slide>) slideJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "order"));
        return slides;
    }
}
