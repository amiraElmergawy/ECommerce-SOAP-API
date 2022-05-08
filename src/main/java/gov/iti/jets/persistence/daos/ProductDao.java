package gov.iti.jets.persistence.daos;

import java.util.List;
import java.util.Optional;

import gov.iti.jets.persistence.entities.Category;
import gov.iti.jets.persistence.entities.ProductEntity;

public interface ProductDao {

    public Optional<ProductEntity> getProductById(int id);
    public List<ProductEntity> getProducts();
    public List<ProductEntity> getProductsPage(int pageNo);
    public boolean updateProduct(ProductEntity productEntity);
    public boolean createProduct(ProductEntity productEntity);
    public List<ProductEntity> search(String productName);
    public boolean deleteProduct(int id);
    public List<ProductEntity> getProductsByCategory(Category category);

}
