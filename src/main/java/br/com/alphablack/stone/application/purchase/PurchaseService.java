package br.com.alphablack.stone.application.purchase;

import br.com.alphablack.stone.core.purchase.Purchase;

import java.util.List;

public interface PurchaseService {

    List<Purchase> getAllPurchases();
    List<Purchase> findAllByClientId(Long id);
    Purchase savePurchase(Purchase purchase);
}
