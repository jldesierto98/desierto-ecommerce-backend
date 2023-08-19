package com.desierto.ecommerce.product.entity;

import com.desierto.ecommerce.product.response.ProductResponse;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@NamedNativeQuery(name = "Product.findProductByCategory_Named",
        query = "SELECT p.id, p.image_url as imageUrl, p.name as name, p.unit_price as unitPrice, p.description FROM product p WHERE p.category_id = :id",
        resultSetMapping = "Mapping.ProductResponse")

@NamedNativeQuery(name = "Product.findProductByKeyword_Named",
        query = "SELECT p.id, p.image_url as imageUrl, p.name as name, p.unit_price as unitPrice, p.description as description FROM product p " +
                "WHERE p.name " +
                "LIKE CONCAT('%', :keyword, '%')",
        resultSetMapping = "Mapping.ProductResponse")

@NamedNativeQuery(name = "Product.findByProductId_Named",
        query = "SELECT p.id, p.image_url as imageUrl, p.name as name, p.unit_price as unitPrice, p.description FROM product p WHERE p.id = :id",
        resultSetMapping = "Mapping.ProductResponse")


@SqlResultSetMapping(name = "Mapping.ProductResponse",
        classes = @ConstructorResult(targetClass = ProductResponse.class,
                columns = {
                        @ColumnResult(name="id", type = Long.class),
                        @ColumnResult(name = "imageUrl"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "unitPrice"),
                        @ColumnResult(name = "description")
                }))


@Entity
@Table(name="product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;

    @Column(name = "sku")
    private String sku;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "active")
    private boolean active;

    @Column(name = "units_in_stock")
    private int unitsInStock;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;
}