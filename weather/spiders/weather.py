# -*- coding: utf-8 -*-
import scrapy

from tianqi.items import TianqiItem


class WeatherSpider(scrapy.Spider):
    name = 'Weather'
    allowed_domains = ['www.weather.com.cn']#允许的域名
    start_urls = ['http://www.weather.com.cn/weather/101220101.shtml',
                   'http://www.weather.com.cn/weather/101280101.shtml',
                  'http://www.weather.com.cn/weather/101270101.shtml'
                  ]
    def parse(self, response):
        '''
                     筛选信息的函数：
                     city = 城市
                     date = 日期
                     temperature = 当天的温度
                     weather = 当天的天气
                     wind = 当天的风向
                     '''
        # 先建立一个列表，用来保存每天的信息
        items = []


        for sel in response.xpath('//*[@id="7d"]/ul/li'):
            item = TianqiItem()
            item['city_name'] = response.xpath("//div[@class='crumbs fl']/a/text()").extract_first()
            item['city_addition'] = response.xpath("//div[@class='crumbs fl']/a[2]/text()").extract_first()
            item['city_addition2'] = response.xpath("//div[@class='crumbs fl']/span[3]/text()").extract_first()
            item['date'] = sel.xpath('h1/text()').extract()
            item['weather'] = sel.xpath('p[@class="wea"]/text()').extract()
            item['temperatureMax'] = sel.xpath("p[@class='tem']/span/text()").extract()
            item['temperatureMin'] = sel.xpath("p[@class='tem']/i/text()").extract()
            item['wind'] = sel.xpath('p[@class="win"]/i/text()').extract()
            items.append(item)

        return items
    pass
