import scrapy
from doubanbooktop250.items import Doubanbooktop250Item

class DoubanbookSpider(scrapy.Spider):
    name = 'doubanbook_2_1'
    start_urls = [
        'https://book.douban.com/top250'
    ]
    allowed_domains = [
        'douban.com'
    ]

    global dict
    dict = {}
    global items
    items = []

    def parse(self, response):
        for book in response.css('tr.item'):
            item = Doubanbooktop250Item()
            item['book'] = book.css('div.pl2 a::text').extract_first().replace('\n', '').strip()
            #book_detail = response.css('div.pl2 a::attr(href)').extract_first()
            #book_detail_url = response.urljoin(book_detail)
            #yield scrapy.Request(book_detail_url, callback=self.parse_comment)
            item['author'] = book.css('td p.pl::text').extract_first().split('/')[0].strip()
            item['quote'] = book.css('p.quote span.inq::text').extract_first()
            item['star'] = book.css('div.star.clearfix span.rating_nums::text').extract_first()
            item['num'] = book.css('div.star.clearfix span.pl::text').extract_first().strip("(").strip(")").replace('\n', '').strip()
            #item['img'] = book.css('a.nbg img').xpath('@src').extract_first().replace('\n', '').strip()
            item['img'] = book.css('a.nbg img::attr(src)').extract_first().replace('\n', '').strip()
            book_about = book.css('td a.nbg::attr(href)').extract_first()
            item['book_about_url'] = response.urljoin(book_about)
            item['commit_url'] = book.css('div.pl2 a::attr(href)').extract_first() + "doings"
            yield scrapy.Request(item['commit_url'], callback=self.parse_commit,meta={'item':item})

        next_page = response.css('span.next a::attr(href)').extract_first()
        next_page_url = response.urljoin(next_page)
        yield scrapy.Request(next_page_url, callback=self.parse)

    def parse_commit(self,response):
        dict = {}
        item = response.meta['item']
        for summary in response.css('p.pl'):
            date=summary.css('p.pl span::text').extract_first().replace('\n', '').strip()
            if date in dict.keys():
                dict[date] += 1
            else:
                dict[date] = 1

        item['date'] = dict
        yield item


