package chinh.nguyen.repository;

import chinh.nguyen.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {
    Boolean findByNameContaining(String name);
    Boolean existsByName(String name);
    Page<Category> findAll(Pageable pageable);
}
