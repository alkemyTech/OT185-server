package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.model.Alkymer;
import com.alkemy.ong.domain.model.AlkymerList;
import com.alkemy.ong.domain.repository.usecase.AlkymerService;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.mapper.AlkymerControllerMapper;
import com.alkemy.ong.ports.input.rs.request.CreateAlkymerRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateAlkymerRequest;
import com.alkemy.ong.ports.input.rs.response.AlkymerResponse;
import com.alkemy.ong.ports.input.rs.response.AlkymerResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.ALKYMERS_URI;

@RestController
@RequestMapping(ALKYMERS_URI)
@RequiredArgsConstructor
public class AlkymerController {

    private final AlkymerService service;
    private final AlkymerControllerMapper mapper;

    @PostMapping
    public ResponseEntity<Void> createAlkymers(@Valid @RequestBody CreateAlkymerRequest createAlkymerRequest) {

        Alkymer alkymer = mapper.createAlkymerRequestToAlkymer(createAlkymerRequest);

        final long id = service.createEntity(alkymer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<AlkymerResponseList> getAlkymers(@RequestParam Optional<Integer> page,
                                                           @RequestParam Optional<Integer> size) {

        final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);

        AlkymerList list = service.getList(PageRequest.of(pageNumber, pageSize));

        AlkymerResponseList response;
        {
            response = new AlkymerResponseList();

            List<AlkymerResponse> content = mapper.alkymerListToAlkymerResponseList(list.getContent());
            response.setContent(content);

            final int nextPage = list.getPageable().next().getPageNumber();
            response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

            final int previousPage = list.getPageable().previousOrFirst().getPageNumber();
            response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

            response.setTotalPages(list.getTotalPages());
            response.setTotalElements(list.getTotalElements());
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlkymerResponse> getAlkymer(@Valid @NotNull @PathVariable Long id) {
        Alkymer alkymer = service.getByIdIfExists(id);
        AlkymerResponse response = mapper.alkymerToAlkymerResponse(alkymer);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlkeymer(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody UpdateAlkymerRequest updateAlkymerRequest) {
        Alkymer alkymer = mapper.updateAlkymerRequestToAlkymer(updateAlkymerRequest);
        service.updateEntityIfExists(id, alkymer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlkeymer(@Valid @NotNull @PathVariable Long id) {
        service.deleteById(id);
    }

}
