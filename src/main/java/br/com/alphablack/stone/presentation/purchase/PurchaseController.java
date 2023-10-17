package br.com.alphablack.stone.presentation.purchase;

import br.com.alphablack.stone.adapters.cache.CacheGateway;
import br.com.alphablack.stone.application.client.ClientService;
import br.com.alphablack.stone.application.creditcard.CreditCardService;
import br.com.alphablack.stone.application.purchase.PurchaseService;
import br.com.alphablack.stone.core.client.Client;
import br.com.alphablack.stone.core.creditcard.CreditCard;
import br.com.alphablack.stone.core.purchase.Purchase;
import br.com.alphablack.stone.presentation.dto.PurchaseDTO;
import br.com.alphablack.stone.presentation.dto.PurchaseRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/starstore")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final CacheGateway cacheGateway;
    private final ClientService clientService;
    private final CreditCardService creditCardService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, CacheGateway cacheGateway, ClientService clientService, CreditCardService creditCardService) {
        this.purchaseService = purchaseService;
        this.cacheGateway = cacheGateway;
        this.clientService = clientService;
        this.creditCardService = creditCardService;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ResponseEntity<List<PurchaseDTO>> getAllPurchases() {
        List<PurchaseDTO> cachedPurchases = (List<PurchaseDTO>) cacheGateway.get("allPurchases");

        if (cachedPurchases != null) {
            return ResponseEntity.ok(cachedPurchases);
        }

        List<Purchase> purchaseList = purchaseService.getAllPurchases();

        List<PurchaseDTO> purchaseDTOList = purchaseList.stream()
                .map(this::convertToPurchaseDTO)
                .collect(Collectors.toList());

        cacheGateway.put("allPurchases", purchaseDTOList, 60);

        return ResponseEntity.ok(purchaseDTOList);
    }

    @RequestMapping(value = "/history/{clientId}", method = RequestMethod.GET)
    public ResponseEntity<List<PurchaseDTO>> findAllById(@PathVariable(value = "clientId") String clientId) {
        List<PurchaseDTO> cachedPurchases = (List<PurchaseDTO>) cacheGateway.get(clientId);

        if (cachedPurchases != null) {
            return ResponseEntity.ok(cachedPurchases);
        }

        List<Purchase> purchaseList = purchaseService.findAllByClientId(Long.parseLong(clientId));

        List<PurchaseDTO> purchaseDTOList = purchaseList.stream()
                .map(this::convertToPurchaseDTO)
                .collect(Collectors.toList());

        cacheGateway.put(clientId, purchaseDTOList, 60);

        return ResponseEntity.ok(purchaseDTOList);
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ResponseEntity<PurchaseDTO> buy(@Valid @RequestBody PurchaseRequestDTO purchaseRequest) {
        Purchase purchase = this.processPurchase(purchaseRequest);
        PurchaseDTO purchaseDTO = convertToPurchaseDTO(purchase);

        return new ResponseEntity<>(purchaseDTO, HttpStatus.CREATED);
    }

    private Purchase processPurchase(PurchaseRequestDTO purchaseRequestDTO) {
       Optional<Client> client = clientService.findById(Long.parseLong(purchaseRequestDTO.getClientId()));
       Optional<CreditCard> creditCard = creditCardService.findByNumber(purchaseRequestDTO.getCreditCard().getCardNumber());

       if (client.isEmpty()) {
           Client newClient = new Client();
           newClient.setName(purchaseRequestDTO.getClientName());
           client = Optional.ofNullable(clientService.saveClient(newClient));
       }

       if (creditCard.isEmpty()) {
           CreditCard newCreditCard = new CreditCard();
           newCreditCard.setNumber(purchaseRequestDTO.getCreditCard().getCardNumber());
           newCreditCard.setHolderName(purchaseRequestDTO.getCreditCard().getCardHolderName());
           newCreditCard.setCv(purchaseRequestDTO.getCreditCard().getCvv().toString());
           newCreditCard.setExpDate(purchaseRequestDTO.getCreditCard().getExpDate());
           creditCard = Optional.ofNullable(creditCardService.saveCreditCard(newCreditCard));
       }

       Purchase purchase = new Purchase();
       purchase.setClient(client.get());
       purchase.setCreditCard(creditCard.get());
       purchase.setPurchaseValue(purchaseRequestDTO.getTotalToPay());
       purchase.setPurchaseDate(new Date());

       return purchaseService.savePurchase(purchase);
    }

    private PurchaseDTO convertToPurchaseDTO(Purchase purchase) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setClientId(purchase.getClient().getId().toString());
        purchaseDTO.setPurchaseId(purchase.getId().toString());
        purchaseDTO.setValue(purchase.getPurchaseValue());
        purchaseDTO.setDate(purchase.getPurchaseDate());
        purchaseDTO.setCardNumber(purchase.getCreditCard().getNumber());

        return purchaseDTO;
    }
}
