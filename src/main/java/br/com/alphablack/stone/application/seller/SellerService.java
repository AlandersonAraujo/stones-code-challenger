package br.com.alphablack.stone.application.seller;

import br.com.alphablack.stone.core.seller.Seller;

import java.util.Optional;

public interface SellerService {
    Seller saveSeller(Seller seller);
    Optional<Seller> findByName(String name);
}
