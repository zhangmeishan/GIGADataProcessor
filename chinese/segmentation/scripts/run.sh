#!/bin/bash
cd stanford-segmenter-2015-12-09/
javac -cp stanford-segmenter-3.6.0.jar SegGigaword.java

start=$1
end=$2

for i in `seq  -f %03g $start $end`  
do  
    # echo $i 
    mkdir ../../split/$i
    java -mx2g -cp "*:." SegGigaword ../../../extract-text/split/$i ../../split/$i
done