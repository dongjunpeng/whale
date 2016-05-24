package com.buterfleoge.whale.biz.order.impl.create;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.order.impl.create.CreateOrderProcessor.CreateOrderContext;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.protocol.order.CreateOrderRequest;
import com.buterfleoge.whale.type.protocol.order.CreateOrderResponse;

/**
 *
 * @author xiezhenzong
 *
 */
public class CreateOrderHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CreateOrderHandler.class);

    @Autowired
    private List<CreateOrderProcessor> processors = new CopyOnWriteArrayList<CreateOrderProcessor>();

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    public void createOrder(Long accountid, CreateOrderRequest request, CreateOrderResponse response) throws Exception {
        OrderInfo orderInfo = null;
        try {
            orderInfo = orderInfoRepository.findByOrderidAndAccountid(request.getOrderid(), accountid);
        } catch (Exception e) {
            LOG.error("find orderInfo failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.BIZ_ERROR);
            return;
        }

        if (orderInfo == null) {
            response.setStatus(Status.BIZ_ERROR);
            return;
        }

        TravelGroup group = null;
        try {
            group = travelGroupRepository.findOne(orderInfo.getGroupid());
        } catch (Exception e) {
            LOG.error("find group failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.BIZ_ERROR);
            return;
        }

        if (group == null) {
            response.setStatus(Status.BIZ_ERROR);
            return;
        }

        CreateOrderContext context = new CreateOrderContext(orderInfo, group);
        for (CreateOrderProcessor createOrderProcessor : processors) {
            createOrderProcessor.doCreate(accountid, request, response, context);
            if (response.hasError()) {
                return;
            }
        }
    }

    public void addProcessor(CreateOrderProcessor processor) {
        processors.add(processor);
    }

}
