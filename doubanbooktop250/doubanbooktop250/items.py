# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class Doubanbooktop250Item(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    book = scrapy.Field()
    author = scrapy.Field()
    star = scrapy.Field()
    quote = scrapy.Field()
    num = scrapy.Field()
    img = scrapy.Field()

    book_about_url = scrapy.Field()
    commit_url = scrapy.Field()
    date = scrapy.Field()
