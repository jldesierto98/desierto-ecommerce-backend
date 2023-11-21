package com.desierto.ecommerce.product.entity;

import com.desierto.ecommerce.product.response.OrderHistoryResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


//@NamedNativeQuery(name = "Orders.findOrderByCustomerEmail_Named",
//        query = "SELECT o.order_tracking_number as orderTrackingNumber, o.total_price as totalPrice, o.total_quantity as totalQuantity FROM orders o " +
//                "LEFT OUTER JOIN customer c " +
//                "ON o.customer_id = c.id " +
//                "WHERE c.email = :email",
//        resultSetMapping = "Mapping.OrderHistoryResponse")
//
//
//@SqlResultSetMapping(name = "Mapping.OrderHistoryResponse",
//        classes = @ConstructorResult(targetClass = OrderHistoryResponse.class,
//                columns = {
//                        @ColumnResult(name = "orderTrackingNumber", type = String.class),
//                        @ColumnResult(name = "totalPrice", type = BigDecimal.class),
//                        @ColumnResult(name = "totalQuantity", type = Integer.class),
//                }))



@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="order_tracking_number")
    private String orderTrackingNumber;

    @Column(name="total_quantity")
    private int totalQuantity;

    @Column(name="total_price")
    private BigDecimal totalPrice;

    @Column(name="status")
    private String status;

    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;

    public void add(OrderItem item) {

        if (item != null) {
            if (orderItems == null) {
                orderItems = new HashSet<>();
            }

            orderItems.add(item);
            item.setOrder(this);
        }
    }
}
