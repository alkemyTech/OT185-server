package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.common.exception.ConflictException;
import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.Slide;
import com.alkemy.ong.domain.model.SlideList;
import com.alkemy.ong.domain.repository.SlideRepository;
import com.alkemy.ong.domain.usecase.SlideService;
import com.alkemy.ong.ports.output.s3.AmazonS3Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Long create(Slide slide, String imageBase64) {

        slide.setOrder(checkAndSetOrder(slide.getOrder()));
        slide.setImageUrl(getUrlAmazonS3(imageBase64));
        return slideJpaRepository.save(slide).getId();
    }

    private String getUrlAmazonS3(String imageBase64) {
        MultipartFile image = decodeBase64ToMultipart(imageBase64);
        return amazonS3Client.uploadFile(image);
    }

    private Integer checkAndSetOrder(Integer order) {
        if (order == null) {
            List<Slide> slides = findAll();
            if (slides.size() == 0) {
                order = 1;
            } else {
                Integer lastOrder = slides.get(slides.size() - 1).getOrder();
                order = lastOrder + 1;
            }
        }
        if (!slideJpaRepository.findByOrder(order).isEmpty()) {
            throw new ConflictException("The number indicated already exists.");
        }
        return order;
    }

    private MultipartFile decodeBase64ToMultipart(String imageBase64) {

        String dataType;
        String fileName = "slide";
        if (imageBase64.indexOf("data:image/png;") != -1) {
            dataType = "image/png";
            imageBase64 = imageBase64.replace("data:image/png;base64,", "");
            fileName += ".png";
        } else {
            dataType = "image/jpeg";
            imageBase64 = imageBase64.replace("data:image/jpeg;base64,", "");
            fileName += ".jpeg";
        }
        try {
            byte[] imageByte = Base64.getDecoder().decode(imageBase64);
            FileItem fileItem = new DiskFileItem(new String(imageByte), dataType, true, fileName, DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, new java.io.File(System.getProperty("java.io.tmpdir")));
            InputStream input = new ByteArrayInputStream(imageByte);
            OutputStream os = fileItem.getOutputStream();
            int ret = input.read();
            while (ret != -1) {
                os.write(ret);
                ret = input.read();
            }
            os.flush();
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            return multipartFile;

        } catch (IOException e) {
            log.error("error converting file", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Slide> findAll() {
        List<Slide> slides = (List<Slide>) slideJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "order"));
        return slides;
    }

    @Override
    @Transactional(readOnly = true)
    public SlideList getList(PageRequest pageRequest) {
        Page<Slide> page = slideJpaRepository.findAll(pageRequest);
        return new SlideList(page.getContent(), pageRequest, page.getTotalElements());
    }
}
