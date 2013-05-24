package com.xu.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cn2Spell {
    static Logger logger = LoggerFactory.getLogger(Cn2Spell.class);

    /**
     * 汉字转换位汉语拼音首字母，英文字符不变
     *
     * @param chines 汉字
     * @return 拼音
     */
    public static String converterToFirstSpell(String chines) {
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat df = new HanyuPinyinOutputFormat();
        df.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        df.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char aChar : nameChar) {
            if (aChar > 128) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(aChar, df)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    logger.error("Pinyin format err", e);
                }
            } else {
                pinyinName += aChar;
            }
        }
        return pinyinName;
    }

    /**
     * 汉字转换位汉语拼音，英文字符不变
     *
     * @param chines 汉字
     * @return 拼音
     */
    public static String converterToSpell(String chines) {
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char aNameChar : nameChar) {
            if (aNameChar > 128) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(aNameChar, defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName += aNameChar;
            }
        }
        return pinyinName;
    }
}
