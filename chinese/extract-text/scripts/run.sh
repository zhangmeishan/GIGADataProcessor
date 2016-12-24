#!/bin/bash
for i in `find ../../original/data/ -name *.gz`;
do
  echo $i
  # echo ../data/`basename $i .gz`.txt
  { echo "<ROOT>"; zcat $i; echo "</ROOT>"; } | cat | ./extract-text.py | sed 's/&lt;\ *//g' | sed 's/&gt;\ *//g' | sed 's/&amp;\ *//g' | python norm-char.py | java -cp . SentenceSpliter > ../data/`basename $i .gz`.txt
done


for i in `find ../data/ -name cna_cmn_*.txt`;
do 
    echo $i
    opencc -i $i -o ../data/`basename $i .txt`.chs.txt -c tw2sp.json
done