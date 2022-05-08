package gov.iti.jets.service.servicesImpl;

import java.util.List;
import java.util.Optional;

import gov.iti.jets.persistence.daosImpl.ProductDaoImpl;
import gov.iti.jets.persistence.entities.Category;
import gov.iti.jets.persistence.entities.ProductEntity;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public class ProductServiceImpl {

    @WebMethod
    public Optional<ProductEntity> getProduct(int id) {
        return ProductDaoImpl.INSTANCE.getProductById(id);
    }

    @WebMethod
    public List<ProductEntity> getProducts() {
        return ProductDaoImpl.INSTANCE.getProducts();
    }

    @WebMethod
    public boolean updateProduct(String name, double amount, int categoryId, int quantity, int id) {
        Category category = Category.KITCHEN;
        // LIVINGROOM,BATHROOM,DININGROOM,BEDROOM,KITCHEN;
        switch (categoryId) {
            case 0:
                category = Category.LIVINGROOM;
                break;
            case 1:
                category = Category.BATHROOM;
                break;
            case 2:
                category = Category.DININGROOM;
                break;
            case 3:
                category = Category.BEDROOM;
                break;
            default: // KITCHEN
                break;
        }
        ProductEntity product = new ProductEntity(name, amount, category);
        if (quantity != 0)
            product.setQuantity(quantity);
        product.setId(id);
        return ProductDaoImpl.INSTANCE.updateProduct(product);
    }

    @WebMethod
    public boolean createProduct(String name, double amount, int categoryId, int quantity) {
        Category category = Category.KITCHEN;
        // LIVINGROOM,BATHROOM,DININGROOM,BEDROOM,KITCHEN;
        switch (categoryId) {
            case 0:
                category = Category.LIVINGROOM;
                break;
            case 1:
                category = Category.BATHROOM;
                break;
            case 2:
                category = Category.DININGROOM;
                break;
            case 3:
                category = Category.BEDROOM;
                break;
            default: // KITCHEN
                break;
        }
        ProductEntity product = new ProductEntity(name, amount, category);
        if (quantity != 0)
            product.setQuantity(quantity);
        return ProductDaoImpl.INSTANCE.updateProduct(product);
    }

    @WebMethod
    public List<ProductEntity> search(String key) {
        return ProductDaoImpl.INSTANCE.search(key);
    }

    @WebMethod
    public boolean deleteProduct(int id) {
        return ProductDaoImpl.INSTANCE.deleteProduct(id);
    }

    @WebMethod
    public List<ProductEntity> getProductsByCategory(int categoryId) {
        Category category = Category.KITCHEN;
        // LIVINGROOM,BATHROOM,DININGROOM,BEDROOM,KITCHEN;
        switch (categoryId) {
            case 0:
                category = Category.LIVINGROOM;
                break;
            case 1:
                category = Category.BATHROOM;
                break;
            case 2:
                category = Category.DININGROOM;
                break;
            case 3:
                category = Category.BEDROOM;
                break;
            default: // KITCHEN
                break;
        }
        System.out.println("from service: " + category.name());
        return ProductDaoImpl.INSTANCE.getProductsByCategory(category);
    }

}
