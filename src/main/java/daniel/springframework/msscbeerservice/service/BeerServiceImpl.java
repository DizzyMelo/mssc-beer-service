package daniel.springframework.msscbeerservice.service;

import daniel.springframework.msscbeerservice.web.model.BeerDto;
import daniel.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService{
    @Override
    public BeerDto getBeer(UUID id) {
        return BeerDto.builder()
                .id(id)
                .beerName("No beer")
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .beerStyle(BeerStyleEnum.GOSE)
                .price(BigDecimal.TEN)
                .quantityOnHand(30)
                .upc(173L)
                .version(1)
                .build();
    }

    @Override
    public BeerDto createBeer(BeerDto beerDto) {
        return beerDto.toBuilder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateBeer(UUID id, BeerDto beerDto) {
        log.debug("method not yet implemented");
    }

    @Override
    public void deleteBeer(UUID id) {
        log.debug("Method not yet implemented");
    }
}
