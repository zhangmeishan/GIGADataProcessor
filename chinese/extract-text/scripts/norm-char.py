#!/usr/bin/python
# encoding: utf-8

import sys
import re
import argparse

"""
 全角数字转半角
 全角英文字母转半角
 全角中文标点转半角
 转小写(可选)
"""

def normChar(istring, tolower):
    rstring = ""
    for uchar in istring:
        inside_code = ord(uchar)
        if inside_code == 0x3000:
            inside_code = 0x0020
        else:
            inside_code -= 0xfee0
        if inside_code < 0x0020 or inside_code > 0x7e:
            rstring += uchar
        else:
            rstring += chr(inside_code)
    rstring = re.sub(r'\s+', ' ', rstring)

    if tolower:
        rstring = rstring.lower()
    return rstring


if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('-l', '--toLower', action='store_true')
    option = parser.parse_args()
    tolower = option.toLower

    for line in sys.stdin:
        line = normChar(line.decode('utf8').strip(), tolower)
        print line.encode('utf8')
