package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.exception.OrderException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.IOrderService;

@RunWith(MockitoJUnitRunner.class)
public class TestClass {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private IOrderService orderService;

    @Test
    public void placeOrder_ValidInput_OrderPlacedSuccessfully() throws Exception {
        // Arrange
        Customer customer = new Customer(1L, "John Doe");
        Product product = new Product(1L, "Product 1", "This is product 1", null);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(orderRepository).save(any(Order.class));

        // Act
        Order order = orderService.placeOrder(1L, 1L, 2);

        // Assert
        assertNotNull(order);
        assertEquals(1L, order.getCustomer().getCustomerId());
        assertEquals(1L, order.getProduct().getProductId().longValue());
        assertEquals(2, order.getQuantity());
        verify(customerRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test(expected = OrderException.class)
    public void placeOrder_CustomerNotFound_ThrowsOrderException() throws Exception {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        orderService.placeOrder(1L, 1L, 2);

        // Assert
        verify(customerRepository, times(1)).findById(1L);
        verify(productRepository, times(0)).findById(1L);
        verify(orderRepository, times(0)).save(any(Order.class));
    }

    @Test(excepted = OrderException.class)
    public void placeOrder_ProductNotFound_ThrowsOrderException() throws Exception {
        // Arrange
        Customer customer = new Customer(1L, "John Doe");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        orderService.placeOrder(1L, 1L, 2);

        // Assert
        verify(customerRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).findById(1L);
        verify(orderRepository, times(0)).save(any(Order.class));
    }
}

