package com.xu.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Cn2SpellTest {
    @Test
    public void testConverterToSpell() {
        assertEquals("hanyupinyin", Cn2Spell.converterToSpell("汉语pinyin"));
    }

    @Test
    public void testConverterToFirstSpell() {
        assertEquals("hypinyin", Cn2Spell.converterToFirstSpell("汉语pinyin"));
    }
}