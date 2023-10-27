package ps.demo.quicktest;

import lombok.SneakyThrows;
import ps.demo.common.GzipUtil;
import ps.demo.common.MyReadWriteUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipString {

    public static void main(String[] args) {
        String longStr = "七六万人前往千人万人lakfalkfa;qw;krlqlwerkqw;krqwlrjqwlkrjlqwkrjq;wl" +
                "jr我立刻家里五口人去了人权为借口请问前往六日七日kqrlkqwhellowGod, ti's yes确认确认为" +
                "lkwerqlwk请问日期情况我今日前来看望了请问科瑞亲王客人去k21lk4lksdjflqk2l34124;1;234" +
                "q离开人群冷酷无情为了让强冷空气两人加起来快捷键去了解离开家维权绿卡qwkejrqwkrqw;qwrqwrqq;" +
                "乐蔷尔客人驱蚊器问问群里问了科瑞亲王去了人口旅客入境请问日期 埃里克人家我去看了人气奖请问乳胶漆" +
                "\uD83D\uDE0A\uD83D\uDE02\uD83D\uDC95\uD83D\uDE01\uD83D\uDC4D\uD83D\uDE0D\uD83D" +
                "\uDE4C\uD83D\uDE4C\uD83D\uDE01\uD83D\uDE01\uD83D\uDE18\uD83D\uDE0D❤️\uD83D" +
                "\uDE0D\uD83D\uDE18\uD83E\uDD37\u200D♂️\uD83E\uDD37\u200D♀️\uD83D\uDC74\uD83D" +
                "\uDC69\u200D\uD83E\uDDB0\uD83D\uDC69\u200D\uD83E\uDDB0\uD83D\uDC69\u200D\uD83E" +
                "\uDDB0\uD83E\uDDE7\uD83C\uDF8D\uD83C\uDF83\uD83C\uDF83\uD83C\uDF80\uD83C\uDF80" +
                "\uD83E\uDD50\uD83E\uDD57\uD83E\uDD6F\uD83E\uDD56\uD83E\uDD56\uD83E\uDD56\uD83E" +
                "\uDD56\uD83C\uDF54\uD83E\uDD5E\uD83E\uDED3\uD83C\uDF2F\uD83D\uDE96\uD83D\uDE96" +
                "\uD83D\uDE8E\uD83D\uDE8E\uD83D\uDE91\uD83D\uDC94qerqlqkrqwlkrjqw;lkrqwkrlqkrjwq" +
                "请两位人去楼空人lk3j2lksldl2345pfspokowjerlakrlwqkrq;lk34jq;wlkrjq;3k;q3k44k2wr";
        //longStr = MyReadWriteUtil.readFileContent(new File("C:\\Users\\Dell\\2023-10-18_210654-BigFile.txt"));
        String compressed = compress(longStr);

        System.out.println("original length="+longStr.length()+"\tcompressed length="+compressed.length());
        System.out.println("-->is equal="+longStr.equals(uncompress(compressed)));

        byte[] bytes = GzipUtil.compress(longStr.getBytes());
        byte[] bytes2 = GzipUtil.uncompress(bytes);
        System.out.println("-->is equal="+longStr.equals(new String(bytes2)));

        String compressed2 = compress2(longStr);

        System.out.println("original length="+longStr.length()+"\tcompressed2 length="+compressed2.length());
        System.out.println("-->is equal="+longStr.equals(uncompress2(compressed2)));


    }

    @SneakyThrows
    public static String compress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return new String(Base64.getEncoder().encode(GzipUtil.compress(str.getBytes(StandardCharsets.UTF_8))));
    }

    public static String uncompress(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }
        return new String(GzipUtil.uncompress(Base64.getDecoder().decode(compressedStr.getBytes(StandardCharsets.UTF_8))));
    }

    @SneakyThrows
    public static String compress2(String str) {
        return new String(GzipUtil.compress(str.getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1);
    }

    @SneakyThrows
    public static String uncompress2(String compressedStr) {
        return new String(GzipUtil.uncompress(compressedStr.getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1);
    }
}
