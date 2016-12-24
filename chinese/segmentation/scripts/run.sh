#!/bin/bash
for i in `find ../../extract-text/data/ -name *.txt`;
do
  echo $i
  # echo ../data/`basename $i .txt`.seg
  ./stanford-segmenter-2015-12-09/segment.sh ctb  $i UTF-8 0 > ../data/`basename $i .txt`.seg
done



