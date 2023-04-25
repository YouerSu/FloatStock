package com.futurecute.floatstock.engine

import java.net.URL
import java.nio.charset.Charset

enum class Exchange(val exchangeName: String){
    US("us"),HK("hk"),SZ("sz"),SH("sh")
}

enum class InfoType(val index: Int){
    Exchange(0),
    Name(1),
    Code(2),
    Price(3),
    NetChg(4),
    YLDRate(5),
    //No need others

}

/*
        *
        *   序号	含义
            1	200-美股（us），100-港股（hk），51-深圳（sz），1-上海（sh）
            2	股票名字
            3	股票代码
            4	当前价格
            5	涨跌
            6	涨跌%
            7	成交量
            8	成交额
            9	-
            10	总市值
*/

class Stock(exchange: Exchange,_code: String) {
    val code = "${exchange.exchangeName}$_code"
    var name: String? = null
    var price: Float? = null
    var netChg: Float? = null
    var yldRate: Float? = null

    init {
        stockList.add(this)
    }

    companion object {

        val stockList = mutableListOf<Stock>()

        private fun splitInfo(info: String) =
            info.split("/\".*\"*/".toRegex()).first { it.isNotBlank() }.replace("\"","").split("~")

        fun updateAllInfo() {
            var stocks = ""
            stockList.forEach { stocks+="s_${ it.code}," }
            val url = "http://qt.gtimg.cn/q=$stocks"
            val resources = URL(url).readText(charset("GBK"))
            val infoLista = resources.split(";\n").filter { it.isNotBlank() }
            val infoList = infoLista.map { splitInfo(it) }
            for (index in stockList.indices) {
                val stock = stockList[index]
                val info = infoList[index]
                stock.name = info[InfoType.Name.index]
                stock.price = info[InfoType.Price.index].toFloat()
                stock.netChg = info[InfoType.NetChg.index].toFloat()
                stock.yldRate = info[InfoType.YLDRate.index].toFloat()
            }
        }
    }




}