package ru.gb.gbrestmart.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbrestmart.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao extends JpaRepository<Product, Long> {

    Optional<Product> findByTitle(String title);
    List<Product> findAllByTitleContaining(String title);

}
