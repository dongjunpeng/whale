/**
 *
 */
package com.buterfleoge.whale.biz.order;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.Printer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.aspose.words.Document;
import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.Utils;
import com.buterfleoge.whale.biz.OrderBiz;
import com.buterfleoge.whale.biz.OrderCreateBiz;
import com.buterfleoge.whale.biz.TravelBiz;
import com.buterfleoge.whale.dao.CouponRepository;
import com.buterfleoge.whale.dao.DiscountRepository;
import com.buterfleoge.whale.dao.OrderDiscountRepository;
import com.buterfleoge.whale.dao.OrderHistoryRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.OrderTravellersRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.dao.TravelRouteRepository;
import com.buterfleoge.whale.type.CouponStatus;
import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.GroupStatus;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.OrderStatusCategory;
import com.buterfleoge.whale.type.entity.Coupon;
import com.buterfleoge.whale.type.entity.Discount;
import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.OrderHistory;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderTraveller;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.Error;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.CreateOrderRequest;
import com.buterfleoge.whale.type.protocol.order.NewOrderRequest;
import com.buterfleoge.whale.type.protocol.order.NewOrderResponse;
import com.buterfleoge.whale.type.protocol.order.OrderRequest;
import com.buterfleoge.whale.type.protocol.order.PreviewContractRequest;

/**
 * @author xiezhenzong
 *
 */
@Service("orderCreateBiz")
public class OrderCreateBizImpl implements OrderCreateBiz {

    private static final Logger LOG = LoggerFactory.getLogger(OrderCreateBizImpl.class);

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderTravellersRepository orderTravellersRepository;

    @Autowired
    private OrderDiscountRepository orderDiscountRepository;

    @Autowired
    private TravelRouteRepository travelRouteRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private OrderBiz orderBiz;

    @Autowired
    private TravelBiz travelBiz;

    @Value("${order.contractTemplatePath}")
    private String contractTemplatePath;

    @Value("${order.contractPath}")
    private String contractPath;

    @Value("${order.preview.contractTemplatePath}")
    private String previewContractTemplatePath;

    @Value("${order.preview.contractPath}")
    private String privewContractPath;

    private static final Set<String> NOT_ALLOWED_WORD = new HashSet<String>(10);

    static {
        NOT_ALLOWED_WORD.add("Evaluation");
        NOT_ALLOWED_WORD.add("Only.");
        NOT_ALLOWED_WORD.add("Created");
        NOT_ALLOWED_WORD.add("with");
        NOT_ALLOWED_WORD.add("Aspose.Words.");
        NOT_ALLOWED_WORD.add("Copyright");
        NOT_ALLOWED_WORD.add("2003-2016");
        NOT_ALLOWED_WORD.add("Aspose");
        NOT_ALLOWED_WORD.add("Pty");
        NOT_ALLOWED_WORD.add("Ltd.");
    }

