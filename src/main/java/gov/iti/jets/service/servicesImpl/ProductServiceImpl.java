package gov.iti.jets.service.servicesImpl;

import java.util.List;
import java.util.Optional;

import gov.iti.jets.persistence.daosImpl.ProductDaoImpl;
import gov.iti.jets.persistence.entities.Category;
import gov.iti.jets.persistence.entities.ProductEntity;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public class ProductServiceImpl {

    @WebMethod
    public ProductEntity getProduct(@WebParam(name = "id")int id) {
        Optional<ProductEntity> product = ProductDaoImpl.INSTANCE.getProductById(id);
        if(product.isPresent())
            return product.get();
        throw new RuntimeException("Product is not found");
    }

    @WebMethod
    public List<ProductEntity> getProducts() {
        return ProductDaoImpl.INSTANCE.getProducts();
    }

    @WebMethod
    public boolean updateProduct(@WebParam(name = "name")String name,@WebParam(name = "amount") double amount,@WebParam(name = "categoryId") int categoryId,
    @WebParam(name = "quantity") int quantity,@WebParam(name = "id") int id) {
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
    public boolean createProduct(@WebParam(name = "name")String name,@WebParam(name = "amount") double amount,@WebParam(name = "categoryId") int categoryId,@WebParam(name = "quantity") int quantity) {
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
    public List<ProductEntity> search(@WebParam(name = "key")String key) {
        return ProductDaoImpl.INSTANCE.search(key);
    }

    @WebMethod
    public boolean deleteProduct(@WebParam(name = "id")int id) {
        return ProductDaoImpl.INSTANCE.deleteProduct(id);
    }

    @WebMethod
    public List<ProductEntity> getProductsByCategory(@WebParam(name = "categoryId")int categoryId) {
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
