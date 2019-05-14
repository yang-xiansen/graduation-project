# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html

import csv
import json
import os
import datetime


class WeathercsvPipeline(object):
    def __init__(self):
        # csv文件的位置,无需事先创建
        store_file = os.path.dirname(__file__) + '/spiders/weather.csv'
        # 追加方式写入文件
        self.file = open(store_file, 'a',encoding='utf-8-sig',newline = '')
        # csv写法# dialect为打开csv文件的方式，默认是excel，delimiter="\t"参数指写入的时候的分隔符
        self.writer = csv.writer(self.file)
        timestr = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        f = open(store_file, "r",encoding='utf-8')
        lines = f.readlines()
        for line in lines:
            if "省份" in line:
                self.writer.writerow([timestr])
                break
            else:
                self.writer.writerow(['  省份  ', '  城市  ', '  县（乡）  ', '  日期  ', '  最高温度  ', '  最低温度  ', '  天气  ', '  风向  '])
                break


    def process_item(self, items, spider):


        self.writer.writerow([items['city_name'], items['city_addition'], items['city_addition2'], items['date'],
                              items['temperatureMax'], items['temperatureMin'], items['weather'], items['wind']])
        return items


    def open_spider(self, spider):
        pass

    def close_spider(self, spider):
            # 关闭爬虫时顺便将文件保存退出
        self.file.close()

















