package br.com.alphablack.stone.presentation;

import br.com.alphablack.stone.application.cache.CacheService;
import br.com.alphablack.stone.application.client.ClientService;
import br.com.alphablack.stone.application.creditcard.CreditCardService;
import br.com.alphablack.stone.application.purchase.PurchaseService;
import br.com.alphablack.stone.core.client.Client;
import br.com.alphablack.stone.core.creditcard.CreditCard;
import br.com.alphablack.stone.core.purchase.Purchase;
import br.com.alphablack.stone.presentation.dto.CreditCardRequestDTO;
import br.com.alphablack.stone.presentation.dto.PurchaseDTO;
import br.com.alphablack.stone.presentation.dto.PurchaseRequestDTO;
import br.com.alphablack.stone.presentation.purchase.PurchaseController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PurchaseControllerTest {

    @Mock
    private PurchaseService purchaseService;

    @Mock
    private CacheService cacheService;

    @Mock
    private ClientService clientService;

    @Mock
    private CreditCardService creditCardService;

    @InjectMocks
    private PurchaseController purchaseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPurchases() {
        List<Purchase> purchaseList = new ArrayList<>();
        Client newClient = new Client();
        newClient.setId(1L);
        newClient.setName("Luke Skywalker");

        CreditCard newCreditCard = new CreditCard();
        newCreditCard.setId(1L);
        newCreditCard.setNumber("1234123412341193");
        newCreditCard.setHolderName("Luke Skywalker");
        newCreditCard.setCv("789");
        newCreditCard.setExpDate("12/24");

        Purchase newPurchase = new Purchase();
        newPurchase.setId(1L);
        newPurchase.setClient(newClient);
        newPurchase.setPurchaseValue(1911);
        newPurchase.setPurchaseDate(new Date());
        newPurchase.setCreditCard(newCreditCard);

        purchaseList.add(newPurchase);
        when(purchaseService.getAllPurchases()).thenReturn(purchaseList);
        when(cacheService.get("allPurchases")).thenReturn(null);

        ResponseEntity<List<PurchaseDTO>> responseEntity = purchaseController.getAllPurchases();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(cacheService, times(1)).put(eq("allPurchases"), anyList(), eq(60));
    }

    @Test
    void testFindAllById() {
        String clientId = "1";
        List<Purchase> purchaseList = new ArrayList<>();

        Client newClient = new Client();
        newClient.setId(1L);
        newClient.setName("Luke Skywalker");

        CreditCard newCreditCard = new CreditCard();
        newCreditCard.setId(1L);
        newCreditCard.setNumber("1234123412341193");
        newCreditCard.setHolderName("Luke Skywalker");
        newCreditCard.setCv("789");
        newCreditCard.setExpDate("12/24");

        Purchase newPurchase = new Purchase();
        newPurchase.setId(1L);
        newPurchase.setClient(newClient);
        newPurchase.setPurchaseValue(1911);
        newPurchase.setPurchaseDate(new Date());
        newPurchase.setCreditCard(newCreditCard);

        purchaseList.add(newPurchase);

        when(purchaseService.findAllByClientId(Long.parseLong(clientId))).thenReturn(purchaseList);
        when(cacheService.get(clientId)).thenReturn(null);

        ResponseEntity<List<PurchaseDTO>> responseEntity = purchaseController.findAllById(clientId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(cacheService, times(1)).put(eq(clientId), anyList(), eq(60));
    }

    @Test
    void testBuy() {
        PurchaseRequestDTO purchaseRequestDTO = new PurchaseRequestDTO();
        purchaseRequestDTO.setClientId("1");
        purchaseRequestDTO.setClientName("Luke Skywalker");
        purchaseRequestDTO.setTotalToPay(1911);

        CreditCardRequestDTO creditCardRequestDTO = new CreditCardRequestDTO();
        creditCardRequestDTO.setCardNumber("1234123412341193");
        creditCardRequestDTO.setValue(7990);
        creditCardRequestDTO.setCardHolderName("Luke Skywalker");
        creditCardRequestDTO.setCvv(789);
        creditCardRequestDTO.setExpDate("12/24");
        purchaseRequestDTO.setCreditCard(creditCardRequestDTO);

        Optional<Client> mockedClient = Optional.of(new Client(1L, "Luke Skywalker"));
        when(clientService.findById(anyLong())).thenReturn(mockedClient);
        when(creditCardService.findByNumber(anyString())).thenReturn(Optional.empty());
        when(clientService.saveClient(any(Client.class))).thenReturn(new Client());
        when(creditCardService.saveCreditCard(any(CreditCard.class))).thenReturn(new CreditCard());

        Client newClient = new Client();
        newClient.setId(1L);
        newClient.setName("Luke Skywalker");

        CreditCard newCreditCard = new CreditCard();
        newCreditCard.setId(1L);
        newCreditCard.setNumber("1234123412341193");
        newCreditCard.setHolderName("Luke Skywalker");
        newCreditCard.setCv("789");
        newCreditCard.setExpDate("12/24");

        Purchase newPurchase = new Purchase();
        newPurchase.setId(1L);
        newPurchase.setClient(newClient);
        newPurchase.setPurchaseValue(1911);
        newPurchase.setPurchaseDate(new Date());
        newPurchase.setCreditCard(newCreditCard);

        when(purchaseService.savePurchase(any(Purchase.class))).thenReturn(newPurchase);

        ResponseEntity<PurchaseDTO> responseEntity = purchaseController.buy(purchaseRequestDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}
