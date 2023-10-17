package br.com.alphablack.stone.application.seller;

import br.com.alphablack.stone.core.seller.Seller;
import br.com.alphablack.stone.infrastructure.seller.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller saveSeller(Seller seller) {
        return this.sellerRepository.save(seller);
    }

    @Override
    public Optional<Seller> findByName(String name) {
        return this.sellerRepository.findByName(name);
    }
}
