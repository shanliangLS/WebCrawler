# -*- coding:utf-8 -*-

import sys
from jieba import analyse
import io
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')

tfidf = analyse.extract_tags

text = ''

for i in range(1, len(sys.argv)):
    text += sys.argv[i]
print(text)

if __name__ == '__main__':
    # 关键词提取所使用停用词文本语料库可以切换成自定义语料库的路径。
    # analyse.set_stop_words("stopwords.txt")

    # 引入TextRank关键词抽取接口
    textrank = analyse.textrank
    # 基于TextRank算法进行关键词抽取
    keywords_textrank = textrank(text, topK=5, withWeight=False, allowPOS=('n', 'ns', 'vn', 'v', 'nz'))
    # 输出抽取出的关键词
    print('start**', end='')

    for keyword in keywords_textrank:
        print(keyword + ",", end='')
    print('**end', end='')
    # print('tf idf')

    # TFIDF
# keywords_tfidf = analyse.extract_tags(text,topK = 5, withWeight = False, allowPOS = ('n','ns','vn','v','nz'))

# 输出抽取出的关键词
# for keyword in keywords_tfidf:
# print (keyword + "/")
