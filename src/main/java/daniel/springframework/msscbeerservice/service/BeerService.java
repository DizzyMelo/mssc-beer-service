package daniel.springframework.msscbeerservice.service;

import daniel.springframework.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeer(UUID id);
    BeerDto createBeer(BeerDto beerDto);
    void updateBeer(UUID id, BeerDto beerDto);
    void deleteBeer(UUID id);
}
