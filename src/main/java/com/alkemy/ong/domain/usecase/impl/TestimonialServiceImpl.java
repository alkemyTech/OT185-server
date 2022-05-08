package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.Testimonial;
import com.alkemy.ong.domain.repository.TestimonialRepository;
import com.alkemy.ong.domain.usecase.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;

    @Override
    @Transactional
    public void deleteById(Long id){
        testimonialRepository.findById(id).ifPresent(testimonialRepository::delete);
    }

    @Override
    @Transactional
    public Long createEntity(Testimonial testimonial) {
        return testimonialRepository.save(testimonial).getId();
    }

    @Override
    @Transactional
    public Optional<Testimonial> updateEntityIfExists(Long id, Testimonial testimonial){
        testimonialRepository.findById(id)
                .map(testimonialJpa -> {
                    Optional.ofNullable(testimonial.getName()).ifPresent(testimonialJpa::setName);
                    Optional.ofNullable(testimonial.getImage()).ifPresent(testimonialJpa::setImage);
                    Optional.ofNullable(testimonial.getContent()).ifPresent(testimonialJpa::setContent);

                    return testimonialRepository.save(testimonialJpa);
                }).orElseThrow(() -> new NotFoundException(id));
        return testimonialRepository.findById(id);
    }


}
