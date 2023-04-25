package com.futurecute.floatstock.engine


import org.junit.Test

class StockTest {

    init {
        Stock(Exchange.SH,"600519")
        Stock(Exchange.HK,"08270")
    }

    @Test
    fun getName() {

        Stock.updateAllInfo()
        Stock.stockList.forEach { it -> it.name?.let { it -> print("Name:$it\n") } }
    }

    @Test
    fun getPrice() {
        Stock.updateAllInfo()
        Stock.stockList.forEach { it -> it.price?.let { it -> print("Price:$it\n") } }
    }

    @Test
    fun getNetChg() {
        Stock.updateAllInfo()
        Stock.stockList.forEach { it -> it.netChg?.let { it -> print("NetChg:$it\n") } }
    }

    @Test
    fun getYldRate() {
        Stock.updateAllInfo()
        Stock.stockList.forEach { it -> it.yldRate?.let { it -> print("YLDRate:$it\n") } }
    }
}