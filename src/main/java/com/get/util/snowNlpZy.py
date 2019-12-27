# -*- coding:utf-8 -*-


from snownlp import SnowNLP
import sys
import io

sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')

try:
    text = ''

    for i in range(1, len(sys.argv)):
        text += sys.argv[i]

    s = SnowNLP(text)

    # TextRank算法
    print('start**', end='')
    print('。'.join(s.summary(5)), end='')
    print('**end', end='')
except Exception as e:
    print(e)
