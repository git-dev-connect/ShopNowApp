package com.shopnow.ShopNowApp.Repo;

import com.shopnow.ShopNowApp.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {

    @Query("SELECT p FROM Products p")
    List<Products> getAllProducts();
}
