/**
 *
 */
package com.buterfleoge.whale.biz.order.impl;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aspose.words.Document;
import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.Utils;
import com.buterfleoge.whale.biz.order.CreateOrderBiz;
import com.buterfleoge.whale.biz.order.OrderBiz;
import com.buterfleoge.whale.biz.travel.TravelBiz;
import com.buterfleoge.whale.dao.DiscountCodeRepository;
import com.buterfleoge.whale.dao.DiscountRepository;
import com.buterfleoge.whale.dao.OrderDiscountRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.OrderTravellersRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.dao.TravelRouteRepository;
import com.buterfleoge.whale.type.DiscountCodeStatus;
import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.GroupStatus;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.OrderStatusCategory;
import com.buterfleoge.whale.type.entity.Discount;
import com.buterfleoge.whale.type.entity.DiscountCode;
import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.Error;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.CreateOrderRequest;
import com.buterfleoge.whale.type.protocol.order.GetContractRequest;
import com.buterfleoge.whale.type.protocol.order.NewOrderRequest;
import com.buterfleoge.whale.type.protocol.order.NewOrderResponse;

/**
 * @author xiezhenzong
 *
 */
@Service("createOrderBiz")
public class CreateOrderBizImpl implements CreateOrderBiz {

    private static final Logger LOG = LoggerFactory.getLogger(CreateOrderBizImpl.class);

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
    private DiscountCodeRepository discountCodeRepository;

    @Autowired
    private OrderBiz orderBiz;

    @Autowired
    private TravelBiz travelBiz;

    @Value("${order.contractTemplatePath}")
    private String contractTemplatePath;

    @Value("${order.contractPath}")
    private String contractPath;

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
            response.addError(new Error("本团人数已经满，您可直接致电海逍遥进行咨询。"));
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
        List<OrderTravellers> travellers = request.getTravellers();
        for (OrderTravellers traveller : travellers) {
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
        orderInfo.setCreateTime(now);
        orderInfo.setModTime(now);

        group.setActualCount(group.getActualCount() + count);
        if (group.getActualCount().equals(group.getMaxCount())) {
            group.setStatus(GroupStatus.FULL.value);
        }

        DiscountCode discountCode = discountCodeRepository.findByDiscountCode(request.getDiscountCode());
        OrderDiscount policyOrderDiscount = createPolicyOrderDiscount(orderid, request.getPolicyDiscountid(), now);
        OrderDiscount studentOrderDiscount = createStudentOrderDiscount(orderid, request.getStudentDiscountid(),
                studentCount, now);
        OrderDiscount codeOrderDiscount = createCodeOrderDiscount(orderid, request.getDiscountCode(), now);
        List<OrderDiscount> discountList = getOrderDiscountList(policyOrderDiscount, studentOrderDiscount,
                codeOrderDiscount);

        try {
            orderInfoRepository.save(orderInfo);
            travelGroupRepository.save(group);
            orderTravellersRepository.save(travellers);
            if (!discountList.isEmpty()) {
                orderDiscountRepository.save(discountList);
            }
            if (discountCode != null) {
                discountCode.setStatus(DiscountCodeStatus.OCCUPIED.value);
                discountCodeRepository.save(discountCode);
            }
        } catch (Exception e) {
            LOG.error("save order info failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            throw e;
        }
    }

    @Override
    public String createContract(Long accountid, GetContractRequest request, Response response) throws Exception {
        OrderInfo orderInfo = orderInfoRepository.findByOrderidAndAccountid(request.getOrderid(), accountid);
        if (orderInfo == null) {
            throw new Exception("Can't find this order, orderid: " + request.getOrderid());
        }
        TravelRoute route = travelRouteRepository.findByRouteidAndVisibleTrue(orderInfo.getRouteid());
        TravelGroup group = travelGroupRepository.findOne(orderInfo.getGroupid());

        Map<String, String> mappings = createContractDataMapping(request, orderInfo, route, group);
        String docPath = createContractPath(orderInfo, ".docx");
        String pdfPath = createContractPath(orderInfo, ".pdf");

        createContractDocxFile(mappings, docPath);
        new Document(docPath).save(pdfPath); // convert docx to pdf
        removeWaterMark(pdfPath); // docx转pdf的工具是免费版，会被加上水印，用pdfbox去掉

        return pdfPath;
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
            policyOrderDiscount.setDesc(policyDiscount.getDesc());
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
            studentOrderDiscount.setDesc(studentDiscount.getDesc());
            studentOrderDiscount.setAddTime(addTime);
        }
        return studentOrderDiscount;
    }

    private OrderDiscount createCodeOrderDiscount(Long orderid, String code, Date addTime) {
        OrderDiscount codeOrderDiscount = null;
        DiscountCode discountCode = discountCodeRepository.findByDiscountCode(code);
        if (discountCode != null) {
            codeOrderDiscount = new OrderDiscount();
            codeOrderDiscount.setOrderid(orderid);
            codeOrderDiscount.setDiscountCode(discountCode.getDiscountCode());
            codeOrderDiscount.setType(DiscountType.COUPON.value);
            codeOrderDiscount.setValue(discountCode.getValue());
            codeOrderDiscount.setAddTime(addTime);
        }
        return codeOrderDiscount;
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

    private Map<String, String> createContractDataMapping(GetContractRequest request, OrderInfo orderInfo,
            TravelRoute route, TravelGroup group) throws Exception {
        HashMap<String, String> contractDataMapping = new HashMap<String, String>();
        contractDataMapping.put("${travellers}", request.getTravellers());
        contractDataMapping.put("${groupName}", Utils.getProductName(route, group));
        contractDataMapping.put("${startDate}", DateFormatUtils.format(group.getStartDate(), Pattern.DATE));
        contractDataMapping.put("${endDate}", DateFormatUtils.format(group.getEndDate(), Pattern.DATE));
        contractDataMapping.put("${departure}", route.getDeparture());
        contractDataMapping.put("${distination}", route.getDistination());
        contractDataMapping.put("${route}", route.getRoute());
        contractDataMapping.put("${price}", Utils.formatPrice(group.getPrice()));
        contractDataMapping.put("${count}", String.valueOf(request.getCount()));
        contractDataMapping.put("${totalPrice}", Utils.formatPrice(request.getPrice()));
        contractDataMapping.put("${actualPrice}", Utils.formatPrice(request.getActualPrice()));
        contractDataMapping.put("${currenttime}",
                DateFormatUtils.format(System.currentTimeMillis(), Pattern.DATE_TIME));
        return contractDataMapping;
    }

    private String createContractPath(OrderInfo orderInfo, String suffix) {
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

    private void createContractDocxFile(Map<String, String> mappings, String docPath)
            throws IOException, InvalidFormatException, FileNotFoundException {
        XWPFDocument doc = new XWPFDocument(OPCPackage.open(contractTemplatePath));
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
    }

}
