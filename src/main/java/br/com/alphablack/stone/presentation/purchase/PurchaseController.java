package br.com.alphablack.stone.presentation.purchase;

import br.com.alphablack.stone.adapters.cache.CacheGateway;
import br.com.alphablack.stone.application.purchase.PurchaseService;
import br.com.alphablack.stone.core.purchase.Purchase;
import br.com.alphablack.stone.presentation.dto.PurchaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/starstore/history")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final CacheGateway cacheGateway;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, CacheGateway cacheGateway) {
        this.purchaseService = purchaseService;
        this.cacheGateway = cacheGateway;
    }

    @GetMapping
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

    @RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
    public ResponseEntity<List<PurchaseDTO>> findAllById(@PathVariable(value = "clientId") String clientId) {
        List<PurchaseDTO> cachedPurchases = (List<PurchaseDTO>) cacheGateway.get(clientId);

        if (cachedPurchases != null) {
            return ResponseEntity.ok(cachedPurchases);
        }

        List<Purchase> purchaseList = purchaseService.findAllByClientId(Long.parseLong(clientId));

        System.out.println("Quantidade " + purchaseList.size());

        List<PurchaseDTO> purchaseDTOList = purchaseList.stream()
                .map(this::convertToPurchaseDTO)
                .collect(Collectors.toList());

        cacheGateway.put(clientId, purchaseDTOList, 60);

        return ResponseEntity.ok(purchaseDTOList);
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
