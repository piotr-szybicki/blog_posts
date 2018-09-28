package com.example.dealership.commad.domain.sales;

import com.example.dealership.commad.datamodel.BuyOfferDTO;
import com.example.dealership.commad.datamodel.OfferIdDTO;
import com.example.dealership.commad.domain.admissions.CarOfferAdmissionsService;
import com.example.dealership.commad.domain.admissions.repository.entites.snapshots.CarOfferSnapshot;
import com.example.dealership.commad.domain.sales.exceptions.BuyOfferInvalidException;
import com.example.dealership.commad.domain.sales.factory.JPABuyOfferFactory;
import com.example.dealership.commad.domain.sales.factory.JPABuyOfferFactoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Currency.getInstance;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BuyOfferServiceImplTest {

    @InjectMocks
    BuyOfferServiceImpl buyOfferService;

    @Mock
    CarOfferAdmissionsService carOfferAdmissionsService;

    @Spy
    private JPABuyOfferFactory jpaBuyOfferFactory = new JPABuyOfferFactoryImpl();

    @Test
    public void admitOrRejectNewOffer_offerCreatedCarSold(){
        //given
        UUID id = randomUUID();
        final BuyOfferDTO buyOfferDTO = createOfferDTO(id);
        when(carOfferAdmissionsService.snapshot(id)).thenReturn(crateSnapshot(id, new BigDecimal(10l), FALSE, "PLN"));

        //when
        final OfferIdDTO offerIdDTO = buyOfferService.admitOrRejectNewOffer(buyOfferDTO);

        //then
        assertThat(offerIdDTO).isNotNull();
        assertThat(offerIdDTO.message).isNull();
    }

    @Test
    public void admitOrRejectNewOffer_offerCreatedCarNotSold(){
        //given
        UUID id = randomUUID();
        final BuyOfferDTO buyOfferDTO = createOfferDTO(id);
        when(carOfferAdmissionsService.snapshot(id)).thenReturn(crateSnapshot(id, new BigDecimal(11l), FALSE, "PLN"));

        //when
        final OfferIdDTO offerIdDTO = buyOfferService.admitOrRejectNewOffer(buyOfferDTO);

        //then
        assertThat(offerIdDTO).isNotNull();
        assertThat(offerIdDTO.message).isNotNull();
        assertThat(offerIdDTO.message).isEqualTo("the price was too low, however we notify the seller about your intrest");
    }

    @Test(expected = BuyOfferInvalidException.class)
    public void admitOrRejectNewOffer_exceptionCarAlredySold(){
        //given
        UUID id = randomUUID();
        final BuyOfferDTO buyOfferDTO = createOfferDTO(id);
        when(carOfferAdmissionsService.snapshot(id)).thenReturn(crateSnapshot(id, new BigDecimal(11l), TRUE, "PLN"));

        //when
        try {
            final OfferIdDTO offerIdDTO = buyOfferService.admitOrRejectNewOffer(buyOfferDTO);
            fail("the test shuld not pass, the car was alredy sold");
        } catch (Exception e){

            //then
            assertThat(e).isInstanceOf(BuyOfferInvalidException.class);
            assertThat(e.getMessage()).contains("sold");
        }
    }

    @Test
    public void admitOrRejectNewOffer_exceptionDifferentCurrencies(){
        //given
        UUID id = randomUUID();
        final BuyOfferDTO buyOfferDTO = createOfferDTO(id);
        when(carOfferAdmissionsService.snapshot(id)).thenReturn(crateSnapshot(id, new BigDecimal(11l), FALSE, "USD"));

        //when
        try {
            final OfferIdDTO offerIdDTO = buyOfferService.admitOrRejectNewOffer(buyOfferDTO);
            fail("the test shuld not pass, the currency given ware different");
        } catch (Exception e){

            //then
            assertThat(e).isInstanceOf(BuyOfferInvalidException.class);
            assertThat(e.getMessage()).contains("wrong currency");
        }
    }

    private CarOfferSnapshot crateSnapshot(UUID id, BigDecimal priceAskedFromTheOwner, Boolean sold, String currency) {
        return new CarOfferSnapshot(id.toString(), "Audi", "A4", new Date(), priceAskedFromTheOwner, getInstance(currency), sold);
    }

    private BuyOfferDTO createOfferDTO(UUID id) {
        return new BuyOfferDTO(id, "PLN", new BigDecimal(10l));
    }


}