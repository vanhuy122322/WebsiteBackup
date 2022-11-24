package fa.training.spring.service.productservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.spring.Util.CustomMongoQuery;
import fa.training.spring.Util.RenderSlug;
import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.productdto.CategoryDTO;
import fa.training.spring.dto.productdto.ProductDTO;
import fa.training.spring.dto.shopdto.ShopDTO;
import fa.training.spring.entity.productentity.Product;
import fa.training.spring.mapper.productmapper.CategoryMapper;
import fa.training.spring.mapper.productmapper.ProductMapper;
import fa.training.spring.repository.productrepository.CategoryRepository;
import fa.training.spring.repository.productrepository.ProductRepository;
import fa.training.spring.service.productservice.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomMongoQuery<Product> customRepository;
    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<ProductDTO> getAll() {
        return productMapper.mapToListDTO(productRepository.findAll(), ProductDTO.class);
    }

    @Override
    public ProductDTO getOneByName(RequestDTO<ProductDTO> requestDTO) {
        return productMapper.mapToDTO(productRepository.findByName(requestDTO.getData().getName()), ProductDTO.class);
    }

    @Override
    public ProductDTO getOneById(RequestDTO<ProductDTO> requestDTO) {
        return productMapper.mapToDTO(productRepository.findById(requestDTO.getId()).orElse(null),
                ProductDTO.class);
    }

    @Override
    public ProductDTO add(RequestDTO<ProductDTO> requestDTO) {
        Product product = productMapper.mapToEntity(Product.class, requestDTO.getData());
        product.setSlug(RenderSlug.renderSlug(product.getName()));
        return productMapper.mapToDTO(productRepository.save(product), ProductDTO.class);
    }

    @Override
    public ProductDTO update(RequestDTO<ProductDTO> requestDTO) {
        return productMapper.mapToDTO(
                productRepository.updateProduct(productMapper.mapToEntity(Product.class, requestDTO.getData())),
                ProductDTO.class);
    }

    @Override
    public boolean delete(RequestDTO<ProductDTO> requestDTO) {
        Product product = productRepository.findById(requestDTO.getId()).orElse(null);
        if (product != null) {
            customRepository.delete(requestDTO.getId(), Product.class);
        }

        if (productRepository.findById(requestDTO.getId()).get().isDeleteStatus()) {
            return true;
        }
        return false;
    }

    @Override
    public ProductDTO getOneBySlug(RequestDTO<ProductDTO> requestDTO) {
        return productMapper.mapToDTO(
                productRepository.findProductBySlug(requestDTO.getData().getSlug()),
                ProductDTO.class);
    }

    @Override
    public List<ProductDTO> search(RequestDTO<ProductDTO> requestDTO) {
        if (requestDTO.getData().getName() == null) {
            requestDTO.getData().setName("");
        } else {

        }
        if (requestDTO.getData().getCategory() == null) {
            requestDTO.getData().setCategory("");
        } else {

        }
        if (requestDTO.getData().getSubCategory() == null) {
            requestDTO.getData().setSubCategory("");
        } else {

        }
        List<Product> products = productRepository
                .findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCaseAndSubCategoryContainingIgnoreCase(
                        requestDTO.getData().getName(),
                        requestDTO.getData().getCategory(),
                        requestDTO.getData().getSubCategory());
        return productMapper.mapToListDTO(products, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getProductBySubCategory(RequestDTO<ProductDTO> requestDTO) {
        List<Product> products = productRepository.findByCategoryOrSubCategory(requestDTO.getData().getCategory(),
                requestDTO.getData().getSubCategory());
        return productMapper.mapToListDTO(products, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getProductByProductDetail(RequestDTO<ProductDTO> requestDTO) {
        List<Product> products = new ArrayList<>();
        List<Product> products1 = new ArrayList<>();
        List<Product> products2 = productRepository.findBySubCategory(requestDTO.getInput());
        for (Product product : products2) {
            if (requestDTO.getFrom() == "" && requestDTO.getTo() == "" && requestDTO.getListOrigin() == null
                    && requestDTO.getListMaterial() == null && requestDTO.getListSize() == null
                    && requestDTO.getListColor() == null) {
                products = products2;
            }
            if (requestDTO.getFrom() == "" && requestDTO.getTo() == "") {
                if (requestDTO.getListColor() != null) {
                    for (String string : requestDTO.getListColor()) {
                        if (product.getProductDetail().getColor().contains(string)) {
                            products.add(product);
                        }
                    }
                }
                if (requestDTO.getListOrigin() != null) {
                    for (String string : requestDTO.getListOrigin()) {
                        if (product.getProductDetail().getOrigin().contains(string)) {
                            products.add(product);
                        }
                    }
                }
                if (requestDTO.getListSize() != null) {
                    for (String string : requestDTO.getListSize()) {
                        if (product.getProductDetail().getSize().contains(string)) {
                            products.add(product);
                        }
                    }
                }
                if (requestDTO.getListMaterial() != null) {
                    for (String string : requestDTO.getListMaterial()) {
                        if (product.getProductDetail().getMaterial().contains(string)) {
                            products.add(product);
                        }
                    }
                }
            }
            if (requestDTO.getFrom() != "" && requestDTO.getTo() != "") {
                if (product.getPrice() >= Double.valueOf(requestDTO.getFrom())
                        && product.getPrice() <= Double.valueOf(requestDTO.getTo())) {
                    products1.add(product);
                }
                for (Product product1 : products1) {
                    if (requestDTO.getListColor() != null) {
                        for (String string : requestDTO.getListColor()) {
                            if (product.getProductDetail().getColor().contains(string)) {
                                products.add(product);
                            }
                        }
                    }
                    if (requestDTO.getListOrigin() != null) {
                        for (String string : requestDTO.getListOrigin()) {
                            if (product.getProductDetail().getOrigin().contains(string)) {
                                products.add(product);
                            }
                        }
                    }
                    if (requestDTO.getListSize() != null) {
                        for (String string : requestDTO.getListSize()) {
                            if (product.getProductDetail().getSize().contains(string)) {
                                products.add(product);
                            }
                        }
                    }
                    if (requestDTO.getListMaterial() != null) {
                        for (String string : requestDTO.getListMaterial()) {
                            if (product.getProductDetail().getMaterial().contains(string)) {
                                products.add(product);
                            }
                        }
                    }
                }
            }
            if (requestDTO.getFrom() != "" && requestDTO.getTo() == "") {
                if (product.getPrice() >= Double.valueOf(requestDTO.getFrom())) {
                    products.add(product);
                }
                for (Product product1 : products1) {
                    if (requestDTO.getListColor() != null) {
                        for (String string : requestDTO.getListColor()) {
                            if (product.getProductDetail().getColor().contains(string)) {
                                products.add(product);
                            }
                        }
                    }
                    if (requestDTO.getListOrigin() != null) {
                        for (String string : requestDTO.getListOrigin()) {
                            if (product.getProductDetail().getOrigin().contains(string)) {
                                products.add(product);
                            }
                        }
                    }
                    if (requestDTO.getListSize() != null) {
                        for (String string : requestDTO.getListSize()) {
                            if (product.getProductDetail().getSize().contains(string)) {
                                products.add(product);
                            }
                        }
                    }
                    if (requestDTO.getListMaterial() != null) {
                        for (String string : requestDTO.getListMaterial()) {
                            if (product.getProductDetail().getMaterial().contains(string)) {
                                products.add(product);
                            }
                        }
                    }
                }
            }
            if (requestDTO.getFrom() == "" && requestDTO.getTo() != "") {
                if (product.getPrice() <= Double.valueOf(requestDTO.getTo())) {
                    products.add(product);
                }
                for (Product product1 : products1) {
                    if (requestDTO.getListColor() != null) {
                        for (String string : requestDTO.getListColor()) {
                            if (product.getProductDetail().getColor().contains(string)) {
                                products.add(product);
                            }
                        }
                    }
                    if (requestDTO.getListOrigin() != null) {
                        for (String string : requestDTO.getListOrigin()) {
                            if (product.getProductDetail().getOrigin().contains(string)) {
                                products.add(product);
                            }
                        }
                    }
                    if (requestDTO.getListSize() != null) {
                        for (String string : requestDTO.getListSize()) {
                            if (product.getProductDetail().getSize().contains(string)) {
                                products.add(product);
                            }
                        }
                    }
                    if (requestDTO.getListMaterial() != null) {
                        for (String string : requestDTO.getListMaterial()) {
                            if (product.getProductDetail().getMaterial().contains(string)) {
                                products.add(product);
                            }
                        }
                    }
                }
            }
        }
        List<Product> listProduct = products.stream().distinct().collect(Collectors.toList());
        return productMapper.mapToListDTO(listProduct, ProductDTO.class);
    }

    @Override
    public CategoryDTO getSubCategoryBySubCategories(RequestDTO<ProductDTO> requestDTO) {
        CategoryDTO categoryDTO = categoryMapper.mapToDTO(
                categoryRepository.findBySubCategoriesNameLike(requestDTO.getData().getSubCategory()),
                CategoryDTO.class);
        return categoryDTO;
    }

    @Override
    public CategoryDTO getCategoryByCategory(RequestDTO<ProductDTO> requestDTO) {
        CategoryDTO categoryDTO = categoryMapper.mapToDTO(
                categoryRepository.findByName(requestDTO.getData().getCategory()),
                CategoryDTO.class);
        return categoryDTO;
    }
    
    @Override
    public List<ProductDTO> getProductByShopName(RequestDTO<ShopDTO> req) {
        return productMapper.mapToListDTO(
                productRepository.findByShopNameAndDeleteStatus(req.getData().getName(), false), ProductDTO.class);
    }

    @Override
    public boolean addList(RequestDTO<ProductDTO> requestDTO) {
        List<Product> products = productMapper.mapToListEntity(Product.class, requestDTO.getListData());
        products = productRepository.saveAll(products);
        return products != null ? true : false;
    }

}
