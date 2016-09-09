package com.buterfleoge.whale;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.aspose.words.Document;

public class Test {

    private static final Set<String> unAllowedWord = new HashSet<String>();

    static {
        unAllowedWord.add("Evaluation");
        unAllowedWord.add("Only.");
        unAllowedWord.add("Created");
        unAllowedWord.add("with");
        unAllowedWord.add("Aspose.Words.");
        unAllowedWord.add("Copyright");
        unAllowedWord.add("2003-2016");
        unAllowedWord.add("Aspose");
        unAllowedWord.add("Pty");
        unAllowedWord.add("Ltd.");
    }

    public static void main(String[] args) throws Exception {
        String inputfilepath = "/Users/xiezhenzong/Downloads/travel_contract.docx";
        String outputfilepath = "/Users/xiezhenzong/Downloads/test.docx";
        String outputfilepathPdf = "/Users/xiezhenzong/Downloads/test.pdf";

        HashMap<String, String> mappings = new HashMap<String, String>();
        mappings.put("${travellers}", "xiezhenzong,蓬蓬,蓬蓬1，蓬蓬a");
        mappings.put("${groupName}", "大西北1队");
        mappings.put("${startDate}", "2016-09-08");
        mappings.put("${endDate}", "2016-09-09");
        mappings.put("${departure}", "北京");
        mappings.put("${distination}", "银川");
        mappings.put("${route}", "兰州-祁连草原-卓尔山-祁连县-七彩丹霞-张掖-嘉峪关-莫高窟-鸣沙山月牙泉-敦煌-茶卡盐湖-青海湖-鸟岛-贵德-坎布拉-兰州");
        mappings.put("${price}", "¥100");
        mappings.put("${count}", "4");
        mappings.put("${totalPrice}", "¥400");
        mappings.put("${actualPrice}", "¥350");
        mappings.put("${currenttime}", "2016-09-09 23:23:23");

        XWPFDocument doc = new XWPFDocument(OPCPackage.open(inputfilepath));
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

        doc.write(new FileOutputStream(outputfilepath));
        new Document(outputfilepath).save(outputfilepathPdf);

        PDDocument document = PDDocument.load(new File(outputfilepathPdf));
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
                    if (unAllowedWord.contains(string.getString())) {
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
        document.save(outputfilepathPdf);
    }

    public static void convertWordToPdf(String src, String desc) throws Exception {

        // try {
        // FileInputStream fs = new FileInputStream(src);
        // XWPFDocument doc = new XWPFDocument(fs);
        // Document pdfdoc = new Document(PageSize.A4, 72, 72, 72, 72);
        // PdfWriter pwriter = PdfWriter.getInstance(pdfdoc, new
        // FileOutputStream(desc));
        // pwriter.setInitialLeading(20);
        // List<XWPFParagraph> plist = doc.getParagraphs();
        // pdfdoc.open();
        // for (int i = 0, n = plist.size(); i < n; i++) {
        // XWPFParagraph pa = plist.get(i);
        // List<XWPFRun> runs = pa.getRuns();
        // for (int j = 0, m = runs.size(); j < m; j++) {
        // XWPFRun run = runs.get(j);
        //
        // int color = getCode(run.getColor());
        // String text = run.getText(-1);
        // if (text != null) {
        // FontSelector selector = new FontSelector();
        // selector.addFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 12));
        // Font font = FontFactory.getFont("STSongStd-Light", "UniGB-UCS2-H",
        // BaseFont.NOT_EMBEDDED);
        // font.setSize(run.getFontSize());
        // // font.setColor(new BaseColor(color));
        //
        // if (run.isBold() && run.isItalic())
        // font.setStyle(Font.BOLDITALIC);
        // else if (run.isBold())
        // font.setStyle(Font.BOLD);
        // else if (run.isItalic())
        // font.setStyle(Font.ITALIC);
        // else if (run.isStrike())
        // font.setStyle(Font.STRIKETHRU);
        // else
        // font.setStyle(Font.NORMAL);
        //
        // selector.addFont(font);
        // Phrase ph = selector.process(text);
        // pdfdoc.add(ph);
        // }
        //
        // List<XWPFPicture> piclist = run.getEmbeddedPictures();
        // Iterator<XWPFPicture> iterator = piclist.iterator();
        // while (iterator.hasNext()) {
        // XWPFPicture pic = iterator.next();
        // Image imag = Image.getInstance(pic.getPictureData().getData());
        // imag.setAbsolutePosition(100.0F, 400.0F);
        // pdfdoc.add(imag);
        // }
        //
        //
        // }
        // // output new line
        // pdfdoc.add(new Chunk(Chunk.NEWLINE));
        // }
        // // close pdf document
        // pdfdoc.close();
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
    }

    // public static int getCode(String code) {
    // int colorCode;
    // if (code != null)
    // colorCode = Long.decode("0x" + code).intValue();
    // else
    // colorCode = Long.decode("0x000000").intValue();
    // return colorCode;
    // }

}
