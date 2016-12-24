#!/usr/bin/env python
#encoding: utf-8

import sys
import os
import re
from lxml import etree

def extractText():
    xmltree = etree.parse(sys.stdin)

    # get documents from xml file
    documents = xmltree.xpath('//DOC')
    for doc in documents:
        # filename is copied from docID
        docID = doc.attrib['id']
        
        # get text in headline
        headlines = doc.findall('.//HEADLINE')
        if len(headlines) > 0:
            text = headlines[0].text.strip() 
            print text.encode('utf-8')

        # get text in dateline
        datelines = doc.findall('.//DATELINE')
        if len(datelines) > 0:
            text = datelines[0].text.strip()
            print text.encode('utf-8')

        # get texts in each paragraph
        paragraphs = doc.findall('.//P')
        for p in paragraphs:
            #print p.text,
            text = p.text.strip().replace('\n', '')
            print text.encode('utf-8')

if __name__ == '__main__':
    extractText()

