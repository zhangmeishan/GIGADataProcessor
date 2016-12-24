#!/bin/bash
for i in `find ../../original/data/ -name *.gz`;
do
  # echo $i
  # echo ../data/`basename $i .gz`.txt
  { echo "<ROOT>"; zcat $i; echo "</ROOT>"; } | cat | ./extract-text.py | sed 's/&lt;\ *//g' | sed 's/&gt;\ *//g' | sed 's/&amp;\ *//g' > ../data/`basename $i .gz`.txt
done