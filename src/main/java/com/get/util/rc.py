# -*- coding:utf-8 -*-

import sys
import jieba
import io
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')

text = ''

for i in range(1, len(sys.argv)):
    text += sys.argv[i]
print(text)

words1=jieba.lcut(text)#全模式
# words2=jieba.lcut_for_search(file_context)#搜索引擎模式

#统计词频

data1={}
for chara in words1:
    if len(chara)<2:
        continue
    if chara in data1:
        data1[chara]+=1
    else:
        data1[chara]=1

data1=sorted(data1.items(),key = lambda x:x[1],reverse = True) #排序
print('start**', end='')
print(data1[:10])
print('**end', end='')