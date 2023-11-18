package com.fpoly.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fpoly.model.Order;
import com.fpoly.model.OrderStatusHistory;
import com.fpoly.repository.OrderStatusHistoryRepository;
import com.fpoly.service.OrderStatusHistoryService;

@Service
public class OrderStatusHistoryServiceImpl implements OrderStatusHistoryService {

	@Autowired
	OrderStatusHistoryRepository orderStatusHistoryRepository;

	@Override
	public void saveOrderStatusHistory(OrderStatusHistory orderStatusHistory) {
		orderStatusHistoryRepository.save(orderStatusHistory);
	}

	@Override
	public List<OrderStatusHistory> findByOrder(Order id) {
		return orderStatusHistoryRepository.findByOrder(id, Sort.by(Sort.Order.desc("historyId")));
	}

}
