package br.com.alphablack.stone.application.purchase;

import br.com.alphablack.stone.core.purchase.Purchase;
import br.com.alphablack.stone.infrastructure.purchase.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }


    @Override
    public List<Purchase> getAllPurchases() {
        return this.purchaseRepository.findAll();
    }

    @Override
    public List<Purchase> findAllByClientId(Long id) {
        return this.purchaseRepository.findAllByClient_Id(id);
    }

    @Override
    public Purchase savePurchase(Purchase purchase) {
        return this.purchaseRepository.save(purchase);
    }
}
