#-*- coding=utf-8 -*-
import sys
import sgmllib
import re
import gzip

cur_ps = []

class DeSGMLifier(sgmllib.SGMLParser):
    global cur_ps

    def __init__(self):
        # initialize base class
        sgmllib.SGMLParser.__init__(self)
        self.inp = False
        self.flag=False

    def unknown_starttag(self, tag, attrs):
        # the attrs argument is a list of (attr, value) tuples.
        if tag == 'p' or tag=="headline" or tag=='dateline':
            self.inp = True
        else:
            self.inp = False
        if tag=='dateline':
            self.flag=True
        else:
            self.flag=False

    def handle_data(self, text):
        # called for each text section
        if self.flag:
            '''
            texts = text.split('  ', 2)
            if len(texts) < 3:
                pass
            else:
                text=texts[2]
            '''
            timeline = ''
            for word in text.strip().split():
                if re.match(ur"[\u4e00-\u9fa5]+", word):
                   timeline += word
            text = timeline
        
        if self.inp:
            cur_ps.append(text.strip().replace('\n', ''))
    def handle_entityref(self, text):
        # called for each entity
        pass
    def unknown_endtag(self, tag):
        # called for each end tag
        self.inp = False
        self.flag=False



def desgmlify(files):
    for f in files:
        desgmlify_file(f)

def desgmlify_file(file):
    global cur_ps
    infile = gzip.open(file, 'r')
    t = ''
    for line in infile:
        t += line
    infile.close()

    d = DeSGMLifier()
    d.feed(t.decode('utf8'))
    d.close()
    for p in cur_ps:
        p=p.encode('utf8')
        print p
    cur_ps = []

if __name__ == '__main__':
    desgmlify(sys.argv[1:])
