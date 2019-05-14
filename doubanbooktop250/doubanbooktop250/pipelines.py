# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html

import pymongo
from scrapy.conf import settings
import csv
import json
import os
import datetime

class MongoDBPipeline(object):
    def __init__(self):
        host = settings['MONGODB_HOST']
        port = settings['MONGODB_PORT']
        client = pymongo.MongoClient(host=host, port=port)
        dbName = settings['MONGODB_DB']
        tdb = client[dbName]
        self.post = tdb[settings['MONGODB_COLLECTION']]

        # csv文件的位置,无需事先创建
        store_file = os.path.dirname(__file__) + '/spiders/book.csv'
        
		# 追加方式写入文件
        self.file = open(store_file, 'a', encoding='utf-8-sig', newline='')
        
		# csv写法# dialect为打开csv文件的方式，默认是excel，delimiter="\t"参数指写入的时候的分隔符
        self.writer = csv.writer(self.file)
        #获得当前时间
		timestr = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        #读取文件
		f = open(store_file, "r", encoding='utf-8')
        #读取全部行数
		lines = f.readlines()
        for line in lines:
            #判断作者是否在表头
			if "作者" in line:
                #在的话就插入这次时间
				self.writer.writerow([timestr])
                break
            else:
			#不在就插入表头
			#先插入固定的表头
                it = ['  书名  ', '  作者  ','   星级   ','  quote  ','  评价人数  ']
                for i in range(10):
				#获得从今天起往前的十天时间并插入到表头
                    it.append(self.get_date(i))
                print(it)
                self.writer.writerow(it)
                break


    def process_item(self, item, spider):
        movie_info = dict(item)
        self.post.insert(movie_info)
        it = [item['book'], item['author'],item['star'],item['quote'],item['num']]
		#获得评论时间，并写到csv文件
        for i in item['date'].keys():
            it.append(item['date'][i])
        self.writer.writerow(it)
        return item


    def open_spider(self, spider):
        pass

    def close_spider(self, spider):
            # 关闭爬虫时顺便将文件保存退出
        self.file.close()

	#今天往前的时间函数
    def get_date(self,n):
        # 格式化为 年月日 形式 2019-02-25
        return (datetime.date.today() - datetime.timedelta(days=n))