    @Override
    public void newOrder(Long accountid, NewOrderRequest request, NewOrderResponse response) throws Exception {
        try {
            OrderInfo orderInfo = orderInfoRepository.findByAccountidAndRouteidAndGroupidAndStatusIn(accountid,
                    request.getRouteid(), request.getGroupid(), OrderStatusCategory.NO_ALLOW_NEW.getOrderStatuses());
            if (orderInfo != null) {
                orderInfo = orderBiz.changeOrderInfoStatusIfTimeout(orderInfo);
                if (orderInfo.getStatus() != OrderStatus.TIMEOUT.value) {
                    response.setOrderid(orderInfo.getOrderid());
                    return;
                }
            }
        } catch (Exception e) {
            LOG.error("find order info failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return;
        }
        if (!travelBiz.isGroupAvailable(request.getGroupid(), request, response)) {
            response.setStatus(Status.BIZ_ERROR);
            response.addError(new Error("本团已经不可报名，您可直接致电走之旅行进行咨询。"));
            return;
        }
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAccountid(accountid);
        orderInfo.setRouteid(request.getRouteid());
        orderInfo.setGroupid(request.getGroupid());
        orderInfo.setStatus(OrderStatus.NEW.value);
        orderInfo.setIsAgreed(Boolean.FALSE);
        orderInfo.setAddTime(new Date());
        orderInfo.setModTime(orderInfo.getAddTime());
        try {
            orderInfo = orderInfoRepository.save(orderInfo);
            response.setOrderid(orderInfo.getOrderid());
        } catch (Exception e) {
            LOG.error("new order info failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createOrder(Long accountid, CreateOrderRequest request, Response response) throws Exception {
        List<OrderTraveller> travellers = request.getTravellers();
        for (OrderTraveller traveller : travellers) {
            traveller.setOrderid(request.getOrderid());
        }
        Date now = new Date();
        int count = travellers.size();
        int studentCount = request.getStudentCount();
        Long orderid = request.getOrderid();
        OrderInfo orderInfo = orderInfoRepository.findByOrderidAndAccountid(orderid, accountid);
        TravelGroup group = travelGroupRepository.findOne(orderInfo.getGroupid());

        orderInfo.setStatus(OrderStatus.WAITING.value);
        orderInfo.setCount(count);
        orderInfo.setStudentCount(studentCount);
        orderInfo.setPrice(group.getPrice().multiply(BigDecimal.valueOf(count)));
        orderInfo.setActualPrice(request.getActualPrice());
        orderInfo.setIsAgreed(Boolean.TRUE);
        orderInfo.setEmergencyContact(request.getEmergencyContact());
        orderInfo.setEmergencyMobile(request.getEmergencyMobile());
        orderInfo.setRoommate(request.getRoommate());
        orderInfo.setCreateTime(now);
        orderInfo.setModTime(now);

        group.setActualCount(group.getActualCount() + count);
        if (group.getActualCount().equals(group.getMaxCount())) {
            group.setStatus(GroupStatus.FULL.value);
        }

        Coupon coupon = null;
        if (request.getCouponid() != null) {
            coupon = couponRepository.findByCouponidAndAccountid(request.getCouponid(), accountid);
        }
        OrderDiscount policyOrderDiscount = createPolicyOrderDiscount(orderid, request.getPolicyDiscountid(), now);
        OrderDiscount studentOrderDiscount = createStudentOrderDiscount(orderid, request.getStudentDiscountid(),
                studentCount, now);
        OrderDiscount codeOrderDiscount = createCouponOrderDiscount(orderid, coupon, now);
        List<OrderDiscount> discountList = getOrderDiscountList(policyOrderDiscount, studentOrderDiscount,
                codeOrderDiscount);

        try {
            orderInfoRepository.save(orderInfo);
            travelGroupRepository.save(group);
            orderTravellersRepository.save(travellers);
            if (!discountList.isEmpty()) {
                orderDiscountRepository.save(discountList);
            }
            if (coupon != null) {
                coupon.setStatus(CouponStatus.USED.value);
                coupon.setModTime(now);
                couponRepository.save(coupon);
            }
            orderHistoryRepository.save(OrderHistory.newInstance(orderid, orderInfo.getStatus(), "用户新建订单"));
        } catch (Exception e) {
            LOG.error("save order info failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            throw e;
        }
    }

    @Override
    public String getContract(Long accountid, OrderRequest request, Response response) throws Exception {
        OrderInfo orderInfo = orderInfoRepository.findByOrderidAndAccountid(request.getOrderid(), accountid);
        if (orderInfo == null) {
            throw new Exception("Can't find this order, orderid: " + request.getOrderid());
        }
        List<OrderTraveller> orderTravellers = orderTravellersRepository.findByOrderid(orderInfo.getOrderid());
        if (CollectionUtils.isEmpty(orderTravellers)) {
            throw new Exception("empty order travellers, orderid: " + request.getOrderid());
        }
        String travellers = Utils.join(orderTravellers, ",", new Printer<OrderTraveller>() {
            @Override
            public String print(OrderTraveller object, Locale locale) {
                return object.getName();
            }
        });
        return createContract(contractPath, contractTemplatePath, orderInfo, travellers, orderInfo.getCount(), orderInfo.getPrice(),
                orderInfo.getActualPrice());
    }

    @Override
    public String previewContract(Long accountid, PreviewContractRequest request, Response response) throws Exception {
        OrderInfo orderInfo = orderInfoRepository.findByOrderidAndAccountid(request.getOrderid(), accountid);
        if (orderInfo == null) {
            throw new Exception("Can't find this order, orderid: " + request.getOrderid());
        }
        return createContract(privewContractPath, previewContractTemplatePath, orderInfo, request.getTravellers(), request.getCount(),
                request.getPrice(), request.getActualPrice());
    }

    private OrderDiscount createPolicyOrderDiscount(Long orderid, Long discountPolicyid, Date addTime) {
        if (discountPolicyid == null || discountPolicyid <= 0) {
            return null;
        }
        OrderDiscount policyOrderDiscount = null;
        Discount policyDiscount = discountRepository.findOne(discountPolicyid);
        if (policyDiscount != null) {
            policyOrderDiscount = new OrderDiscount();
            policyOrderDiscount.setOrderid(orderid);
            policyOrderDiscount.setDiscountid(policyDiscount.getDiscountid());
            policyOrderDiscount.setType(policyDiscount.getType());
            policyOrderDiscount.setValue(policyDiscount.getValue());
            policyOrderDiscount.setName(policyDiscount.getName());
            policyOrderDiscount.setAddTime(addTime);
        }
        return policyOrderDiscount;
    }

    private OrderDiscount createStudentOrderDiscount(Long orderid, Long sutdentDiscountid, int studentCount, Date addTime) {
        if (sutdentDiscountid == null || studentCount <= 0) {
            return null;
        }
        OrderDiscount studentOrderDiscount = null;
        Discount studentDiscount = discountRepository.findOne(sutdentDiscountid);
        if (studentDiscount != null) {
            studentOrderDiscount = new OrderDiscount();
            studentOrderDiscount.setOrderid(orderid);
            studentOrderDiscount.setDiscountid(studentDiscount.getDiscountid());
            studentOrderDiscount.setType(DiscountType.STUDENT.value);
            studentOrderDiscount.setValue(studentDiscount.getValue().multiply(BigDecimal.valueOf(studentCount)));
            studentOrderDiscount.setName(studentDiscount.getName());
            studentOrderDiscount.setAddTime(addTime);
        }
        return studentOrderDiscount;
    }

    private OrderDiscount createCouponOrderDiscount(Long orderid, Coupon coupon, Date addTime) {
        OrderDiscount orderDiscount = null;
        if (coupon != null) {
            orderDiscount = new OrderDiscount();
            orderDiscount.setOrderid(orderid);
            orderDiscount.setCouponid(coupon.getCouponid());
            orderDiscount.setName(coupon.getName());
            orderDiscount.setType(DiscountType.COUPON.value);
            orderDiscount.setValue(coupon.getValue());
            orderDiscount.setAddTime(addTime);
        }
        return orderDiscount;
    }

    private List<OrderDiscount> getOrderDiscountList(OrderDiscount policyOrderDiscount,
            OrderDiscount studentOrderDiscount, OrderDiscount codeOrderDiscount) {
        List<OrderDiscount> discounts = new ArrayList<OrderDiscount>(3);
        if (policyOrderDiscount != null) {
            discounts.add(policyOrderDiscount);
        }
        if (studentOrderDiscount != null) {
            discounts.add(studentOrderDiscount);
        }
        if (codeOrderDiscount != null) {
            discounts.add(codeOrderDiscount);
        }
        return discounts;
    }

    private String createContract(String contactPath, String templateContactPath, OrderInfo orderInfo, String travellers, Integer count,
            BigDecimal price, BigDecimal actualPrice) throws Exception {
        TravelRoute route = travelRouteRepository.findByRouteidAndVisibleTrue(orderInfo.getRouteid());
        TravelGroup group = travelGroupRepository.findOne(orderInfo.getGroupid());

        Map<String, String> mappings = createContractDataMapping(travellers, count, price, actualPrice, route, group);
        String docPath = createContractPath(contactPath, orderInfo, ".docx");
        String pdfPath = createContractPath(contactPath, orderInfo, ".pdf");

        createContractDocxFile(templateContactPath, mappings, docPath);
        new Document(docPath).save(pdfPath); // convert docx to pdf
        removeWaterMark(pdfPath); // docx转pdf的工具是免费版，会被加上水印，用pdfbox去掉
        return pdfPath;
    }

    private Map<String, String> createContractDataMapping(String travellers, Integer count, BigDecimal price, BigDecimal actualPrice,
            TravelRoute route, TravelGroup group) throws Exception {
        HashMap<String, String> contractDataMapping = new HashMap<String, String>();
        contractDataMapping.put("${travellers}", travellers);
        contractDataMapping.put("${groupName}", Utils.getProductName(route, group));
        contractDataMapping.put("${startDate}", DateFormatUtils.format(group.getStartDate(), Pattern.DATE));
        contractDataMapping.put("${endDate}", DateFormatUtils.format(group.getEndDate(), Pattern.DATE));
        contractDataMapping.put("${departure}", route.getDeparture());
        contractDataMapping.put("${distination}", route.getDistination());
        contractDataMapping.put("${route}", route.getRoute());
        contractDataMapping.put("${price}", Utils.formatPrice(group.getPrice()));
        contractDataMapping.put("${count}", String.valueOf(count));
        contractDataMapping.put("${totalPrice}", Utils.formatPrice(price));
        contractDataMapping.put("${actualPrice}", Utils.formatPrice(actualPrice));
        contractDataMapping.put("${currenttime}", DateFormatUtils.format(System.currentTimeMillis(), Pattern.DATE));
        return contractDataMapping;
    }

    private String createContractPath(String contractPath, OrderInfo orderInfo, String suffix) {
        String path = new StringBuilder(contractPath).append("contract_").append(orderInfo.getOrderid()).append(suffix)
                .toString();
        try {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            LOG.error("clear path: " + path + " failed.", e);
        }
        return path;
    }

    private void createContractDocxFile(String templatePath, Map<String, String> mappings, String docPath)
            throws IOException, InvalidFormatException, FileNotFoundException {
        XWPFDocument doc = new XWPFDocument(OPCPackage.open(templatePath));
        for (XWPFParagraph p : doc.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null) {
                        for (String key : mappings.keySet()) {
                            if (text.contains(key)) {
                                text = text.replace(key, mappings.get(key));
                                r.setText(text, 0);
                            }
                        }
                    }
                }
            }
        }
        doc.write(new FileOutputStream(docPath));
    }

    private void removeWaterMark(String pdfPath) throws IOException {
        PDDocument document = PDDocument.load(new File(pdfPath));
        PDPage page = document.getPages().get(0);
        PDFStreamParser parser = new PDFStreamParser(page);
        parser.parse();
        List<Object> tokens = parser.getTokens();
        List<Object> newTokens = new ArrayList<Object>();
        for (Object token : tokens) {
            if (token instanceof Operator) {
                Operator op = (Operator) token;
                if (op.getName().equals("TJ") || op.getName().equals("Tj") || op.getName().equals("'")) {
                    COSString string = (COSString) newTokens.get(newTokens.size() - 1);
                    if (NOT_ALLOWED_WORD.contains(string.getString())) {
                        newTokens.remove(newTokens.size() - 1);
                        continue;
                    }
                }
            }
            newTokens.add(token);
        }
        PDStream newContents = new PDStream(document);
        OutputStream out = newContents.createOutputStream(COSName.FLATE_DECODE);
        ContentStreamWriter writer = new ContentStreamWriter(out);
        writer.writeTokens(newTokens);
        out.close();
        page.setContents(newContents);
        document.save(pdfPath);
        document.close();
    }

}
