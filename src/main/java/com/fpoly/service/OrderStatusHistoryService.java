package com.fpoly.service;

import java.util.List;


import com.fpoly.model.Order;
import com.fpoly.model.OrderStatusHistory;

public interface OrderStatusHistoryService {
	void saveOrderStatusHistory(OrderStatusHistory orderStatusHistory);

	List<OrderStatusHistory> findByOrder(Order findById);
}
