# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class TianqiItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    city_name = scrapy.Field()
    city_addition = scrapy.Field()
    city_addition2 = scrapy.Field()
    date = scrapy.Field()
    temperatureMax = scrapy.Field()
    temperatureMin = scrapy.Field()
    weather = scrapy.Field()
    wind = scrapy.Field()
    pass
