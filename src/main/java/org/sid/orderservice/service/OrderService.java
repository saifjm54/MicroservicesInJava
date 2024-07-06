package org.sid.orderservice.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import jakarta.transaction.Transactional;
import org.sid.orderservice.aggregates.Order;
import org.sid.orderservice.command.CreateOrderCommand;
import org.sid.orderservice.command.OrderCommand;
import org.sid.orderservice.dto.OrderDetails;
import org.sid.orderservice.saga.CreateOrderSagaState;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;

public class OrderService {

    private AggregateRepository<Order, OrderCommand> orderRepository;

    @Autowired
    private SagaManager<CreateOrderSagaState> createOrderSagaManager;


    public OrderService(AggregateRepository<Order, OrderCommand> orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public EntityWithIdAndVersion<Order> createOrder(OrderDetails orderDetails) throws ExecutionException, InterruptedException {
        EntityWithIdAndVersion<Order> order = orderRepository.save(new CreateOrderCommand(orderDetails)).get();

        CreateOrderSagaState data = new CreateOrderSagaState(order.getAggregate().getId(), orderDetails);

        createOrderSagaManager.create(data,Order.class,order.getEntityId());

        return order;
    }

}
