package chinh.nguyen.service.category;

import chinh.nguyen.model.Category;
import chinh.nguyen.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService extends IGeneralService<Category> {
     boolean findByNameContaining(String name);
    Boolean existsByName(String name);
    Page<Category> findAll(Pageable pageable);
    Page<Category> findPaginated(int PageNo, int pageSize);
}
