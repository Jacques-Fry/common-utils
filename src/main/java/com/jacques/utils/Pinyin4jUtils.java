package com.jacques.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.HashSet;
import java.util.Set;

/**
 * 汉字转拼音工具
 *
 * @author Jacques·Fry
 * @info 汉字转拼音工具
 * @date 2020/8/22 15:11
 */
public class Pinyin4jUtils {

    private static Pinyin4jUtils INSTANCE;

    private Pinyin4jUtils() {
        // 单例
    }

    public static Pinyin4jUtils getInstance() {
        // 双重锁
        if (INSTANCE == null) {
            synchronized (Pinyin4jUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Pinyin4jUtils();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * 获取汉字拼音首字母
     *
     * @param name 汉字
     * @param type 返回字母大小写
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String getFirstLetter(String name, HanyuPinyinCaseType type) throws BadHanyuPinyinOutputFormatCombination {
        if (type == null) {
            type = HanyuPinyinCaseType.LOWERCASE;
        }
        char[] charArray = name.toCharArray();
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 设置大小写格式
        defaultFormat.setCaseType(type);
        // 设置声调格式：
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charArray.length; i++) {
            //匹配中文,非中文转换会转换成null
            if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i], defaultFormat);
                if (hanyuPinyinStringArray != null) {
                    pinyin.append(hanyuPinyinStringArray[0].charAt(0));
                }
            }
        }
        return pinyin.toString();
    }

    /**
     * getFirstSpellPinYin:(多音字的时候获取第一个).
     *
     * @param src         传入的拼音字符串，以逗号隔开
     * @param isFullSpell 是否全拼，true:全拼，false:第一个汉字全拼(其它汉字取首字母)
     * @return 第一个拼音
     */
    public static String getFirstSpellPinYin(String src, boolean isFullSpell) {
        String targetStr = Pinyin4jUtils.makeStringByStringSet(Pinyin4jUtils.getPinyin(src, isFullSpell));
        String[] split = targetStr.split(",");
        if (split.length > 1) {
            targetStr = split[0];
        }
        return targetStr;
    }

    /**
     * makeStringByStringSet:(拼音字符串集合转换字符串(逗号分隔)).
     *
     * @param pingYinSet 拼音集合
     * @return 带逗号字符串
     */
    public static String makeStringByStringSet(Set<String> pingYinSet) {
        StringBuilder str = new StringBuilder();
        int i = 0;
        if (pingYinSet.size() > 0) {
            for (String s : pingYinSet) {
                if (i == pingYinSet.size() - 1) {
                    str.append(s);
                } else {
                    str.append(s + ",");
                }
                i++;
            }
        }
        return str.toString().toLowerCase();
    }

    /**
     * getPinyin:(获取汉字拼音).
     *
     * @param src         汉字
     * @param isFullSpell 是否全拼,如果为true：全拼，false:首字全拼
     * @return
     */
    public static Set<String> getPinyin(String src, boolean isFullSpell) {
        if (src != null && !"".equalsIgnoreCase(src.trim())) {
            char[] srcChar;
            srcChar = src.toCharArray();
            // 汉语拼音格式输出类
            HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();

            // 输出设置，大小写，音标方式等
            hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

            String[][] temp = new String[src.length()][];
            for (int i = 0; i < srcChar.length; i++) {
                char c = srcChar[i];
                // 中文
                if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {
                    try {
                        temp[i] = PinyinHelper.toHanyuPinyinStringArray(
                                srcChar[i], hanYuPinOutputFormat);
                        if (!isFullSpell) {
                            if (i == 0) {
                                temp[i] = temp[i];
                            } else {
                                String[] tTemps = new String[temp[i].length];
                                for (int j = 0; j < temp[i].length; j++) {
                                    char t = temp[i][j].charAt(0);
                                    tTemps[j] = Character.toString(t);
                                }
                                temp[i] = tTemps;
                            }
                        }
                    } catch (BadHanyuPinyinOutputFormatCombination e) {
                        e.printStackTrace();
                    }
                // 英文
                } else if (((int) c >= 65 && (int) c <= 90) || ((int) c >= 97 && (int) c <= 122)) {
                    temp[i] = new String[]{String.valueOf(srcChar[i])};
                } else {
                    temp[i] = new String[]{""};
                }
            }
            String[] pingyinArray = exchange(temp);
            Set<String> pinyinSet = new HashSet<String>();
            for (int i = 0; i < pingyinArray.length; i++) {
                pinyinSet.add(pingyinArray[i]);
            }
            return pinyinSet;
        }
        return null;
    }

    /**
     * 递归
     *
     * @param strJaggedArray
     * @return
     */
    public static String[] exchange(String[][] strJaggedArray) {
        String[][] temp = doExchange(strJaggedArray);
        return temp[0];
    }

    /**
     * 递归
     *
     * @param strJaggedArray
     * @return
     */
    private static String[][] doExchange(String[][] strJaggedArray) {
        int len = strJaggedArray.length;
        if (len >= 2) {
            int len1 = strJaggedArray[0].length;
            int len2 = strJaggedArray[1].length;
            int newlen = len1 * len2;
            String[] temp = new String[newlen];
            int index = 0;
            for (int i = 0; i < len1; i++) {
                for (int j = 0; j < len2; j++) {
                    temp[index] = strJaggedArray[0][i] + strJaggedArray[1][j];
                    index++;
                }
            }
            String[][] newArray = new String[len - 1][];
            for (int i = 2; i < len; i++) {
                newArray[i - 1] = strJaggedArray[i];
            }
            newArray[0] = temp;
            return doExchange(newArray);
        } else {
            return strJaggedArray;
        }
    }
}
